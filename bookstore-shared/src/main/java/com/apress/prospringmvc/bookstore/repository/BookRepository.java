package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.BookSearchCriteria;

public interface BookRepository {

    Book findById(long id);

    List<Book> findRandom(int count);

    List<Book> findBooks(BookSearchCriteria bookSearchCriteria);

}
