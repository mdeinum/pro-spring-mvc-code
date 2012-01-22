package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.Order;

public interface OrderRepository {

    Order save(Order order);

    List<Order> findByAccount(Account account);

}
