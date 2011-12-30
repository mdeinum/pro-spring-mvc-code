package com.apress.prospringmvc.pizzarus.domain;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class ShoppingCartDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Pizza pizza;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private ShoppingCartDetail order;

	private int amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public ShoppingCartDetail getOrder() {
		return order;
	}

	public void setOrder(ShoppingCartDetail order) {
		this.order = order;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
