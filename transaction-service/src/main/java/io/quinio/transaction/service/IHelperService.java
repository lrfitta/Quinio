package io.quinio.transaction.service;

import java.util.Calendar;

/**
 * @author Luis Angel Rodriguez Fitta
 * Servicio de utilerias
 */
public interface IHelperService {
	/**
	 * Calcula el primer dia de la semana apartir de una fecha
	 * @param calendar  fecha de la que se quiere calcular el primer dia
	 * @return Primer dia de la semana
	 */
	public Calendar getFirstDayOfWeek(final Calendar calendar);
	
	/**
	 * Calcula la fecha de la semana pasada
	 * @param calendar Fecha para calcular
	 * @return Fecha de la semana pasada
	 */
	public Calendar getLastWeek(final Calendar calendar);
}
