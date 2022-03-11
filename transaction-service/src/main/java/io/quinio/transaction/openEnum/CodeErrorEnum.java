package io.quinio.transaction.openEnum;

/**
 * @author Luis Angel Rodriguez Fitta
 * Codigos de error
 */
public enum CodeErrorEnum {
	SANDBOX("001");
	/**
	 * Codigo de error
	 */
	private String code;
	CodeErrorEnum(final String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
}
