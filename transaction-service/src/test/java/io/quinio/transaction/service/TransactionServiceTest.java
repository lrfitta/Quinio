package io.quinio.transaction.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
	
	@Test
	public void generateReport() {
		ResponseBean response = service.generateReport();
		Assertions.assertNotNull(response);
		Assertions.assertEquals(ResultEnum.SUCCESS, response.getResult());
	} 
}
