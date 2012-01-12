package com.apress.prospringmvc.moneytransfer.annotation.hierarchy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.prospringmvc.moneytransfer.annotation.MoneyTransferServiceImpl;
import com.apress.prospringmvc.moneytransfer.service.MoneyTransferService;

@Configuration
public class ChildApplicationContextConfiguration {

    @Bean
    public MoneyTransferService moneyTransferService() {
        return new MoneyTransferServiceImpl();
    }

}
