package com.apress.prospringmvc.pizzarus.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.domain.Shop;

public class OrderForm implements Serializable {

	private Map<Pizza, Integer> pizzas = new HashMap<Pizza, Integer>();
	private Pizza pizza;
	private List<Pizza> selectablePizzas = new ArrayList<Pizza>();
	private int quantity;
	private Shop shop;
	private Date deliveryDate;
	private Date orderDate;

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<Pizza> getSelectablePizzas() {
		return selectablePizzas;
	}

	public void setSelectablePizzas(List<Pizza> selectablePizzas) {
		this.selectablePizzas = selectablePizzas;
	}

}