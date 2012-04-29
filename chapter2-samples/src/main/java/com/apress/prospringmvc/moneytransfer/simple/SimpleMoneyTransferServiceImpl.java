package com.apress.prospringmvc.moneytransfer.simple;

import java.math.BigDecimal;

import com.apress.prospringmvc.moneytransfer.domain.Account;
import com.apress.prospringmvc.moneytransfer.domain.MoneyTransferTransaction;
import com.apress.prospringmvc.moneytransfer.domain.Transaction;
import com.apress.prospringmvc.moneytransfer.repository.AccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.MapBasedAccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.MapBasedTransactionRepository;
import com.apress.prospringmvc.moneytransfer.repository.TransactionRepository;
import com.apress.prospringmvc.moneytransfer.service.MoneyTransferService;

/**
 * {@code MoneyTransferService} implementation which instantiates the needed beans itself.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 *
 */
public class SimpleMoneyTransferServiceImpl implements MoneyTransferService {

    private AccountRepository accountRepository = new MapBasedAccountRepository();
    private TransactionRepository transactionRepository = new MapBasedTransactionRepository();

    public SimpleMoneyTransferServiceImpl() {
        super();
        ((MapBasedAccountRepository) this.accountRepository).initialize();

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
