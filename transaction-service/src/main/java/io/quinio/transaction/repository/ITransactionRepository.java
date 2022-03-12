package io.quinio.transaction.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
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
}
