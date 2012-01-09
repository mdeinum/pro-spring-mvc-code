package com.apress.prospringmvc.pizzarus.support;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;
import org.joda.time.DateTime;
import org.joda.time.Interval;

public class IntervalUserType implements UserType {

	private static final int[] TYPES = new int[] { Types.TIMESTAMP, Types.TIMESTAMP };

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor sessionImplementor, Object owner)
			throws HibernateException, SQLException {
		if (resultSet == null) {
			return null;
		}

		DateTime start = getAsDateTime(resultSet, names[0], sessionImplementor);
		DateTime end = getAsDateTime(resultSet, names[1], sessionImplementor);
		if (start == null || end == null) {
			return null;
		}
		return new Interval(start, end);
	}

	private DateTime getAsDateTime(ResultSet resultSet, String string, SessionImplementor sessionImplementor)
			throws SQLException {
		return new DateTime(StandardBasicTypes.TIMESTAMP.nullSafeGet(resultSet, string, sessionImplementor));
	}

	@Override
	public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			statement.setNull(index, StandardBasicTypes.TIMESTAMP.sqlType());
			statement.setNull(index + 1, StandardBasicTypes.TIMESTAMP.sqlType());
			return;
		}
		Interval interval = (Interval) value;
		statement.setTimestamp(index, new Timestamp(interval.getStart().getMillis()));
		statement.setTimestamp(index + 1, new Timestamp(interval.getEnd().getMillis()));

	}

	@Override
	public int[] sqlTypes() {
		return TYPES;
	}

	@Override
	public Class<?> returnedClass() {
		return Interval.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}
}