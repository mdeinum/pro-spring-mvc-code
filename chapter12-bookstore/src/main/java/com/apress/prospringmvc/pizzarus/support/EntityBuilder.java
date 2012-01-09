package com.apress.prospringmvc.pizzarus.support;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Super class for builders that build domain objects. This super class is able to store the product before returning it
 * using an {@link EntityManager}.
 * 
 * @author Koen Serneels
 */

public abstract class EntityBuilder<T extends Serializable> {

	protected T product;
	
	{
		initProduct();
	}

	@PersistenceContext
	private EntityManager entityManager;

	public T build(Boolean... doNotPersist) {
		T product = assembleProduct();
		if (ArrayUtils.isEmpty(doNotPersist)
				|| (ArrayUtils.isNotEmpty(doNotPersist) && doNotPersist[0] == Boolean.FALSE)) {
			entityManager.persist(product);
		}
		T temp = SerializationUtils.clone(product);
		initProduct();
		return temp;
	}

	abstract void initProduct();

	abstract T assembleProduct();
}