package io.quinio.transaction.openEnum;

/**
 * @author Luis Angel Rodriguez Fitta
 * Codigos de error
 */
public enum CodeErrorEnum {
	SANDBOX("001"),
	NO_DATA_REPORT("002");
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
