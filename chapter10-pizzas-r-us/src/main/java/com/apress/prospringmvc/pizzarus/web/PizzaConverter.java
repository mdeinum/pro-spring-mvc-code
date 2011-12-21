package com.apress.prospringmvc.pizzarus.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.converter.Converter;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.service.PizzasService;

public class PizzaConverter implements Converter<String, Pizza>, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public Pizza convert(String source) {
		PizzasService pizzaService = (PizzasService) applicationContext.getBean("pizzaService");

		for (Pizza pizza : pizzaService.getPizzas()) {
			if (pizza.getName().equals(source)) {
				return pizza;
			}
		}
		throw new IllegalArgumentException(source + " cannot be converted to a Pizza object");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
