package io.quinio.transaction.exception;

import io.quinio.transaction.openEnum.CodeErrorEnum;

/**
 * @author Luis Angel Rodriguez Fitta
 * Excepcion personalizada
 */
public class QuinioException extends Exception {
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Codigo de error
	 */
	private CodeErrorEnum code;
	
	/**
	 * @param message mensaje de error
	 * @param code Codigo de error
	 */
	public QuinioException(String message, CodeErrorEnum code) {
        super(message);
        this.code = code;
    }
	
    /**
     * Getter para obtener el codigo de error
     * 
     * @return Codigo de error
     */
    public CodeErrorEnum getCode() {
        return this.code;
    }
}
