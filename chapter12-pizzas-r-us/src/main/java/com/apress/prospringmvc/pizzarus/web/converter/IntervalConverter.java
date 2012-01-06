package com.apress.prospringmvc.pizzarus.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.joda.time.Interval;
import org.springframework.core.convert.converter.Converter;

import com.apress.prospringmvc.pizzarus.support.IntervalSupport;

public class IntervalConverter implements Converter<String, Interval> {

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");

	@Override
	public Interval convert(String source) {
		if (source == null) {
			return null;
		}

		try {
			return new IntervalSupport().createInterval(simpleDateFormat.parse(source));
		} catch (ParseException e) {
			throw new IllegalArgumentException(source + " cannot be converted to a Pizza object");
		}
	}
}
