package com.apress.prospringmvc.moneytransfer.service;

import java.math.BigDecimal;

import com.apress.prospringmvc.moneytransfer.domain.Account;
import com.apress.prospringmvc.moneytransfer.domain.MoneyTransferTransaction;
import com.apress.prospringmvc.moneytransfer.domain.Transaction;
import com.apress.prospringmvc.moneytransfer.repository.AccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.TransactionRepository;

/**
 * Base class for {@link MoneyTransferService} implementations. This class implements the business logic to transfer money. 
 * Subclasses must implement the techniques to retrieve the repositories.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public abstract class AbstractMoneyTransferService implements MoneyTransferService {

    @Override
    public final Transaction transfer(String source, String target, BigDecimal amount) {
        Account src = getAccountRepository().find(source);
        Account dst = getAccountRepository().find(target);

        src.credit(amount);
        dst.debit(amount);

        MoneyTransferTransaction transaction = new MoneyTransferTransaction(src, dst, amount);
        getTransactionRepository().store(transaction);
        return transaction;
    }

    protected abstract AccountRepository getAccountRepository();

    protected abstract TransactionRepository getTransactionRepository();

}
