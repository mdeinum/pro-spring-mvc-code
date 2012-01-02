package com.apress.prospringmvc.pizzarus.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesView;

import com.apress.prospringmvc.pizzarus.web.converter.IntervalConverter;
import com.apress.prospringmvc.pizzarus.web.converter.PizzaConverter;
import com.apress.prospringmvc.pizzarus.web.converter.ShopConverter;

/**
 * WebMvc Configuration.
 * 
 * @author Koen Serneels
 */

@Configuration
@EnableWebMvc
@Profile("container")
public class WebMvcContextConfiguration extends WebMvcConfigurationSupport {

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**/*").addResourceLocations("classpath:/META-INF/web-resources/");
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Bean
	public ViewResolver tilesViewResolver() {
		UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setOrder(1);
		urlBasedViewResolver.setViewClass(TilesView.class);
		return urlBasedViewResolver;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setOrder(2);
		internalResourceViewResolver.setPrefix("/WEB-INF/view/");
		internalResourceViewResolver.setSuffix(".jspx");
		internalResourceViewResolver.setViewClass(JstlView.class);
		return internalResourceViewResolver;
	}

	@Bean
	public PizzaConverter pizzaConverter() {
		return new PizzaConverter();
	}

	@Bean
	public ShopConverter shopConverter() {
		return new ShopConverter();
	}

	@Bean
	public IntervalConverter periodConverter() {
		return new IntervalConverter();
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles/tiles-configuration.xml" });
		return tilesConfigurer;
	}

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addConverter(pizzaConverter());
		registry.addConverter(shopConverter());
		registry.addConverter(periodConverter());
	}
}
