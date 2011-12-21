package com.apress.prospringmvc.pizzarus.support;

import com.apress.prospringmvc.pizzarus.domain.Shop;

public class ShopBuilder extends EntityBuilder<Shop> {

	private Shop product = new Shop();

	public ShopBuilder name(String name) {
		product.setShopName(name);
		return this;
	}

	public ShopBuilder city(String city) {
		product.setCity(city);
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
