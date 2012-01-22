package com.apress.prospringmvc.bookstore.domain.support;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import com.apress.prospringmvc.bookstore.domain.Address;
import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.Permission;
import com.apress.prospringmvc.bookstore.domain.Role;

/**
 * Builds {@link Account} domain objects
 * 
 * @author Koen Serneels
 */

@Component
public class AccountBuilder extends EntityBuilder<Account> {

	@Override
	void initProduct() {
		product = new Account();
	}

	public AccountBuilder credentials(String username, String password) {
		product.setUsername(username);
		product.setPassword(DigestUtils.sha256Hex(password + "{" + username
				+ "}"));
		return this;
	}

	public AccountBuilder address(String city, String postalCode,
			String street, String houseNumber, String boxNumber) {
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setHouseNumber(houseNumber);
		address.setPostalCode(postalCode);
		address.setBoxNumber(boxNumber);

		product.setAddress(address);
		return this;
	}

	public AccountBuilder roleWithPermissions(Role role,
			Permission... permissions) {
		product.getRoles().add(role);

		for (Permission permission : permissions) {
			role.getPermissions().add(permission);
		}
		return this;
	}

	public AccountBuilder email(String email) {
		product.setEmailAddress(email);
		return this;
	}

	public AccountBuilder name(String firstName, String lastName) {
		product.setFirstName(firstName);
		product.setLastName(lastName);
		return this;
	}

	@Override
	Account assembleProduct() {
		return product;
	}
}