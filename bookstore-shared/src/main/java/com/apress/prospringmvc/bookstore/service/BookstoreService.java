package com.apress.prospringmvc.bookstore.service;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;

public interface BookstoreService {

	Book findById(long id);

	List<Book> findBooksByCategory(Category category);

	List<Book> findRandomBooks();

	List<Order> findOrdersForCustomer(Customer customer);

	Order createOrder(Order order, Customer customer);
}
