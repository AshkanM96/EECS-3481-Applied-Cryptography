package util;

/**
 * Utility binary methods.
 * 
 * @author Professor Hamzeh Roumani
 * @author Ashkan Moatamed
 */
public class Binary {
	/**
	 * No dependencies.
	 */

	/**
	 * Binary radix value.
	 */
	public static final int RADIX = 2;

	/**
	 * Number of bits per one byte.
	 */
	public static final int BITS_PER_BYTE = Byte.SIZE;

	/**
	 * Number of bits per one char.
	 */
	public static final int BITS_PER_CHAR = Character.SIZE;

	/**
	 * Number of bits per one short.
	 */
	public static final int BITS_PER_SHORT = Short.SIZE;

	/**
	 * Number of bits per one int.
	 */
	public static final int BITS_PER_INT = Integer.SIZE;

	/**
	 * Number of bits per one long.
	 */
	public static final int BITS_PER_LONG = Long.SIZE;

	/**
	 * Mask for byte to int conversion using & (i.e., bitwise and).
	 */
	public static final int MASK_BYTE_TO_INT = 0xFF;

	/**
	 * Mask for char to int conversion using & (i.e., bitwise and).
	 */
	public static final int MASK_CHAR_TO_INT = 0xFFFF;

	/**
	 * Mask for short to int conversion using & (i.e., bitwise and).
	 */
	public static final int MASK_SHORT_TO_INT = 0xFFFF;

	/**
	 * Mask for byte to long conversion using & (i.e., bitwise and).
	 */
	public static final long MASK_BYTE_TO_LONG = 0xFFL;

	/**
	 * Mask for char to long conversion using & (i.e., bitwise and).
	 */
	public static final long MASK_CHAR_TO_LONG = 0xFFFFL;

	/**
	 * Mask for short to long conversion using & (i.e., bitwise and).
	 */
	public static final long MASK_SHORT_TO_LONG = 0xFFFFL;

	/**
	 * Mask for int to long conversion using & (i.e., bitwise and).
	 */
	public static final long MASK_INT_TO_LONG = 0xFFFFFFFFL;

	/**
	 * Prevent instantiation.
	 */
	private Binary() {
		// Empty by design.
	}

	/**
	 * @param b
	 *            the given boolean
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given boolean.
	 */
	public static int countOnes(boolean b) {
		return (b ? 1 : 0);
	}

	/**
	 * @param b
	 *            the given byte
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given byte.
	 */
	public static int countOnes(byte b) {
		int result = 0;
		/**
		 * Note that we should use the bit mask when initializing <code>int_b</code>, since the binary
		 * representation of the value of <code>b</code> differs when being represented as an int instead of
		 * a byte when <code>b</code> is negative.
		 */
		for (int int_b = b & Binary.MASK_BYTE_TO_INT; int_b != 0; int_b >>>= 1) {
			/**
			 * Don't do <code>int_b &= 1</code> since we need the value of <code>int_b</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>int_b</code>.
			 */
			result += (int_b & 1);
		}
		return result;
	}

	/**
	 * @param c
	 *            the given char
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given char.
	 */
	public static int countOnes(char c) {
		int result = 0;
		/**
		 * Note that we should use the bit mask when initializing <code>int_c</code>, since the binary
		 * representation of the value of <code>c</code> differs when being represented as an int instead of
		 * a char when <code>c</code> is negative.
		 */
		for (int int_c = c & Binary.MASK_CHAR_TO_INT; int_c != 0; int_c >>>= 1) {
			/**
			 * Don't do <code>int_c &= 1</code> since we need the value of <code>int_c</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>int_c</code>.
			 */
			result += (int_c & 1);
		}
		return result;
	}

	/**
	 * @param s
	 *            the given short
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given short.
	 */
	public static int countOnes(short s) {
		int result = 0;
		/**
		 * Note that we should use the bit mask when initializing <code>int_s</code>, since the binary
		 * representation of the value of <code>s</code> differs when being represented as an int instead of
		 * a short when <code>s</code> is negative.
		 */
		for (int int_s = s & Binary.MASK_SHORT_TO_INT; int_s != 0; int_s >>>= 1) {
			/**
			 * Don't do <code>int_s &= 1</code> since we need the value of <code>int_s</code> to remain
			 * unchanged. Note that the difference is the <code>&=</code> instead of the <code>&</code> which
			 * will mutate <code>int_s</code>.
			 */
			result += (int_s & 1);
		}
		return result;
	}

	/**
	 * @param i
	 *            the given int
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given int.
	 */
	public static int countOnes(int i) {
		int result = 0;
		for (/* Already initialized. */; i != 0; i >>>= 1) {
			/**
			 * Don't do <code>i &= 1</code> since we need the value of <code>i</code> to remain unchanged. Note
			 * that the difference is the <code>&=</code> instead of the <code>&</code> which will mutate
			 * <code>i</code>.
			 */
			result += (i & 1);
		}
		return result;
	}

	/**
	 * @param l
	 *            the given long
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given long.
	 */
	public static int countOnes(long l) {
		int result = 0;
		for (/* Already initialized. */; l != 0L; l >>>= 1L) {
			/**
			 * Don't do <code>l &= 1L</code> since we need the value of <code>l</code> to remain unchanged. Note
			 * that the difference is the <code>&=</code> instead of the <code>&</code> which will mutate
			 * <code>l</code>.
			 */
			result += (l & 1L);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given boolean array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given boolean array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countOnes(boolean[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += (data[i] ? 1L : 0L); // i.e., Binary.countOnes(data[i])
		}
		return result;
	}

	/**
	 * @param data
	 *            the given byte array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countOnes(byte[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += Binary.countOnes(data[i]);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given char array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countOnes(char[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += Binary.countOnes(data[i]);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given short array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given short array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countOnes(short[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += Binary.countOnes(data[i]);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given int array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given int array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countOnes(int[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += Binary.countOnes(data[i]);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given long array
	 * 
	 * @return The number of "on" bits (i.e., equal to 1) in the given long array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countOnes(long[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += Binary.countOnes(data[i]);
		}
		return result;
	}

	/**
	 * @param b
	 *            the given boolean
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given boolean.
	 */
	public static int countZeroes(boolean b) {
		return (b ? 0 : 1); // i.e., 1 - Binary.countOnes(b)
	}

	/**
	 * @param b
	 *            the given byte
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given byte.
	 */
	public static int countZeroes(byte b) {
		/**
		 * The same algorithm that was used for <code>Binary.countOnes(byte)</code> may not work here.
		 * Instead, we count all of the ones, and then subtract them from the total number of bits.
		 */
		return (Binary.BITS_PER_BYTE - Binary.countOnes(b));
	}

	/**
	 * @param c
	 *            the given char
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given char.
	 */
	public static int countZeroes(char c) {
		/**
		 * The same algorithm that was used for <code>Binary.countOnes(char)</code> may not work here.
		 * Instead, we count all of the ones, and then subtract them from the total number of bits.
		 */
		return (Binary.BITS_PER_CHAR - Binary.countOnes(c));
	}

	/**
	 * @param s
	 *            the given short
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given short.
	 */
	public static int countZeroes(short s) {
		/**
		 * The same algorithm that was used for <code>Binary.countOnes(short)</code> may not work here.
		 * Instead, we count all of the ones, and then subtract them from the total number of bits.
		 */
		return (Binary.BITS_PER_SHORT - Binary.countOnes(s));
	}

	/**
	 * @param i
	 *            the given int
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given int.
	 */
	public static int countZeroes(int i) {
		/**
		 * The same algorithm that was used for <code>Binary.countOnes(int)</code> may not work here.
		 * Instead, we count all of the ones, and then subtract them from the total number of bits.
		 */
		return (Binary.BITS_PER_INT - Binary.countOnes(i));
	}

	/**
	 * @param l
	 *            the given long
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given long.
	 */
	public static int countZeroes(long l) {
		/**
		 * The same algorithm that was used for <code>Binary.countOnes(long)</code> may not work here.
		 * Instead, we count all of the ones, and then subtract them from the total number of bits.
		 */
		return (Binary.BITS_PER_LONG - Binary.countOnes(l));
	}

	/**
	 * @param data
	 *            the given boolean array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given boolean array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countZeroes(boolean[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += (data[i] ? 0L : 1L); // i.e., Binary.countZeroes(data[i])
		}
		return result;
	}

	/**
	 * @param data
	 *            the given byte array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countZeroes(byte[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += (Binary.BITS_PER_BYTE - Binary.countOnes(data[i])); // i.e., Binary.countZeroes(data[i])
		}
		return result;
	}

	/**
	 * @param data
	 *            the given char array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countZeroes(char[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += (Binary.BITS_PER_CHAR - Binary.countOnes(data[i])); // i.e., Binary.countZeroes(data[i]);
		}
		return result;
	}

	/**
	 * @param data
	 *            the given short array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given short array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countZeroes(short[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += (Binary.BITS_PER_SHORT - Binary.countOnes(data[i])); // i.e., Binary.countZeroes(data[i])
		}
		return result;
	}

	/**
	 * @param data
	 *            the given int array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given int array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countZeroes(int[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += (Binary.BITS_PER_INT - Binary.countOnes(data[i])); // i.e., Binary.countZeroes(data[i])
		}
		return result;
	}

	/**
	 * @param data
	 *            the given long array
	 * 
	 * @return The number of "off" bits (i.e., equal to 0) in the given long array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long countZeroes(long[] data) throws NullPointerException {
		long result = 0L;
		for (int i = 0; i != data.length; ++i) {
			result += (Binary.BITS_PER_LONG - Binary.countOnes(data[i])); // i.e., Binary.countZeroes(data[i])
		}
		return result;
	}

	/**
	 * Postcondition: <code>Result == BigInteger.valueOf(b).testBit(bitIndex)</code>
	 * 
	 * @param b
	 *            the given byte
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return The bit at the given index where the rightmost (i.e., least significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_BYTE <= bitIndex)</code>
	 */
	public static boolean getBit(byte b, int bitIndex) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_BYTE <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE)
		return ((b & (1 << bitIndex)) != 0);
	}

	/**
	 * Postcondition: <code>Result == BigInteger.valueOf(c).testBit(bitIndex)</code>
	 * 
	 * @param c
	 *            the given char
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return The bit at the given index where the rightmost (i.e., least significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_CHAR <= bitIndex)</code>
	 */
	public static boolean getBit(char c, int bitIndex) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_CHAR <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR)
		return ((c & (1 << bitIndex)) != 0);
	}

	/**
	 * Postcondition: <code>Result == BigInteger.valueOf(s).testBit(bitIndex)</code>
	 * 
	 * @param s
	 *            the given short
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return The bit at the given index where the rightmost (i.e., least significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_SHORT <= bitIndex)</code>
	 */
	public static boolean getBit(short s, int bitIndex) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_SHORT <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT)
		return ((s & (1 << bitIndex)) != 0);
	}

	/**
	 * Postcondition: <code>Result == BigInteger.valueOf(i).testBit(bitIndex)</code>
	 * 
	 * @param i
	 *            the given int
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return The bit at the given index where the rightmost (i.e., least significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_INT <= bitIndex)</code>
	 */
	public static boolean getBit(int i, int bitIndex) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_INT <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT)
		return ((i & (1 << bitIndex)) != 0);
	}

	/**
	 * Postcondition: <code>Result == BigInteger.valueOf(l).testBit(bitIndex)</code>
	 * 
	 * @param l
	 *            the given long
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return The bit at the given index where the rightmost (i.e., least significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_LONG <= bitIndex)</code>
	 */
	public static boolean getBit(long l, int bitIndex) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_LONG <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG)
		return ((l & (1L << bitIndex)) != 0L);
	}

	/**
	 * @param data
	 *            the given byte array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>Binary.getBit(data[(data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_BYTE))], (int) (bitIndex % Binary.BITS_PER_BYTE))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_BYTE * data.length <= bitIndex)</code>
	 */
	public static boolean getBit(byte[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_BYTE * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_BYTE));
		return Binary.getBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_BYTE));
	}

	/**
	 * @param data
	 *            the given char array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>Binary.getBit(data[(data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_CHAR))], (int) (bitIndex % Binary.BITS_PER_CHAR))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_CHAR * data.length <= bitIndex)</code>
	 */
	public static boolean getBit(char[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_CHAR * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_CHAR));
		return Binary.getBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_CHAR));
	}

	/**
	 * @param data
	 *            the given short array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>Binary.getBit(data[(data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_SHORT))], (int) (bitIndex % Binary.BITS_PER_SHORT))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_SHORT * data.length <= bitIndex)</code>
	 */
	public static boolean getBit(short[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_SHORT * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_SHORT));
		return Binary.getBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_SHORT));
	}

	/**
	 * @param data
	 *            the given int array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>Binary.getBit(data[(data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_INT))], (int) (bitIndex % Binary.BITS_PER_INT))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_INT * data.length <= bitIndex)</code>
	 */
	public static boolean getBit(int[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_INT * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_INT));
		return Binary.getBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_INT));
	}

	/**
	 * @param data
	 *            the given long array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>Binary.getBit(data[(data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_LONG))], (int) (bitIndex % Binary.BITS_PER_LONG))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_LONG * data.length <= bitIndex)</code>
	 */
	public static boolean getBit(long[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_LONG * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_LONG));
		return Binary.getBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_LONG));
	}

	/**
	 * Postcondition: <code>Result == BigInteger.valueOf(b).flipBit(bitIndex).byteValue()</code>
	 * 
	 * @param b
	 *            the given byte
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return The byte with its specified bit being flipped where the rightmost (i.e., least
	 *         significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_BYTE <= bitIndex)</code>
	 */
	public static byte flipBit(byte b, int bitIndex) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_BYTE <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE)
		return ((byte) (b ^ (1 << bitIndex)));
	}

	/**
	 * Postcondition: <code>Result == ((char) BigInteger.valueOf(c).flipBit(bitIndex).intValue())</code>
	 * 
	 * @param c
	 *            the given char
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return The char with its specified bit being flipped where the rightmost (i.e., least
	 *         significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_CHAR <= bitIndex)</code>
	 */
	public static char flipBit(char c, int bitIndex) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_CHAR <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR)
		return ((char) (c ^ (1 << bitIndex)));
	}

	/**
	 * Postcondition: <code>Result == BigInteger.valueOf(s).flipBit(bitIndex).shortValue()</code>
	 * 
	 * @param s
	 *            the given short
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return The short with its specified bit being flipped where the rightmost (i.e., least
	 *         significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_SHORT <= bitIndex)</code>
	 */
	public static short flipBit(short s, int bitIndex) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_SHORT <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT)
		return ((short) (s ^ (1 << bitIndex)));
	}

	/**
	 * Postcondition: <code>Result == BigInteger.valueOf(i).flipBit(bitIndex).intValue()</code>
	 * 
	 * @param i
	 *            the given int
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return The int with its specified bit being flipped where the rightmost (i.e., least
	 *         significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_INT <= bitIndex)</code>
	 */
	public static int flipBit(int i, int bitIndex) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_INT <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT)
		return (i ^ (1 << bitIndex));
	}

	/**
	 * Postcondition: <code>Result == BigInteger.valueOf(l).flipBit(bitIndex).longValue()</code>
	 * 
	 * @param l
	 *            the given long
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return The long with its specified bit being flipped where the rightmost (i.e., least
	 *         significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_LONG <= bitIndex)</code>
	 */
	public static long flipBit(long l, int bitIndex) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_LONG <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG)
		return (l ^ (1L << bitIndex));
	}

	/**
	 * <pre>
	 * <code>
	 * final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_BYTE));
	 * data[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_BYTE));
	 * </code>
	 * </pre>
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_BYTE * data.length <= bitIndex)</code>
	 */
	public static byte[] flipBitEquals(byte[] data, long bitIndex)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_BYTE * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_BYTE));
		data[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_BYTE));
		return data;
	}

	/**
	 * <pre>
	 * <code>
	 * final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_CHAR));
	 * data[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_CHAR));
	 * </code>
	 * </pre>
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_CHAR * data.length <= bitIndex)</code>
	 */
	public static char[] flipBitEquals(char[] data, long bitIndex)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_CHAR * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_CHAR));
		data[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_CHAR));
		return data;
	}

	/**
	 * <pre>
	 * <code>
	 * final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_SHORT));
	 * data[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_SHORT));
	 * </code>
	 * </pre>
	 * 
	 * @param data
	 *            the given short array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_SHORT * data.length <= bitIndex)</code>
	 */
	public static short[] flipBitEquals(short[] data, long bitIndex)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_SHORT * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_SHORT));
		data[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_SHORT));
		return data;
	}

	/**
	 * <pre>
	 * <code>
	 * final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_INT));
	 * data[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_INT));
	 * </code>
	 * </pre>
	 * 
	 * @param data
	 *            the given int array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_INT * data.length <= bitIndex)</code>
	 */
	public static int[] flipBitEquals(int[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_INT * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_INT));
		data[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_INT));
		return data;
	}

	/**
	 * <pre>
	 * <code>
	 * final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_LONG));
	 * data[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_LONG));
	 * </code>
	 * </pre>
	 * 
	 * @param data
	 *            the given long array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_LONG * data.length <= bitIndex)</code>
	 */
	public static long[] flipBitEquals(long[] data, long bitIndex)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_LONG * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_LONG));
		data[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_LONG));
		return data;
	}

	/**
	 * @param data
	 *            the given byte array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>Binary.flipBitEquals(Arrays.copyOf(data, data.length), bitIndex)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_BYTE * data.length <= bitIndex)</code>
	 */
	public static byte[] flipBit(byte[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_BYTE * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_BYTE));
		final byte[] result = new byte[data.length];
		System.arraycopy(data, 0, result, 0, dataIndex);
		result[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_BYTE));
		System.arraycopy(data, dataIndex + 1, result, dataIndex + 1, result.length - 1 - dataIndex);
		return result;
	}

	/**
	 * @param data
	 *            the given char array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>Binary.flipBitEquals(Arrays.copyOf(data, data.length), bitIndex)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_CHAR * data.length <= bitIndex)</code>
	 */
	public static char[] flipBit(char[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_CHAR * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_CHAR));
		final char[] result = new char[data.length];
		System.arraycopy(data, 0, result, 0, dataIndex);
		result[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_CHAR));
		System.arraycopy(data, dataIndex + 1, result, dataIndex + 1, result.length - 1 - dataIndex);
		return result;
	}

	/**
	 * @param data
	 *            the given short array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>Binary.flipBitEquals(Arrays.copyOf(data, data.length), bitIndex)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_SHORT * data.length <= bitIndex)</code>
	 */
	public static short[] flipBit(short[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_SHORT * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_SHORT));
		final short[] result = new short[data.length];
		System.arraycopy(data, 0, result, 0, dataIndex);
		result[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_SHORT));
		System.arraycopy(data, dataIndex + 1, result, dataIndex + 1, result.length - 1 - dataIndex);
		return result;
	}

	/**
	 * @param data
	 *            the given int array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>Binary.flipBitEquals(Arrays.copyOf(data, data.length), bitIndex)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_INT * data.length <= bitIndex)</code>
	 */
	public static int[] flipBit(int[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_INT * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_INT));
		final int[] result = new int[data.length];
		System.arraycopy(data, 0, result, 0, dataIndex);
		result[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_INT));
		System.arraycopy(data, dataIndex + 1, result, dataIndex + 1, result.length - 1 - dataIndex);
		return result;
	}

	/**
	 * @param data
	 *            the given long array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @return <code>Binary.flipBitEquals(Arrays.copyOf(data, data.length), bitIndex)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_LONG * data.length <= bitIndex)</code>
	 */
	public static long[] flipBit(long[] data, long bitIndex) throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_LONG * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_LONG));
		final long[] result = new long[data.length];
		System.arraycopy(data, 0, result, 0, dataIndex);
		result[dataIndex] = Binary.flipBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_LONG));
		System.arraycopy(data, dataIndex + 1, result, dataIndex + 1, result.length - 1 - dataIndex);
		return result;
	}

	/**
	 * <code>data = !data</code>.
	 * 
	 * @param data
	 *            the given boolean array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static boolean[] compEquals(boolean[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = !data[i];
		}
		return data;
	}

	/**
	 * <code>data ~= data</code>.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static byte[] compEquals(byte[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = (byte) ~data[i];
		}
		return data;
	}

	/**
	 * <code>data ~= data</code>.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static char[] compEquals(char[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = (char) ~data[i];
		}
		return data;
	}

	/**
	 * <code>data ~= data</code>.
	 * 
	 * @param data
	 *            the given short array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static short[] compEquals(short[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = (short) ~data[i];
		}
		return data;
	}

	/**
	 * <code>data ~= data</code>.
	 * 
	 * @param data
	 *            the given int array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int[] compEquals(int[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = ~data[i];
		}
		return data;
	}

	/**
	 * <code>data ~= data</code>.
	 * 
	 * @param data
	 *            the given long array
	 * 
	 * @return <code>data</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long[] compEquals(long[] data) throws NullPointerException {
		for (int i = 0; i != data.length; ++i) {
			data[i] = ~data[i];
		}
		return data;
	}

	/**
	 * Compute the complement of the given boolean array.
	 * 
	 * @param data
	 *            the given boolean array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static boolean[] comp(boolean[] data) throws NullPointerException {
		final boolean[] result = new boolean[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = !data[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise complement of the given byte array.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static byte[] comp(byte[] data) throws NullPointerException {
		final byte[] result = new byte[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (byte) ~data[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise complement of the given char array.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static char[] comp(char[] data) throws NullPointerException {
		final char[] result = new char[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (char) ~data[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise complement of the given short array.
	 * 
	 * @param data
	 *            the given short array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static short[] comp(short[] data) throws NullPointerException {
		final short[] result = new short[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (short) ~data[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise complement of the given int array.
	 * 
	 * @param data
	 *            the given int array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static int[] comp(int[] data) throws NullPointerException {
		final int[] result = new int[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = ~data[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise complement of the given long array.
	 * 
	 * @param data
	 *            the given long array
	 * 
	 * @return <code>Binary.compEquals(Arrays.copyOf(data, data.length))</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static long[] comp(long[] data) throws NullPointerException {
		final long[] result = new long[data.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = ~data[i];
		}
		return result;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] andEquals(boolean[] lhs, boolean[] rhs)
			throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] andEquals(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] andEquals(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] andEquals(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] andEquals(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs &= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] andEquals(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] &= rhs[i];
		}
		return lhs;
	}

	/**
	 * Compute the and of the two given boolean arrays.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] and(boolean[] lhs, boolean[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final boolean[] result = new boolean[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] && rhs[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise and of the two given byte arrays.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] and(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final byte[] result = new byte[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (byte) (lhs[i] & rhs[i]);
		}
		return result;
	}

	/**
	 * Compute the bitwise and of the two given char arrays.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] and(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final char[] result = new char[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (char) (lhs[i] & rhs[i]);
		}
		return result;
	}

	/**
	 * Compute the bitwise and of the two given short arrays.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] and(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final short[] result = new short[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (short) (lhs[i] & rhs[i]);
		}
		return result;
	}

	/**
	 * Compute the bitwise and of the two given int arrays.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] and(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final int[] result = new int[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] & rhs[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise and of the two given long arrays.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>Binary.andEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] and(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final long[] result = new long[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] & rhs[i];
		}
		return result;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] orEquals(boolean[] lhs, boolean[] rhs)
			throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] orEquals(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] orEquals(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] orEquals(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] orEquals(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs |= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] orEquals(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] |= rhs[i];
		}
		return lhs;
	}

	/**
	 * Compute the or of the two given boolean arrays.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] or(boolean[] lhs, boolean[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final boolean[] result = new boolean[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] || rhs[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise or of the two given byte arrays.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] or(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final byte[] result = new byte[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (byte) (lhs[i] | rhs[i]);
		}
		return result;
	}

	/**
	 * Compute the bitwise or of the two given char arrays.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] or(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final char[] result = new char[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (char) (lhs[i] | rhs[i]);
		}
		return result;
	}

	/**
	 * Compute the bitwise or of the two given short arrays.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] or(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final short[] result = new short[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (short) (lhs[i] | rhs[i]);
		}
		return result;
	}

	/**
	 * Compute the bitwise or of the two given int arrays.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] or(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final int[] result = new int[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] | rhs[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise or of the two given long arrays.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>Binary.orEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] or(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final long[] result = new long[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] | rhs[i];
		}
		return result;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] xorEquals(boolean[] lhs, boolean[] rhs)
			throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] xorEquals(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] xorEquals(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] xorEquals(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] xorEquals(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * <code>lhs ^= rhs</code>.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>lhs</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] xorEquals(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != lhs.length; ++i) {
			lhs[i] ^= rhs[i];
		}
		return lhs;
	}

	/**
	 * Compute the xor of the two given boolean arrays.
	 * 
	 * @param lhs
	 *            the given left hand side boolean array
	 * 
	 * @param rhs
	 *            the given right hand side boolean array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static boolean[] xor(boolean[] lhs, boolean[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final boolean[] result = new boolean[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] ^ rhs[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise xor of the two given byte arrays.
	 * 
	 * @param lhs
	 *            the given left hand side byte array
	 * 
	 * @param rhs
	 *            the given right hand side byte array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static byte[] xor(byte[] lhs, byte[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final byte[] result = new byte[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (byte) (lhs[i] ^ rhs[i]);
		}
		return result;
	}

	/**
	 * Compute the bitwise xor of the two given char arrays.
	 * 
	 * @param lhs
	 *            the given left hand side char array
	 * 
	 * @param rhs
	 *            the given right hand side char array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static char[] xor(char[] lhs, char[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final char[] result = new char[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (char) (lhs[i] ^ rhs[i]);
		}
		return result;
	}

	/**
	 * Compute the bitwise xor of the two given short arrays.
	 * 
	 * @param lhs
	 *            the given left hand side short array
	 * 
	 * @param rhs
	 *            the given right hand side short array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static short[] xor(short[] lhs, short[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final short[] result = new short[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = (short) (lhs[i] ^ rhs[i]);
		}
		return result;
	}

	/**
	 * Compute the bitwise xor of the two given int arrays.
	 * 
	 * @param lhs
	 *            the given left hand side int array
	 * 
	 * @param rhs
	 *            the given right hand side int array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static int[] xor(int[] lhs, int[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final int[] result = new int[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] ^ rhs[i];
		}
		return result;
	}

	/**
	 * Compute the bitwise xor of the two given long arrays.
	 * 
	 * @param lhs
	 *            the given left hand side long array
	 * 
	 * @param rhs
	 *            the given right hand side long array
	 * 
	 * @return <code>Binary.xorEquals(Arrays.copyOf(lhs, lhs.length), rhs)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(lhs == null) || (rhs == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>lhs.length != rhs.length</code>
	 */
	public static long[] xor(long[] lhs, long[] rhs) throws NullPointerException, IllegalArgumentException {
		if (lhs.length != rhs.length) {
			throw new IllegalArgumentException();
		}

		final long[] result = new long[lhs.length];
		for (int i = 0; i != result.length; ++i) {
			result[i] = lhs[i] ^ rhs[i];
		}
		return result;
	}

	/**
	 * Given a boolean, convert it to a string of bits.
	 * 
	 * @param b
	 *            the given boolean
	 * 
	 * @return The resulting string.
	 */
	public static String toString(boolean b) {
		return (b ? "1" : "0");
	}

	/**
	 * Given a byte, convert it to a string of bits.
	 * 
	 * @param b
	 *            the given byte
	 * 
	 * @return The resulting string.
	 */
	public static String toString(byte b) {
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_BYTE);
		/**
		 * Note that we have to use <code>Integer.toBinaryString</code> instead of
		 * <code>Integer.toString(int, Binary.RADIX)</code>, since the latter includes a negative sign in
		 * its returned string when it needs to. Moreover, we should use the bit mask when passing the given
		 * byte to <code>Integer.toBinaryString</code>, since the binary representation of the value of
		 * <code>b</code> differs when being represented as an int instead of a byte when <code>b</code> is
		 * negative.
		 */
		final String result = Integer.toBinaryString(b & Binary.MASK_BYTE_TO_INT);
		// Pad with zeroes on the left to have Binary.BITS_PER_BYTE chars in total.
		for (int count = Binary.BITS_PER_BYTE - result.length(); count != 0; --count) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given a char, convert it to a string of bits.
	 * 
	 * @param c
	 *            the given char
	 * 
	 * @return The resulting string.
	 */
	public static String toString(char c) {
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_CHAR);
		/**
		 * Note that we have to use <code>Integer.toBinaryString</code> instead of
		 * <code>Integer.toString(int, Binary.RADIX)</code>, since the latter includes a negative sign in
		 * its returned string when it needs to. Moreover, we should use the bit mask when passing the given
		 * char to <code>Integer.toBinaryString</code>, since the binary representation of the value of
		 * <code>c</code> differs when being represented as an int instead of a char when <code>c</code> is
		 * negative.
		 */
		final String result = Integer.toBinaryString(c & Binary.MASK_CHAR_TO_INT);
		// Pad with zeroes on the left to have Binary.BITS_PER_CHAR chars in total.
		for (int count = Binary.BITS_PER_CHAR - result.length(); count != 0; --count) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given a short, convert it to a string of bits.
	 * 
	 * @param s
	 *            the given short
	 * 
	 * @return The resulting string.
	 */
	public static String toString(short s) {
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_SHORT);
		/**
		 * Note that we have to use <code>Integer.toBinaryString</code> instead of
		 * <code>Integer.toString(int, Binary.RADIX)</code>, since the latter includes a negative sign in
		 * its returned string when it needs to. Moreover, we should use the bit mask when passing the given
		 * short to <code>Integer.toBinaryString</code>, since the binary representation of the value of
		 * <code>s</code> differs when being represented as an int instead of a short when <code>s</code> is
		 * negative.
		 */
		final String result = Integer.toBinaryString(s & Binary.MASK_SHORT_TO_INT);
		// Pad with zeroes on the left to have Binary.BITS_PER_SHORT chars in total.
		for (int count = Binary.BITS_PER_SHORT - result.length(); count != 0; --count) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given an int, convert it to a string of bits.
	 * 
	 * @param i
	 *            the given int
	 * 
	 * @return The resulting string.
	 */
	public static String toString(int i) {
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_INT);
		/**
		 * Note that we have to use <code>Integer.toBinaryString</code> instead of
		 * <code>Integer.toString(int, Binary.RADIX)</code>, since the latter includes a negative sign in
		 * its returned string when it needs to.
		 */
		final String result = Integer.toBinaryString(i);
		// Pad with zeroes on the left to have Binary.BITS_PER_INT chars in total.
		for (int count = Binary.BITS_PER_INT - result.length(); count != 0; --count) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given a long, convert it to a string of bits.
	 * 
	 * @param l
	 *            the given long
	 * 
	 * @return The resulting string.
	 */
	public static String toString(long l) {
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_LONG);
		/**
		 * Note that we have to use <code>Long.toBinaryString</code> instead of
		 * <code>Long.toString(long, Binary.RADIX)</code>, since the latter includes a negative sign in its
		 * returned string when it needs to.
		 */
		final String result = Long.toBinaryString(l);
		// Pad with zeroes on the left to have Binary.BITS_PER_LONG chars in total.
		for (int count = Binary.BITS_PER_LONG - result.length(); count != 0; --count) {
			sb.append('0');
		}
		return sb.append(result).toString();
	}

	/**
	 * Given a boolean array, convert it to a string of bits.
	 * 
	 * @param data
	 *            the given boolean array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(boolean[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder(data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(data[i] ? "1" : "0"); // i.e., Binary.toString(data[i])
		}
		return sb.toString();
	}

	/**
	 * Given a byte array, convert it to a string of bits.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(byte[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder(data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Binary.toString(data[i]));
		}
		return sb.toString();
	}

	/**
	 * Given a char array, convert it to a string of bits.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(char[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder(data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Binary.toString(data[i]));
		}
		return sb.toString();
	}

	/**
	 * Given a short array, convert it to a string of bits.
	 * 
	 * @param data
	 *            the given short array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(short[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder(data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Binary.toString(data[i]));
		}
		return sb.toString();
	}

	/**
	 * Given an int array, convert it to a string of bits.
	 * 
	 * @param data
	 *            the given int array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(int[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder(data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Binary.toString(data[i]));
		}
		return sb.toString();
	}

	/**
	 * Given a long array, convert it to a string of bits.
	 * 
	 * @param data
	 *            the given long array
	 * 
	 * @return The resulting string.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 */
	public static String toString(long[] data) throws NullPointerException {
		final StringBuilder sb = new StringBuilder(data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Binary.toString(data[i]));
		}
		return sb.toString();
	}
}
