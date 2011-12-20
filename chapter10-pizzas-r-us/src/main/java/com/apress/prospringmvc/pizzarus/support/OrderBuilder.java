package com.apress.prospringmvc.pizzarus.support;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.OrderDetail;
import com.apress.prospringmvc.pizzarus.domain.Pizza;

public class OrderBuilder {

	private List<OrderDetail> ordetDetails = new ArrayList<OrderDetail>();
	private Order product = new Order();

	public OrderBuilder addPizza(String name, String description, BigDecimal price, int amount) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setAmount(amount);
		Pizza pizza = new Pizza();
		pizza.setDescription(description);
		pizza.setName(name);
		pizza.setPrice(price);
		orderDetail.setPizza(pizza);
		ordetDetails.add(orderDetail);
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

	public OrderBuilder delivered() {
		product.setDelivered(true);
		return this;
	}

	public Order build() {
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		BigDecimal totalOrderPrice = new BigDecimal("0");
		totalOrderPrice.setScale(2, RoundingMode.HALF_UP);

		for (OrderDetail orderDetail : orderDetails) {
			totalOrderPrice = totalOrderPrice.add(orderDetail.getPizza().getPrice()
					.multiply(new BigDecimal(orderDetail.getAmount())));
		}

		product.setOrderDetails(orderDetails);
		product.setTotalOrderPrice(totalOrderPrice);

		return product;
	}
}