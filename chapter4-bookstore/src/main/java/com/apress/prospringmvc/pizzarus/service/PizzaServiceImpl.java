package com.apress.prospringmvc.pizzarus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import com.apress.prospringmvc.pizzarus.repository.PizzaRepository;

/**
 * Created by IntelliJ IDEA.
 * User: marten
 * Date: 12-12-11
 * Time: 11:32
 * To change this template use File | Settings | File Templates.
 */
@Service(value = "pizzaService")
@Transactional
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public List<Pizza> findAll() {
        return this.pizzaRepository.findAll();
    }
}
