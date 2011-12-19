package com.apress.prospringmvc.pizzarus.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.EJB3NamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration class containing our infrastructure beans. 
 * 
 * @author Marten Deinum
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.apress.prospringmvc.pizzarus.service",
        "com.apress.prospringmvc.pizzarus.repository" })
public class InfrastructureConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2);
        return builder.build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("com.apress.prospringmvc.pizzarus.domain");
        builder.setNamingStrategy(new EJB3NamingStrategy());
        builder.setProperty("hibernate.show_sql", "true");
        builder.setProperty("hibernate.hbm2ddl.auto", "update");
        return builder.buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    @DependsOn(value = "sessionFactory")
    public DataSourceInitializer dataSourceInitializer() {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource());
        initializer.setEnabled(true);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("/META-INF/sql/data.sql"));
        initializer.setDatabasePopulator(populator);
        return initializer;
    }

}
