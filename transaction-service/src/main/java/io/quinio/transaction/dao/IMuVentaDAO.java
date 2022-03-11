package io.quinio.transaction.dao;

import io.quinio.transaction.bean.ResponseBean;
import io.quinio.transaction.exception.QuinioException;

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
	public ResponseBean getTransactions(final Integer page, final Integer size) throws QuinioException;
}
