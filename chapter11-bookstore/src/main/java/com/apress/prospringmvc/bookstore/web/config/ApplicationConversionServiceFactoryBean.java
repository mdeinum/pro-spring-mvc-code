package com.apress.prospringmvc.bookstore.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import com.apress.prospringmvc.bookstore.web.converter.CategoryConverter;
import com.apress.prospringmvc.bookstore.web.converter.BookConverter;

/**
 * Special purpose factory bean that creates an {@link FormattingConversionService}. This service acts as an adapter
 * between the Spring MVC and Web Flow converter machinery. Here we autowire the Spring MVC {@link Converter}s and then
 * register them with the Web Flow {@link FormatterRegistry}
 * 
 * @author Koen Serneels
 */

public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Autowired
	private BookConverter bookConverter;
	@Autowired
	private CategoryConverter category;

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		registry.addConverter(bookConverter);
		registry.addConverter(category);
	}
}
