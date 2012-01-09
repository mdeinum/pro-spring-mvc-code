package com.apress.prospringmvc.moneytransfer.repository;

import java.util.Set;

import com.apress.prospringmvc.moneytransfer.domain.Account;
import com.apress.prospringmvc.moneytransfer.domain.Transaction;

/**
 * Repository to store and retrieve {@link Transaction}s.
 * 
 * @author Marten Deinum
 *
 */
public interface TransactionRepository {

    void store(Transaction transaction);

    Set<Transaction> find(Account account);

}
