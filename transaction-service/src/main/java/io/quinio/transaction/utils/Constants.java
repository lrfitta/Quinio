package io.quinio.transaction.utils;

/**
 * @author Luis Angel Rodriguez Fitta
 * Int
 */
/**
 * @author Luis Angel Rodriguez Fitta
 *
 */
public interface Constants {
	/**
	 * Numero default del numero de registros por pagina para la busqueda de transacciones
	 */
	Integer DEFAULT_NUMBER_OF_ROWS_TRANSACTION = 50;
	/**
	 * Numero default del numero de registros por pagina para la busqueda de transacciones
	 */
	Integer DEFAULT_NUMBER_OF_ROWS_REPORT = 20;
	/**
	 * Header X-Escale-Details
	 */
	String HEADER_ESCALE_DETAILS = "X-Escale-Details";
	/**
	 * Tipo de bonficacion bonus
	 */
	String TYPE_BONUS = "bonus";
	/**
	 * Tipo de bonificacion admin bonus
	 */
	String TYPE_BONUS_ADMIN = "admin_bonus";
	
	/**
	 * Tipo bonificacion redencion
	 */
	String TYPE_REDEEM = "redeem";
	/**
	 * Tipo bonificacion expiracion
	 */
	String TYPE_EXPIRATION = "expiration";
	/**
	 * Formato de fecha para la geenracion de graficas
	 */
	String FORMAT_DATE_GRAPH = "dd-MM-yyyy";
	/**
	 * Time zone default
	 */
	String DEFAULT_TIME_ZONE = "UTC";
}
