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

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.service.PizzasService;
import com.apress.prospringmvc.pizzarus.support.IntervalSupport;
import com.apress.prospringmvc.pizzarus.support.OrderBuilder;

/**
 * Controller to be used to place and view orders using the {@link PizzasService}. This controller can be used using
 * Spring MVC (view orders) or by POJO access (for example Web Flow) for placing orders
 * 
 * @author Koen Serneels
 */

@Controller
public class OrderController {

	@Autowired
	private PizzasService pizzasService;

	@RequestMapping("ordersOverview.html")
	public ModelAndView retrieveOrders(HttpSession httpSession) {
		List<Order> orders = pizzasService.getOrdersForCustomer((Customer) httpSession
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
		orderForm.setSelectablePizzas(pizzasService.getPizzas());
		orderForm.setSelectableShops(pizzasService.getShops());
		return orderForm;
	}

	public void addPizza(OrderForm orderForm) {
		Pizza pizza = orderForm.getPizza();
		if (orderForm.getPizzas().containsKey(pizza)) {
			orderForm.getPizzas().put(pizza, orderForm.getPizzas().get(pizza) + orderForm.getQuantity());
		} else {
			orderForm.getPizzas().put(pizza, orderForm.getQuantity());
		}
	}

	public Long placeOrder(Customer customer, OrderForm orderForm) {
		Order order = new OrderBuilder().addPizzas(orderForm.getPizzas())
				.deliverySlot(new IntervalSupport().createInterval(orderForm.getDeliveryDate()))
				.orderDate(orderForm.getOrderDate()).shop(orderForm.getShop()).build(true);
		return pizzasService.addOrder(customer, order);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
