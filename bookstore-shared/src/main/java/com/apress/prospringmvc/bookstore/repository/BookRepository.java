package com.apress.prospringmvc.bookstore.repository;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;

public interface BookRepository {

    Book findById(long id);

    List<Book> findByCategory(Category category);

    List<Book> findRandom(int count);

}
