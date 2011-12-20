package com.apress.prospringmvc.pizzarus.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * An order consists out of one or more {@link OrderDetail}s. It is the link between
 * 
 * 
 * @author M. Deinum
 * @author C. Yates
 * @author K. Serneels
 */

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date orderDate;
	private Date DeliveryDate;
	private boolean delivered;
	private BigDecimal totalOrderPrice;

	@OneToMany(mappedBy = "order", orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

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

	public Date getDeliveryDate() {
		return DeliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		DeliveryDate = deliveryDate;
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
}
