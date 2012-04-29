package com.apress.prospringmvc.moneytransfer.annotation.profiles;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.prospringmvc.ApplicationContextLogger;
import com.apress.prospringmvc.moneytransfer.domain.Transaction;
import com.apress.prospringmvc.moneytransfer.service.MoneyTransferService;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public class MoneyTransferSpring {

    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferSpring.class);

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.setProperty("spring.profiles.active", "test");

        AnnotationConfigApplicationContext ctx1 = new AnnotationConfigApplicationContext(
                ApplicationContextConfiguration.class);
        transfer(ctx1);
        ApplicationContextLogger.log(ctx1);

        ApplicationContext ctx2 = new ClassPathXmlApplicationContext(
                "/com/apress/prospringmvc/moneytransfer/annotation/profiles/application-context.xml");
        transfer(ctx2);

        ApplicationContextLogger.log(ctx2);

        System.setProperty("spring.profiles.active", "local");
        AnnotationConfigApplicationContext ctx3 = new AnnotationConfigApplicationContext(
                ApplicationContextConfiguration.class);
        transfer(ctx3);
        ApplicationContextLogger.log(ctx3);

        ApplicationContext ctx4 = new ClassPathXmlApplicationContext(
                "/com/apress/prospringmvc/moneytransfer/annotation/profiles/application-context.xml");
        transfer(ctx4);
        ApplicationContextLogger.log(ctx4);

    }

    private static void transfer(ApplicationContext ctx) {
        MoneyTransferService service = ctx.getBean("moneyTransferService", MoneyTransferService.class);
        Transaction transaction = service.transfer("123456", "654321", new BigDecimal("250.00"));
        logger.info("Money Transfered: {}", transaction);
    }
}
