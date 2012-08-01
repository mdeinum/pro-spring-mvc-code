package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Category;

/**
 * Repository for working with {@link Category} domain objects
 *  
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public interface CategoryRepository {

	List<Category> findAll();

	Category findById(long id);

	void storeCategory(Category category);
}
