package se.kth.iv1201.boblaghei.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import se.kth.iv1201.boblaghei.rest.security.JWTAuthenticationFilter;
import se.kth.iv1201.boblaghei.rest.security.JWTLoginFilter;
import se.kth.iv1201.boblaghei.service.SecurityService;

/**
 * Configuration of authorization by authentication via Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityService securityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .csrf().ignoringAntMatchers("/api/**").and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/users/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/users/register").permitAll()
                .and().addFilterBefore(new JWTLoginFilter("/api/users/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/recruiter/**")
                .access("hasRole('ROLE_RECRUITER')")
                .antMatchers("/register").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login?error")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .permitAll()
                .and().exceptionHandling().accessDeniedPage("/forbidden");
    }

}
