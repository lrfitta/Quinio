package io.quinio.transaction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.quinio.transaction.model.TransactionBean;

/**
 * @author Luis Angel Rodriguez Fitta
 *Repositorio para la colleccion Transaction
 */
@Repository
@Transactional
public interface ITransactionRepository extends MongoRepository<TransactionBean, String> {
	
	/**
	 * FInd by id transaction
	 * @param idTransaction Id de la trasaccion
	 * @return
	 */
	public List<TransactionBean> findByIdTransaction(String idTransaction);
	
	/**
	 * Busca las transacciones diarias
	 * @param startDate Fecha de inicio
	 * @param endDate Fecha de fin
	 * @return Lista de transacciones en el rango
	 */
	@Query(value = "{createdAt: {$gte: ?0, $lte: ?1}, type: {$in: [\"bonus\",\"admin_bonus\"]}}")
	public List<TransactionBean> findGraphDaily(Date startDate, Date endDate);
	

}
