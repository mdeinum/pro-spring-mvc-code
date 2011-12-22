package com.apress.prospringmvc.pizzarus.service;

import java.util.List;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.domain.Shop;

public interface PizzasService {

	List<Pizza> getPizzas();

	List<Shop> getShops();

	Customer authenticateCustomer(String username, String password) throws InvalidCredentialsException;

	Long addOrder(Customer customer, Order order);

	List<Order> getOrdersForCustomer(Customer customer);

}
