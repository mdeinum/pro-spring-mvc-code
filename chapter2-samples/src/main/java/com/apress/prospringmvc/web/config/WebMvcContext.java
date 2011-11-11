package com.apress.prospringmvc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcContext {

	@Bean
	public Object indexController() {
		return new IndexController();
	}
	
}
