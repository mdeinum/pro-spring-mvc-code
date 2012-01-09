package com.apress.prospringmvc.moneytransfer.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.Test;

import com.apress.prospringmvc.moneytransfer.domain.Account;
import com.apress.prospringmvc.moneytransfer.domain.MoneyTransferTransaction;
import com.apress.prospringmvc.moneytransfer.domain.Transaction;

public class MapBasedTransactionRepositoryTests {

    private final MapBasedTransactionRepository repository = new MapBasedTransactionRepository();

    @Test
    public void store() {
        Account src = new Account("src");
        Account dst = new Account("dst");
        BigDecimal amount = BigDecimal.TEN;
        MoneyTransferTransaction transaction = new MoneyTransferTransaction(src, dst, amount);

        this.repository.store(transaction);

        Set<Transaction> srxTxs = this.repository.find(src);
        Set<Transaction> dstTxs = this.repository.find(dst);

        assertTrue(srxTxs.size() == 1);
        assertTrue(dstTxs.size() == 1);

        assertEquals(transaction, srxTxs.iterator().next());
        assertEquals(transaction, dstTxs.iterator().next());

    }

    @Test
    public void findNonExisting() {
        Set<Transaction> transactions = this.repository.find(new Account("foobar"));
        assertNotNull(transactions);
        assertTrue(transactions.isEmpty());

    }

}
