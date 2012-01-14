package com.apress.prospringmvc.bookstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
// order is a reserved SQL keyword, hence the explicit table definition
@Table(name = "orders")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "shipping_street")),
			@AttributeOverride(name = "houseNumber", column = @Column(name = "shipping_houseNumber")),
			@AttributeOverride(name = "boxNumber", column = @Column(name = "shipping_boxNumber")),
			@AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
			@AttributeOverride(name = "postalCode", column = @Column(name = "shipping_postalCode")),
			@AttributeOverride(name = "country", column = @Column(name = "shipping_country")) })
	private Address shippingAddress;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "billing_street")),
			@AttributeOverride(name = "houseNumber", column = @Column(name = "billing_houseNumber")),
			@AttributeOverride(name = "boxNumber", column = @Column(name = "billing_boxNumber")),
			@AttributeOverride(name = "city", column = @Column(name = "billing_city")),
			@AttributeOverride(name = "postalCode", column = @Column(name = "billing_postalCode")),
			@AttributeOverride(name = "country", column = @Column(name = "billing_country")) })
	private Address billingAddress;

	@ManyToOne(optional = false)
	private Customer customer;

	private boolean billingSameAsShipping = true;

	private Date orderDate;
	private Date deliveryDate;

	private BigDecimal totalOrderPrice;

	// One to many creates a join table by default, prevent this as
	// order detail is our specific join table
	@JoinColumn(name = "order_id")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

	public Order() {
		super();
	}

	public Order(Customer customer) {
		super();
		this.customer = customer;
		this.shippingAddress = new Address(customer.getAddress());
	}

	public Address getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		if (this.billingSameAsShipping) {
			return this.shippingAddress;
		}
		return this.billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public boolean isBillingSameAsShipping() {
		return this.billingSameAsShipping;
	}

	public void setBillingSameAsShipping(boolean billingSameAsShipping) {
		this.billingSameAsShipping = billingSameAsShipping;
	}

	public Long getId() {
		return this.id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public BigDecimal getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderPrice(BigDecimal totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}
}
