package com.apress.prospringmvc.pizzarus.support;

import com.apress.prospringmvc.pizzarus.domain.Shop;

public class ShopBuilder extends EntityBuilder<Shop> {

	private Shop product;

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
