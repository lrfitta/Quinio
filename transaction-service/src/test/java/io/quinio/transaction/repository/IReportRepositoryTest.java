package io.quinio.transaction.repository;

import java.util.Calendar;
import java.util.TimeZone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import io.quinio.transaction.model.ReportBean;
import io.quinio.transaction.utils.Constants;

/**
 * @author Luis Angel Rodriguez Fitta
 * test para IReportRepository
 */
@SpringBootTest
@ActiveProfiles("test")
public class IReportRepositoryTest {
	@Autowired
	private IReportRepository repository;
	
	@Test
	public void findByRangeDate() {
		Pageable paging = PageRequest.of(0, 2); 
		Calendar start = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		start.set(Calendar.YEAR, 2021);
		start.set(Calendar.MONTH, Calendar.SEPTEMBER);
		start.set(Calendar.DATE, 1);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		
		Calendar end = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		end.set(Calendar.YEAR, 2021);
		end.set(Calendar.MONTH, Calendar.OCTOBER);
		end.set(Calendar.DATE, 11);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		
		Page<ReportBean> page = repository.findByRangeDate(start.getTime(), end.getTime(), paging);
		Assertions.assertTrue(page.hasContent());
		Assertions.assertEquals(2, page.getNumberOfElements());
		
	}
	
}
