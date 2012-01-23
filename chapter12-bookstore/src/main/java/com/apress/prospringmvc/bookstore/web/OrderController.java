package com.apress.prospringmvc.bookstore.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.support.LazyResultInitializerStrategy;
import com.apress.prospringmvc.bookstore.domain.support.OrderBuilder;
import com.apress.prospringmvc.bookstore.service.BookstoreService;
import com.apress.prospringmvc.bookstore.service.CategoryService;

/**
 * Controller to be used to place and view orders using the {@link BookstoreService}. This controller can be used using
 * Spring MVC (view orders) or by POJO access (for example Web Flow) for placing orders
 * 
 * @author Koen Serneels
 */

@Controller
public class OrderController {

	@Autowired
	private BookstoreService bookstoreService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("ordersOverview.htm")
	public ModelAndView retrieveOrders(HttpSession httpSession) {
		List<Order> orders = bookstoreService.findOrdersForAccount((Account) httpSession
				.getAttribute(AuthenticationController.AUTHENTICATED_ACCOUNT_KEY));

		ModelAndView mov = new ModelAndView();
		mov.setViewName("ordersOverview");
		mov.getModel().put("orders", orders);

		return mov;
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

	public Long placeOrder(Account account, OrderForm orderForm) {
		Order order = new OrderBuilder().addBooks(orderForm.getBooks()).deliveryDate(orderForm.getDeliveryDate())
				.orderDate(orderForm.getOrderDate()).account(account).build(true);
		return bookstoreService.createOrder(order).getId();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
