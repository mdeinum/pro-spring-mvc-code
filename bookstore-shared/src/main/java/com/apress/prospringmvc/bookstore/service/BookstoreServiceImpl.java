package com.apress.prospringmvc.bookstore.service;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;
import com.apress.prospringmvc.bookstore.domain.Cart;
import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.OrderDetail;
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
    public Book findBook(long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public List<Book> findRandomBooks() {
        return this.bookRepository.findRandom(RANDOM_BOOKS);
    }

    @Override
    public List<Order> findOrdersForCustomer(Customer customer) {
        return this.orderRepository.findByCustomer(customer);
    }

    @Override
    @Transactional(readOnly = false)
    public Order createOrder(Cart cart, Customer customer) {
        Order order = new Order(customer);
        for (Entry<Book, Integer> line : cart.getBooks().entrySet()) {
            OrderDetail detail = new OrderDetail();
            order.addOrderDetail(new OrderDetail(line.getKey(), line.getValue()));
        }
        cart.clear();
        return order;
    }

    @Override
    @Transactional(readOnly = false)
    public Order store(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public List<Book> findBooks(BookSearchCriteria bookSearchCriteria) {
        return this.bookRepository.findBooks(bookSearchCriteria);
    }

    @Override
    public Order findOrder(long id) {
        return this.orderRepository.findById(id);
    }
}
