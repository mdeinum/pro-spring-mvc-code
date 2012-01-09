package com.apress.prospringmvc.pizzarus.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * An order detail is the link table between {@link Order} and {@link Pizza} We also store how many pizza's are ordered
 * in the {@link #amount} field
 * 
 * @author Koen Serneels
 */

@Entity
public class OrderDetail implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Pizza pizza;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
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