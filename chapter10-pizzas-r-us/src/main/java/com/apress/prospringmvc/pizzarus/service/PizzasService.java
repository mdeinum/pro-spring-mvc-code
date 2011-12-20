package com.apress.prospringmvc.pizzarus.service;

import java.util.List;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.Pizza;

public interface PizzasService {

	List<Pizza> getPizzas();

	Customer authenticateCustomer(String username, String password) throws InvalidCredentialsException;

	void addOrder(Customer customerDetached,Order order);

}
