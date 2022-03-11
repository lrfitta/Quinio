package io.quinio.transaction.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import io.quinio.transaction.bean.ResponseBean;

/**
 * @author Luis Angel Rodriguez Fitta
 * Test class MuVentaDAO
 */
@SpringBootTest
@ActiveProfiles("test")
public class MuVentaDAOTest {
	
	@Autowired
	private IMuVentaDAO dao;
	
	@Test
	public void getTransactions() {
		Assertions.assertDoesNotThrow(() -> {
			ResponseBean response =  dao.getTransactions(0, 20);
			Assertions.assertNotNull(response);
		});
	}

}
