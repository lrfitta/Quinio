package io.quinio.transaction.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.quinio.transaction.bean.ResponseBean;
import io.quinio.transaction.bean.TransactionBean;
import io.quinio.transaction.dao.IMuVentaDAO;
import io.quinio.transaction.exception.QuinioException;
import io.quinio.transaction.repository.ITransactionRepository;
import io.quinio.transaction.service.ITransactionService;
import io.quinio.transaction.utils.Constants;

/**
 * @author Luis Angel Rodriguez Fitta
 * Implementacion de la interfaz ITransactionService
 */
@Service
public class TransactionService implements ITransactionService {
	/**
	 * Logger
	 */
	private final static Logger LOGGER = LogManager.getLogger(TransactionService.class);
	
	@Autowired
	private ITransactionRepository transactionrepository;
	@Autowired
	private IMuVentaDAO muVentaDao;
	
	@Override
	public void recoverTransactions() throws QuinioException {
		int page = 0;
		boolean flag = true;
		ResponseBean response = null;
		LOGGER.info("Se obtiene la informacion y se guarda en mongo");
		while(flag) {
			response = muVentaTransactions(page);
			flag = !response.getLast();
			page += 1;
		}
		LOGGER.info("Finaliza la obtencion de informacion");
	}
	
	private ResponseBean muVentaTransactions(final int page) throws QuinioException {
		ResponseBean response = muVentaDao.getTransactions(page, Constants.DEFAULT_NUMBER_OF_ROWS_TRANSACTION);
		if(response != null && !response.getEmpty()) {
			List<TransactionBean> transactions = response.getTransactions();
			transactions.forEach(transaction -> {
				List<TransactionBean> tmp = transactionrepository.findByIdTransaction(transaction.getIdTransaction());
				if(tmp.isEmpty()) {
					transactionrepository.save(transaction);
				}
			});
		}
		return response;
	}

}
