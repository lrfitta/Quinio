package io.quinio.transaction.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.quinio.transaction.dao.impl.MuVentaDAO;
import io.quinio.transaction.model.ReportRequestBean;
import io.quinio.transaction.model.ReportResponseBean;
import io.quinio.transaction.model.ResponseBean;
import io.quinio.transaction.openEnum.ResultEnum;
import io.quinio.transaction.service.impl.CalendarService;
import io.quinio.transaction.service.impl.GraphService;
import io.quinio.transaction.service.impl.TransactionService;
import io.quinio.transaction.util.Util;

/**
 * @author Luis Angel Rodriguez Fitta
 * Test TransactionService
 */
@ActiveProfiles("test")
@DataMongoTest(includeFilters = @ComponentScan.Filter(Component.class))
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MuVentaDAO.class, CalendarService.class, TransactionService.class, GraphService.class})
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"io.quinio"})
public class TransactionServiceTest {
	@Autowired
	private ITransactionService service;
	@Autowired 
	MongoTemplate mongoTemplate;
	
	@Test
	public void generateReport() {
		ResponseBean response = service.generateReport();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(ResultEnum.SUCCESS, response.getResult());
	}
	
	@Test
	public void getReport() throws JsonMappingException, JsonProcessingException {
		Util util = new Util();
		util.loadReport(mongoTemplate);
		util.loadTransaction(mongoTemplate);
		ReportRequestBean request = new ReportRequestBean();
		request.setPage(0);
		request.setSize(1);
		request.setStartDate("10-10-2021");
		request.setEndDate("10-10-2021");
		ReportResponseBean response = service.getReport(request);
		Assertions.assertEquals(ResultEnum.SUCCESS, response.getResult());
	}
}
