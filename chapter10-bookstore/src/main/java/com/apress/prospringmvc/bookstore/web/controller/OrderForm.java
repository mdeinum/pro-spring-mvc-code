package com.apress.prospringmvc.bookstore.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Order;

/**
 * Form to capture all elements of a order creation flow. When all mandatory elements are filled in a new {@link Order}
 * can be created based upon info
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 * 
 */
public class OrderForm implements Serializable {

	private Map<Book, Integer> selectedBooks = new HashMap<Book, Integer>();

	private Long bookId;
	private Integer quantity;
	private Long categoryId;

	private String deliveryDate;
	private String orderDate;

	public void resetSelectedBooks() {
		selectedBooks.clear();
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Map<Book, Integer> getSelectedBooks() {
		return selectedBooks;
	}

	public void setSelectedBooks(Map<Book, Integer> selectedBooks) {
		this.selectedBooks = selectedBooks;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
}