package com.apress.prospringmvc.bookstore.web;

import org.hibernate.validator.constraints.NotEmpty;

public class ManageCategoryForm {

	@NotEmpty
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
