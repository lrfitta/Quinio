package io.quinio.transaction.service;

import io.quinio.transaction.exception.QuinioException;

/**
 * @author Luis Angel Rodriguez Fitta
 * Servicio para el procesamiento de transacciones
 */
public interface ITransactionService {
	
	/**
	 * Metodo para recuperar las transacciones del servicio y guardarlas en la bd.
	 * @throws QuinioException
	 */
	public void recoverTransactions() throws QuinioException;

}
