package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.pizzarus.domain.Pizza;

/**
 * JPA implementation for the {@link PizzaRepository}.
 * 
 * @author Koen Serneels
 */

@Repository("pizzaRepository")
public class JpaPizzaRepository implements PizzaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Finds all pizza's stored in the database. Note that this query is cached. Calling this multiple times will cause
	 * no database overhead.
	 */
	@Override
	public List<Pizza> findAll() {
		return entityManager.createQuery("select p from Pizza p", Pizza.class).setHint("org.hibernate.cacheable", true)
				.getResultList();
	}
}
