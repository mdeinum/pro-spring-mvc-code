package com.apress.prospringmvc.pizzarus.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Models a "Pizza" in our domain. A pizza has a name, a longer description (possibly describing its ingredients) and a
 * price.
 * 
 * @author M. Deinum
 * @author C. Yates
 * @author K. Serneels
 */

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class Pizza implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;
	private String description;
	private BigDecimal price;

	@SuppressWarnings("unused")
	private Pizza() {
		// For ORM
	}

	public Pizza(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Pizza other = (Pizza) object;

		return new EqualsBuilder().append(this.getName(), other.getName()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).toHashCode();
	}
}