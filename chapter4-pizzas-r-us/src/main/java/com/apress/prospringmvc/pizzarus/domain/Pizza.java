package com.apress.prospringmvc.pizzarus.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 12-12-11
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Pizza extends AbstractEntity {

    private String name;
    private String description;
    private BigDecimal price;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

}
