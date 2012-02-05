package com.apress.prospringmvc.bookstore.domain.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.apress.prospringmvc.bookstore.domain.Account;
import com.apress.prospringmvc.bookstore.domain.Book;
import com.apress.prospringmvc.bookstore.domain.Category;
import com.apress.prospringmvc.bookstore.domain.Order;
import com.apress.prospringmvc.bookstore.domain.Permission;
import com.apress.prospringmvc.bookstore.domain.Role;

/**
 * Sets up initial data so the application can be used straight away. The data setup is executed in a separate
 * transaction, and committed when the {@link #setupData()} method returns
 * 
 * @author Koen Serneels
 */

public class InitialDataSetup {

    private TransactionTemplate transactionTemplate;

    @Autowired
    private OrderBuilder orderBuilder;
    @Autowired
    private AccountBuilder accountBuilder;
    @Autowired
    private BookBuilder bookBuilder;
    @Autowired
    private CategoryBuilder categoryBuilder;
    @PersistenceContext
    private EntityManager entityManager;

    public InitialDataSetup(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void initialize() {
        this.transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                if (dataIsAlreadyPresent()) {
                    return null;
                }

                Account johnDoe;
                Category category;

                // Create account for JD and admin
                {
                    Permission permissionAddCategories = new Permission("PERM_ADD_CATEGORIES");
                    Permission permissionAddBooks = new Permission("PERM_ADD_BOOKS");
                    Permission permissionCreateOrders = new Permission("PERM_CREATE_ORDER");
                    Role roleCustomer = new Role("ROLE_CUSTOMER");
                    Role roleAdmin = new Role("ROLE_ADMIN");
                    Role roleAuthor = new Role("ROLE_AUTHOR");

                    johnDoe = InitialDataSetup.this.accountBuilder
                            .address("Brussels", "1000", "Nieuwstraat", "1", "A", "BE").email("foo@test.com")
                            .credentials("jd", "secret").name("John", "Doe")
                            .roleWithPermissions(roleCustomer, permissionCreateOrders).build();

                    InitialDataSetup.this.accountBuilder.address("Antwerp", "2000", "Meir", "1", "A", "BE")
                            .email("bar@test.com").credentials("admin", "secret").name("Super", "User")
                            .roleWithPermissions(roleAdmin, permissionAddBooks, permissionAddCategories).build();

                    InitialDataSetup.this.accountBuilder.address("Gent", "9000", "Abdijlaan", "1", "A", "BE")
                            .email("baz@test.com").credentials("author", "secret").name("Some", "Author")
                            .roleWithPermissions(roleAuthor, permissionAddBooks).build();
                }

                // Create category
                {
                    category = InitialDataSetup.this.categoryBuilder.name("IT").build();
                    InitialDataSetup.this.categoryBuilder.name("Java").build();
                    InitialDataSetup.this.categoryBuilder.name("Web").build();
                }

                // Create different books and directly attach them with an order
                List<Order> orders = new ArrayList<Order>();
                {
                    Book book = InitialDataSetup.this.bookBuilder.title("Effective Java").isbn("9780321356680")
                            .description("brings together seventy-eight indispensable programmer’s rules of thumb")
                            .author("Joshua Bloch").year(2008).price("31.20").category(category).build();
                    orders.add(InitialDataSetup.this.orderBuilder.addBook(book, 1).deliveryDate(new Date())
                            .orderDate(new Date()).account(johnDoe).build());

                    book = InitialDataSetup.this.bookBuilder
                            .title("Refactoring: Improving the Design of Existing Code")
                            .isbn("9780201485677")
                            .description(
                                    "Refactoring is about improving the design of existing code. It is the process of "
                                            + "changing a software system in such a way that it does not alter the external beha"
                                            + "vior of the code, yet improves its internal structure")
                            .author("Martin Fowler").year(1999).price("41.39").category(category).build();
                    orders.add(InitialDataSetup.this.orderBuilder.addBook(book, 1).deliveryDate(new Date())
                            .orderDate(new Date()).account(johnDoe).build());

                    book = InitialDataSetup.this.bookBuilder
                            .title("Clean Code: A Handbook of Agile Software Craftsmanship")
                            .isbn("9780132350884")
                            .description(
                                    "Even bad code can function. But if code isn’t clean, it can bring a development organization "
                                            + "to its knees. Every year, countless hours and significant resources are lost because of poorly "
                                            + "written code. But it doesn’t have to be that way")
                            .author("Robert C. Martin").year(2008).price("33.32").category(category).build();
                    orders.add(InitialDataSetup.this.orderBuilder.addBook(book, 1).deliveryDate(new Date())
                            .orderDate(new Date()).account(johnDoe).build());

                    book = InitialDataSetup.this.bookBuilder
                            .title("Agile Software Development, Principles, Patterns, and Practices")
                            .isbn("9780135974445")
                            .description(
                                    "A unique collection of the latest software development methods. Includes OOD, UML, Design Patterns, Agile and XP methods with a "
                                            + "detailed description of a complete software design for reusable programs in C++ and Java.")
                            .author("Robert C. Martin").year(2002).price("54.61").category(category).build();
                    orders.add(InitialDataSetup.this.orderBuilder.addBook(book, 1).deliveryDate(new Date())
                            .orderDate(new Date()).account(johnDoe).build());

                    book = InitialDataSetup.this.bookBuilder
                            .title("Practical API Design: Confessions of a Java Framework Architect")
                            .isbn("9781430209737")
                            .description(
                                    "The definitive guide to API design, this book will be required reading for all designers and engineers involved with the development,"
                                            + "testing, and maintenance of APIs.").author("Jaroslav Tulach").year(2008)
                            .price("56.01").category(category).build();
                    orders.add(InitialDataSetup.this.orderBuilder.addBook(book, 1).deliveryDate(new Date())
                            .orderDate(new Date()).account(johnDoe).build());

                }
                return null;
            }

            private boolean dataIsAlreadyPresent() {
                return InitialDataSetup.this.entityManager.createQuery("select count(a.id) from Account a", Long.class)
                        .getSingleResult().longValue() > 0;
            }
        });
    }
}