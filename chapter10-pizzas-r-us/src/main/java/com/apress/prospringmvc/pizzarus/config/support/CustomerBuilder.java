package com.apress.prospringmvc.pizzarus.config.support;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.CustomerAddress;
import com.apress.prospringmvc.pizzarus.domain.Order;

public class CustomerBuilder {

	private Customer product = new Customer();

	public CustomerBuilder credentials(String username, String password) {
		product.setUsername(username);
		product.setPassword(DigestUtils.sha512Hex(password));
		return this;
	}

	public CustomerBuilder address(String city, String postalCode, String street, String houseNumber, String boxNumber) {
		CustomerAddress customerAddress = new CustomerAddress();
		customerAddress.setStreet(street);
		customerAddress.setCity(city);
		customerAddress.setHouseNumber(houseNumber);
		customerAddress.setPostalCode(postalCode);
		customerAddress.setBoxNumber(boxNumber);

		product.setCustomerAddress(customerAddress);
		return this;
	}

	public CustomerBuilder email(String email) {
		product.setEmailAddress(email);
		return this;
	}

	public CustomerBuilder name(String firstName, String lastName) {
		product.setFirstName(firstName);
		product.setLastName(lastName);
		return this;
	}

	public CustomerBuilder addOrder(Order order) {
		product.getOrders().add(order);
		return this;
	}

	public Customer build() {
		return product;
	}
}