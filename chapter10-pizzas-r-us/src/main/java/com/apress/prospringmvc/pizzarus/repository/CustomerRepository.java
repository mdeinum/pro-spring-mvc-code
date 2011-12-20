package com.apress.prospringmvc.pizzarus.repository;

import com.apress.prospringmvc.pizzarus.domain.Customer;

public interface CustomerRepository {

	Customer getCustomer(String username);
}
