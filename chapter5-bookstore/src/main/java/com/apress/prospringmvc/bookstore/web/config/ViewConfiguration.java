package com.apress.prospringmvc.bookstore.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesViewResolver;

/**
 * Spring MVC configuration for the View Technologies.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
@Configuration
public class ViewConfiguration {

    @Bean
    public TilesConfigurer tilesConfigurer() {
        return new TilesConfigurer();
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setOrder(2);
        return tilesViewResolver;
    }

}
