package util;

import java.util.Arrays;

/**
 * Scytale cipher.
 * 
 * @author Ashkan Moatamed
 */
public class Scytale implements Iterable<Integer> {
	/**
	 * Dependencies: <code>
	 * 		1. util.MatrixInt
	 * 		2. util.CryptoTools
	 * 		3. util.MathUtil
	 * 		4. util.BidirectionalIterator
	 * </code>
	 */

	/**
	 * Scytale cipher key entries must be in <code>[0, Scytale.MODULUS - 1] \cap \doubleZ</code>.
	 */
	public static final int MODULUS = 2;

	/**
	 * Cipher key.
	 */
	protected MatrixInt key;

	/**
	 * Save <code>this.key.isTranspositionRow()</code>.
	 */
	protected boolean isValidKey;

	/**
	 * Construct a Scytale object with the given key side and the given integer in every entry.
	 * 
	 * @param side
	 *            the given key side
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>side <= 0</code>
	 */
	public Scytale(int side, int n) throws IllegalArgumentException {
		this.key(side, n);
	}

	/**
	 * Construct a Scytale object with the given key side and zeroes in every entry.
	 * 
	 * @param side
	 *            the given key side
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>side <= 0</code>
	 */
	public Scytale(int side) throws IllegalArgumentException {
		this.key(side);
	}

	/**
	 * Construct a Scytale object from the given two dimensional integer key array.
	 * 
	 * @param key
	 *            the given two dimensional integer key array
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(key.length == 0) || ((valid i) implies (key[i].length != key.length))</code>
	 */
	public Scytale(int[][] key) throws NullPointerException, IllegalArgumentException {
		this.key(key);
	}

	/**
	 * Construct a Scytale object from the given one dimensional integer key array. The cipher key will
	 * take <code>(int) Math.sqrt(key.length)</code> as its number of rows and number of columns.
	 * Therefore, some of the right-end entries of the given integer array will be ignored if its length
	 * is not a perfect square. Specifically, the last accessed index will be
	 * <code>((int) Math.sqrt(key.length)) * ((int) Math.sqrt(key.length)) - 1</code>.
	 * 
	 * @param key
	 *            the given one dimensional integer key array
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>key.length == 0</code>
	 */
	public Scytale(int[] key) throws NullPointerException, IllegalArgumentException {
		this.key(key);
	}

	/**
	 * Construct a Scytale object from the given MatrixInt key object.
	 * 
	 * @param key
	 *            the given MatrixInt key object
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!key.isSquare()</code>
	 */
	public Scytale(MatrixInt key) throws NullPointerException, IllegalArgumentException {
		this.key(key);
	}

	/**
	 * Construct a Scytale object from the given Hill object.
	 * 
	 * @param h
	 *            the given Hill object
	 * 
	 * @throws NullPointerException
	 *             If <code>h == null</code>
	 */
	public Scytale(Hill h) throws NullPointerException {
		this(h.key);
	}

	/**
	 * Copy ctor.
	 * 
	 * @param other
	 *            the given Scytale object
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 */
	public Scytale(Scytale other) throws NullPointerException {
		this.key = other.key();
		this.isValidKey = other.isValidKey;
	}

	/**
	 * @return <code>this.key.numRows</code> or <code>this.key.numCols</code>.
	 */
	public int keySide() {
		// assert this.key.isSquare();
		return this.key.numRows;
	}

	/**
	 * @return A deep copy of <code>this.key</code>.
	 */
	public MatrixInt key() {
		return new MatrixInt(this.key);
	}

	/**
	 * Reset the calling object's key to a have the given key side and the given integer in every entry.
	 * The actual value used to fill the key will be equivalent to the given integer
	 * <code>(mod Scytale.MODULUS)</code>.
	 * 
	 * @param side
	 *            the given key side
	 * 
	 * @param n
	 *            the given integer
	 * 
	 * @return The old key.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>side <= 0</code>
	 */
	public MatrixInt key(int side, int n) throws IllegalArgumentException {
		final MatrixInt oldKey = this.key;
		this.key = new MatrixInt(side, side, MathUtil.mod(n, Scytale.MODULUS));
		this.isValidKey = false;
		return oldKey;
	}

	/**
	 * Reset the calling object's key to a have the given key side and zeroes in every entry.
	 * 
	 * @param side
	 *            the given key side
	 * 
	 * @return The old key.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>side <= 0</code>
	 */
	public MatrixInt key(int side) throws IllegalArgumentException {
		// No need to modEquals(Scytale.MODULUS) this.key since this.key.isAllZero().
		final MatrixInt oldKey = this.key;
		this.key = new MatrixInt(side, side);
		this.isValidKey = false;
		return oldKey;
	}

	/**
	 * Reset the calling object's key to the given two dimensional integer key array.
	 * 
	 * @param key
	 *            the given two dimensional integer key array
	 * 
	 * @return The old key.
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(key.length == 0) || ((valid i) implies (key[i].length != key.length))</code>
	 */
	public MatrixInt key(int[][] key) throws NullPointerException, IllegalArgumentException {
		final MatrixInt tmp = new MatrixInt(key);
		if (!tmp.isSquare()) {
			throw new IllegalArgumentException();
		}

		// Set this.
		final MatrixInt oldKey = this.key;
		this.key = tmp.modEquals(Scytale.MODULUS);
		this.isValidKey = false;
		return oldKey;
	}

	/**
	 * Reset the calling object's key to the given one dimensional integer key array. The cipher key
	 * will take <code>(int) Math.sqrt(key.length)</code> as its number of rows and number of columns.
	 * Therefore, some of the right-end entries of the given integer array will be ignored if its length
	 * is not a perfect square. Specifically, the last accessed index will be
	 * <code>((int) Math.sqrt(key.length)) * ((int) Math.sqrt(key.length)) - 1</code>.
	 * 
	 * @param key
	 *            the given one dimensional integer key array
	 * 
	 * @return The old key.
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>key.length == 0</code>
	 */
	public MatrixInt key(int[] key) throws NullPointerException, IllegalArgumentException {
		final MatrixInt oldKey = this.key;
		this.key = MatrixInt.square(key).modEquals(Scytale.MODULUS);
		this.isValidKey = false;
		return oldKey;
	}

	/**
	 * Reset the calling object's key to the given MatrixInt key object.
	 * 
	 * @param key
	 *            the given MatrixInt key object
	 * 
	 * @return The old key.
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!key.isSquare()</code>
	 */
	public MatrixInt key(MatrixInt key) throws NullPointerException, IllegalArgumentException {
		if (!key.isSquare()) {
			throw new IllegalArgumentException();
		}

		// Set this.
		final MatrixInt oldKey = this.key;
		this.key = key.mod(Scytale.MODULUS);
		this.isValidKey = false;
		return oldKey;
	}

	/**
	 * @param row
	 *            the given row index
	 * 
	 * @param col
	 *            the given column index
	 * 
	 * @return <code>this.key.get(row, col)</code>.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If
	 *             <code>(row < 0) || (row >= this.keySide()) || (col < 0) || (col >= this.keySide())</code>
	 */
	public int get(int row, int col) throws IndexOutOfBoundsException {
		return this.key.get(row, col);
	}

	/**
	 * <code>this.key.set(row, col, MathUtil.mod(entry, Scytale.MODULUS))</code>.
	 * 
	 * @param row
	 *            the given row index
	 * 
	 * @param col
	 *            the given column index
	 * 
	 * @param entry
	 *            the given entry
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If
	 *             <code>(row < 0) || (row >= this.numRows) || (col < 0) || (col >= this.numCols)</code>
	 */
	public void set(int row, int col, int entry) throws IndexOutOfBoundsException {
		this.key.set(row, col, MathUtil.mod(entry, Scytale.MODULUS));
		this.isValidKey = false;
	}

	/**
	 * Set every entry of <code>this.key</code> to <code>MathUtil.mod(n, Scytale.MODULUS)</code>.
	 * 
	 * @param n
	 *            the given integer
	 */
	public void fill(int n) {
		this.key.fill(MathUtil.mod(n, Scytale.MODULUS));
		this.isValidKey = false;
	}

	@Override
	public String toString() {
		return this.key.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.key.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return ((obj instanceof Scytale) ? this.equals((Scytale) obj) : false);
	}

	/**
	 * @param other
	 *            the given Scytale object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(Scytale other) {
		return (this == other ? true : this.key.equals(other.key));
	}

	/**
	 * @return <code>this.key.iterator(false)</code>.
	 */
	@Override
	public MatrixInt.MatrixIntIterator iterator() {
		return this.key.iterator(false);
	}

	/**
	 * Check if the current cipher key is valid.
	 * 
	 * @return <code>this.key.isTranspositionRow()</code>.
	 */
	public boolean checkValidKey() {
		return this.key.isTranspositionRow();
	}

	/**
	 * Check if the current cipher key is valid and has been prepared for use.
	 * 
	 * @return <code>this.isValidKey</code>.
	 */
	public boolean isValidKey() {
		return this.isValidKey;
	}

	/**
	 * @throws IllegalStateException
	 *             If <code>!this.isValidKey()</code>
	 */
	public void validateKey() throws IllegalStateException {
		if (!this.isValidKey()) {
			throw new IllegalStateException();
		}
	}

	/**
	 * Prepare the cipher key so that it can be used for encryption and decryption only if it is valid.
	 * Before and even after a call to this method, <code>this.isValidKey</code> may be
	 * <code>false</code>. If that is the case, then it means that the cipher key is invalid and as such
	 * no encryption or decryption may be performed.
	 * 
	 * @return <code>this.isValidKey = this.key.isTranspositionRow()</code>.
	 */
	public boolean prepareKey() {
		// The following is meant to be an assignment of this.isValidKey.
		return (this.isValidKey = this.key.isTranspositionRow());
	}

	/**
	 * Apply the key to the given char array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting char array.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isValidKey()</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>data.length != this.keySide()</code>
	 */
	protected char[] applyFixedInput(char[] data) throws IllegalStateException, IllegalArgumentException {
		this.validateKey();
		// Even though the following is a repeated check, it'll save
		// an int array conversion and a row MatrixInt construction.
		if (data.length != this.keySide()) {
			throw new IllegalArgumentException();
		}
		return CryptoTools.toCharArray(MatrixInt.times(MatrixInt.row(CryptoTools.toIntArray(data)), this.key).data[0]);
	}

	/**
	 * Apply the key to the given char array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting char array.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isValidKey()</code>
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(data).length != this.keySide()</code>
	 */
	public char[] apply(char[] data) throws IllegalStateException, NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a cleaning.
		this.validateKey();
		return this.applyFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Apply the key to the given byte array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isValidKey()</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>data.length != this.keySide()</code>
	 */
	protected byte[] applyFixedInput(byte[] data) throws IllegalStateException, IllegalArgumentException {
		this.validateKey();
		// Even though the following is a repeated check, it'll save
		// an int array conversion and a row MatrixInt construction.
		if (data.length != this.keySide()) {
			throw new IllegalArgumentException();
		}
		return CryptoTools.toByteArray(MatrixInt.times(MatrixInt.row(CryptoTools.toIntArray(data)), this.key).data[0]);
	}

	/**
	 * Apply the key to the given byte array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isValidKey()</code>
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(data).length != this.keySide()</code>
	 */
	public byte[] apply(byte[] data) throws IllegalStateException, NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a cleaning.
		this.validateKey();
		return this.applyFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Place the given char array in a matrix with the given number of columns and as many rows as
	 * needed. Then transpose the matrix and return a new char array that is the result of appending all
	 * entries of the transposed matrix that came from the given char array. To undo the affect of this
	 * method, just invoke it again on the resulting char array from the first invocation and
	 * <code>MatrixInt.otherDim(data.length, numCols)</code> where <code>numCols</code> is the same
	 * value passed to the first invocation. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @param numCols
	 *            the given number of columns
	 * 
	 * @return The resulting char array.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(numCols <= 0) || (numCols > data.length)</code>
	 */
	protected static char[] placeFixedInput(char[] data, int numCols) throws IllegalArgumentException {
		if (data.length <= 1) {
			return Arrays.copyOf(data, data.length);
		}

		final MatrixInt matrix = new MatrixInt(MatrixInt.otherDim(data.length, numCols), numCols);
		// Fill the matrix using its iterator with mutation support.
		final MatrixInt.MatrixIntIterator it_matrix = matrix.iterator(true);
		for (final char c : data) {
			it_matrix.next((int) c);
		}

		final char[] result = new char[data.length];
		final MatrixInt transpose = matrix.transpose();
		// it_transpose does not need to mutate transpose but
		// allowing mutation, means direct pointer copy of matrix
		// and so more time efficient.
		final MatrixInt.MatrixIntIterator it_transpose = transpose.iterator(true);
		for (int i = 0, entry = 0; i != result.length; /* Update inside. */) {
			// No need to check it_transpose.hasNext(), since it_transpose size
			// (i.e., matrix size) is greater than or equal to data.length
			// (i.e., result.length).
			entry = it_transpose.next();
			if (entry != 0) {
				// Only non-zero entries originated from data.
				result[i++] = (char) entry;
			}
		}
		return result;
	}

	/**
	 * Place the given char array in a matrix with the given number of columns and as many rows as
	 * needed. Then transpose the matrix and return a new char array that is the result of appending all
	 * entries of the transposed matrix that came from the given char array. To undo the affect of this
	 * method, just invoke it again on the resulting char array from the first invocation and
	 * <code>MatrixInt.otherDim(data.length, numCols)</code> where <code>numCols</code> is the same
	 * value passed to the first invocation.
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @param numCols
	 *            the given number of columns
	 * 
	 * @return The resulting char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(numCols <= 0) || (numCols > CryptoTools.clean(data).length)</code>
	 */
	public static char[] place(char[] data, int numCols) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a cleaning.
		if (numCols <= 0) {
			throw new IllegalArgumentException();
		}
		return Scytale.placeFixedInput(CryptoTools.clean(data), numCols);
	}

	/**
	 * Place the given byte array in a matrix with the given number of columns and as many rows as
	 * needed. Then transpose the matrix and return a new byte array that is the result of appending all
	 * entries of the transposed matrix that came from the given byte array. To undo the affect of this
	 * method, just invoke it again on the resulting byte array from the first invocation and
	 * <code>MatrixInt.otherDim(data.length, numCols)</code> where <code>numCols</code> is the same
	 * value passed to the first invocation. <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @param numCols
	 *            the given number of columns
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(numCols <= 0) || (numCols > data.length)</code>
	 */
	protected static byte[] placeFixedInput(byte[] data, int numCols) throws IllegalArgumentException {
		if (data.length <= 1) {
			return Arrays.copyOf(data, data.length);
		}

		final MatrixInt matrix = new MatrixInt(MatrixInt.otherDim(data.length, numCols), numCols);
		// Fill the matrix using its iterator with mutation support.
		final MatrixInt.MatrixIntIterator it_matrix = matrix.iterator(true);
		for (final byte b : data) {
			it_matrix.next((int) b);
		}

		final byte[] result = new byte[data.length];
		final MatrixInt transpose = matrix.transpose();
		// it_transpose does not need to mutate transpose but
		// allowing mutation, means direct pointer copy of matrix
		// and so more time efficient.
		final MatrixInt.MatrixIntIterator it_transpose = transpose.iterator(true);
		for (int i = 0, entry = 0; i != result.length; /* Update inside. */) {
			// No need to check it_transpose.hasNext(), since it_transpose size
			// (i.e., matrix size) is greater than or equal to data.length
			// (i.e., result.length).
			entry = it_transpose.next();
			if (entry != 0) {
				// Only non-zero entries originated from data.
				result[i++] = (byte) entry;
			}
		}
		return result;
	}

	/**
	 * Place the given byte array in a matrix with the given number of columns and as many rows as
	 * needed. Then transpose the matrix and return a new byte array that is the result of appending all
	 * entries of the transposed matrix that came from the given byte array. To undo the affect of this
	 * method, just invoke it again on the resulting byte array from the first invocation and
	 * <code>MatrixInt.otherDim(data.length, numCols)</code> where <code>numCols</code> is the same
	 * value passed to the first invocation.
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @param numCols
	 *            the given number of columns
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>data == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(numCols <= 0) || (numCols > CryptoTools.clean(data).length)</code>
	 */
	public static byte[] place(byte[] data, int numCols) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a cleaning.
		if (numCols <= 0) {
			throw new IllegalArgumentException();
		}
		return Scytale.placeFixedInput(CryptoTools.clean(data), numCols);
	}
}
