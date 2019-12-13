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
	 * Saves <code>this.key.isTransposition()</code>.
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
	 * Construct a Scytale object from the given one dimensional integer key array.
	 * 
	 * @param key
	 *            the given one dimensional integer key array
	 * 
	 * @param floor
	 *            specifies whether the floor of <code>sqrt(key.length)</code> should be used
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>key.length == 0</code>
	 */
	public Scytale(int[] key, boolean floor) throws NullPointerException, IllegalArgumentException {
		this.key(key, floor);
	}

	/**
	 * Equivalent to <code>new Scytale(key, true)</code>.
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
		this.key(key, true);
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
		this.key = new MatrixInt(side, side, (int) MathUtil.modFixedInput(n, Scytale.MODULUS));
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
		// No need to do this.key.modEquals(Scytale.MODULUS) since this.key.isAllZero().
		final MatrixInt oldKey = this.key;
		this.key = new MatrixInt(side, side, 0);
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
		if (tmp.numRows != tmp.numCols) { // i.e., !tmp.isSquare()
			throw new IllegalArgumentException();
		}
		final MatrixInt oldKey = this.key;
		this.key = tmp.modEquals(Scytale.MODULUS);
		this.isValidKey = false;
		return oldKey;
	}

	/**
	 * Reset the calling object's key to the given one dimensional integer key array.
	 * 
	 * @param key
	 *            the given one dimensional integer key array
	 * 
	 * @param floor
	 *            specifies whether the floor of <code>sqrt(key.length)</code> should be used
	 * 
	 * @return The old key.
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>key.length == 0</code>
	 */
	public MatrixInt key(int[] key, boolean floor) throws NullPointerException, IllegalArgumentException {
		final MatrixInt oldKey = this.key;
		this.key = MatrixInt.square(key, floor).modEquals(Scytale.MODULUS);
		this.isValidKey = false;
		return oldKey;
	}

	/**
	 * @param key
	 *            the given one dimensional integer key array
	 * 
	 * @return <code>this.key(key, true)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>key.length == 0</code>
	 */
	public MatrixInt key(int[] key) throws NullPointerException, IllegalArgumentException {
		return this.key(key, true);
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
		if (key.numRows != key.numCols) { // i.e., !key.isSquare()
			throw new IllegalArgumentException();
		}
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
	 *             <code>(row < 0) || (this.keySide() <= row) || (col < 0) || (this.keySide() <= col)</code>
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
	 *             <code>(row < 0) || (this.keySide() <= row) || (col < 0) || (this.keySide() <= col)</code>
	 */
	public void set(int row, int col, int entry) throws IndexOutOfBoundsException {
		this.key.set(row, col, (int) MathUtil.modFixedInput(entry, Scytale.MODULUS));
		this.isValidKey = false;
	}

	/**
	 * Set every entry of <code>this.key</code> to <code>MathUtil.mod(n, Scytale.MODULUS)</code>.
	 * 
	 * @param n
	 *            the given integer
	 */
	public void fill(int n) {
		this.key.fill((int) MathUtil.modFixedInput(n, Scytale.MODULUS));
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
		return ((other == null) ? false : ((this == other) ? true : this.key.equals(other.key)));
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
	 * @return <code>this.key.isTransposition()</code>.
	 */
	public boolean checkValidKey() {
		return this.key.isTransposition();
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
	 * @return <code>this.isValidKey = this.key.isTransposition()</code>.
	 */
	public boolean prepareKey() {
		// The following is meant to be an assignment of this.isValidKey.
		return (this.isValidKey = this.key.isTransposition());
	}

	/**
	 * Apply the key to the given char array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>. <br>
	 * Precondition: <code>this.isValidKey()</code> <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given char array
	 * 
	 * @return The resulting char array.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>data.length != this.keySide()</code>
	 */
	protected char[] applyFixedInput(char[] data) throws IllegalArgumentException {
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
		this.validateKey();
		return this.applyFixedInput(CryptoTools.clean(data));
	}

	/**
	 * Apply the key to the given byte array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>. <br>
	 * Precondition: <code>this.isValidKey()</code> <br>
	 * Precondition: <code>Arrays.equals(data, CryptoTools.clean(data))</code>
	 * 
	 * @param data
	 *            the given byte array
	 * 
	 * @return The resulting byte array.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>data.length != this.keySide()</code>
	 */
	protected byte[] applyFixedInput(byte[] data) throws IllegalArgumentException {
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
	 *             If <code>(numCols < 1) || (data.length < numCols)</code>
	 */
	protected static char[] placeFixedInput(char[] data, int numCols) throws IllegalArgumentException {
		if (data.length <= 1) {
			return Arrays.copyOf(data, data.length);
		}

		final MatrixInt matrix = new MatrixInt(MatrixInt.otherDim(data.length, numCols), numCols, 0);
		// Fill the matrix using its iterator with mutation support.
		final MatrixInt.MatrixIntIterator matrix_it = matrix.iterator(true);
		for (int i = 0; i != data.length; ++i) {
			matrix_it.next((int) data[i]);
		}

		final char[] result = new char[data.length];
		final MatrixInt transpose = matrix.transpose();
		/*
		 * transpose_it does not need to mutate transpose but allowing mutation, means direct pointer copy
		 * of matrix and so its more time efficient.
		 */
		final MatrixInt.MatrixIntIterator transpose_it = transpose.iterator(true);
		for (int i = 0, entry = 0; i != result.length; /* Update inside. */) {
			/*
			 * No need to check transpose_it.hasNext(), since transpose_it size (i.e., matrix size) is greater
			 * than or equal to data.length (i.e., result.length).
			 */
			entry = transpose_it.next();
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
	 *             If <code>(numCols < 1) || (CryptoTools.clean(data).length < numCols)</code>
	 */
	public static char[] place(char[] data, int numCols) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a cleaning.
		if (numCols < 1) {
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
	 *             If <code>(numCols < 1) || (data.length < numCols)</code>
	 */
	protected static byte[] placeFixedInput(byte[] data, int numCols) throws IllegalArgumentException {
		if (data.length <= 1) {
			return Arrays.copyOf(data, data.length);
		}

		final MatrixInt matrix = new MatrixInt(MatrixInt.otherDim(data.length, numCols), numCols, 0);
		// Fill the matrix using its iterator with mutation support.
		final MatrixInt.MatrixIntIterator matrix_it = matrix.iterator(true);
		for (int i = 0; i != data.length; ++i) {
			matrix_it.next((int) data[i]);
		}

		final byte[] result = new byte[data.length];
		final MatrixInt transpose = matrix.transpose();
		/*
		 * transpose_it does not need to mutate transpose but allowing mutation, means direct pointer copy
		 * of matrix and so its more time efficient.
		 */
		final MatrixInt.MatrixIntIterator transpose_it = transpose.iterator(true);
		for (int i = 0, entry = 0; i != result.length; /* Update inside. */) {
			/*
			 * No need to check transpose_it.hasNext(), since transpose_it size (i.e., matrix size) is greater
			 * than or equal to data.length (i.e., result.length).
			 */
			entry = transpose_it.next();
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
	 *             If <code>(numCols < 1) || (CryptoTools.clean(data).length < numCols)</code>
	 */
	public static byte[] place(byte[] data, int numCols) throws NullPointerException, IllegalArgumentException {
		// Even though the following is a repeated check, it'll save a cleaning.
		if (numCols < 1) {
			throw new IllegalArgumentException();
		}
		return Scytale.placeFixedInput(CryptoTools.clean(data), numCols);
	}
}
