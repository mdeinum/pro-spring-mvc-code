package com.apress.prospringmvc.pizzarus.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.time.Interval;

@Entity
public class DeliverySlot implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Type(type = "com.apress.prospringmvc.pizzarus.support.IntervalUserType")
	@Columns(columns = { @Column(name = "start"), @Column(name = "end") })
	private Interval deliveryInterval;

	DeliverySlot() {
		// For ORM
	}

	public DeliverySlot(Interval interval) {
		this.deliveryInterval = interval;
	}

	public Interval getDeliveryInterval() {
		return deliveryInterval;
	}

	public void setDeliveryInterval(Interval deliveryInterval) {
		this.deliveryInterval = deliveryInterval;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}