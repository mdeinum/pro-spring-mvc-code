package com.apress.prospringmvc.moneytransfer.scanning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.moneytransfer.repository.AccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.TransactionRepository;
import com.apress.prospringmvc.moneytransfer.service.AbstractMoneyTransferService;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
@Service("moneyTransferService")
@Transactional
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
