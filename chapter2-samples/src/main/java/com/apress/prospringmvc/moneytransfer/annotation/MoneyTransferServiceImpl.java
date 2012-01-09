package com.apress.prospringmvc.moneytransfer.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import com.apress.prospringmvc.moneytransfer.repository.AccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.TransactionRepository;
import com.apress.prospringmvc.moneytransfer.service.AbstractMoneyTransferService;

public class MoneyTransferServiceImpl extends AbstractMoneyTransferService {

    @Autowired
    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;

    @Override
    protected AccountRepository getAccountRepository() {
        return this.accountRepository;
    }

    @Override
    protected TransactionRepository getTransactionRepository() {
        return this.transactionRepository;
    }

    @Autowired
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

}
