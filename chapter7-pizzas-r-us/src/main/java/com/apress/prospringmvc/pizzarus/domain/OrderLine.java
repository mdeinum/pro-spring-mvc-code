package com.apress.prospringmvc.pizzarus.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Entity representing a line from an order.
 * 
 * @author Marten Deinum
 *
 */
@Entity
public class OrderLine extends AbstractEntity {

    private BigDecimal quantity = BigDecimal.ONE;

    @ManyToOne(optional = false)
    private Pizza pizza;

    public BigDecimal getQuantity() {
        return this.quantity;
    }

    public void setQuantity(final BigDecimal quantity) {
        this.quantity = quantity;
    }

    public void setPizza(final Pizza pizza) {
        this.pizza = pizza;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    /**
     * Calculate the total price for this OrderLine.
     * 
     * @return the calculated price.
     */
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        if (this.pizza != null) {
            total = this.pizza.getPrice().multiply(this.quantity);
        }
        return total;
    }
}
