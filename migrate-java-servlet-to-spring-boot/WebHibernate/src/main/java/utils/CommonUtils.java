package utils;

import java.util.Calendar;
import java.util.Locale;

public class CommonUtils {
	public static boolean isBoolean(String s) {
		if (s != null) {
			if (s.equals("true") || s.equals("false")) {
				return Boolean.parseBoolean(s);
			}
			return false;
		} else {
			return false;
		}

	}

	private static Locale locale = null;

	public static Locale getLocale() {
		if (locale == null) {
			locale = new Locale("th", "TH");
		}
		return locale;

	}

	public static Calendar getCalendar() {
		Calendar now = Calendar.getInstance(CommonUtils.getLocale());
		return now;

	}
}
