package com.apress.prospringmvc.pizzarus.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.apress.prospringmvc.pizzarus.domain.Customer;
import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.Shop;

/**
 * Sets up initial data so the application can be used straight away. The data setup is executed in a separate
 * transaction, and commited when the {@link #setupData()} method returns
 * 
 * @author Koen Serneels
 */

public class InitialDataSetup {

	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private OrderBuilder orderBuilder;
	@Autowired
	private ShopBuilder shopBuilder;
	@Autowired
	private CustomerBuilder customerBuilder;

	public void setupData() {
		transactionTemplate.execute(new TransactionCallback<Void>() {

			@Override
			public Void doInTransaction(TransactionStatus status) {

				Shop brussels;
				Customer johnDoe;

				// Create customer
				{
					johnDoe = customerBuilder.address("Brussels", "1000", "Nieuwstraat", "1", "A")
							.credentials("jd", "secret").name("John", "Doe").build();
				}

				// Setup different shops
				{
					brussels = shopBuilder.shop("Pizza-r-us", "Brussels").build();
					shopBuilder.shop("Pizza-r-us", "Antwerp").build();
					shopBuilder.shop("Pizza-r-us", "Gent").build();
					shopBuilder.shop("Pizza-r-us", "Bruges").build();
					shopBuilder.shop("Pizza-r-us", "Liege").build();
					shopBuilder.shop("Pizza-r-us", "Charleroi").build();
				}

				// Create different pizza's and directly attach them with an order
				List<Order> orders = new ArrayList<Order>();
				{
					orders.add(orderBuilder
							.buildAndAddPizza("Pizza Margherita",
									"Tomato, sliced mozzarella, basil and extra-virgin olive oil",
									new BigDecimal("13.20"), 1)
							.deliverySlot(new IntervalSupport().createInterval(1, 1, 1, 0))
							.orderDate(new Date()).shop(brussels).customer(johnDoe).build());

					orders.add(orderBuilder
							.buildAndAddPizza(
									"Pizza Funghi",
									"olive oil, garlic, sliced assorted mushrooms, parsley leaves, thyme leaves, fontina cheese, oregano",
									new BigDecimal("10.00"), 1)
							.deliverySlot(new IntervalSupport().createInterval(2, 1, 1, 0))
							.orderDate(new Date()).shop(brussels).customer(johnDoe).build());

					orders.add(orderBuilder
							.buildAndAddPizza("Pizza capricciosa",
									"mozzarella, tomato, mushrooms, artichokes, cooked ham, olives, oil",
									new BigDecimal("15.45"), 1)
							.deliverySlot(new IntervalSupport().createInterval(3, 1, 1, 0))
							.orderDate(new Date()).shop(brussels).customer(johnDoe).build());

					orders.add(orderBuilder
							.buildAndAddPizza("Pizza quattro formaggi",
									"tomatoes, and the cheeses mozzarella, stracchino, fontina, and gorgonzola",
									new BigDecimal("11.24"), 1)
							.deliverySlot(new IntervalSupport().createInterval(4, 1, 1, 0))
							.orderDate(new Date()).shop(brussels).customer(johnDoe).build());
				}

				return null;
			}
		});
	}
}
