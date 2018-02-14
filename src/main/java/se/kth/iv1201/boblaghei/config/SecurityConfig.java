package se.kth.iv1201.boblaghei.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
