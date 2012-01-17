package com.apress.prospringmvc.bookstore.domain.support;

import org.springframework.stereotype.Component;

import com.apress.prospringmvc.bookstore.domain.Category;

@Component
public class CategoryBuilder extends EntityBuilder<Category> {

    @Override
    void initProduct() {
    }

    @Override
    Category assembleProduct() {
        return this.product;
    }

    public CategoryBuilder name(String name) {
        this.product = new Category(name);
        return this;
    }

}
