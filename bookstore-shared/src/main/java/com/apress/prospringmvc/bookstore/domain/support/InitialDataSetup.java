package com.apress.prospringmvc.bookstore.domain.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Customer;
import com.apress.prospringmvc.bookstore.domain.Order;

/**
 * Sets up initial data so the application can be used straight away. The data setup is executed in a separate
 * transaction, and commited when the {@link #setupData()} method returns
 * 
 * @author Koen Serneels
 */

public class InitialDataSetup implements ApplicationListener<ContextRefreshedEvent> {

	private TransactionTemplate transactionTemplate;

	@Autowired
	private OrderBuilder orderBuilder;
	@Autowired
	private CustomerBuilder customerBuilder;
	@Autowired
	private BookBuilder bookBuilder;

	public InitialDataSetup(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		transactionTemplate.execute(new TransactionCallback<Void>() {
			@Override
			public Void doInTransaction(TransactionStatus status) {
				Customer johnDoe;
				Category category;

				// Create customer
				{
					johnDoe = customerBuilder.address("Brussels", "1000", "Nieuwstraat", "1", "A")
							.credentials("jd", "secret").name("John", "Doe").build();
				}

				// Create customer
				{
					category = new Category("IT");
				}

				// Create different books and directly attach them with an order
				List<Order> orders = new ArrayList<Order>();
				{
					Book book = bookBuilder.title("Effective Java")
							.description("brings together seventy-eight indispensable programmer’s rules of thumb")
							.author("Joshua Bloch").year(2008).price("31.20").category(category).build();
					orders.add(orderBuilder.addBook(book, 1).deliveryDate(new Date()).orderDate(new Date())
							.customer(johnDoe).build());

					book = bookBuilder
							.title("Refactoring: Improving the Design of Existing Code")
							.description(
									"Refactoring is about improving the design of existing code. It is the process of "
											+ "changing a software system in such a way that it does not alter the external beha"
											+ "vior of the code, yet improves its internal structure")
							.author("Martin Fowler").year(1999).price("41.39").category(category).build();
					orders.add(orderBuilder.addBook(book, 1).deliveryDate(new Date()).orderDate(new Date())
							.customer(johnDoe).build());

					book = bookBuilder
							.title("Clean Code: A Handbook of Agile Software Craftsmanship")
							.description(
									"Even bad code can function. But if code isn’t clean, it can bring a development organization "
											+ "to its knees. Every year, countless hours and significant resources are lost because of poorly "
											+ "written code. But it doesn’t have to be that way")
							.author("Robert C. Martin").year(2008).price("33.32").category(category).build();
					orders.add(orderBuilder.addBook(book, 1).deliveryDate(new Date()).orderDate(new Date())
							.customer(johnDoe).build());

					book = bookBuilder
							.title("Agile Software Development, Principles, Patterns, and Practices")
							.description(
									"A unique collection of the latest software development methods. Includes OOD, UML, Design Patterns, Agile and XP methods with a "
											+ "detailed description of a complete software design for reusable programs in C++ and Java.")
							.author("Robert C. Martin").year(2002).price("54.61").category(category).build();
					orders.add(orderBuilder.addBook(book, 1).deliveryDate(new Date()).orderDate(new Date())
							.customer(johnDoe).build());

					book = bookBuilder
							.title("Practical API Design: Confessions of a Java Framework Architect")
							.description(
									"The definitive guide to API design, this book will be required reading for all designers and engineers involved with the development,"
											+ "testing, and maintenance of APIs.").author("Jaroslav Tulach").year(2008)
							.price("56.01").category(category).build();
					orders.add(orderBuilder.addBook(book, 1).deliveryDate(new Date()).orderDate(new Date())
							.customer(johnDoe).build());

				}
				return null;
			}
		});
	}
}