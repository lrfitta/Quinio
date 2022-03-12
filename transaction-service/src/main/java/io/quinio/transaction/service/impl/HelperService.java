package io.quinio.transaction.service.impl;

import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import io.quinio.transaction.service.IHelperService;

/**
 * @author Luis Angel Rodriguez Fitta
 *
 */
@Service
public class HelperService implements IHelperService {
	/**
	 * Logger
	 */
	private final static Logger LOGGER = LogManager.getLogger(HelperService.class);
	/**
	 * Calcula el primer dia de la semana apartir de una fecha
	 * @param calendar  fecha de la que se quiere calcular el primer dia
	 * @return Primer dia de la semana
	 */
	@Override
	public Calendar getFirstDayOfWeek(Calendar calendar) {
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		Calendar firstDay = Calendar.getInstance();
		firstDay.setTime(calendar.getTime());
		firstDay.add(Calendar.DAY_OF_WEEK, (dayOfWeek - 1) * -1);
		firstDay.set(Calendar.HOUR, 0);
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
		Calendar week = Calendar.getInstance();
		week.setTime(calendar.getTime());
		week.add(Calendar.DATE, -7);
		week.set(Calendar.HOUR, 0);
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
		Calendar end = Calendar.getInstance();
		end.setTime(calendar.getTime());
		end.add(Calendar.DATE, 6);
		end.set(Calendar.HOUR, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		end.set(Calendar.MILLISECOND, 999);
		LOGGER.info("Fecha a calcular (getEndDayOfWeek): " + calendar.getTime());
		LOGGER.info("Semana pasada (getEndDayOfWeek): " + end.getTime());
		return end;
	}

	/**
	 * @param calendar Calendario a setear el horario
	 * @return Calendar con hora zero
	 */
	@Override
	public Calendar setTimeZero(Calendar calendar) {
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}
	
	

}
