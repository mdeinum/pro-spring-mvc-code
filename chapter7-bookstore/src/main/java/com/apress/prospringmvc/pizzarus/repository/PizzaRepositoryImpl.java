package com.apress.prospringmvc.pizzarus.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pizza> findAll() {
        return this.entityManager.createQuery("select p from Pizza p order by p.name", Pizza.class).getResultList();
    }

    @Override
    public Pizza getById(final Long id) {
        return this.entityManager.find(Pizza.class, id);
    }

}
