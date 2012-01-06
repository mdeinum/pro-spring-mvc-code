package com.apress.prospringmvc.pizzarus.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 15-12-11
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "orders")
//Order is a reserved keyword in SQL, hence the explicit declaration for the table
public class Order extends AbstractEntity {

    private final Date date = new Date();
    @NotEmpty(message = "required")
    private String name;
    /* Address Information */
    @NotEmpty(message = "required")
    private String address;
    private String postcode;
    @NotEmpty(message = "required")
    private String city;
    @NotEmpty(message = "required")
    private String country;

    /* Payment Information */
    @NotEmpty(message = "required")
    @Digits(integer = 16, fraction = 0, message = "invalid")
    @Size(min = 16, max = 16, message = "invalid")
    private String creditcard;

    private String comment;

    /**
     * Lines for this order.
     */
    @NotEmpty(message = "required")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderLine> lines = new ArrayList<OrderLine>();

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return this.date;
    }

    public List<OrderLine> getLines() {
        return this.lines;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(final String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCreditcard() {
        return this.creditcard;
    }

    public void setCreditcard(final String creditcard) {
        this.creditcard = creditcard;
    }

    /**
     * Calculate the total for this order.
     */
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderLine line : this.lines) {
            total = total.add(line.getTotal());
        }
        return total;
    }

    public void addOrderLine(final OrderLine orderLine) {
        this.lines.add(orderLine);
    }

    public void removeEmptyOrderLines() {
        Iterator<OrderLine> orderLineIt = this.lines.iterator();
        while (orderLineIt.hasNext()) {
            OrderLine line = orderLineIt.next();
            if (line.getQuantity().signum() <= 0) {
                orderLineIt.remove();
            }
        }
    }

}
