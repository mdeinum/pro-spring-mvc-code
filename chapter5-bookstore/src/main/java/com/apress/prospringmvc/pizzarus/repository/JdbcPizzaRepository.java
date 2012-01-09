package com.apress.prospringmvc.pizzarus.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.apress.prospringmvc.pizzarus.domain.Pizza;

/**
 * JDBC implementation for the {@link PizzaRepository}.
 * 
 * @author M. Deinum
 *
 */
@Repository("pizzaRepository")
public class JdbcPizzaRepository extends JdbcDaoSupport implements
		PizzaRepository {

	@Autowired
	private JdbcPizzaRepository(DataSource dataSource) {
		super();
		super.setDataSource(dataSource);
	}
	
	@Override
	public List<Pizza> findAll() {
		String sql = "select * from pizza order by name";
		return getJdbcTemplate().query(sql, new PizzaRowMapper());
	}
	
	/**
	 * Map a row to a Pizza object.
	 * 
	 * @author M. Deinum
	 *
	 */
	private static class PizzaRowMapper implements RowMapper<Pizza> {

		@Override
		public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
			Pizza pizza = new Pizza();
			pizza.setId(rs.getLong("id"));
			pizza.setDescription(rs.getString("description"));
			pizza.setName(rs.getString("name"));
			pizza.setPrice(rs.getBigDecimal("price"));
			return pizza;
		}
		
	}

}
