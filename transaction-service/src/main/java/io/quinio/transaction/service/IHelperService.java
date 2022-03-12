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
	
	/**
	 * Calcula el ultimo dia de la semana
	 * @param calendar Fecha para el calculo
	 * @return ultimo dia de la semana
	 */
	public Calendar getEndDayOfWeek(final Calendar calendar);
	
	/**
	 * @param calendar Calendario a setear el horario
	 * @return Calendar con hora zero
	 */
	public Calendar setTimeZero(final Calendar calendar);
}
