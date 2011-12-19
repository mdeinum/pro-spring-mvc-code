package com.apress.prospringmvc.pizzarus.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Base class for objects stored in the database. Provides an id (long) and basic toString, hashCode and equals methods.
 *
 * @author Marten Deinum
 */
@MappedSuperclass
public abstract class AbstractEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    protected AbstractEntity() {
        super();
    }

    public long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(final Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

}
