package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import com.apress.prospringmvc.pizzarus.domain.Pizza;

public interface PizzaRepository {

	/**
	 * Finds all pizza's
	 */
	List<Pizza> findAll();

}
