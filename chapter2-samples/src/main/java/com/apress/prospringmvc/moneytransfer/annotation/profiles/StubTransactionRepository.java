package com.apress.prospringmvc.moneytransfer.annotation.profiles;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apress.prospringmvc.moneytransfer.domain.Account;
import com.apress.prospringmvc.moneytransfer.domain.Transaction;
import com.apress.prospringmvc.moneytransfer.repository.TransactionRepository;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public class StubTransactionRepository implements TransactionRepository {

    private final Logger logger = LoggerFactory.getLogger(StubTransactionRepository.class);

    @Override
    public void store(Transaction transaction) {
        this.logger.info("Stored: {}", transaction);
    }

    @Override
    public Set<Transaction> find(Account account) {
        return new HashSet<Transaction>();
    }

}
