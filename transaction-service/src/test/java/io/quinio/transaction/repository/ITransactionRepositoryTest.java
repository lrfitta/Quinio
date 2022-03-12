package io.quinio.transaction.repository;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.quinio.transaction.model.TransactionBean;

/**
 * @author Luis Angel Rodriguez Fitta
 * Test ITransactionRepository
 */
@SpringBootTest
@ActiveProfiles("test")
public class ITransactionRepositoryTest {
	@Autowired
	private ITransactionRepository repository;
	
	@Test
	public void findGraphDaily() {
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, 2021);
		start.set(Calendar.MONTH, Calendar.OCTOBER);
		start.set(Calendar.DATE, 10);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		
		Calendar end = Calendar.getInstance();
		end.set(Calendar.YEAR, 2021);
		end.set(Calendar.MONTH, Calendar.OCTOBER);
		end.set(Calendar.DATE, 16);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		
		List<TransactionBean> data = repository.findGraphDaily(start.getTime(), end.getTime());
		Assertions.assertEquals(6, data.size());
	}
}
