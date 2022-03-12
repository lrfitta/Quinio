package io.quinio.transaction.dao;

import io.quinio.transaction.exception.QuinioException;
import io.quinio.transaction.model.MuVentaTransactionResponseBean;

/**
 * @author Luis Angel Rodriguez Fitta
 * DAO para el consumo del sandbox de muventa.
 */
public interface IMuVentaDAO {

	/**
	 * Metodo para consumir el endPoint orders/v1/loyalty/bulk/export/transactions
	 * @param page NUmero de la pagina
	 * @param size Numero de registros por pagina
	 */
	public MuVentaTransactionResponseBean getTransactions(final Integer page, final Integer size) throws QuinioException;
}
