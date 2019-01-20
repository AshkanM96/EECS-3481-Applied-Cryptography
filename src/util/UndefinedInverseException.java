package util;

/**
 * Exception to be thrown when an integer does not have an inverse.
 * 
 * @author Ashkan Moatamed
 */
public class UndefinedInverseException extends ArithmeticException {
	/**
	 * No dependencies.
	 */

	/**
	 * Eclipse automatically generated serial version UID.
	 */
	private static final long serialVersionUID = 4787475305008371946L;

	/**
	 * Constructs an <code>UndefinedInverseException</code> with no detail message.
	 */
	public UndefinedInverseException() {
		super();
	}

	/**
	 * Constructs an <code>UndefinedInverseException</code> with the specified detail message.
	 *
	 * @param s
	 *            the detail message
	 */
	public UndefinedInverseException(String s) {
		super(s);
	}
}
