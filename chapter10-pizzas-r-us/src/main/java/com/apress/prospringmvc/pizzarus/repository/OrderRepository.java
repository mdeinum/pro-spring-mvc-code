package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.Order;

public interface OrderRepository {

	Long saveOrder(Customer customer, Order order);

	List<Order> getOrders(Long customerId);

}
