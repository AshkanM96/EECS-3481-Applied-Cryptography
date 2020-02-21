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
	 * Number of bits needed to store one byte.
	 */
	public static final int BITS_PER_BYTE = Byte.SIZE;

	/**
	 * Number of bits needed to store one char.
	 */
	public static final int BITS_PER_CHAR = Character.SIZE;

	/**
	 * Number of bits needed to store one short.
	 */
	public static final int BITS_PER_SHORT = Short.SIZE;

	/**
	 * Number of bits needed to store one int.
	 */
	public static final int BITS_PER_INT = Integer.SIZE;

	/**
	 * Number of bits needed to store one long.
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

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
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
			result += (int) (l & 1L);
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
		/**
		 * It's fine to do <code>b &= (1 << bitIndex)</code> instead of <code>b & (1 << bitIndex)</code>
		 * since we don't need the value of <code>b</code> to remain unchanged.
		 */
		return ((b &= (1 << bitIndex)) != 0);
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
		/**
		 * It's fine to do <code>c &= (1 << bitIndex)</code> instead of <code>c & (1 << bitIndex)</code>
		 * since we don't need the value of <code>c</code> to remain unchanged.
		 */
		return ((c &= (1 << bitIndex)) != 0);
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
		/**
		 * It's fine to do <code>s &= (1 << bitIndex)</code> instead of <code>s & (1 << bitIndex)</code>
		 * since we don't need the value of <code>s</code> to remain unchanged.
		 */
		return ((s &= (1 << bitIndex)) != 0);
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
		/**
		 * It's fine to do <code>i &= (1 << bitIndex)</code> instead of <code>i & (1 << bitIndex)</code>
		 * since we don't need the value of <code>i</code> to remain unchanged.
		 */
		return ((i &= (1 << bitIndex)) != 0);
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
		/**
		 * It's fine to do <code>l &= (1L << bitIndex)</code> instead of <code>l & (1L << bitIndex)</code>
		 * since we don't need the value of <code>l</code> to remain unchanged.
		 */
		return ((l &= (1L << bitIndex)) != 0L);
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
		/**
		 * Don't do <code>data[dataIndex] &= ...</code> since we need the value of
		 * <code>data[dataIndex]</code> to remain unchanged. Note that the difference is the <code>&=</code>
		 * instead of the <code>&</code> which will mutate <code>data[dataIndex]</code>.
		 */
		return ((data[dataIndex] & (1 << ((int) (bitIndex % Binary.BITS_PER_BYTE)))) != 0);
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
		/**
		 * Don't do <code>data[dataIndex] &= ...</code> since we need the value of
		 * <code>data[dataIndex]</code> to remain unchanged. Note that the difference is the <code>&=</code>
		 * instead of the <code>&</code> which will mutate <code>data[dataIndex]</code>.
		 */
		return ((data[dataIndex] & (1 << ((int) (bitIndex % Binary.BITS_PER_CHAR)))) != 0);
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
		/**
		 * Don't do <code>data[dataIndex] &= ...</code> since we need the value of
		 * <code>data[dataIndex]</code> to remain unchanged. Note that the difference is the <code>&=</code>
		 * instead of the <code>&</code> which will mutate <code>data[dataIndex]</code>.
		 */
		return ((data[dataIndex] & (1 << ((int) (bitIndex % Binary.BITS_PER_SHORT)))) != 0);
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
		/**
		 * Don't do <code>data[dataIndex] &= ...</code> since we need the value of
		 * <code>data[dataIndex]</code> to remain unchanged. Note that the difference is the <code>&=</code>
		 * instead of the <code>&</code> which will mutate <code>data[dataIndex]</code>.
		 */
		return ((data[dataIndex] & (1 << ((int) (bitIndex % Binary.BITS_PER_INT)))) != 0);
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
		/**
		 * Don't do <code>data[dataIndex] &= ...</code> since we need the value of
		 * <code>data[dataIndex]</code> to remain unchanged. Note that the difference is the <code>&=</code>
		 * instead of the <code>&</code> which will mutate <code>data[dataIndex]</code>.
		 */
		return ((data[dataIndex] & (1L << ((int) (bitIndex % Binary.BITS_PER_LONG)))) != 0L);
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
		/**
		 * It's fine to do <code>b ^= (1 << bitIndex)</code> instead of <code>b ^ (1 << bitIndex)</code>
		 * since we don't need the value of <code>b</code> to remain unchanged.
		 */
		return (b ^= (1 << bitIndex));
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
		/**
		 * It's fine to do <code>c ^= (1 << bitIndex)</code> instead of <code>c ^ (1 << bitIndex)</code>
		 * since we don't need the value of <code>c</code> to remain unchanged.
		 */
		return (c ^= (1 << bitIndex));
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
		/**
		 * It's fine to do <code>s ^= (1 << bitIndex)</code> instead of <code>s ^ (1 << bitIndex)</code>
		 * since we don't need the value of <code>s</code> to remain unchanged.
		 */
		return (s ^= (1 << bitIndex));
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
		/**
		 * It's fine to do <code>i ^= (1 << bitIndex)</code> instead of <code>i ^ (1 << bitIndex)</code>
		 * since we don't need the value of <code>i</code> to remain unchanged.
		 */
		return (i ^= (1 << bitIndex));
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
		/**
		 * It's fine to do <code>l ^= (1L << bitIndex)</code> instead of <code>l ^ (1L << bitIndex)</code>
		 * since we don't need the value of <code>l</code> to remain unchanged.
		 */
		return (l ^= (1L << bitIndex));
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
		data[dataIndex] ^= (1 << ((int) (bitIndex % Binary.BITS_PER_BYTE)));
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
		data[dataIndex] ^= (1 << ((int) (bitIndex % Binary.BITS_PER_CHAR)));
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
		data[dataIndex] ^= (1 << ((int) (bitIndex % Binary.BITS_PER_SHORT)));
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
		data[dataIndex] ^= (1 << ((int) (bitIndex % Binary.BITS_PER_INT)));
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
		data[dataIndex] ^= (1L << ((int) (bitIndex % Binary.BITS_PER_LONG)));
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
		System.arraycopy(data, 0, result, 0, result.length);
		result[dataIndex] ^= (1 << ((int) (bitIndex % Binary.BITS_PER_BYTE)));
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
		System.arraycopy(data, 0, result, 0, result.length);
		result[dataIndex] ^= (1 << ((int) (bitIndex % Binary.BITS_PER_CHAR)));
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
		System.arraycopy(data, 0, result, 0, result.length);
		result[dataIndex] ^= (1 << ((int) (bitIndex % Binary.BITS_PER_SHORT)));
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
		System.arraycopy(data, 0, result, 0, result.length);
		result[dataIndex] ^= (1 << ((int) (bitIndex % Binary.BITS_PER_INT)));
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
		System.arraycopy(data, 0, result, 0, result.length);
		result[dataIndex] ^= (1L << ((int) (bitIndex % Binary.BITS_PER_LONG)));
		return result;
	}

	/**
	 * Postcondition:
	 * <code>on implies (Result == BigInteger.valueOf(b).setBit(bitIndex).byteValue())</code> <br>
	 * Postcondition:
	 * <code>(!on) implies (Result == BigInteger.valueOf(b).clearBit(bitIndex).byteValue())</code>
	 * 
	 * @param b
	 *            the given byte
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
	 * 
	 * @return The byte with its specified bit being set to the given boolean where the rightmost (i.e.,
	 *         least significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_BYTE <= bitIndex)</code>
	 */
	public static byte setBit(byte b, int bitIndex, boolean on) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_BYTE <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE)
		/**
		 * It's fine to do <code>b |= ...</code> or <code>b &= ...</code> instead of <code>b | ...</code>
		 * and <code>b & ...</code> since we don't need the value of <code>b</code> to remain unchanged.
		 */
		return (on ? (b |= (1 << bitIndex)) : (b &= ~(1 << bitIndex)));
	}

	/**
	 * Postcondition:
	 * <code>on implies (Result == ((char) BigInteger.valueOf(c).setBit(bitIndex).intValue()))</code>
	 * <br>
	 * Postcondition:
	 * <code>(!on) implies (Result == ((char) BigInteger.valueOf(c).clearBit(bitIndex).intValue()))</code>
	 * 
	 * @param c
	 *            the given char
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
	 * 
	 * @return The char with its specified bit being set to the given boolean where the rightmost (i.e.,
	 *         least significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_CHAR <= bitIndex)</code>
	 */
	public static char setBit(char c, int bitIndex, boolean on) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_CHAR <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR)
		/**
		 * It's fine to do <code>c |= ...</code> or <code>c &= ...</code> instead of <code>c | ...</code>
		 * and <code>c & ...</code> since we don't need the value of <code>c</code> to remain unchanged.
		 */
		return (on ? (c |= (1 << bitIndex)) : (c &= ~(1 << bitIndex)));
	}

	/**
	 * Postcondition:
	 * <code>on implies (Result == BigInteger.valueOf(s).setBit(bitIndex).shortValue())</code> <br>
	 * Postcondition:
	 * <code>(!on) implies (Result == BigInteger.valueOf(s).clearBit(bitIndex).shortValue())</code>
	 * 
	 * @param s
	 *            the given short
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
	 * 
	 * @return The short with its specified bit being set to the given boolean where the rightmost
	 *         (i.e., least significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_SHORT <= bitIndex)</code>
	 */
	public static short setBit(short s, int bitIndex, boolean on) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_SHORT <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT)
		/**
		 * It's fine to do <code>s |= ...</code> or <code>s &= ...</code> instead of <code>s | ...</code>
		 * and <code>s & ...</code> since we don't need the value of <code>s</code> to remain unchanged.
		 */
		return (on ? (s |= (1 << bitIndex)) : (s &= ~(1 << bitIndex)));
	}

	/**
	 * Postcondition:
	 * <code>on implies (Result == BigInteger.valueOf(i).setBit(bitIndex).intValue())</code> <br>
	 * Postcondition:
	 * <code>(!on) implies (Result == BigInteger.valueOf(i).clearBit(bitIndex).intValue())</code>
	 * 
	 * @param i
	 *            the given int
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
	 * 
	 * @return The int with its specified bit being set to the given boolean where the rightmost (i.e.,
	 *         least significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_INT <= bitIndex)</code>
	 */
	public static int setBit(int i, int bitIndex, boolean on) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_INT <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT)
		/**
		 * It's fine to do <code>i |= ...</code> or <code>i &= ...</code> instead of <code>i | ...</code>
		 * and <code>i & ...</code> since we don't need the value of <code>i</code> to remain unchanged.
		 */
		return (on ? (i |= (1 << bitIndex)) : (i &= ~(1 << bitIndex)));
	}

	/**
	 * Postcondition:
	 * <code>on implies (Result == BigInteger.valueOf(l).setBit(bitIndex).longValue())</code> <br>
	 * Postcondition:
	 * <code>(!on) implies (Result == BigInteger.valueOf(l).clearBit(bitIndex).longValue())</code>
	 * 
	 * @param l
	 *            the given long
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
	 * 
	 * @return The long with its specified bit being set to the given boolean where the rightmost (i.e.,
	 *         least significant) bit has index 0.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(bitIndex < 0) || (Binary.BITS_PER_LONG <= bitIndex)</code>
	 */
	public static long setBit(long l, int bitIndex, boolean on) throws IllegalArgumentException {
		if ((bitIndex < 0) || (Binary.BITS_PER_LONG <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG)
		/**
		 * It's fine to do <code>l |= ...</code> or <code>l &= ...</code> instead of <code>l | ...</code>
		 * and <code>l & ...</code> since we don't need the value of <code>l</code> to remain unchanged.
		 */
		return (on ? (l |= (1L << bitIndex)) : (l &= ~(1L << bitIndex)));
	}

	/**
	 * <pre>
	 * <code>
	 * final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_BYTE));
	 * data[dataIndex] = Binary.setBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_BYTE), on);
	 * </code>
	 * </pre>
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
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
	public static byte[] setBitEquals(byte[] data, long bitIndex, boolean on)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_BYTE * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_BYTE));
		if (on) {
			data[dataIndex] |= (1 << ((int) (bitIndex % Binary.BITS_PER_BYTE)));
		} else {
			data[dataIndex] &= ~(1 << ((int) (bitIndex % Binary.BITS_PER_BYTE)));
		}
		return data;
	}

	/**
	 * <pre>
	 * <code>
	 * final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_CHAR));
	 * data[dataIndex] = Binary.setBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_CHAR), on);
	 * </code>
	 * </pre>
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
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
	public static char[] setBitEquals(char[] data, long bitIndex, boolean on)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_CHAR * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_CHAR));
		if (on) {
			data[dataIndex] |= (1 << ((int) (bitIndex % Binary.BITS_PER_CHAR)));
		} else {
			data[dataIndex] &= ~(1 << ((int) (bitIndex % Binary.BITS_PER_CHAR)));
		}
		return data;
	}

	/**
	 * <pre>
	 * <code>
	 * final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_SHORT));
	 * data[dataIndex] = Binary.setBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_SHORT), on);
	 * </code>
	 * </pre>
	 * 
	 * @param data
	 *            the given short array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
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
	public static short[] setBitEquals(short[] data, long bitIndex, boolean on)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_SHORT * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_SHORT));
		if (on) {
			data[dataIndex] |= (1 << ((int) (bitIndex % Binary.BITS_PER_SHORT)));
		} else {
			data[dataIndex] &= ~(1 << ((int) (bitIndex % Binary.BITS_PER_SHORT)));
		}
		return data;
	}

	/**
	 * <pre>
	 * <code>
	 * final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_INT));
	 * data[dataIndex] = Binary.setBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_INT), on);
	 * </code>
	 * </pre>
	 * 
	 * @param data
	 *            the given int array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
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
	public static int[] setBitEquals(int[] data, long bitIndex, boolean on)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_INT * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_INT));
		if (on) {
			data[dataIndex] |= (1 << ((int) (bitIndex % Binary.BITS_PER_INT)));
		} else {
			data[dataIndex] &= ~(1 << ((int) (bitIndex % Binary.BITS_PER_INT)));
		}
		return data;
	}

	/**
	 * <pre>
	 * <code>
	 * final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_LONG));
	 * data[dataIndex] = Binary.setBit(data[dataIndex], (int) (bitIndex % Binary.BITS_PER_LONG), on);
	 * </code>
	 * </pre>
	 * 
	 * @param data
	 *            the given long array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
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
	public static long[] setBitEquals(long[] data, long bitIndex, boolean on)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_LONG * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_LONG));
		if (on) {
			data[dataIndex] |= (1L << ((int) (bitIndex % Binary.BITS_PER_LONG)));
		} else {
			data[dataIndex] &= ~(1L << ((int) (bitIndex % Binary.BITS_PER_LONG)));
		}
		return data;
	}

	/**
	 * @param data
	 *            the given byte array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
	 * 
	 * @return <code>Binary.setBitEquals(Arrays.copyOf(data, data.length), bitIndex, on)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_BYTE * data.length <= bitIndex)</code>
	 */
	public static byte[] setBit(byte[] data, long bitIndex, boolean on)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_BYTE * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_BYTE * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_BYTE));
		final byte[] result = new byte[data.length];
		System.arraycopy(data, 0, result, 0, result.length);
		if (on) {
			result[dataIndex] |= (1 << ((int) (bitIndex % Binary.BITS_PER_BYTE)));
		} else {
			result[dataIndex] &= ~(1 << ((int) (bitIndex % Binary.BITS_PER_BYTE)));
		}
		return result;
	}

	/**
	 * @param data
	 *            the given char array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
	 * 
	 * @return <code>Binary.setBitEquals(Arrays.copyOf(data, data.length), bitIndex, on)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_CHAR * data.length <= bitIndex)</code>
	 */
	public static char[] setBit(char[] data, long bitIndex, boolean on)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_CHAR * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_CHAR * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_CHAR));
		final char[] result = new char[data.length];
		System.arraycopy(data, 0, result, 0, result.length);
		if (on) {
			result[dataIndex] |= (1 << ((int) (bitIndex % Binary.BITS_PER_CHAR)));
		} else {
			result[dataIndex] &= ~(1 << ((int) (bitIndex % Binary.BITS_PER_CHAR)));
		}
		return result;
	}

	/**
	 * @param data
	 *            the given short array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
	 * 
	 * @return <code>Binary.setBitEquals(Arrays.copyOf(data, data.length), bitIndex, on)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_SHORT * data.length <= bitIndex)</code>
	 */
	public static short[] setBit(short[] data, long bitIndex, boolean on)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_SHORT * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_SHORT * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_SHORT));
		final short[] result = new short[data.length];
		System.arraycopy(data, 0, result, 0, result.length);
		if (on) {
			result[dataIndex] |= (1 << ((int) (bitIndex % Binary.BITS_PER_SHORT)));
		} else {
			result[dataIndex] &= ~(1 << ((int) (bitIndex % Binary.BITS_PER_SHORT)));
		}
		return result;
	}

	/**
	 * @param data
	 *            the given int array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
	 * 
	 * @return <code>Binary.setBitEquals(Arrays.copyOf(data, data.length), bitIndex, on)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_INT * data.length <= bitIndex)</code>
	 */
	public static int[] setBit(int[] data, long bitIndex, boolean on)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_INT * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_INT * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_INT));
		final int[] result = new int[data.length];
		System.arraycopy(data, 0, result, 0, result.length);
		if (on) {
			result[dataIndex] |= (1 << ((int) (bitIndex % Binary.BITS_PER_INT)));
		} else {
			result[dataIndex] &= ~(1 << ((int) (bitIndex % Binary.BITS_PER_INT)));
		}
		return result;
	}

	/**
	 * @param data
	 *            the given long array
	 * 
	 * @param bitIndex
	 *            the given bit index
	 * 
	 * @param on
	 *            the new bit value
	 * 
	 * @return <code>Binary.setBitEquals(Arrays.copyOf(data, data.length), bitIndex, on)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(data.length == 0) || (bitIndex < 0) || (Binary.BITS_PER_LONG * data.length <= bitIndex)</code>
	 */
	public static long[] setBit(long[] data, long bitIndex, boolean on)
			throws NullPointerException, IllegalArgumentException {
		if (data.length == 0) {
			throw new IllegalArgumentException();
		} else if ((bitIndex < 0L) || (Binary.BITS_PER_LONG * ((long) data.length) <= bitIndex)) {
			throw new IllegalArgumentException();
		}
		// (data.length != 0) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG * data.length)
		// i.e., (1 <= data.length) && (0 <= bitIndex) && (bitIndex < Binary.BITS_PER_LONG * data.length)
		final int dataIndex = (data.length - 1) - ((int) (bitIndex / Binary.BITS_PER_LONG));
		final long[] result = new long[data.length];
		System.arraycopy(data, 0, result, 0, result.length);
		if (on) {
			result[dataIndex] |= (1L << ((int) (bitIndex % Binary.BITS_PER_LONG)));
		} else {
			result[dataIndex] &= ~(1L << ((int) (bitIndex % Binary.BITS_PER_LONG)));
		}
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
			sb.append(data[i] ? '1' : '0'); // i.e., Binary.toString(data[i])
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
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_BYTE * data.length);
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
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_CHAR * data.length);
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
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_SHORT * data.length);
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
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_INT * data.length);
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
		final StringBuilder sb = new StringBuilder(Binary.BITS_PER_LONG * data.length);
		for (int i = 0; i != data.length; ++i) {
			sb.append(Binary.toString(data[i]));
		}
		return sb.toString();
	}

	/**
	 * Given a string of bits, convert it to a boolean array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting boolean array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             If <code>((s.charAt(i) != '0') && (s.charAt(i) != '1'))</code> for some valid
	 *             <code>i</code>
	 */
	public static boolean[] toBooleans(String s) throws NullPointerException, NumberFormatException {
		final boolean[] result = new boolean[s.length()];
		char c = '\0';
		for (int i = 0; i != result.length; ++i) {
			// assert (!result[i]);
			c = s.charAt(i);
			if (c == '1') {
				result[i] = true;
			} else if (c != '0') {
				throw new NumberFormatException();
			}
		}
		return result;
	}

	/**
	 * Given a string of bits, convert it to a byte array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             Thrown by
	 *             <code>Integer.parseInt(s.substring(i, i + Binary.BITS_PER_BYTE), Binary.RADIX)</code>
	 *             where <code>i</code> is valid
	 */
	public static byte[] toBytes(String s) throws NullPointerException, NumberFormatException {
		final int s_length = s.length();
		final int quotient = s_length / Binary.BITS_PER_BYTE, remainder = s_length - Binary.BITS_PER_BYTE * quotient;
		// remainder == s_length % Binary.BITS_PER_BYTE

		byte[] result = null;
		int result_idx = 0;
		if (remainder == 0) {
			result = new byte[quotient];
		} else {
			result = new byte[1 + quotient];
			result[0] = (byte) Integer.parseInt(s.substring(0, remainder), Binary.RADIX);
			result_idx = 1;
		}
		for (int s_idx = remainder; s_idx != s_length; s_idx += Binary.BITS_PER_BYTE, ++result_idx) {
			result[result_idx] = (byte) Integer.parseInt(s.substring(s_idx, s_idx + Binary.BITS_PER_BYTE),
					Binary.RADIX);
		}
		return result;
	}

	/**
	 * Given a string of bits, convert it to a char array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             Thrown by
	 *             <code>Integer.parseInt(s.substring(i, i + Binary.BITS_PER_CHAR), Binary.RADIX)</code>
	 *             where <code>i</code> is valid
	 */
	public static char[] toChars(String s) throws NullPointerException, NumberFormatException {
		final int s_length = s.length();
		final int quotient = s_length / Binary.BITS_PER_CHAR, remainder = s_length - Binary.BITS_PER_CHAR * quotient;
		// remainder == s_length % Binary.BITS_PER_CHAR

		char[] result = null;
		int result_idx = 0;
		if (remainder == 0) {
			result = new char[quotient];
		} else {
			result = new char[1 + quotient];
			result[0] = (char) Integer.parseInt(s.substring(0, remainder), Binary.RADIX);
			result_idx = 1;
		}
		for (int s_idx = remainder; s_idx != s_length; s_idx += Binary.BITS_PER_CHAR, ++result_idx) {
			result[result_idx] = (char) Integer.parseInt(s.substring(s_idx, s_idx + Binary.BITS_PER_CHAR),
					Binary.RADIX);
		}
		return result;
	}

	/**
	 * Given a string of bits, convert it to a short array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting short array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             Thrown by
	 *             <code>Integer.parseInt(s.substring(i, i + Binary.BITS_PER_SHORT), Binary.RADIX)</code>
	 *             where <code>i</code> is valid
	 */
	public static short[] toShorts(String s) throws NullPointerException, NumberFormatException {
		final int s_length = s.length();
		final int quotient = s_length / Binary.BITS_PER_SHORT, remainder = s_length - Binary.BITS_PER_SHORT * quotient;
		// remainder == s_length % Binary.BITS_PER_SHORT

		short[] result = null;
		int result_idx = 0;
		if (remainder == 0) {
			result = new short[quotient];
		} else {
			result = new short[1 + quotient];
			result[0] = (short) Integer.parseInt(s.substring(0, remainder), Binary.RADIX);
			result_idx = 1;
		}
		for (int s_idx = remainder; s_idx != s_length; s_idx += Binary.BITS_PER_SHORT, ++result_idx) {
			result[result_idx] = (short) Integer.parseInt(s.substring(s_idx, s_idx + Binary.BITS_PER_SHORT),
					Binary.RADIX);
		}
		return result;
	}

	/**
	 * Given a string of bits, convert it to an int array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting int array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             Thrown by
	 *             <code>Integer.parseInt(s.substring(i, i + Binary.BITS_PER_INT), Binary.RADIX)</code>
	 *             where <code>i</code> is valid
	 */
	public static int[] toInts(String s) throws NullPointerException, NumberFormatException {
		final int s_length = s.length();
		final int quotient = s_length / Binary.BITS_PER_INT, remainder = s_length - Binary.BITS_PER_INT * quotient;
		// remainder == s_length % Binary.BITS_PER_INT

		int[] result = null;
		int result_idx = 0;
		if (remainder == 0) {
			result = new int[quotient];
		} else {
			result = new int[1 + quotient];
			result[0] = Integer.parseInt(s.substring(0, remainder), Binary.RADIX);
			result_idx = 1;
		}
		for (int s_idx = remainder; s_idx != s_length; s_idx += Binary.BITS_PER_INT, ++result_idx) {
			result[result_idx] = Integer.parseInt(s.substring(s_idx, s_idx + Binary.BITS_PER_INT), Binary.RADIX);
		}
		return result;
	}

	/**
	 * Given a string of bits, convert it to a long array.
	 * 
	 * @param s
	 *            the given string
	 * 
	 * @return The resulting long array.
	 * 
	 * @throws NullPointerException
	 *             If <code>s == null</code>
	 * 
	 * @throws NumberFormatException
	 *             Thrown by
	 *             <code>Long.parseLong(s.substring(i, i + Binary.BITS_PER_LONG), Binary.RADIX)</code>
	 *             where <code>i</code> is valid
	 */
	public static long[] toLongs(String s) throws NullPointerException, NumberFormatException {
		final int s_length = s.length();
		final int quotient = s_length / Binary.BITS_PER_LONG, remainder = s_length - Binary.BITS_PER_LONG * quotient;
		// remainder == s_length % Binary.BITS_PER_LONG

		long[] result = null;
		int result_idx = 0;
		if (remainder == 0) {
			result = new long[quotient];
		} else {
			result = new long[1 + quotient];
			result[0] = Long.parseLong(s.substring(0, remainder), Binary.RADIX);
			result_idx = 1;
		}
		for (int s_idx = remainder; s_idx != s_length; s_idx += Binary.BITS_PER_LONG, ++result_idx) {
			result[result_idx] = Long.parseLong(s.substring(s_idx, s_idx + Binary.BITS_PER_LONG), Binary.RADIX);
		}
		return result;
	}
}
