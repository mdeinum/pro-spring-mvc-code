package com.apress.prospringmvc.moneytransfer.jndi;

import java.math.BigDecimal;

import javax.naming.InitialContext;
import javax.naming.NamingException;

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
public class JndiMoneyTransferServiceImpl implements MoneyTransferService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public JndiMoneyTransferServiceImpl() {
        try {
            InitialContext context = new InitialContext();
            this.accountRepository = (AccountRepository) context.lookup("accountRepository");
            this.transactionRepository = (TransactionRepository) context.lookup("transactionRepository");
        } catch (NamingException e) {
            throw new IllegalStateException(e);
        }
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
