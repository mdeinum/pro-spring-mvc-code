package com.apress.prospringmvc.bookstore.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.prospringmvc.bookstore.web.IndexController;

/**
 * Configuration for the MVC part of the application. Should only contain web related beans and configuration.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
@Configuration
public class WebMvcContextConfiguration {

    @Bean
    public IndexController indexController() {
        return new IndexController();
    }

}
