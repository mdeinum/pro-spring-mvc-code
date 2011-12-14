package com.apress.prospringmvc.pizzarus.repository;

import com.apress.prospringmvc.pizzarus.domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Pizza> findAll() {
        final String sql = "select id, name, description, price from pizza order by name";
        return jdbcTemplate.query(sql, new PizzaRowMapper());
    }

    private static class PizzaRowMapper implements RowMapper<Pizza> {
        @Override
        public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Pizza(rs.getLong("id"), rs.getString("name"), rs.getString("description"), rs.getBigDecimal("price"));
        }
    }
}
