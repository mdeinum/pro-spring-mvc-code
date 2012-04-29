package com.apress.prospringmvc.moneytransfer.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Base class for transaction subclasses.
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */
public abstract class Transaction {

    private final Account source;
    private final BigDecimal amount;
    private final Date date = new Date();

    protected Transaction(final Account source, final BigDecimal amount) {
        super();
        this.source = source;
        this.amount = amount;
    }

    public Account getSource() {
        return this.source;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(1, 13, this);
    }
}
