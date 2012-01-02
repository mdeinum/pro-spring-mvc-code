package com.apress.prospringmvc.pizzarus.support;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTimeFieldType;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.MutableDateTime;

public class IntervalSupport {

	public static int DEFAULT_DELIVERY_INTERVAL_IN_MINUTES = 30;

	private int deliveryIntervalInMinutes = DEFAULT_DELIVERY_INTERVAL_IN_MINUTES;

	public IntervalSupport() {
		// Take the default interval
	}

	public IntervalSupport(int deliveryIntervalInMinutes) {
		this.deliveryIntervalInMinutes = deliveryIntervalInMinutes;
	}

	public Interval createInterval(int month, int day, int hour, int minute) {
		return createInterval(new GregorianCalendar().get(Calendar.YEAR), day, month, hour, minute);
	}

	public Interval createInterval(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return createInterval(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
	}

	public Interval createInterval(int year, int month, int day, int hour, int minute) {

		MutableDateTime mutableDateTime = new MutableDateTime();
		mutableDateTime.set(DateTimeFieldType.year(), year);
		mutableDateTime.set(DateTimeFieldType.monthOfYear(), month);
		mutableDateTime.set(DateTimeFieldType.dayOfMonth(), day);
		mutableDateTime.set(DateTimeFieldType.hourOfDay(), hour);
		mutableDateTime.set(DateTimeFieldType.minuteOfHour(), minute);
		Duration duration = new Duration(deliveryIntervalInMinutes * 60 * 1000);

		return new Interval(mutableDateTime.toDateTime(), duration);
	}
}
