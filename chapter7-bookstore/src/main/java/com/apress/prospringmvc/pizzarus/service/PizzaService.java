package com.apress.prospringmvc.pizzarus.service;

import java.util.List;

import com.apress.prospringmvc.pizzarus.domain.Pizza;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 12-12-11
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public interface PizzaService {

    List<Pizza> findAll();

    Pizza getById(Long id);
}
