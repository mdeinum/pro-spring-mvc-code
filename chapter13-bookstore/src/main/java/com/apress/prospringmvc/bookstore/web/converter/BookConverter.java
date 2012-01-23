package com.apress.prospringmvc.bookstore.web.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.service.BookstoreService;

/**
 * Tries to convert a Long (which resembles the primary key of a {@link Book} to a Book entity using the
 * {@link BookstoreService}. When conversion cannot take place an {@link IllegalArgumentException} is thrown
 * 
 * @author Koen Serneels
 */

public class BookConverter implements GenericConverter {

	@Autowired
	private BookstoreService bookstoreService;

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> set = new HashSet<ConvertiblePair>();
		set.add(new ConvertiblePair(String.class, Book.class));
		set.add(new ConvertiblePair(Book.class, String.class));
		return set;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		if (source == null) {
			return null;
		}
		if (sourceType.getObjectType().isAssignableFrom(Book.class)) {
			return ((Book) source).getTitle();
		}

		if (sourceType.getObjectType().isAssignableFrom(String.class)
				&& targetType.getObjectType().isAssignableFrom(Book.class)) {

			return bookstoreService.findBook(Long.parseLong((String) source));
		}
		throw new IllegalArgumentException(source + " cannot be converted to a Book object");
	}
}