package com.apress.prospringmvc.bookstore.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.support.LazyResultInitializerStrategy;
import com.apress.prospringmvc.bookstore.domain.support.OrderBuilder;
import com.apress.prospringmvc.bookstore.service.BookstoreService;
import com.apress.prospringmvc.bookstore.service.CategoryService;
import com.apress.prospringmvc.bookstore.web.security.BookstoreUserDetails;
import com.apress.prospringmvc.bookstore.web.security.SecurityContextSupport;

/**
 * Controller to be used to place and view orders using the {@link BookstoreService}. This controller can be used using
 * Spring MVC (view orders) or by POJO access (for example Web Flow) for placing orders
 * 
 * @author Koen Serneels
 */

@Component
public class OrderController {
	@Autowired
	private BookstoreService bookstoreService;

	@Autowired
	private CategoryService categoryService;

	public List<Order> retrieveOrders() {
		List<Order> orders = bookstoreService.findOrdersForAccount(SecurityContextSupport.getUserDetails()
				.getAccount(), new LazyResultInitializerStrategy<Order>() {
			@Override
			public Order initialize(Order order) {
				Hibernate.initialize(order.getOrderDetails());
				return order;
			}
		});
		return orders;
	}

	public OrderForm initializeForm() {
		OrderForm orderForm = new OrderForm();
		orderForm.setQuantity(1);
		orderForm.setOrderDate(new Date());
		return orderForm;
	}

	public List<Category> initializeCategories() {
		return categoryService.findAll();
	}

	public List<Book> initializeBooks(OrderForm orderForm) {
		return bookstoreService.findBooksByCategory(orderForm.getCategory());
	}

	public void addBook(OrderForm orderForm) {
		Book book = orderForm.getBook();
		if (orderForm.getBooks().containsKey(book)) {
			orderForm.getBooks().put(book, orderForm.getBooks().get(book) + orderForm.getQuantity());
		} else {
			orderForm.getBooks().put(book, orderForm.getQuantity());
		}
	}

	public Long placeOrder(OrderForm orderForm) {
		Order order = new OrderBuilder().addBooks(orderForm.getBooks()).deliveryDate(orderForm.getDeliveryDate())
				.orderDate(orderForm.getOrderDate()).account(SecurityContextSupport.getUserDetails().getAccount())
				.build(true);
		return bookstoreService.createOrder(order).getId();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
