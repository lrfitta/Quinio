package io.quinio.transaction.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
	public void recoverTransactions() {
		Assertions.assertDoesNotThrow(() -> {
			service.recoverTransactions();
		});
	} 
}
