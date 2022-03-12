package io.quinio.transaction.service;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import io.quinio.transaction.model.GraphBean;
import io.quinio.transaction.model.ReportBean;
import io.quinio.transaction.repository.IReportRepository;

@SpringBootTest
@ActiveProfiles("test")
public class GraphServiceTest {
	@Autowired
	private IReportRepository repository;
	@Autowired
	private IGraphService service;
	
	@Test
	public void weekly() {
		Pageable paging = PageRequest.of(0, 2); 
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, 2021);
		start.set(Calendar.MONTH, Calendar.SEPTEMBER);
		start.set(Calendar.DATE, 1);
		start.set(Calendar.HOUR, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		
		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR, 2021);
		end.set(Calendar.MONTH, Calendar.OCTOBER);
		end.set(Calendar.DATE, 11);
		end.set(Calendar.HOUR, 11);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		end.set(Calendar.MILLISECOND, 999);
		
		Page<ReportBean> page = repository.findByRangeDate(start.getTime(), end.getTime(), paging);
		Assertions.assertTrue(page.hasContent());
		Assertions.assertEquals(2, page.getNumberOfElements());
		
		List<GraphBean> graph = service.weekly(page.getContent());
		GraphBean bean = graph.get(0);
		Assertions.assertEquals("10-10-2021",bean.getLabel());
		Assertions.assertEquals(6,bean.getValue());
	}
	

	@Test
	public void daily() {
		Pageable paging = PageRequest.of(0, 1); 
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, 2021);
		start.set(Calendar.MONTH, Calendar.OCTOBER);
		start.set(Calendar.DATE, 10);
		start.set(Calendar.HOUR, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		
		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR, 2021);
		end.set(Calendar.MONTH, Calendar.OCTOBER);
		end.set(Calendar.DATE, 10);
		end.set(Calendar.HOUR, 11);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		end.set(Calendar.MILLISECOND, 999);
		
		Page<ReportBean> page = repository.findByRangeDate(start.getTime(), end.getTime(), paging);
		Assertions.assertTrue(page.hasContent());
		Assertions.assertEquals(1, page.getNumberOfElements());
		
		List<GraphBean> graph = service.daily(page.getContent());
		Assertions.assertEquals(2, graph.size());
	}

}
