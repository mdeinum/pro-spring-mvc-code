package com.apress.prospringmvc.pizzarus.web.config;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

import com.apress.prospringmvc.pizzarus.web.interceptor.OpeningHoursInterceptor;


/**
 * WebMvc Configuration.
 * 
 * @author M. Deinum
 */
@Configuration
public class WebMvcContext extends WebMvcConfigurationSupport {

	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**", "*.js").addResourceLocations("/WEB-INF/js");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images");		
	}
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
		registry.addInterceptor(new OpeningHoursInterceptor());
		registry.addInterceptor(new ThemeChangeInterceptor());
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	public HandlerExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DataAccessException", "db-error");
		mappings.setProperty("Exception", "error");
		resolver.setExceptionMappings(mappings);
		return resolver;
	}
	
}
