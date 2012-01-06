package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import com.apress.prospringmvc.pizzarus.domain.Pizza;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 12-12-11
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */
public interface PizzaRepository {

    List<Pizza> findAll();

    Pizza getById(Long id);

}
