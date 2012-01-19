package com.apress.prospringmvc.bookstore.service;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.support.LazyResultInitializerStrategy;

/**
 * Main entry point for the bookstore. 
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public interface BookstoreService {

    Book findBook(long id);

    Order findOrder(long id);

    List<Book> findBooksByCategory(Category category);

    List<Book> findRandomBooks();

    List<Order> findOrdersForCustomer(Customer customer, LazyResultInitializerStrategy<Order> lazyResultInitializer);

    Order createOrder(Order order);

    List<Book> findBooks(BookSearchCriteria bookSearchCriteria);
}
