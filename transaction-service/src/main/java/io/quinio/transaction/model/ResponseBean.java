package io.quinio.transaction.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.quinio.transaction.openEnum.CodeErrorEnum;
import io.quinio.transaction.openEnum.ResultEnum;
import lombok.Data;

/**
 * @author Luis Angel Rodriguez Fitta
 * Response de los servicios
 */
@Data
@JsonInclude(Include.NON_NULL)
public class ResponseBean {
	/**
	 * Resultado de la oepracion
	 */
	private ResultEnum result;
	/**
	 * Descripcion del error
	 */
	private String description;
	/**
	 * Codigo de error
	 */
	private CodeErrorEnum codeError;
}
