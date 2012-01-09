package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import com.apress.prospringmvc.pizzarus.domain.Shop;

public interface ShopRepository {

	/**
	 * Finds all shops
	 */
	List<Shop> findAll();

}
