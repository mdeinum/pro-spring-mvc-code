package com.apress.prospringmvc.pizzarus.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.apress.prospringmvc.pizzarus.domain.Shop;
import com.apress.prospringmvc.pizzarus.service.PizzasService;

/**
 * Tries to convert a Long (which resembles the primary key of a {@link Shop} to a Shop entity using the
 * {@link PizzasService}. When conversion cannot take place an {@link IllegalArgumentException} is thrown
 * 
 * @author Koen Serneels
 */

public class ShopConverter implements Converter<String, Shop> {

	@Autowired
	private PizzasService pizzaService;

	@Override
	public Shop convert(String source) {
		if (source == null) {
			return null;
		}

		for (Shop shop : pizzaService.getShops()) {
			if (shop.getId().toString().equals(source)) {
				return shop;
			}
		}
		throw new IllegalArgumentException(source + " cannot be converted to a Shop object");
	}
}
