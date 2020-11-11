package core;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {

	/**
	 * Capitalize the first letter of the string
	 * @return Capitalized string
	 */
	public static String capitalize(String name) {
		if (name != null && name.length() != 0) {
			char[] chars = name.toCharArray();
			chars[0] = Character.toUpperCase(chars[0]);
			return new String(chars);
		} else {
			return name;
		}
	}
	
	public static String strToEnum(String name) {
		name = name.toUpperCase();
		return name.replaceAll(" ", "_");
	}
	
	public static String enumToString(String name) {
		name = name.toLowerCase();
		name = name.replaceAll("_", " ");
		return capitalize(name);
	}

	public static double clamp(double input, double min, double max) {
		return Math.max(Math.min(input, max), min);
	}

	public static long nextMidnight(long currentTime) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();

		date.setTime(currentTime);
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.HOUR_OF_DAY, 24);

		return cal.getTime().getTime();
	}
}
