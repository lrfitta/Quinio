package io.quinio.transaction.service;

import java.util.Calendar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Luis Angel Rodriguez Fitta
 * Test service HelperService
 */
@SpringBootTest
@ActiveProfiles("test")
public class HelperServiceTest {
	@Autowired
	private IHelperService service;
	
	@Test
	public void getFirstDayOfWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 11);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.YEAR, 2022);
		
		Calendar firstDay = service.getFirstDayOfWeek(calendar);
		Assertions.assertEquals(6, firstDay.get(Calendar.DATE));
		Assertions.assertEquals(2, firstDay.get(Calendar.MONTH));
		Assertions.assertEquals(2022, firstDay.get(Calendar.YEAR));
	}
}
