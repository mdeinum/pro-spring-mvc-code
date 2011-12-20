package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.pizzarus.domain.Pizza;

/**
 * JPA implementation for the {@link PizzaRepository}.
 * 
 * @author M. Deinum
 * 
 */
@Repository("pizzaRepository")
public class JpaPizzaRepository implements PizzaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<Pizza> findAll() {
		return (List<Pizza>) entityManager.createQuery("select p from Pizza p").getResultList();
	}
}
