package com.apress.prospringmvc.bookstore.domain;

/**
 * Object to hold the search criteria to search books.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public class BookSearchCriteria {

    private String title;
    private Category category;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return this.category;
    }

}
