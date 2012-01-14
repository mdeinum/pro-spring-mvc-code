package com.apress.prospringmvc.pizzarus.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.support.OrderBuilder;
import com.apress.prospringmvc.bookstore.service.BookstoreService;
import com.apress.prospringmvc.bookstore.service.CategoryService;

/**
 * Controller to be used to place and view orders using the {@link PizzasService}. This controller can be used using
 * Spring MVC (view orders) or by POJO access (for example Web Flow) for placing orders
 * 
 * @author Koen Serneels
 */

@Controller
public class OrderController {

	@Autowired
	private BookstoreService pizzasService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("ordersOverview.html")
	public ModelAndView retrieveOrders(HttpSession httpSession) {
		List<Order> orders = pizzasService.findOrdersForCustomer((Customer) httpSession
				.getAttribute(AuthenticationController.AUTHENTICATED_CUSTOMER_KEY));

		ModelAndView mov = new ModelAndView();
		mov.setViewName("ordersOverview");
		mov.getModel().put("orders", orders);

		return mov;
	}

	public OrderForm initializeForm() {
		OrderForm orderForm = new OrderForm();
		orderForm.setQuantity(1);
		orderForm.setOrderDate(new Date());
		orderForm.setSelectableCategories(categoryService.findAll());
		return orderForm;
	}

	public void addPizza(OrderForm orderForm) {
		Book pizza = orderForm.getBook();
		if (orderForm.getBooks().containsKey(pizza)) {
			orderForm.getBooks().put(pizza, orderForm.getBooks().get(pizza) + orderForm.getQuantity());
		} else {
			orderForm.getBooks().put(pizza, orderForm.getQuantity());
		}
	}

	public Long placeOrder(Customer customer, OrderForm orderForm) {
		Order order = new OrderBuilder().addBooks(orderForm.getBooks()).deliveryDate(orderForm.getDeliveryDate())
				.orderDate(orderForm.getOrderDate()).build(true);
		return pizzasService.createOrder(order, customer).getId();
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
