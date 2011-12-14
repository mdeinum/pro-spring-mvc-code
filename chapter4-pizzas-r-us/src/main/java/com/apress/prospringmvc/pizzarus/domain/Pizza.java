package com.apress.prospringmvc.pizzarus.domain;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 12-12-11
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
public class Pizza {

    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    
    public Pizza(long id, String name, String description, BigDecimal price) {
        super();
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
        
    public BigDecimal getPrice() {
        return this.price;
    }

}
