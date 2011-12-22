package com.apress.prospringmvc.pizzarus.web;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.service.PizzasService;

public class PizzaConverter implements GenericConverter, ApplicationContextAware {

	@Autowired
	private PizzasService pizzaService;
	private ApplicationContext applicationContext;

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> set = new HashSet<ConvertiblePair>();
		set.add(new ConvertiblePair(String.class, Pizza.class));
		set.add(new ConvertiblePair(Pizza.class, String.class));
		return set;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (source == null) {
			return null;
		}

		if (sourceType.getObjectType().isAssignableFrom(Pizza.class)) {
			return ((Pizza) source).getName();
		}

		if (sourceType.getObjectType().isAssignableFrom(String.class)
				&& targetType.getObjectType().isAssignableFrom(Pizza.class)) {
			// PizzasService pizzaService = (PizzasService) applicationContext.getBean("pizzaService");

			for (Pizza pizza : pizzaService.getPizzas()) {
				if (pizza.getName().equals(source)) {
					return pizza;
				}
			}
		}
		throw new IllegalArgumentException(source + " cannot be converted to a Pizza object");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
