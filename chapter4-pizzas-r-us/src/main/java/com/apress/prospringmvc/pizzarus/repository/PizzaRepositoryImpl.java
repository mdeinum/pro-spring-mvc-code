package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.pizzarus.domain.Pizza;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 12-12-11
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
@Repository("pizzaRepository")
public class PizzaRepositoryImpl implements PizzaRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Pizza> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("from Pizza order by name").list();
    }

}
