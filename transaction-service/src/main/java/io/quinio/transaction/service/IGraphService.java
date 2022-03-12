package io.quinio.transaction.service;

import java.util.List;

import io.quinio.transaction.model.GraphBean;
import io.quinio.transaction.model.ReportBean;

/**
 * @author Luis Angel Rodriguez Fitta
 * Servicio para la creacion de graficas
 */
public interface IGraphService {
	
	/**
	 * Genera la informacion para la creacion de la grafica semanalmente
	 * @param data Informacion para generar el reporte
	 * @return Map con la informaicon de la grafica
	 */
	public List<GraphBean> weekly(final  List<ReportBean> data);
	
	/**
	 * Genera la informacion para la creacion de la grafica diariamente
	 * @param data Informacion para generar el reporte
	 * @return Map con la informaicon de la grafica
	 */
	public List<GraphBean> daily(final  List<ReportBean> data);
}
