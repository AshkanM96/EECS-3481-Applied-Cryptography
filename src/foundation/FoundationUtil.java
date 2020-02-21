package foundation;

/**
 * Utility class used in other classes in the foundation package.
 * 
 * @author Ashkan Moatamed
 */
public class FoundationUtil {
	/**
	 * No dependencies.
	 */

	/**
	 * Plaintext file name suffix used for the output file name of an exhaustive decryption use case.
	 */
	public static final String PROBABLE_EXHAUSTIVE = "probable_exhaustive";

	/**
	 * Plaintext file name suffix used for the output file name of a cryptanalytic decryption use case.
	 */
	public static final String PROBABLE_CRYPTA = "probable_crypta";

	/**
	 * Plaintext file extension.
	 */
	public static final String PLAINTEXT_EXTENSION = "pt";

	/**
	 * Ciphertext file extension.
	 */
	public static final String CIPHERTEXT_EXTENSION = "ct";

	/**
	 * Relative path to data directory.
	 */
	public static final String DATA_DIRECTORY = "./src/data/";

	/**
	 * Relative path to data.in directory.
	 */
	public static final String DATA_IN_DIRECTORY = FoundationUtil.DATA_DIRECTORY + "in/";

	/**
	 * Relative path to data.out directory.
	 */
	public static final String DATA_OUT_DIRECTORY = FoundationUtil.DATA_DIRECTORY + "out/";

	/**
	 * Prevent instantiation.
	 */
	private FoundationUtil() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	/**
	 * Get the output file name of either an exhaustive or a cryptanalytic decryption algorithm.
	 * 
	 * @param filename
	 *            the given file name
	 * 
	 * @param exhaustive
	 *            specifies whether the decryption is exhaustive or cryptanalytic
	 * 
	 * @return The resulting output file name.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 */
	public static String outName(String filename, boolean exhaustive) throws NullPointerException {
		if (filename == null) {
			throw new NullPointerException();
		}

		final StringBuilder sb = new StringBuilder(filename);
		sb.append('_').append(exhaustive ? FoundationUtil.PROBABLE_EXHAUSTIVE : FoundationUtil.PROBABLE_CRYPTA);
		return sb.toString();
	}

	/**
	 * @param filename
	 *            the given file name
	 * 
	 * @return <code>FoundationConstants.outName(filename, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 */
	public static String outNameExhaustive(String filename) throws NullPointerException {
		return FoundationUtil.outName(filename, true);
	}

	/**
	 * @param filename
	 *            the given file name
	 * 
	 * @return <code>FoundationConstants.outName(filename, false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 */
	public static String outNameCryptanalytic(String filename) throws NullPointerException {
		return FoundationUtil.outName(filename, false);
	}

	/**
	 * Get the relative path to the file with the given name in the data directory.
	 * 
	 * @param filename
	 *            the given file name
	 * 
	 * @param input
	 *            specifies whether the file is an input file or an output file
	 * 
	 * @param plaintext
	 *            specifies whether the file is a plaintext or a ciphertext
	 * 
	 * @return The resulting relative file path.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 */
	public static String filePath(String filename, boolean input, boolean plaintext) throws NullPointerException {
		if (filename == null) {
			throw new NullPointerException();
		}

		final StringBuilder sb = new StringBuilder(
				input ? FoundationUtil.DATA_IN_DIRECTORY : FoundationUtil.DATA_OUT_DIRECTORY);
		sb.append(filename).append('.')
				.append(plaintext ? FoundationUtil.PLAINTEXT_EXTENSION : FoundationUtil.CIPHERTEXT_EXTENSION);
		return sb.toString();
	}

	/**
	 * @param filename
	 *            the given file name
	 * 
	 * @param input
	 *            specifies whether the file is an input file or an output file
	 * 
	 * @return <code>FoundationConstants.filePath(filename, input, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 */
	public static String plainPath(String filename, boolean input) throws NullPointerException {
		return FoundationUtil.filePath(filename, input, true);
	}

	/**
	 * @param filename
	 *            the given file name
	 * 
	 * @return <code>FoundationConstants.plainPath(filename, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 */
	public static String inPlainPath(String filename) throws NullPointerException {
		return FoundationUtil.plainPath(filename, true);
	}

	/**
	 * @param filename
	 *            the given file name
	 * 
	 * @return <code>FoundationConstants.plainPath(filename, false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 */
	public static String outPlainPath(String filename) throws NullPointerException {
		return FoundationUtil.plainPath(filename, false);
	}

	/**
	 * @param filename
	 *            the given file name
	 * 
	 * @param input
	 *            specifies whether the file is an input file or an output file
	 * 
	 * @return <code>FoundationConstants.filePath(filename, input, false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 */
	public static String cipherPath(String filename, boolean input) throws NullPointerException {
		return FoundationUtil.filePath(filename, input, false);
	}

	/**
	 * @param filename
	 *            the given file name
	 * 
	 * @return <code>FoundationConstants.cipherPath(filename, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 */
	public static String inCipherPath(String filename) throws NullPointerException {
		return FoundationUtil.cipherPath(filename, true);
	}

	/**
	 * @param filename
	 *            the given file name
	 * 
	 * @return <code>FoundationConstants.cipherPath(filename, false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 */
	public static String outCipherPath(String filename) throws NullPointerException {
		return FoundationUtil.cipherPath(filename, false);
	}
}
