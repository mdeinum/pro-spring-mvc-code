package com.apress.prospringmvc.pizzarus.domain;

import java.io.Serializable;

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
 * A shop is the place where {@link Order}s are placed. For the sample it just has a name and a city. The business key
 * of a shop is the combination of name and city. Two shop entities with the same name and city are considered equal.
 * 
 * @author Koen Serneels
 */

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "shopName", "city" }) })
public class Shop implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String shopName;

	@Column(nullable = false)
	private String city;

	@SuppressWarnings("unused")
	private Shop() {
		// For ORM
	}

	public Shop(String shopName, String city) {
		this.shopName = shopName;
		this.city = city;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Shop other = (Shop) object;

		return new EqualsBuilder().append(this.getCity(), other.getCity())
				.append(this.getShopName(), other.getShopName()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getShopName()).append(getCity()).toHashCode();
	}
}
