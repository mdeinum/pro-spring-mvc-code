package com.apress.prospringmvc.pizzarus.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import com.apress.prospringmvc.pizzarus.web.PizzaConverter;

public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Autowired
	private PizzaConverter pizzaConverter;

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		registry.addConverter(pizzaConverter);
	}
}
