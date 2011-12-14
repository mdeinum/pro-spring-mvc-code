package com.apress.prospringmvc.pizzarus.repository;

import com.apress.prospringmvc.pizzarus.domain.Pizza;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 12-12-11
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */
public interface PizzaRepository {

    List<Pizza> findAll();

}
