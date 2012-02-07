package com.apress.prospringmvc.bookstore.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configures the main infrastructure related beans such as:
 * 
 * <ul>
 * <li>Creates the {@link EntityManagerFactory} based upon information in the META-INF/persistence.xml
 * <li>Creates a JPA local transaction manager</li>
 * <li>Creates a datasource to a local database (if running with the local profile)</li>
 * </ul>
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.apress.prospringmvc.bookstore.service",
        "com.apress.prospringmvc.bookstore.repository", "com.apress.prospringmvc.bookstore.domain.support" })
public class InfrastructureContextConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("bookstore");
        localContainerEntityManagerFactoryBean.setDataSource(this.dataSource);
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.apress.prospringmvc.bookstore.domain");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(this.entityManagerFactory);
        transactionManager.setDataSource(this.dataSource);
        return transactionManager;
    }

    @Configuration
    public static class LocalDataSourceConfiguration {
        @Bean
        public DataSource dataSource() {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            builder.setType(EmbeddedDatabaseType.H2);
            return builder.build();
        }
    }
}
