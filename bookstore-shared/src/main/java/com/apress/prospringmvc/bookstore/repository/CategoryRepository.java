package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Category;

public interface CategoryRepository {

	List<Category> findAll();

	Category findById(long id);

	void storeCategory(Category category);
}
