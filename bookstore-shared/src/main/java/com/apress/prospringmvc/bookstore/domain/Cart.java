package com.apress.prospringmvc.bookstore.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Basic implementation of a cart.
 * 
 * @author M. Deinum
 *
 */
public class Cart implements Serializable {

    private Map<Book, Integer> books = new HashMap<Book, Integer>();

    public Map<Book, Integer> getBooks() {
        return Collections.unmodifiableMap(this.books);
    }

    public void addBook(Book book) {
        if (this.books.containsKey(book)) {
            int quantity = this.books.get(book);
            quantity++;
            this.books.put(book, quantity);
        } else {
            this.books.put(book, 1);
        }
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public void clear() {
        this.books.clear();
    }

}
