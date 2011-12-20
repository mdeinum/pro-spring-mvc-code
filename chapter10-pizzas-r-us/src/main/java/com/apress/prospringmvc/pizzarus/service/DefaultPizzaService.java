package com.apress.prospringmvc.pizzarus.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.repository.CustomerRepository;
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

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Pizza> getPizzas() {
		return pizzaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Customer authenticateCustomer(String username, String password) throws InvalidCredentialsException {
		Customer customer = customerRepository.getCustomer(username);

		if (customer.getPassword().equals(DigestUtils.sha512(password))) {
			return customer;
		} else {
			throw new InvalidCredentialsException();
		}
	}

	@Override
	public void addOrder(Customer customerDetached, Order order) {
		Customer customer = customerRepository.getCustomer(customerDetached.getUsername());
		customer.getOrders().add(order);
	}
}
