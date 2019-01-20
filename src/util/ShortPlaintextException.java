package util;

/**
 * Exception to be thrown when the length of a plaintext is less than the allowed minimum.
 * 
 * @author Ashkan Moatamed
 */
public class ShortPlaintextException extends IllegalArgumentException {
	/**
	 * No dependencies.
	 */

	/**
	 * Eclipse automatically generated serial version UID.
	 */
	private static final long serialVersionUID = -649384502783977549L;

	/**
	 * Constructs a <code>ShortPlaintextException</code> with no detail message.
	 */
	public ShortPlaintextException() {
		super();
	}

	/**
	 * Constructs a <code>ShortPlaintextException</code> with the specified detail message.
	 *
	 * @param s
	 *            the detail message
	 */
	public ShortPlaintextException(String s) {
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
	public ShortPlaintextException(String message, Throwable cause) {
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
	public ShortPlaintextException(Throwable cause) {
		super(cause);
	}
}
