package com.apress.prospringmvc.pizzarus.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.format.annotation.DateTimeFormat;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.domain.Shop;

public class OrderForm implements Serializable {

	private List<Pizza> selectablePizzas = new ArrayList<Pizza>();
	private List<Shop> selectableShops = new ArrayList<Shop>();
	private Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();

	private Pizza pizza;
	@NotNull
	@Min(1)
	@Max(99)
	private Integer quantity;
	private Shop shop;

	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date deliveryDate;

	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date orderDate;

	// ---- Form validation methods triggered by webflow according to convention, see reference 5.10. Validating a model
	public void validateSelectPizzas(ValidationContext context) {
		if (context.getUserEvent().equals("next")) {
			MessageContext messages = context.getMessageContext();
			if (pizzas.isEmpty()) {
				messages.addMessage(new MessageBuilder().error().source("pizzas").code("error.page.pizzas.required")
						.build());
			}
		}
	}

	public void validateSelectShop(ValidationContext context) {
		if (context.getUserEvent().equals("next")) {
			MessageContext messages = context.getMessageContext();
			if (shop == null) {
				messages.addMessage(new MessageBuilder().error().source("shop").code("error.page.shop.required")
						.build());
			}
		}
	}

	public void validateSelectDeliveryOptions(ValidationContext context) {
		if (context.getUserEvent().equals("finish")) {
			MessageContext messages = context.getMessageContext();
			if (deliveryDate == null) {
				messages.addMessage(new MessageBuilder().error().source("deliveryDate")
						.code("error.page.selectdeliveryoptions.deliverydate.required").build());
			} else {
				if (deliveryDate.before(DateUtils.setHours(
						DateUtils.setMinutes(DateUtils.setSeconds(DateUtils.setMilliseconds(new Date(), 0), 0), 0), 0))) {
					messages.addMessage(new MessageBuilder().error().source("deliveryDate")
							.code("error.page.selectdeliveryoptions.deliverydate.in.past").build());
				}
			}
		}
	}

	public Map<Pizza, Integer> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Map<Pizza, Integer> pizzas) {
		this.pizzas = pizzas;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Pizza> getSelectablePizzas() {
		return selectablePizzas;
	}

	public void setSelectablePizzas(List<Pizza> selectablePizzas) {
		this.selectablePizzas = selectablePizzas;
	}

	public List<Shop> getSelectableShops() {
		return selectableShops;
	}

	public void setSelectableShops(List<Shop> selectableShops) {
		this.selectableShops = selectableShops;
	}
}