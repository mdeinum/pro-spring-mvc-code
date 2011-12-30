package com.apress.prospringmvc.pizzarus.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * An order consists out of one or more {@link OrderDetail}s. Further more it has special fields identifying when the
 * order was made {@link #orderDate} when the order should be delivered {@link #deliveryDate} and what the total price
 * of the order is {@link #totalOrderPrice}. The latter can also be caculated by iterarting over the {@link OrderDetail}
 * s, but this is a nice denormalization which enables quick access to the total price without fetching any other data.
 * 
 * @author Koen Serneels
 */

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date orderDate;

	@ManyToOne(cascade = javax.persistence.CascadeType.ALL)
	private DeliverySlot deliverySlot;

	private boolean delivered;
	private BigDecimal totalOrderPrice;

	@ManyToOne
	private Customer customer;

	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

	@ManyToOne
	@Cascade(CascadeType.ALL)
	private Shop shop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isDelivered() {
		return delivered;
	}

	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}

	public BigDecimal getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderPrice(BigDecimal totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public DeliverySlot getDeliverySlot() {
		return deliverySlot;
	}

	public void setDeliverySlot(DeliverySlot deliverySlot) {
		this.deliverySlot = deliverySlot;
	}
}
