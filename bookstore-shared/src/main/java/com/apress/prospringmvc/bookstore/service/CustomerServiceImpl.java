package com.apress.prospringmvc.bookstore.service;

import org.apache.commons.codec.digest.DigestUtils;
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
            String pwd = DigestUtils.sha512Hex(password);
            if (!customer.getPassword().equalsIgnoreCase(pwd)) {
                customer = null;
            }
        }

        if (customer == null) {
            throw new AuthenticationException("Wrong username/password combination.", "invalid.username");
        } else {
            return customer;
        }
    }

}
