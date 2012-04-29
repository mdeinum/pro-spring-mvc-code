package com.apress.prospringmvc.bookstore.web.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.support.OrderBuilder;
import com.apress.prospringmvc.bookstore.service.BookstoreService;
import com.apress.prospringmvc.bookstore.service.CategoryService;

/**
 * Controller to be used to place and view orders using the {@link BookstoreService}. This controller can be used using
 * Spring MVC (view orders) or by POJO access (for example Web Flow) for placing orders
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
@Controller
public class OrderController {
	@Autowired
	private BookstoreService bookstoreService;

	@Autowired
	private CategoryService categoryService;

	public List<Order> retrieveOrders(Account account) {
		List<Order> orders = bookstoreService.findOrdersForAccount(account);
		return orders;
	}

	public OrderForm initializeForm() {
		OrderForm orderForm = new OrderForm();
		orderForm.setQuantity(1);
		orderForm.setOrderDate(new Date());
		return orderForm;
	}

	public List<Category> initializeSelectableCategories() {
		return categoryService.findAll();
	}

	public List<Book> initializeSelectableBooks(OrderForm orderForm) {
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

	public Long placeOrder(final Account account, final OrderForm orderForm) {
		Order order = new OrderBuilder() {
			{
				addBooks(orderForm.getBooks());
				deliveryDate(orderForm.getDeliveryDate());
				orderDate(orderForm.getOrderDate());
				account(account);
			}
		}.build(true);

		return bookstoreService.store(order).getId();
	}

	public Event validateDeliveryDate(OrderForm orderForm, MessageContext messageContext) {
		if (orderForm.getDeliveryDate() == null) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("deliveryDate");
			errorMessageBuilder.code("error.page.selectdeliveryoptions.deliverydate.required");
			messageContext.addMessage(errorMessageBuilder.build());
			return new EventFactorySupport().error(this);
		}

		if (!orderForm.getDeliveryDate().after(DateUtils.truncate(orderForm.getOrderDate(), Calendar.DAY_OF_MONTH))) {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("deliveryDate");
			errorMessageBuilder.code("error.page.selectdeliveryoptions.deliverydate.in.past");
			messageContext.addMessage(errorMessageBuilder.build());
			return new EventFactorySupport().error(this);
		}
		return new EventFactorySupport().success(this);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}