package com.apress.prospringmvc.bookstore.domain.support;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import com.apress.prospringmvc.bookstore.domain.Address;
import com.apress.prospringmvc.bookstore.domain.Customer;

/**
 * Builds {@link Customer} domain objects
 * 
 * @author Koen Serneels
 */

@Component
public class CustomerBuilder extends EntityBuilder<Customer> {

	@Override
	void initProduct() {
		product = new Customer();
	}

	public CustomerBuilder credentials(String username, String password) {
		product.setUsername(username);
		product.setPassword(DigestUtils.sha512Hex(password));
		return this;
	}

	public CustomerBuilder address(String city, String postalCode, String street, String houseNumber, String boxNumber) {
		Address customerAddress = new Address();
		customerAddress.setStreet(street);
		customerAddress.setCity(city);
		customerAddress.setHouseNumber(houseNumber);
		customerAddress.setPostalCode(postalCode);
		customerAddress.setBoxNumber(boxNumber);

		product.setAddress(customerAddress);
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

	@Override
	Customer assembleProduct() {
		return product;
	}
}