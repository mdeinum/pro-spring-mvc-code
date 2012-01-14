package com.apress.prospringmvc.bookstore.web.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.service.BookstoreService;
import com.apress.prospringmvc.bookstore.service.CategoryService;

/**
 * Tries to convert a Long (which resembles the primary key of a {@link Shop} to a Shop entity using the
 * {@link BookstoreService}. When conversion cannot take place an {@link IllegalArgumentException} is thrown
 * 
 * @author Koen Serneels
 */

public class CategoryConverter implements GenericConverter {

	@Autowired
	private CategoryService categoryService;

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> set = new HashSet<ConvertiblePair>();
		set.add(new ConvertiblePair(String.class, Category.class));
		set.add(new ConvertiblePair(Category.class, String.class));
		return set;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (source == null) {
			return null;
		}

		if (sourceType.getObjectType().isAssignableFrom(Category.class)) {
			return ((Category) source).getName();
		}

		if (sourceType.getObjectType().isAssignableFrom(String.class)
				&& targetType.getObjectType().isAssignableFrom(Category.class)) {

			return categoryService.findById(Long.parseLong((String) source));
		}
		throw new IllegalArgumentException(source + " cannot be converted to a Shop object");
	}
}
