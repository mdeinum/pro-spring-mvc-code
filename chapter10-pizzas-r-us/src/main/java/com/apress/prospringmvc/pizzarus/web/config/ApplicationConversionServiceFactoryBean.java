package com.apress.prospringmvc.pizzarus.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import com.apress.prospringmvc.pizzarus.web.converter.PizzaConverter;
import com.apress.prospringmvc.pizzarus.web.converter.ShopConverter;

public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Autowired
	private PizzaConverter pizzaConverter;
	@Autowired
	private ShopConverter shopConverter;

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		registry.addConverter(pizzaConverter);
		registry.addConverter(shopConverter);
	}
}
