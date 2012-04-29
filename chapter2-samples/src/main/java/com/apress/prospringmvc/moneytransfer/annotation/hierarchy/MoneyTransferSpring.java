package com.apress.prospringmvc.moneytransfer.annotation.hierarchy;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        ApplicationContext parent = new AnnotationConfigApplicationContext(ParentApplicationContextConfiguration.class);
        AnnotationConfigApplicationContext child = new AnnotationConfigApplicationContext();
        child.register(ChildApplicationContextConfiguration.class);
        child.setParent(parent);
        child.refresh();

        transfer(child);

        ApplicationContextLogger.log(child);
        ApplicationContextLogger.log(parent);
    }

    private static void transfer(ApplicationContext ctx) {
        MoneyTransferService service = ctx.getBean("moneyTransferService", MoneyTransferService.class);
        Transaction transaction = service.transfer("123456", "654321", new BigDecimal("250.00"));

        logger.info("Money Transfered: {}", transaction);
    }

}
