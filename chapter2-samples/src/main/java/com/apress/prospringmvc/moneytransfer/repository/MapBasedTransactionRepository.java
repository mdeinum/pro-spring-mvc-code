package com.apress.prospringmvc.moneytransfer.repository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.moneytransfer.domain.Account;
import com.apress.prospringmvc.moneytransfer.domain.MoneyTransferTransaction;
import com.apress.prospringmvc.moneytransfer.domain.Transaction;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
@Repository("transactionRepository")
public class MapBasedTransactionRepository implements TransactionRepository {

    private final Logger logger = LoggerFactory.getLogger(MapBasedTransactionRepository.class);

    private final Map<Account, Set<Transaction>> storage = new ConcurrentHashMap<Account, Set<Transaction>>();

    @Override
    public void store(Transaction transaction) {
        store(transaction.getSource(), transaction);
        if (transaction instanceof MoneyTransferTransaction) {
            MoneyTransferTransaction mtt = (MoneyTransferTransaction) transaction;
            store(mtt.getTarget(), transaction);
        }
    }

    private void store(Account account, Transaction transaction) {
        Set<Transaction> transactions = this.storage.get(account);
        if (transactions == null) {
            transactions = new HashSet<Transaction>();
            this.storage.put(account, transactions);
        }
        transactions.add(transaction);
        this.logger.info("Stored transaction: {} for account {}.", transaction, account.getNumber());
    }

    @Override
    public Set<Transaction> find(Account account) {
        Set<Transaction> transactions = this.storage.get(account);
        if (transactions == null) {
            transactions = new HashSet<Transaction>();
        }
        return Collections.unmodifiableSet(transactions);
    }

}
