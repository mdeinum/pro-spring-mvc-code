package com.apress.prospringmvc.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.support.LazyResultInitializerStrategy;
import com.apress.prospringmvc.bookstore.repository.BookRepository;
import com.apress.prospringmvc.bookstore.repository.OrderRepository;

@Service("bookstoreService")
@Transactional(readOnly = true)
public class BookstoreServiceImpl implements BookstoreService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Book findById(long id) {
		return this.bookRepository.findById(id);
	}

	@Override
	public List<Book> findBooksByCategory(Category category) {
		return this.bookRepository.findByCategory(category);
	}

	@Override
	public List<Book> findRandomBooks() {
		return this.bookRepository.findRandom(3);
	}

	@Override
	public List<Order> findOrdersForCustomer(Customer customer, LazyResultInitializerStrategy<Order> lazyResultInitializer) {
		return lazyResultInitializer.initialize(orderRepository.findByCustomer(customer));
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}
}
