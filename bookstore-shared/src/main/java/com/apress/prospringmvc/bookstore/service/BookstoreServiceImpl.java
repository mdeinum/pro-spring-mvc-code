package com.apress.prospringmvc.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.support.LazyResultInitializerStrategy;
import com.apress.prospringmvc.bookstore.repository.BookRepository;
import com.apress.prospringmvc.bookstore.repository.OrderRepository;

@Service("bookstoreService")
@Transactional(readOnly = true)
public class BookstoreServiceImpl implements BookstoreService {

	private static final int RANDOM_BOOKS = 2;

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
		return this.bookRepository.findRandom(RANDOM_BOOKS);
	}

	@Override
	public List<Order> findOrdersForAccount(Account account, LazyResultInitializerStrategy<Order> lazyResultInitializer) {
		return lazyResultInitializer.initialize(this.orderRepository.findByAccount(account));
	}

	@Override
	@Transactional(readOnly = false)
	public Order createOrder(Order order) {
		return this.orderRepository.save(order);
	}

	@Override
	public List<Book> findBooks(BookSearchCriteria bookSearchCriteria) {
		return this.bookRepository.findBooks(bookSearchCriteria);
	}

	@Override
	public void addBook(Book book) {
		bookRepository.storeBook(book);
	}
}
