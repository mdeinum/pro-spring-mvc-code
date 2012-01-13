package com.apress.prospringmvc.pizzarus.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.format.annotation.DateTimeFormat;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Order;

/**
 * Form to capture all elements of a order creation flow. When all mandatory elements are filled in a new {@link Order}
 * can be created based upon info
 * 
 * @author Koen Serneels
 */

public class OrderForm implements Serializable {

	private List<Book> selectablePizzas = new ArrayList<Book>();
	private List<Category> selectableCategories = new ArrayList<Category>();
	private Map<Book, Integer> pizzas = new HashMap<Book, Integer>();

	private Book pizza;
	@NotNull
	@Min(1)
	@Max(99)
	private Integer quantity;
	private Category category;

	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date deliveryDate;

	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date orderDate;

	// ---- Form validation methods triggered by webflow according to convention, see reference 5.10. Validating a model
	public void validateSelectPizzas(ValidationContext context) {
		if (context.getUserEvent().equals("next")) {
			MessageContext messages = context.getMessageContext();
			if (pizzas.isEmpty()) {
				messages.addMessage(new MessageBuilder().error().source("pizzas").code("error.page.pizzas.required")
						.build());
			}
		}
	}

	public void validateSelectShop(ValidationContext context) {
		if (context.getUserEvent().equals("next")) {
			MessageContext messages = context.getMessageContext();
			if (category == null) {
				messages.addMessage(new MessageBuilder().error().source("shop").code("error.page.shop.required")
						.build());
			}
		}
	}

	public void validateSelectDeliveryOptions(ValidationContext context) {
		if (context.getUserEvent().equals("finish")) {
			MessageContext messages = context.getMessageContext();
			if (deliveryDate == null) {
				messages.addMessage(new MessageBuilder().error().source("deliveryDate")
						.code("error.page.selectdeliveryoptions.deliverydate.required").build());
			} else {
				if (deliveryDate.before(DateUtils.setHours(
						DateUtils.setMinutes(DateUtils.setSeconds(DateUtils.setMilliseconds(new Date(), 0), 0), 0), 0))) {
					messages.addMessage(new MessageBuilder().error().source("deliveryDate")
							.code("error.page.selectdeliveryoptions.deliverydate.in.past").build());
				}
			}
		}
	}

	public List<Category> getSelectableCategories() {
		return selectableCategories;
	}

	public void setSelectableCategories(List<Category> selectableCategories) {
		this.selectableCategories = selectableCategories;
	}

	public Map<Book, Integer> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Map<Book, Integer> pizzas) {
		this.pizzas = pizzas;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setSelectablePizzas(List<Book> selectablePizzas) {
		this.selectablePizzas = selectablePizzas;
	}

	public void setPizza(Book pizza) {
		this.pizza = pizza;
	}

	public List<Book> getSelectablePizzas() {
		return selectablePizzas;
	}

	public Book getPizza() {
		return pizza;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}