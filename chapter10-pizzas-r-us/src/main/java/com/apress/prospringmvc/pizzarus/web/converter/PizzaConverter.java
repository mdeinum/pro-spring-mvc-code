package com.apress.prospringmvc.pizzarus.web.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.domain.Shop;
import com.apress.prospringmvc.pizzarus.service.PizzasService;

public class PizzaConverter implements GenericConverter {

	@Autowired
	private PizzasService pizzaService;

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> set = new HashSet<ConvertiblePair>();
		set.add(new ConvertiblePair(String.class, Pizza.class));
		set.add(new ConvertiblePair(Pizza.class, String.class));
		set.add(new ConvertiblePair(String.class, Shop.class));
		set.add(new ConvertiblePair(Shop.class, String.class));
		return set;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (source == null) {
			return null;
		}

		if (sourceType.getObjectType().isAssignableFrom(Shop.class)) {
			return ((Shop) source).getShopName();
		}
		if (sourceType.getObjectType().isAssignableFrom(Pizza.class)) {
			return ((Pizza) source).getName();
		}

		if (sourceType.getObjectType().isAssignableFrom(String.class)
				&& targetType.getObjectType().isAssignableFrom(Shop.class)) {

			for (Shop shop : pizzaService.getShops()) {
				if (shop.getId().toString().equals(source)) {
					return shop;
				}
			}
		}

		if (sourceType.getObjectType().isAssignableFrom(String.class)
				&& targetType.getObjectType().isAssignableFrom(Pizza.class)) {

			for (Pizza pizza : pizzaService.getPizzas()) {
				if (pizza.getId().toString().equals(source)) {
					return pizza;
				}
			}
		}
		throw new IllegalArgumentException(source + " cannot be converted to a Pizza object");
	}
}
