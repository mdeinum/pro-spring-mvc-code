package com.apress.prospringmvc.pizzarus.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<ShoppingCartDetail> shoppingCartDetails = new ArrayList<ShoppingCartDetail>();

	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date creationDate;

	@ManyToOne
	private Customer customer;

	@ManyToOne(cascade = javax.persistence.CascadeType.ALL)
	private DeliverySlot deliverySlot;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ShoppingCartDetail> getShoppingCartDetails() {
		return shoppingCartDetails;
	}

	public @ManyToOne
	void setShoppingCartDetails(List<ShoppingCartDetail> shoppingCartDetails) {
		this.shoppingCartDetails = shoppingCartDetails;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public DeliverySlot getDeliverySlot() {
		return deliverySlot;
	}

	public void setDeliverySlot(DeliverySlot deliverySlot) {
		this.deliverySlot = deliverySlot;
	}
}
