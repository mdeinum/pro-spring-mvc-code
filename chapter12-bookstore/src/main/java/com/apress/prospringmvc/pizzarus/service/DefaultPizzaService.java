package com.apress.prospringmvc.pizzarus.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.domain.Shop;
import com.apress.prospringmvc.pizzarus.repository.CustomerRepository;
import com.apress.prospringmvc.pizzarus.repository.OrderRepository;
import com.apress.prospringmvc.pizzarus.repository.PizzaRepository;
import com.apress.prospringmvc.pizzarus.repository.ShopRepository;

/**
 * Default implemenatation of the {@link PizzasService}
 * 
 * @see PizzasService
 * @author Koen Serneels
 */

@Service("pizzaService")
public class DefaultPizzaService implements PizzasService {

	@Autowired
	private PizzaRepository pizzaRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * @see PizzasService#getPizzas()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Pizza> getPizzas() {
		return pizzaRepository.findAll();
	}

	/**
	 * @see PizzasService#authenticateCustomer(String, String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Customer authenticateCustomer(String username, String password) throws InvalidCredentialsException {
		try {
			Customer customer = customerRepository.getCustomer(username);

			if (customer.getPassword().equals(DigestUtils.sha512Hex(password))) {
				return customer;
			} else {
				throw new InvalidCredentialsException();
			}
		} catch (NoResultException noResultException) {
			throw new InvalidCredentialsException();
		}
	}

	/**
	 * @see PizzasService#addOrder(Customer, Order)
	 */
	@Override
	@Transactional
	public Long addOrder(Customer customer, Order order) {
		return orderRepository.saveOrder(customer, order);
	}

	/**
	 * @see PizzasService#getShops()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Shop> getShops() {
		return shopRepository.findAll();
	}

	/**
	 * @see PizzasService#getOrdersForCustomer(Customer)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Order> getOrdersForCustomer(Customer customer) {
		return orderRepository.getOrders(customer.getId());
	}
}
