package com.apress.prospringmvc.moneytransfer.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public class Account {

    private String owner;
    private final String number;
    private BigDecimal balance = BigDecimal.ZERO;

    public Account(final String number) {
        super();
        this.number = number;
    }

    public void debit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public void credit(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient Funds!");
        }
        this.balance = this.balance.subtract(amount);
    }

    public String getNumber() {
        return this.number;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return this.owner;
    }

    @Override
    public int hashCode() {
        return this.number.hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
