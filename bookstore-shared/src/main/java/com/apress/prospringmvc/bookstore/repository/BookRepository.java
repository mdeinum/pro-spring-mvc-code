package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;
import com.apress.prospringmvc.bookstore.domain.Category;

/**
 * Repository for working with {@link Book} domain objects
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public interface BookRepository {

	Book findById(long id);

	List<Book> findByCategory(Category category);

	List<Book> findRandom(int count);

	List<Book> findBooks(BookSearchCriteria bookSearchCriteria);

	void storeBook(Book book);

}
