package com.apress.prospringmvc.bookstore.repository;

import com.apress.prospringmvc.bookstore.domain.Customer;

public interface CustomerRepository {

    Customer findByUsername(String username);

    Customer findById(long id);

    Customer save(Customer customer);

}
