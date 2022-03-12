package io.quinio.transaction.service;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Luis Angel Rodriguez Fitta
 * Servicio de utilerias
 */
public interface ICalendarService {
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
	 * Crea un calendar con un timezone
	 * @param date Date a transformar
	 * @return
	 */
	public Calendar createCalendarTimeZone(Date date);
	
	/**
	 * Setea la hora 00:00:00
	 * @param calendar Calendar a modificar
	 */
	public void setTimeZero(Calendar calendar);
	
	/**
	 * Setea la hora 23:59:59
	 * @param calendar Calendar a modificar
	 */
	public void setTimeEndDate(Calendar calendar);
}
