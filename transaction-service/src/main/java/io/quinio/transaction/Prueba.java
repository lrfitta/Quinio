package io.quinio.transaction;

import java.util.Calendar;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		Calendar first = getFirstDayOfWeek(calendar);
		getLastWeek(first);
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
