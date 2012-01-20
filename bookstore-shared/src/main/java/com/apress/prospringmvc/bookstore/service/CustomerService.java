package com.apress.prospringmvc.bookstore.service;

import com.apress.prospringmvc.bookstore.domain.Customer;

public interface CustomerService {

	Customer save(Customer customer);

	/**
	 * Handles the login logic. If the {@link Customer} can be retrieved and the password is correct we get the
	 * {@link Customer}. In all other cases we get a {@link AuthenticationException}.
	 * @param username the username
	 * @param password the password
	 * @return the customer
	 * @throws AuthenticationException if customer not found or incorrect password
	 */
	Customer login(String username, String password) throws AuthenticationException;

	Customer getCustomer(String username);
}
