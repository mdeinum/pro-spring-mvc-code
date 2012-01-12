package com.apress.prospringmvc.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.repository.CustomerRepository;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = false)
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer login(String username, String password) throws AuthenticationException {
        Customer customer = this.customerRepository.findByUsername(username);
        if (customer != null) {
            if (customer.getPassword().equals(password)) {
                return customer;
            }
        }
        throw new AuthenticationException("Wrong username/password combination.", "invalid.username");
    }

}
