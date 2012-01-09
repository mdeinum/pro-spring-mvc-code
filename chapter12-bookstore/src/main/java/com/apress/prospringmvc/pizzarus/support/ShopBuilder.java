package com.apress.prospringmvc.pizzarus.support;

import org.springframework.stereotype.Component;

import com.apress.prospringmvc.pizzarus.domain.Shop;

/**
 * Builds {@link Shop} domain objects
 * 
 * @author Koen Serneels
 */

@Component
public class ShopBuilder extends EntityBuilder<Shop> {

	@Override
	void initProduct() {
		// Do nothing
	}

	public ShopBuilder shop(String name, String city) {
		product = new Shop(name, city);
		return this;
	}

	public Shop build() {
		return product;
	}

	@Override
	Shop assembleProduct() {
		return product;
	}
}
