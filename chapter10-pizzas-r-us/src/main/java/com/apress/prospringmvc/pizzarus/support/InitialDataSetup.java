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

import com.apress.prospringmvc.pizzarus.domain.Customer;
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
				Customer johnDoe;

				// Create customer
				{
					johnDoe = new CustomerBuilder().address("Brussels", "1000", "Nieuwstraat", "1", "A")
							.credentials("jd", "secret").name("John", "Doe").build();
				}

				// Setup different shops
				{
					brussels = new ShopBuilder().shop("Pizza-r-us", "Brussels").build();
					new ShopBuilder().shop("Pizza-r-us", "Antwerp").build();
					new ShopBuilder().shop("Pizza-r-us", "Gent").build();
					new ShopBuilder().shop("Pizza-r-us", "Bruges").build();
					new ShopBuilder().shop("Pizza-r-us", "Liege").build();
					new ShopBuilder().shop("Pizza-r-us", "Charleroi").build();
				}

				// Create different pizza's and directly attach them with an order
				List<Order> orders = new ArrayList<Order>();
				{
					orders.add(new OrderBuilder()
					.buildAndAddPizza("Pizza Margherita",
							"Tomato, sliced mozzarella, basil and extra-virgin olive oil",
							new BigDecimal("13.20"), 1).deliveryDate(new Date()).orderDate(new Date())
							.shop(brussels).customer(johnDoe).build());

					orders.add(new OrderBuilder()
					.buildAndAddPizza(
							"Pizza Funghi",
							"olive oil, garlic, sliced assorted mushrooms, parsley leaves, thyme leaves, fontina cheese, oregano",
							new BigDecimal("10.00"), 1).deliveryDate(new Date()).orderDate(new Date())
							.shop(brussels).customer(johnDoe).build());

					orders.add(new OrderBuilder()
					.buildAndAddPizza("Pizza capricciosa",
							"mozzarella, tomato, mushrooms, artichokes, cooked ham, olives, oil",
							new BigDecimal("15.45"), 1).deliveryDate(new Date()).orderDate(new Date())
							.shop(brussels).customer(johnDoe).build());

					orders.add(new OrderBuilder()
					.buildAndAddPizza("Pizza quattro formaggi",
							"tomatoes, and the cheeses mozzarella, stracchino, fontina, and gorgonzola",
							new BigDecimal("11.24"), 1).deliveryDate(new Date()).orderDate(new Date())
							.shop(brussels).customer(johnDoe).build());
				}

				EntityBuilder.clear();
				return null;
			}
		});
	}
}
