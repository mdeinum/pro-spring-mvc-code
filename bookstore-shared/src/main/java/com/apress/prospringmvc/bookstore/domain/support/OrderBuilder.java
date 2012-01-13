package com.apress.prospringmvc.bookstore.domain.support;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.OrderDetail;

/**
 * Builds {@link Order} domain objects
 * 
 * @author Koen Serneels
 */

@Component
public class OrderBuilder extends EntityBuilder<Order> {

	private List<OrderDetail> orderDetails;

	@Override
	void initProduct() {
		product = new Order();
		orderDetails = new ArrayList<OrderDetail>();
	}

	public OrderBuilder addBook(Book book, int quantity) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setQuantity(quantity);

		orderDetail.setBook(book);
		orderDetails.add(orderDetail);
		return this;
	}

	public OrderBuilder addBooks(Map<Book, Integer> map) {
		for (Entry<Book, Integer> entry : map.entrySet()) {
			addBook(entry.getKey(), entry.getValue());
		}
		return this;
	}

	public OrderBuilder deliveryDate(Date date) {
		product.setDeliveryDate(date);
		return this;
	}

	public OrderBuilder orderDate(Date date) {
		product.setOrderDate(date);
		return this;
	}

	public OrderBuilder customer(Customer customer) {
		product.setCustomer(customer);
		return this;
	}

	@Override
	Order assembleProduct() {
		BigDecimal totalOrderPrice = new BigDecimal("0");
		totalOrderPrice.setScale(2, RoundingMode.HALF_UP);

		for (OrderDetail orderDetail : orderDetails) {
			totalOrderPrice = totalOrderPrice.add(orderDetail.getBook().getPrice()
					.multiply(new BigDecimal(orderDetail.getQuantity())));
		}

		product.getOrderDetails().addAll(orderDetails);
		product.setTotalOrderPrice(totalOrderPrice);

		return product;
	}
}