package util;

/**
 * Exception to be thrown when the modulus is not valid.
 * 
 * @author Ashkan Moatamed
 */
public class InvalidModulusException extends ArithmeticException {
	/**
	 * No dependencies.
	 */

	/**
	 * Eclipse automatically generated serial version UID.
	 */
	private static final long serialVersionUID = -939344239010002957L;

	/**
	 * Constructs an <code>InvalidModulusException</code> with no detail message.
	 */
	public InvalidModulusException() {
		super();
	}

	/**
	 * Constructs an <code>InvalidModulusException</code> with the specified detail message.
	 *
	 * @param s
	 *            the detail message
	 */
	public InvalidModulusException(String s) {
		super(s);
	}
}
