package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.pizzarus.domain.Shop;

/**
 * JPA implementation for the {@link ShopRepository}
 * 
 * @author Koen Serneels
 */

@Repository("shopRepository")
public class JpaShopRepository implements ShopRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * @see ShopRepository#findAll()
	 */
	@Override
	public List<Shop> findAll() {
		return entityManager.createQuery("select s from Shop s", Shop.class).getResultList();
	}

}
