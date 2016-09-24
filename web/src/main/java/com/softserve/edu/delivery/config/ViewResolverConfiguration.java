package com.softserve.edu.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * This class configures Spring ViewResolver
 * and register its in spring DI container
 */
@Configuration
public class ViewResolverConfiguration {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix("jsp");
        return viewResolver;
    }
}
