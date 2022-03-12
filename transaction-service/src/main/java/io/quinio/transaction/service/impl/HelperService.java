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
		LOGGER.info("Fecha a calcular: " + calendar.getTime());
		LOGGER.info("Dia de inicio de la semana: " + firstDay.getTime());
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
		LOGGER.info("Fecha a calcular: " + calendar.getTime());
		LOGGER.info("Semana pasada: " + week.getTime());
		return week;
	}
	
	

}
