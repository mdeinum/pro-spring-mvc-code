package com.apress.prospringmvc.pizzarus.config;

import org.springframework.context.annotation.*;

import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.*;
import org.springframework.web.servlet.theme.*;


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index.htm").setViewName("index");
	}
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
		registry.addInterceptor(new ThemeChangeInterceptor());
	}
}
