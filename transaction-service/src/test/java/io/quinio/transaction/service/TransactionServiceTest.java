package io.quinio.transaction.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.quinio.transaction.model.ReportRequestBean;
import io.quinio.transaction.model.ReportResponseBean;
import io.quinio.transaction.model.ResponseBean;
import io.quinio.transaction.openEnum.ResultEnum;

/**
 * @author Luis Angel Rodriguez Fitta
 * Test TransactionService
 */
@SpringBootTest
@ActiveProfiles("test")
public class TransactionServiceTest {
	@Autowired
	private ITransactionService service;
	
	/*@Test
	public void generateReport() {
		ResponseBean response = service.generateReport();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(ResultEnum.SUCCESS, response.getResult());
	}*/
	
	@Test
	public void getReport() {
		ReportRequestBean request = new ReportRequestBean();
		request.setPage(0);
		request.setSize(1);
		request.setStartDate("10-10-2021");
		request.setEndDate("10-10-2021");
		ReportResponseBean response = service.getReport(request);
		Assertions.assertEquals(ResultEnum.SUCCESS, response.getResult());
	}
}
