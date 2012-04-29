package com.apress.prospringmvc.moneytransfer.simple;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apress.prospringmvc.moneytransfer.domain.Transaction;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public class SimpleMoneyTransfer {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMoneyTransfer.class);

    public static void main(String[] args) {
        SimpleMoneyTransferServiceImpl service = new SimpleMoneyTransferServiceImpl();

        Transaction transaction = service.transfer("123456", "654321", new BigDecimal("250.00"));

        logger.info("Money Transfered: {}", transaction);

    }

}
