package io.quinio.transaction;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.quinio.transaction.model.GenerateReportResponseBean;
import io.quinio.transaction.model.ReportRequestBean;
import io.quinio.transaction.model.ReportResponseBean;
import io.quinio.transaction.service.ITransactionService;

@SpringBootApplication
public class TransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}
	
	@Autowired
	private ITransactionService transactionService;
	
	/**
	 * Lambda function
	 * @return
	 */
	@Bean
    public Supplier<GenerateReportResponseBean> generateReport() {
        return () -> {
            return transactionService.generateReport();
        };
    }
	
	@Bean
	public Function<ReportRequestBean, ReportResponseBean> getReport() {
		return value -> {
			return transactionService.getReport(value);
		};
	}
	
}
