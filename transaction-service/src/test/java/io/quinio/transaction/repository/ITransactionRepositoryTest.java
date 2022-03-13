package io.quinio.transaction.repository;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.quinio.transaction.dao.impl.MuVentaDAO;
import io.quinio.transaction.model.TransactionBean;
import io.quinio.transaction.service.impl.CalendarService;
import io.quinio.transaction.service.impl.GraphService;
import io.quinio.transaction.service.impl.TransactionService;
import io.quinio.transaction.util.Util;

/**
 * @author Luis Angel Rodriguez Fitta
 * Test ITransactionRepository
 */
@DataMongoTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MuVentaDAO.class, CalendarService.class, TransactionService.class, GraphService.class})
@EnableAutoConfiguration
@ActiveProfiles("test")
public class ITransactionRepositoryTest {
	@Autowired
	private ITransactionRepository repository;
	@Autowired 
	MongoTemplate mongoTemplate;
	
	@Test
	public void findGraphDaily() throws JsonMappingException, JsonProcessingException {
		Util util = new Util();
		util.loadTransaction(mongoTemplate);
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
