package com.apress.prospringmvc.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.repository.BookRepository;
import com.apress.prospringmvc.bookstore.repository.CategoryRepository;

@Service("bookService")
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Book findById(long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public List<Book> findByCategory(long categoryId) {
        Category category = this.categoryRepository.findById(categoryId);
        return this.bookRepository.findByCategory(category);
    }

    @Override
    public List<Book> findRandomBooks() {
        return this.bookRepository.findRandom(3);
    }

}
