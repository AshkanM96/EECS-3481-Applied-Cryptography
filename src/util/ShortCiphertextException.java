package util;

/**
 * Exception to be thrown when the length of a ciphertext is less than the allowed minimum.
 * 
 * @author Ashkan Moatamed
 */
public class ShortCiphertextException extends IllegalArgumentException {
	/**
	 * No dependencies.
	 */

	/**
	 * Eclipse automatically generated serial version UID.
	 */
	private static final long serialVersionUID = 7271456426897173625L;

	/**
	 * Constructs a <code>ShortCiphertextException</code> with no detail message.
	 */
	public ShortCiphertextException() {
		super();
	}

	/**
	 * Constructs a <code>ShortCiphertextException</code> with the specified detail message.
	 *
	 * @param s
	 *            the detail message
	 */
	public ShortCiphertextException(String s) {
		super(s);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 *
	 * <p>
	 * Note that the detail message associated with <code>cause</code> is <i>not</i> automatically
	 * incorporated in this exception's detail message.
	 * </p>
	 *
	 * @param message
	 *            the detail message (which is saved for later retrieval by the
	 *            {@link Throwable#getMessage()} method)
	 * 
	 * @param cause
	 *            the cause (which is saved for later retrieval by the {@link Throwable#getCause()}
	 *            method. <code>null</code> value is permitted and indicates that the cause is
	 *            nonexistent or unknown.)
	 */
	public ShortCiphertextException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message of
	 * <code>(cause == null ? null : cause.toString())</code> (which typically contains the class and
	 * detail message of <code>cause</code>). This constructor is useful for exceptions that are little
	 * more than wrappers for other Throwable objects (for example,
	 * {@link java.security.PrivilegedActionException}).
	 *
	 * @param cause
	 *            the cause (which is saved for later retrieval by the {@link Throwable#getCause()}
	 *            method. <code>null</code> value is permitted and indicates that the cause is
	 *            nonexistent or unknown.)
	 */
	public ShortCiphertextException(Throwable cause) {
		super(cause);
	}
}
