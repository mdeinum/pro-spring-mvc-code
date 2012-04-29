package com.apress.prospringmvc.moneytransfer.domain;

import java.math.BigDecimal;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public class MoneyTransferTransaction extends Transaction {

    private final Account target;

    public MoneyTransferTransaction(final Account source, final Account target, final BigDecimal amount) {
        super(source, amount);
        this.target = target;
    }

    public Account getTarget() {
        return this.target;
    }

}
