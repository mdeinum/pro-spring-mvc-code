package com.apress.prospringmvc.pizzarus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.repository.PizzaRepository;

/**
 * Default implementation for the {@link PizzasService}.
 * 
 * @author M. Deinum
 *
 */
@Service("pizzaService")
public class DefaultPizzaService implements PizzasService {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Pizza> getPizzas() {
		return pizzaRepository.findAll();
	}

}
