package io.quinio.transaction;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer tmp = 9;
		Integer numberOfPages = tmp / 3 + (tmp % 4 == 0 ? 0 : 1);
		System.out.println(numberOfPages);
	}
	
	private static Calendar getFirstDayOfWeek(final Calendar calendar) {
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		Calendar firstDay = Calendar.getInstance();
		calendar.setTime(calendar.getTime());
		firstDay.add(Calendar.DAY_OF_WEEK, (dayOfWeek - 1) * -1);
		System.out.println(firstDay.getTime());
		return firstDay;
	}
	
	public static Calendar getLastWeek(Calendar calendar) {
		Calendar week = Calendar.getInstance();
		week.setTime(calendar.getTime());
		week.add(Calendar.DATE, -7);
		System.out.println(week.getTime());
		return week;
	}

}
