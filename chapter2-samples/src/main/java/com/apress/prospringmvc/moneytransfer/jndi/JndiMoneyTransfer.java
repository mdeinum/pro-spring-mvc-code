package com.apress.prospringmvc.moneytransfer.jndi;

import java.math.BigDecimal;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import com.apress.prospringmvc.moneytransfer.domain.Transaction;
import com.apress.prospringmvc.moneytransfer.repository.MapBasedAccountRepository;
import com.apress.prospringmvc.moneytransfer.repository.MapBasedTransactionRepository;
import com.apress.prospringmvc.moneytransfer.simple.SimpleMoneyTransfer;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public class JndiMoneyTransfer {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMoneyTransfer.class);

    /**
     * @param args
     * @throws NamingException 
     */
    public static void main(String[] args) throws NamingException {
        setupJndi();
        JndiMoneyTransferServiceImpl service = new JndiMoneyTransferServiceImpl();
        Transaction transaction = service.transfer("123456", "654321", new BigDecimal("250.00"));

        logger.info("Money Transfered: {}", transaction);

    }

    private static void setupJndi() throws NamingException {
        logger.info("Setting up a Mocked JNDI server.");
        SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
        MapBasedAccountRepository accountRepository = new MapBasedAccountRepository();
        accountRepository.initialize();
        builder.bind("accountRepository", accountRepository);
        builder.bind("transactionRepository", new MapBasedTransactionRepository());
    }

}
