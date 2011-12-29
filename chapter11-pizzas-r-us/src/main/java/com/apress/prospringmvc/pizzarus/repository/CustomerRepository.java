package com.apress.prospringmvc.pizzarus.repository;

import com.apress.prospringmvc.pizzarus.domain.Customer;

public interface CustomerRepository {

	/**
	 * Return the customer based on the username. When no such customer exists an exception is raised
	 */
	Customer getCustomer(String username);
}
