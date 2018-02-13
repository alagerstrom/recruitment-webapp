package se.kth.iv1201.boblaghei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Substitutes view-controllers for classes with no need for any special mappings.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * Adds basic View-Controllers without the need to create a class for each of them, convenience code.
     * @param registry the registry to which the View-Controller is to be added.
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/forbidden").setViewName("forbidden");
    }
}
