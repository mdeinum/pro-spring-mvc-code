package com.apress.prospringmvc.pizzarus.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

/**
 * Entity representing a line from an order.
 * 
 * @author Marten Deinum
 *
 */
@Entity
public class OrderLine extends AbstractEntity {

    private int quantity;
    private BigDecimal price;
    private String description;

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Calculate the total price for this OrderLine.
     * 
     * @return the calculated price.
     */
    public BigDecimal getTotal() {
        return this.price.multiply(BigDecimal.valueOf(this.quantity));
    }

}
