package io.quinio.transaction.service;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.quinio.transaction.utils.Constants;

/**
 * @author Luis Angel Rodriguez Fitta
 * Test service HelperService
 */
@SpringBootTest
@ActiveProfiles("test")
public class CalendarServiceTest {
	@Autowired
	private ICalendarService service;
	
	@Test
	public void getFirstDayOfWeek() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		calendar.set(Calendar.DATE, 11);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.YEAR, 2022);
		
		Calendar firstDay = service.getFirstDayOfWeek(calendar);
		Assertions.assertEquals(6, firstDay.get(Calendar.DATE));
		Assertions.assertEquals(2, firstDay.get(Calendar.MONTH));
		Assertions.assertEquals(2022, firstDay.get(Calendar.YEAR));
	}
	
	@Test
	public void getLastWeek() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		calendar.set(Calendar.DATE, 11);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.YEAR, 2022);
		
		Calendar firstDay = service.getFirstDayOfWeek(calendar);
		Calendar lastWeek = service.getLastWeek(firstDay);
		Assertions.assertEquals(27, lastWeek.get(Calendar.DATE));
		Assertions.assertEquals(1, lastWeek.get(Calendar.MONTH));
		Assertions.assertEquals(2022, lastWeek.get(Calendar.YEAR));
	}
	
	@Test
	public void getEndDayOfWeek() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		calendar.set(Calendar.DATE, 11);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.YEAR, 2022);
		
		Calendar firstDay = service.getFirstDayOfWeek(calendar);
		Assertions.assertEquals(6, firstDay.get(Calendar.DATE));
		Assertions.assertEquals(2, firstDay.get(Calendar.MONTH));
		Assertions.assertEquals(2022, firstDay.get(Calendar.YEAR));
		Calendar end = service.getEndDayOfWeek(firstDay);
		Assertions.assertEquals(12, end.get(Calendar.DATE));
		Assertions.assertEquals(2, end.get(Calendar.MONTH));
		Assertions.assertEquals(2022, end.get(Calendar.YEAR));
	}
}
