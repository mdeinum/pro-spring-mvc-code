package com.apress.prospringmvc.bookstore.service;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.support.LazyResultInitializerStrategy;

public interface BookstoreService {

    Book findById(long id);

    List<Book> findBooksByCategory(Category category);

    List<Book> findRandomBooks();

    List<Order> findOrdersForCustomer(Customer customer, LazyResultInitializerStrategy<Order> lazyResultInitializer);

    Order createOrder(Order order);

    List<Book> findBooks(BookSearchCriteria bookSearchCriteria);
}
