package io.quinio.transaction;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.quinio.transaction.model.ReportRequestBean;

/**
 * @author Luis Angel Rodriguez Fitta
 * test para lambda aws
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LambdaTest {
	@Autowired
    private MockMvc mockMvc;
	
	@Test
    public void getReport() throws Exception {
		ReportRequestBean request = new ReportRequestBean();
		request.setPage(1);
		request.setSize(5);

		mockMvc.perform(MockMvcRequestBuilders.post("/getReport")
					.content(new ObjectMapper().writeValueAsString(request))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpectAll(status().is2xxSuccessful());
    }
}
