package org.tinyheb.core;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

	private static DateFormat dateFormatter;
	
	public static String toDateString(Date date, Locale locale) {
		dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
		return dateFormatter.format(date);
	}
	
	public static Date getDateFromString(String date, Locale locale) {
		dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
		try {
			return dateFormatter.parse(date);
		} catch (ParseException e) {
			return new Date();
		}
	}
	
	public static String toTimeString(Date date, Locale locale) {
		dateFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		return dateFormatter.format(date);
	}
	
}
