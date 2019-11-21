package util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Utility methods useful for cryptography.
 * 
 * @author Professor Hamzeh Roumani
 * @author Ashkan Moatamed
 */
public class CryptoTools {
	/**
	 * No dependencies.
	 */

	/**
	 * Complement of <code>32</code>.
	 */
	public static final int COMPLEMENT_OF_32 = ~32;

	/**
	 * The number of letters in the English alphabet.
	 */
	public static final int ENGLISH_ALPHABET_SIZE = 26;

	/**
	 * Probability of <code>i<sup>th</sup></code> English character is stored at
	 * <code>CryptoTools.ENGLISH_LETTER_PROBABILITY[i - 1]</code>.
	 */
	protected static final double[] ENGLISH_LETTER_PROBABILITY = { 8.12, 1.49, 2.71, 4.32, 12.02, 2.3, 2.03, 5.92, 7.31,
			0.1, 0.69, 3.98, 2.61, 6.95, 7.68, 1.82, 0.11, 6.02, 6.28, 9.1, 2.88, 1.11, 2.09, 0.17, 2.11, 0.07 };
	static {
		assert (CryptoTools.ENGLISH_ALPHABET_SIZE == CryptoTools.ENGLISH_LETTER_PROBABILITY.length);
	}

	/**
	 * Lower bound for English Index of Coincidence.
	 */
	public static final double ENGLISH_IC_LOWER = 6.4;

	/**
	 * Upper bound for English Index of Coincidence.
	 */
	public static final double ENGLISH_IC_UPPER = 7.1;

	/**
	 * Prevent instantiation.
	 */
	private CryptoTools() {
		// Empty by design.
	}

	/**
	 * @return A shallow copy of <code>CryptoTools.ENGLISH_LETTER_PROBABILITY</code>.
	 */
	public static double[] english() {
		final double[] result = new double[CryptoTools.ENGLISH_ALPHABET_SIZE];
		System.arraycopy(CryptoTools.ENGLISH_LETTER_PROBABILITY, 0, result, 0, result.length);
		return result;
	}

	/**
	 * @param i
	 *            the given index
	 * 
	 * @return <code>CryptoTools.ENGLISH_LETTER_PROBABILITY[i]</code>.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(i < 0) || (CryptoTools.ENGLISH_LETTER_PROBABILITY.length <= i)</code>
	 */
	public static double english(int i) throws IndexOutOfBoundsException {
		return CryptoTools.ENGLISH_LETTER_PROBABILITY[i];
	}

	/**
	 * @param IC
	 *            the given Index of Coincidence
	 * 
	 * @return <code>(Double.compare(CryptoTools.ENGLISH_IC_LOWER, IC) <= 0) && (Double.compare(IC, CryptoTools.ENGLISH_IC_UPPER) <= 0)</code>.
	 */
	public static boolean isEnglishIC(double IC) {
		return ((Double.compare(CryptoTools.ENGLISH_IC_LOWER, IC) <= 0)
				&& (Double.compare(IC, CryptoTools.ENGLISH_IC_UPPER) <= 0));
	}

	/**
	 * Cast every element of the given char array to byte and return it as a byte array.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static byte[] toByteArray(char[] data) throws NullPointerException {
		final byte[] result = new byte[data.length];
		for (int i = 0; i != data.length; ++i) {
			result[i] = (byte) data[i];
		}
		return result;
	}

	/**
	 * Cast every element of the given int array to byte and return it as a byte array.
	 * 
	 * @param data
	 *            the given int array
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static byte[] toByteArray(int[] data) throws NullPointerException {
		final byte[] result = new byte[data.length];
		for (int i = 0; i != data.length; ++i) {
			result[i] = (byte) data[i];
		}
		return result;
	}

	/**
	 * Cast every element of the given byte array to char and return it as a char array.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static char[] toCharArray(byte[] data) throws NullPointerException {
		final char[] result = new char[data.length];
		for (int i = 0; i != data.length; ++i) {
			result[i] = (char) data[i];
		}
		return result;
	}

	/**
	 * Cast every element of the given int array to char and return it as a char array.
	 * 
	 * @param data
	 *            the given int array
	 * 
	 * @return The resulting char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static char[] toCharArray(int[] data) throws NullPointerException {
		final char[] result = new char[data.length];
		for (int i = 0; i != data.length; ++i) {
			result[i] = (char) data[i];
		}
		return result;
	}

	/**
	 * Cast every element of the given byte array to int and return it as an int array.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting int array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int[] toIntArray(byte[] data) throws NullPointerException {
		final int[] result = new int[data.length];
		for (int i = 0; i != data.length; ++i) {
			result[i] = data[i];
		}
		return result;
	}

	/**
	 * Cast every element of the given char array to int and return it as an int array.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting int array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int[] toIntArray(char[] data) throws NullPointerException {
		final int[] result = new int[data.length];
		for (int i = 0; i != data.length; ++i) {
			result[i] = data[i];
		}
		return result;
	}

	/**
	 * @param data
	 *            the given byte array
	 * 
	 * @return <code>(data == null) ? "null" : new String(data)</code>.
	 */
	public static String toString(byte[] data) {
		return ((data == null) ? "null" : new String(data));
	}

	/**
	 * @param data
	 *            the given char array
	 * 
	 * @return <code>(data == null) ? "null" : new String(data)</code>.
	 */
	public static String toString(char[] data) {
		return ((data == null) ? "null" : new String(data));
	}

	/**
	 * @param c
	 *            the given char
	 * 
	 * @return <code>true</code> if and only if the given char is ASCII printable.
	 */
	public static boolean isASCIIPrintable(char c) {
		return ((31 < c) && (c < 127));
	}

	/**
	 * @param s
	 *            the given string
	 * 
	 * @return <code>true</code> if and only if the given string is ASCII printable.
	 */
	public static boolean isASCIIPrintable(String s) {
		if (s == null) {
			return true;
		}

		final int length = s.length();
		for (int i = 0; i != length; ++i) {
			if (!CryptoTools.isASCIIPrintable(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Print the given string to the given print stream if it is ASCII printable.
	 * 
	 * <pre>
	 * <code>
	 * if (CryptoTools.isASCIIPrintable(s)) {
	 * 	ps.print(s);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @param ps
	 *            the given print stream
	 * 
	 * @throws NullPointerException
	 *             If <code>CryptoTools.isASCIIPrintable(s) && (ps == null)</code>
	 */
	public static void printASCII(String s, PrintStream ps) throws NullPointerException {
		if (CryptoTools.isASCIIPrintable(s)) {
			ps.print(s);
		}
	}

	/**
	 * Print the given string to the given print stream if it is ASCII printable.
	 * 
	 * <pre>
	 * <code>
	 * ps.println(CryptoTools.isASCIIPrintable(s) ? s : "");
	 * </code>
	 * </pre>
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @param ps
	 *            the given print stream
	 * 
	 * @throws NullPointerException
	 *             If <code>ps == null</code>
	 */
	public static void printlnASCII(String s, PrintStream ps) throws NullPointerException {
		ps.println(CryptoTools.isASCIIPrintable(s) ? s : "");
	}

	/**
	 * @param c
	 *            the given char
	 * 
	 * @return <code>('A' <= c) && (c <= 'Z')</code>
	 */
	public static boolean isUpperEnglish(int c) {
		return (('A' <= c) && (c <= 'Z'));
	}

	/**
	 * @param c
	 *            the given char
	 * 
	 * @return <code>c</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!CryptoTools.isUpperEnglish(c)</code>
	 */
	public static int upperEnglish(int c) throws IllegalArgumentException {
		if (!CryptoTools.isUpperEnglish(c)) {
			throw new IllegalArgumentException();
		}
		return c;
	}

	/**
	 * Return a new byte array with only the letter entries of the given byte array converted to
	 * uppercase.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static byte[] clean(byte[] data) throws NullPointerException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int i = 0, c = 0; i != data.length; ++i) {
			c = data[i] & CryptoTools.COMPLEMENT_OF_32;
			if (CryptoTools.isUpperEnglish(c)) {
				baos.write(c);
			}
		}
		return baos.toByteArray();
	}

	/**
	 * Return a new char array with only the letter entries of the given char array converted to
	 * uppercase.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static char[] clean(char[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder();
		char c = '\0';
		for (int i = 0; i != data.length; ++i) {
			c = Character.toUpperCase(data[i]);
			if (CryptoTools.isUpperEnglish(c)) {
				sb.append(c);
			}
		}
		return sb.toString().toCharArray();
	}

	/**
	 * Compute the frequencies (as counts) of each letter in the given byte array. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting frequency array.
	 */
	protected static int[] getFrequenciesFixedInput(byte[] data) {
		final int[] freq = new int[CryptoTools.ENGLISH_ALPHABET_SIZE];
		for (int i = 0; i != data.length; ++i) {
			++freq[data[i] - 'A'];
		}
		return freq;
	}

	/**
	 * Compute the frequencies (as counts) of each letter in the given byte array.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting frequency array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int[] getFrequencies(byte[] data) throws NullPointerException {
		return CryptoTools.getFrequenciesFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Compute the frequencies (as counts) of each letter in the given char array. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting frequency array.
	 */
	protected static int[] getFrequenciesFixedInput(char[] data) {
		final int[] freq = new int[CryptoTools.ENGLISH_ALPHABET_SIZE];
		for (int i = 0; i != data.length; ++i) {
			++freq[data[i] - 'A'];
		}
		return freq;
	}

	/**
	 * Compute the frequencies (as counts) of each letter in the given char array.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting frequency array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int[] getFrequencies(char[] data) throws NullPointerException {
		return CryptoTools.getFrequenciesFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Compute the probabilities of each letter in the given byte array. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting probability array.
	 */
	protected static double[] getProbabilitiesFixedInput(byte[] data) {
		final int[] freq = CryptoTools.getFrequenciesFixedInput(data);
		final double[] prob = new double[CryptoTools.ENGLISH_ALPHABET_SIZE];
		for (int i = 0; i != CryptoTools.ENGLISH_ALPHABET_SIZE; ++i) {
			prob[i] = 100.0 * freq[i] / data.length;
		}
		return prob;
	}

	/**
	 * Compute the probabilities of each letter in the given byte array.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting probability array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static double[] getProbabilities(byte[] data) throws NullPointerException {
		return CryptoTools.getProbabilitiesFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Compute the probabilities of each letter in the given char array. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting probability array.
	 */
	protected static double[] getProbabilitiesFixedInput(char[] data) {
		final int[] freq = CryptoTools.getFrequenciesFixedInput(data);
		final double[] prob = new double[CryptoTools.ENGLISH_ALPHABET_SIZE];
		for (int i = 0; i != CryptoTools.ENGLISH_ALPHABET_SIZE; ++i) {
			prob[i] = 100.0 * freq[i] / data.length;
		}
		return prob;
	}

	/**
	 * Compute the probabilities of each letter in the given char array.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting probability array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static double[] getProbabilities(char[] data) throws NullPointerException {
		return CryptoTools.getProbabilitiesFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Compute the dot product of the probability of each letter in the given byte array with
	 * <code>CryptoTools.ENGLISH_LETTER_PROBABILITY</code>. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting dot product.
	 */
	protected static double getEnglishProbabilitiesDotProductFixedInput(byte[] data) {
		final double[] prob = CryptoTools.getProbabilitiesFixedInput(data);
		double result = 0.0;
		for (int i = 0; i != CryptoTools.ENGLISH_ALPHABET_SIZE; ++i) {
			result += prob[i] * CryptoTools.ENGLISH_LETTER_PROBABILITY[i];
		}
		return result;
	}

	/**
	 * Compute the dot product of the probability of each letter in the given byte array with
	 * <code>CryptoTools.ENGLISH_LETTER_PROBABILITY</code>.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting dot product.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static double getEnglishProbabilitiesDotProduct(byte[] data) throws NullPointerException {
		return CryptoTools.getEnglishProbabilitiesDotProductFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Compute the dot product of the probability of each letter in the given char array with
	 * <code>CryptoTools.ENGLISH_LETTER_PROBABILITY</code>. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting dot product.
	 */
	protected static double getEnglishProbabilitiesDotProductFixedInput(char[] data) {
		final double[] prob = CryptoTools.getProbabilitiesFixedInput(data);
		double result = 0.0;
		for (int i = 0; i != CryptoTools.ENGLISH_ALPHABET_SIZE; ++i) {
			result += prob[i] * CryptoTools.ENGLISH_LETTER_PROBABILITY[i];
		}
		return result;
	}

	/**
	 * Compute the dot product of the probability of each letter in the given char array with
	 * <code>CryptoTools.ENGLISH_LETTER_PROBABILITY</code>.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting dot product.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static double getEnglishProbabilitiesDotProduct(char[] data) throws NullPointerException {
		return CryptoTools.getEnglishProbabilitiesDotProductFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Compute the Index of Coincidence of the given byte array out of 100. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The Index of Coincidence.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>data.length < 2</code>
	 */
	protected static double getICFixedInput(byte[] data) throws IllegalArgumentException {
		if (data.length < 2) {
			throw new IllegalArgumentException();
		}

		final int[] freq = CryptoTools.getFrequenciesFixedInput(data);
		long sum = 0L, f = 0L;
		for (int i = 0; i != freq.length; ++i) {
			f = freq[i];
			sum += f * (f - 1L);
		}
		return (100.0 * sum / (data.length * (data.length - 1)));
	}

	/**
	 * Compute the Index of Coincidence of the given byte array out of 100.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The Index of Coincidence.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(data).length < 2</code>
	 */
	public static double getIC(byte[] data) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a cleaning.
		if (data.length < 2) {
			throw new IllegalArgumentException();
		}
		return CryptoTools.getICFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Compute the Index of Coincidence of the given char array out of 100. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The Index of Coincidence.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>data.length < 2</code>
	 */
	protected static double getICFixedInput(char[] data) throws IllegalArgumentException {
		if (data.length < 2) {
			throw new IllegalArgumentException();
		}

		final int[] freq = CryptoTools.getFrequenciesFixedInput(data);
		long sum = 0L, f = 0L;
		for (int i = 0; i != freq.length; ++i) {
			f = freq[i];
			sum += f * (f - 1L);
		}
		return (100.0 * sum / (data.length * (data.length - 1)));
	}

	/**
	 * Compute the Index of Coincidence of the given char array out of 100.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The Index of Coincidence.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(data).length < 2</code>
	 */
	public static double getIC(char[] data) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a cleaning.
		if (data.length < 2) {
			throw new IllegalArgumentException();
		}
		return CryptoTools.getICFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Read the content of the file with the given name and return it as a byte array.
	 * 
	 * @param filename
	 *            the given file name
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>filename == null</code>
	 * 
	 * @throws FileNotFoundException
	 *             Thrown by <code>new FileInputStream(filename)</code>
	 * 
	 * @throws IOException
	 *             Thrown by <code>new FileInputStream(filename)</code>,
	 *             <code>FileInputStream.read()</code>, and <code>FileInputStream.close()</code>
	 */
	public static byte[] fileToBytes(String filename) throws NullPointerException, FileNotFoundException, IOException {
		// Check for filename being null so that a FileInputStream is not created unless needed.
		if (filename == null) {
			throw new NullPointerException();
		}

		try (final FileInputStream fis = new FileInputStream(filename)) {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int b = 0; (b = fis.read()) != -1; /* Update inside. */) {
				baos.write(b);
			}
			fis.close();
			return baos.toByteArray();
		}
	}

	/**
	 * Write the given array to the file with the given name.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @param filename
	 *            the given file name
	 * 
	 * @throws NullPointerException
	 *             If <code>(data == null) || (filename == null)</code>
	 * 
	 * @throws FileNotFoundException
	 *             Thrown by <code>new FileOutputStream(filename)</code>
	 * 
	 * @throws IOException
	 *             Thrown by <code>new FileOutputStream(filename)</code>,
	 *             <code>FileOutputStream.write(data)</code>, and <code>FileOutputStream.close()</code>
	 */
	public static void bytesToFile(byte[] data, String filename)
			throws NullPointerException, FileNotFoundException, IOException {
		// Check for data being null so that a FileOutputStream is not created unless needed.
		if (data == null) {
			throw new NullPointerException();
		}

		try (final FileOutputStream fos = new FileOutputStream(filename)) {
			fos.write(data);
			fos.close();
		}
	}
}
