package com.apress.prospringmvc.bookstore.service;

import java.util.List;

import com.apress.prospringmvc.bookstore.domain.Category;

public interface CategoryService {

    List<Category> findAll();

}
