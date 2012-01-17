package com.apress.prospringmvc.bookstore.domain.support;

import java.io.Serializable;
import java.util.List;

public abstract class LazyResultInitializerStrategy<T extends Serializable> {

	public List<T> initialize(List<T> list) {
		for (T t : list) {
			initialize(t);
		}

		return list;
	}

	public abstract T initialize(T t);
}
