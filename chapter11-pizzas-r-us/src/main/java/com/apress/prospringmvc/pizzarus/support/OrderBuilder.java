package com.apress.prospringmvc.pizzarus.support;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.Interval;
import org.springframework.stereotype.Component;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.DeliverySlot;
import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.OrderDetail;
import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.domain.Shop;

/**
 * Builds {@link Order} domain objects
 * 
 * @author Koen Serneels
 */

@Component
public class OrderBuilder extends EntityBuilder<Order> {

	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

	@Override
	void initProduct() {
		product = new Order();

	}

	public OrderBuilder buildAndAddPizza(String name, String description, BigDecimal price, int amount) {
		Pizza pizza = new Pizza(name);
		pizza.setDescription(description);
		pizza.setPrice(price);
		return addPizza(pizza, amount);
	}

	public OrderBuilder addPizza(Pizza pizza, int amount) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setAmount(amount);

		orderDetail.setPizza(pizza);
		orderDetail.setOrder(product);
		orderDetails.add(orderDetail);
		return this;
	}

	public OrderBuilder addPizzas(Map<Pizza, Integer> map) {
		for (Entry<Pizza, Integer> entry : map.entrySet()) {
			addPizza(entry.getKey(), entry.getValue());
		}
		return this;
	}

	public OrderBuilder deliverySlot(Interval interval) {
		product.setDeliverySlot(new DeliverySlot(interval));
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

	public OrderBuilder shop(Shop shop) {
		product.setShop(shop);
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
			totalOrderPrice = totalOrderPrice.add(orderDetail.getPizza().getPrice()
					.multiply(new BigDecimal(orderDetail.getAmount())));
		}

		product.setOrderDetails(orderDetails);
		product.setTotalOrderPrice(totalOrderPrice);

		return product;
	}
}