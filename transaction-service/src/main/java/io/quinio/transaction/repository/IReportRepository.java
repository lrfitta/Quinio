package io.quinio.transaction.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.quinio.transaction.model.ReportBean;

/**
 * @author Luis Angel Rodriguez Fitta
 * Repositorio para la colleccion Report
 */
@Repository
@Transactional
public interface IReportRepository extends MongoRepository<ReportBean, String> {
	
	/**
	 * Busqueda por inicio de semana
	 * @param startWeek fecha de busqueda
	 * @return
	 */
	public List<ReportBean> findByStartWeek(Date startWeek);
	
	/**
	 * Busqueda por numero de semana
	 * @param numberWeek Numero de semana a buscar
	 * @return 
	 */
	public List<ReportBean> findByNumberWeek(Integer numberWeek);
	
	/**
	 * @param skip Registros a saltar
	 * @param limit Numero de registros
	 * @return Lista de reporte
	 */
	@Query(sort = "{startWeek:-1}", value = "{}")
	public Page<ReportBean> findAll(Pageable page);
}
