package se.kth.iv1201.boblaghei.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Substitutes view-controllers for classes with no need for any special mappings,
 * and add locale change interceptor to registry.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * Adds basic View-Controllers without the need to create a class for each of them, convenience code.
     * @param registry the registry to which the View-Controller is to be added.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/forbidden").setViewName("forbidden");
        registry.addViewController("/sign_out").setViewName("logout");
    }

    /**
     * Bean used by Spring to resolve locale
     *
     * @return A LocaleResolver bean
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return sessionLocaleResolver;
    }

    /**
     * Interceptor used by Spring to allow changing locale.
     * A request that has parameter lang will be interpreted as a locale change.
     *
     * @return A LocaleChangeInterceptor bean, that uses parameter 'lang'
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /**
     * Add an localeChangeInterceptor bean to the registry
     *
     * @param registry The registry to use
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
