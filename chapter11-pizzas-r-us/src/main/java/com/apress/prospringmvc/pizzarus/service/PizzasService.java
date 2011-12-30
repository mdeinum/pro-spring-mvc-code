package com.apress.prospringmvc.pizzarus.service;

import java.util.List;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.domain.Shop;

/**
 * Facade for accessing the Pizza-R-Us backend
 * 
 * @author Koen Serneels
 */

public interface PizzasService {

	/**
	 * Gets all {@link Pizza}s
	 */
	List<Pizza> getPizzas();

	/**
	 * Gets all {@link Shop}s
	 */
	List<Shop> getShops();

	/**
	 * Tries to authenticate a {@link Customer} using its username and password when authentication failes a
	 * {@link InvalidCredentialsException} is thrown
	 */
	Customer authenticateCustomer(String username, String password) throws InvalidCredentialsException;

	/**
	 * Adds an {@link Order} to the given {@Customer} by storing it in database
	 */
	Long addOrder(Customer customer, Order order);

	/**
	 * Gets all the {@link Order}s for the given {@link Customer}
	 */
	List<Order> getOrdersForCustomer(Customer customer);

}
