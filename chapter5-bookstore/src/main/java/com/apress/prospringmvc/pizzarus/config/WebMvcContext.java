package com.apress.prospringmvc.pizzarus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * WebMvc Configuration.
 * 
 * @author M. Deinum
 */
//@Configuration
public class WebMvcContext extends WebMvcConfigurationSupport {

	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**", "*.js").addResourceLocations("/WEB-INF/js");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images");		
	}
	
}
