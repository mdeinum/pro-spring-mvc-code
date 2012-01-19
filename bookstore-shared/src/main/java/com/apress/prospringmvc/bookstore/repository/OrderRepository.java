package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;

public interface OrderRepository {

    /**
     * Find the {@link Order} for the given id.
     * 
     * @param id id of the order to find.
     * @return the order belonging to the id.
     */
    Order findById(long id);

    /**
     * Save the order in the databse.
     */
    Order save(Order order);

    /**
     * Find the orders for the given {@link Customer}.
     * @param customer the customer
     * @return list of orders for the customer, never <code>null</code>
     */
    List<Order> findByCustomer(Customer customer);

}
