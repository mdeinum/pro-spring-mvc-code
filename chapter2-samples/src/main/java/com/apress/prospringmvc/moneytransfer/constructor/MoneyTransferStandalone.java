package com.apress.prospringmvc.moneytransfer.constructor;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apress.prospringmvc.moneytransfer.domain.Transaction;
import com.apress.prospringmvc.moneytransfer.repository.MapBasedAccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.MapBasedTransactionRepository;
import com.apress.prospringmvc.moneytransfer.repository.TransactionRepository;
import com.apress.prospringmvc.moneytransfer.service.MoneyTransferService;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public class MoneyTransferStandalone {

    private static final Logger logger = LoggerFactory.getLogger(MoneyTransferStandalone.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        MapBasedAccountRepository accountRepository = new MapBasedAccountRepository();
        accountRepository.initialize();
        TransactionRepository transactionRepository = new MapBasedTransactionRepository();
        MoneyTransferService service = new MoneyTransferServiceImpl(accountRepository, transactionRepository);

        Transaction transaction = service.transfer("123456", "654321", new BigDecimal("250.00"));

        logger.info("Money Transfered: {}", transaction);

    }

}
