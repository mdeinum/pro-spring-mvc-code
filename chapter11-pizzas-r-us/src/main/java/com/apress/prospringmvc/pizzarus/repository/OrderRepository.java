package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.Order;

public interface OrderRepository {

	/**
	 * Saves an order for the given customer and returns the order key
	 */
	Long saveOrder(Customer customer, Order order);

	/**
	 * Returns the orders for a given customer key
	 */
	List<Order> getOrders(Long customerId);

}
