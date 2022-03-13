package io.quinio.transaction.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quinio.transaction.model.ReportBean;
import io.quinio.transaction.model.TransactionBean;
import io.quinio.transaction.utils.Constants;

public class Util {

	public void loadReport(MongoTemplate mongotemplate) throws JsonMappingException, JsonProcessingException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Report.json");

		StringBuilder textBuilder = new StringBuilder();
		try (Reader reader = new BufferedReader(
				new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
			int c = 0;
			while ((c = reader.read()) != -1) {
				textBuilder.append((char) c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		ReportBean[] arreglo = mapper.readValue(textBuilder.toString(), ReportBean[].class);
		List<ReportBean> reports = Arrays.asList(arreglo);
		mongotemplate.createCollection("Report");
		reports.forEach(report -> {
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
			calendar.setTime(report.getStartWeek());
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			report.setStartWeek(calendar.getTime());
			mongotemplate.save(report, "Report");
		});
	}
	
	public void loadTransaction(MongoTemplate mongotemplate) throws JsonMappingException, JsonProcessingException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Transaction.json");

		StringBuilder textBuilder = new StringBuilder();
		try (Reader reader = new BufferedReader(
				new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
			int c = 0;
			while ((c = reader.read()) != -1) {
				textBuilder.append((char) c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(textBuilder);
		ObjectMapper mapper = new ObjectMapper();
		TransactionBean[] arreglo = mapper.readValue(textBuilder.toString(), TransactionBean[].class);
		List<TransactionBean> transactions = Arrays.asList(arreglo);
		mongotemplate.createCollection("Transaction");
		transactions.forEach(transaction -> {
			mongotemplate.save(transaction, "Transaction");
		});
	}

}
