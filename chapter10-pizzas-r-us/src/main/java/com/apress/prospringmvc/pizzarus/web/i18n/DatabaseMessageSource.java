package com.apress.prospringmvc.pizzarus.web.i18n;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;

/**
 * {@link MessageSource} implementation that is backed by a database. The table should be named messages and contain the columns msg, code and locale (all character based columns).
 * @author M. Deinum
 *
 */
public class DatabaseMessageSource extends AbstractMessageSource {

	private JdbcTemplate jdbcTemplate;
	
	private static final String CODE_LOCALE_QUERY = "select msg from messages where code=? and locale=?";
	private static final String CODE_QUERY = "select msg from messages where code=? and locale is null";
	
	private final RowMapper<String> stringRowMapper = new SingleColumnRowMapper<String>(String.class);
	
	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		List<String> messages = jdbcTemplate.query(CODE_LOCALE_QUERY, stringRowMapper, code, locale.toString());
		if (noMessage(messages)) {
			messages = jdbcTemplate.query(CODE_QUERY, stringRowMapper, code);
		}
		
		if (noMessage(messages)) {
			return null;
		}
		
		return new MessageFormat(messages.get(0), locale);
	}
	
	private boolean noMessage(List<String> messages) {
		return (messages.isEmpty() || StringUtils.isBlank(messages.get(0)));
	}
	
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

}
