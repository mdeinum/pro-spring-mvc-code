package com.apress.prospringmvc.pizzarus.domain;

import org.apache.commons.lang3.builder.*;

/** 
 * Convenience base entity class. Provides simple toString, equals and hashCode methods.
 * 
 * @author M. Deinum
 * @author C. Yates
 */
public abstract class AbstractEntity {

	private long id;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (obj == this) {
			return true;
		}
		
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
	
}
