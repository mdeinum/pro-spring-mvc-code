package com.apress.prospringmvc.moneytransfer.constructor;

import java.math.BigDecimal;

import com.apress.prospringmvc.moneytransfer.domain.Account;
import com.apress.prospringmvc.moneytransfer.domain.MoneyTransferTransaction;
import com.apress.prospringmvc.moneytransfer.domain.Transaction;
import com.apress.prospringmvc.moneytransfer.repository.AccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.TransactionRepository;
import com.apress.prospringmvc.moneytransfer.service.MoneyTransferService;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public class MoneyTransferServiceImpl implements MoneyTransferService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public MoneyTransferServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        super();
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction transfer(String source, String target, BigDecimal amount) {
        Account src = this.accountRepository.find(source);
        Account dst = this.accountRepository.find(target);

        src.credit(amount);
        dst.debit(amount);

        MoneyTransferTransaction transaction = new MoneyTransferTransaction(src, dst, amount);
        this.transactionRepository.store(transaction);
        return transaction;
    }

}
