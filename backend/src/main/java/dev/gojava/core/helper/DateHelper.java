package dev.gojava.core.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public final class DateHelper {

	public static String format(Date date, int style) {
		DateFormat dateInstance = DateFormat.getDateInstance(style, Locale.getDefault());
		return dateInstance.format(date);
	}

	public static Date parse(String stringDate, int style) {
		DateFormat dateInstance = DateFormat.getDateInstance(style, Locale.getDefault());
		try {
			return dateInstance.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
}
