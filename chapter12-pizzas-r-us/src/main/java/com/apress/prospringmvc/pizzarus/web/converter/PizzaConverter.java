package com.apress.prospringmvc.pizzarus.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.service.PizzasService;

/**
 * Tries to convert a Long (which resembles the primary key of a {@link Pizza} to a Pizza entity using the
 * {@link PizzasService}. When conversion cannot take place an {@link IllegalArgumentException} is thrown
 * 
 * @author Koen Serneels
 */

public class PizzaConverter implements Converter<String, Pizza> {

	@Autowired
	private PizzasService pizzaService;

	@Override
	public Pizza convert(String source) {
		if (source == null) {
			return null;
		}

		for (Pizza pizza : pizzaService.getPizzas()) {
			if (pizza.getId().toString().equals(source)) {
				return pizza;
			}
		}

		throw new IllegalArgumentException(source + " cannot be converted to a Pizza object");
	}
}
