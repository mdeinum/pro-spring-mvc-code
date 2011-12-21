package com.apress.prospringmvc.pizzarus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.service.PizzasService;

@Controller
public class OrderController {

	@Autowired
	private PizzasService pizzasService;

	public void addPizza(OrderForm orderForm) {
		Pizza pizza = orderForm.getPizza();
		if (orderForm.getPizzas().containsKey(pizza)) {
			orderForm.getPizzas().put(pizza, orderForm.getPizzas().get(pizza) + orderForm.getQuantity());
		} else {
			orderForm.getPizzas().put(pizza, 1);
		}
	}

	public OrderForm initializeOrderForm() {
		OrderForm orderForm = new OrderForm();
		orderForm.setSelectablePizzas(pizzasService.getPizzas());
		return orderForm;
	}

}
