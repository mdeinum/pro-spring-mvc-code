package com.apress.prospringmvc.pizzarus.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Models a "Pizza" in our domain. A pizza has a name, a longer description (possibly describing its ingredients) and a
 * price.
 * 
 * @author M. Deinum
 * @author C. Yates
 * @author K. Serneels
 */

@Entity
public class OrderDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional=false)
	@Cascade(CascadeType.ALL)
	private Pizza pizza;

	@ManyToOne(optional=false)
	@Cascade(CascadeType.ALL)
	private Order order;

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

	public Order getOrder() {
		return order;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}