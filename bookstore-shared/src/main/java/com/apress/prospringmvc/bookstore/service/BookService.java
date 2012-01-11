package com.apress.prospringmvc.bookstore.service;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Book;

public interface BookService {

    Book findById(long id);

    List<Book> findByCategory(long categoryId);

    List<Book> findRandomBooks();
}
