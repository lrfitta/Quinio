package io.quinio.transaction.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import io.quinio.transaction.service.ICalendarService;
import io.quinio.transaction.utils.Constants;
/**
 * @author Luis Angel Rodriguez Fitta
 *
 */
@Service
public class CalendarService implements ICalendarService {
	/**
	 * Logger
	 */
	private final static Logger LOGGER = LogManager.getLogger(CalendarService.class);
	/**
	 * Calcula el primer dia de la semana apartir de una fecha
	 * @param calendar  fecha de la que se quiere calcular el primer dia
	 * @return Primer dia de la semana
	 */
	@Override
	public Calendar getFirstDayOfWeek(Calendar calendar) {
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		Calendar firstDay = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		firstDay.setTime(calendar.getTime());
		firstDay.add(Calendar.DAY_OF_WEEK, (dayOfWeek - 1) * -1);
		firstDay.set(Calendar.HOUR_OF_DAY, 0);
		firstDay.set(Calendar.MINUTE, 0);
		firstDay.set(Calendar.SECOND, 0);
		firstDay.set(Calendar.MILLISECOND, 0);
		LOGGER.info("Fecha a calcular (getFirstDayOfWeek): " + calendar.getTime());
		LOGGER.info("Dia de inicio de la semana (getFirstDayOfWeek): " + firstDay.getTime());
		return firstDay;
	}
	/**
	 * Calcula la fecha de la semana pasada
	 * @param calendar Fecha para calcular
	 * @return Fecha de la semana pasada
	 */
	@Override
	public Calendar getLastWeek(Calendar calendar) {
		Calendar week = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		week.setTime(calendar.getTime());
		week.add(Calendar.DATE, -7);
		week.set(Calendar.HOUR_OF_DAY, 0);
		week.set(Calendar.MINUTE, 0);
		week.set(Calendar.SECOND, 0);
		week.set(Calendar.MILLISECOND, 0);
		LOGGER.info("Fecha a calcular (getLastWeek): " + calendar.getTime());
		LOGGER.info("Semana pasada (getLastWeek): " + week.getTime());
		return week;
	}
	
	/**
	 * Calcula el ultimo dia de la semana
	 * @param calendar Fecha para el calculo
	 * @return ultimo dia de la semana
	 */
	@Override
	public Calendar getEndDayOfWeek(Calendar calendar) {
		Calendar end = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		end.setTime(calendar.getTime());
		end.add(Calendar.DATE, 6);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		end.set(Calendar.MILLISECOND, 0);
		LOGGER.info("Fecha a calcular (getEndDayOfWeek): " + calendar.getTime());
		LOGGER.info("Semana pasada (getEndDayOfWeek): " + end.getTime());
		return end;
	}
	/**
	 * Crea un calendar con un timezone
	 * @param date Date a transformar
	 * @return
	 */
	@Override
	public Calendar createCalendarTimeZone(Date date) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Constants.DEFAULT_TIME_ZONE));
		calendar.setTime(date);
		return calendar;
	}
	/**
	 * Setea la hora 00:00:00
	 * @param calendar Calendar a modificar
	 */
	@Override
	public void setTimeZero(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}
	/**
	 * Setea la hora 23:59:59
	 * @param calendar Calendar a modificar
	 */
	@Override
	public void setTimeEndDate(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
	}
}
