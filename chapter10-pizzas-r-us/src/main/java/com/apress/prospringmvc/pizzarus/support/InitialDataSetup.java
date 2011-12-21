package com.apress.prospringmvc.pizzarus.support;

import java.math.BigDecimal;
import java.util.Date;

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
				Shop shop = new Shop();
				shop.setShopName("Pizza-r-us");

				Order pizzaMargheritaOrder = new OrderBuilder()
				.addPizza("Pizza Margherita", "Tomato, sliced mozzarella, basil and extra-virgin olive oil",
						new BigDecimal("13.20"), 1).deliveryDate(new Date()).orderDate(new Date()).shop(shop)
						.build();

				Customer johnDoe = new CustomerBuilder().addOrder(pizzaMargheritaOrder)
						.address("Brussels", "1000", "Nieuwstraat", "1", "A").credentials("jd", "secret")
						.name("John", "Doe").build();

				entityManager.persist(johnDoe);

				return null;
			}
		});
	}
}
