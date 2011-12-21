package com.apress.prospringmvc.pizzarus.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerAddress implements Serializable{

	private String street;
	private String houseNumber;
	private String boxNumber;
	private String city;
	private String postalCode;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getBoxNumber() {
		return boxNumber;

	}

	public void setBoxNumber(String boxNumber) {
		this.boxNumber = boxNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}