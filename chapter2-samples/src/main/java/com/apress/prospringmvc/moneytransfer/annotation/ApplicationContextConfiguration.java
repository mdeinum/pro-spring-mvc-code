package com.apress.prospringmvc.moneytransfer.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.prospringmvc.moneytransfer.repository.AccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.MapBasedAccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.MapBasedTransactionRepository;
import com.apress.prospringmvc.moneytransfer.repository.TransactionRepository;
import com.apress.prospringmvc.moneytransfer.service.MoneyTransferService;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
@Configuration
public class ApplicationContextConfiguration {

    @Bean
    public AccountRepository accountRepository() {
        return new MapBasedAccountRepository();
    }

    @Bean
    public TransactionRepository transactionRepository() {
        return new MapBasedTransactionRepository();
    }

    @Bean
    public MoneyTransferService moneyTransferService() {
        return new MoneyTransferServiceImpl();
    }
}
