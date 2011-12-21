package com.apress.prospringmvc.pizzarus.support;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.ArrayUtils;

public abstract class EntityBuilder<T> {

	private static EntityManager entityManager;

	public static void initialize(EntityManager entityManager) {
		EntityBuilder.entityManager = entityManager;
	}

	public static void clear() {
		entityManager = null;
	}

	public T build(Boolean... doNotPersist) {
		T product = assembleProduct();
		if (entityManager != null && ArrayUtils.isEmpty(doNotPersist)
				|| (ArrayUtils.isNotEmpty(doNotPersist) && doNotPersist[0] == Boolean.FALSE)) {
			entityManager.persist(product);
		}
		return product;
	}

	abstract T assembleProduct();
}
