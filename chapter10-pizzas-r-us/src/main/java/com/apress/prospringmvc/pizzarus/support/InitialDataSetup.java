package com.apress.prospringmvc.pizzarus.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.apress.prospringmvc.pizzarus.domain.Order;
import com.apress.prospringmvc.pizzarus.domain.Shop;

public class InitialDataSetup {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private TransactionTemplate transactionTemplate;

	public void setupData() {
		transactionTemplate.execute(new TransactionCallback<Void>() {

			@Override
			public Void doInTransaction(TransactionStatus status) {
				EntityBuilder.initialize(entityManager);
				Shop brussels;

				// Setup different shops
				{
					brussels = new ShopBuilder().name("Pizza-r-us").city("Brussels").build();
					new ShopBuilder().name("Pizza-r-us").city("Antwerp").build();
					new ShopBuilder().name("Pizza-r-us").city("Gent").build();
					new ShopBuilder().name("Pizza-r-us").city("Bruges").build();
					new ShopBuilder().name("Pizza-r-us").city("Liege").build();
					new ShopBuilder().name("Pizza-r-us").city("Charleroi").build();
				}

				// Create different pizza's and directly attach them with an order
				List<Order> orders = new ArrayList<Order>();
				{
					orders.add(new OrderBuilder()
					.addPizza("Pizza Margherita",
							"Tomato, sliced mozzarella, basil and extra-virgin olive oil",
							new BigDecimal("13.20"), 1).deliveryDate(new Date()).orderDate(new Date())
							.shop(brussels).build());

					orders.add(new OrderBuilder()
					.addPizza(
							"Pizza Funghi",
							"olive oil, garlic, sliced assorted mushrooms, parsley leaves, thyme leaves, fontina cheese, oregano",
							new BigDecimal("10.00"), 1).deliveryDate(new Date()).orderDate(new Date())
							.shop(brussels).build());

					orders.add(new OrderBuilder()
					.addPizza("Pizza capricciosa",
							"mozzarella, tomato, mushrooms, artichokes, cooked ham, olives, oil",
							new BigDecimal("15.45"), 1).deliveryDate(new Date()).orderDate(new Date())
							.shop(brussels).build());

					orders.add(new OrderBuilder()
					.addPizza("Pizza quattro formaggi",
							"tomatoes, and the cheeses mozzarella, stracchino, fontina, and gorgonzola",
							new BigDecimal("11.24"), 1).deliveryDate(new Date()).orderDate(new Date())
							.shop(brussels).build());
				}

				// Associate single customer with all orders
				{
					new CustomerBuilder().addOrder(orders.toArray(new Order[0]))
					.address("Brussels", "1000", "Nieuwstraat", "1", "A").credentials("jd", "secret")
					.name("John", "Doe").build();
				}

				EntityBuilder.clear();
				return null;
			}
		});
	}
}
