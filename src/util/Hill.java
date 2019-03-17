package util;

/**
 * Hill cipher.
 * 
 * @author Ashkan Moatamed
 */
public class Hill implements Iterable<Integer> {
	/**
	 * Dependencies: <code>
	 * 		1. util.MatrixInt
	 * 		2. util.CryptoTools
	 * 		3. util.MathUtil
	 * 		4. util.BidirectionalIterator
	 * </code>
	 */

	/**
	 * Cipher key.
	 */
	protected MatrixInt key;

	/**
	 * Inverse of <code>this.key</code>.
	 */
	protected MatrixInt keyInverse;

	/**
	 * Construct a Hill object with the given key side and the given integer in every entry.
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
	public Hill(int side, int n) throws IllegalArgumentException {
		this.key(side, n);
	}

	/**
	 * Construct a Hill object with the given key side and zeroes in every entry.
	 * 
	 * @param side
	 *            the given key side
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>side <= 0</code>
	 */
	public Hill(int side) throws IllegalArgumentException {
		this.key(side);
	}

	/**
	 * Construct a Hill object from the given two dimensional integer key array.
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
	public Hill(int[][] key) throws NullPointerException, IllegalArgumentException {
		this.key(key);
	}

	/**
	 * Construct a Hill object from the given one dimensional integer key array. The cipher key will
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
	public Hill(int[] key) throws NullPointerException, IllegalArgumentException {
		this.key(key);
	}

	/**
	 * Construct a Hill object from the given MatrixInt key object.
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
	public Hill(MatrixInt key) throws NullPointerException, IllegalArgumentException {
		this.key(key);
	}

	/**
	 * Copy ctor.
	 * 
	 * @param other
	 *            the given Hill object
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 */
	public Hill(Hill other) throws NullPointerException {
		this.key = other.key();
		this.keyInverse = other.keyInverse();
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
	 * @return A deep copy of <code>this.keyInverse</code>.
	 */
	public MatrixInt keyInverse() {
		return ((this.keyInverse == null) ? null : new MatrixInt(this.keyInverse));
	}

	/**
	 * Reset the calling object's key to a have the given key side and the given integer in every entry.
	 * The actual value used to fill the key will be equivalent to the given integer
	 * <code>(mod CryptoTools.ENGLISH_ALPHABET_SIZE)</code>.
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
		this.key = new MatrixInt(side, side, (int) MathUtil.modFixedInput(n, CryptoTools.ENGLISH_ALPHABET_SIZE));
		this.keyInverse = null;
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
		// No need to modEquals(CryptoTools.ENGLISH_ALPHABET_SIZE) this.key since this.key.isAllZero().
		final MatrixInt oldKey = this.key;
		this.key = new MatrixInt(side, side);
		this.keyInverse = null;
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
		this.key = tmp.modEquals(CryptoTools.ENGLISH_ALPHABET_SIZE);
		this.keyInverse = null;
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
		this.key = MatrixInt.square(key).modEquals(CryptoTools.ENGLISH_ALPHABET_SIZE);
		this.keyInverse = null;
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
		this.key = key.mod(CryptoTools.ENGLISH_ALPHABET_SIZE);
		this.keyInverse = null;
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
	 * <code>this.key.set(row, col, MathUtil.mod(entry, CryptoTools.ENGLISH_ALPHABET_SIZE))</code>.
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
		this.key.set(row, col, (int) MathUtil.modFixedInput(entry, CryptoTools.ENGLISH_ALPHABET_SIZE));
		this.keyInverse = null;
	}

	/**
	 * Set every entry of <code>this.key</code> to
	 * <code>MathUtil.mod(n, CryptoTools.ENGLISH_ALPHABET_SIZE)</code>.
	 * 
	 * @param n
	 *            the given integer
	 */
	public void fill(int n) {
		this.key.fill((int) MathUtil.modFixedInput(n, CryptoTools.ENGLISH_ALPHABET_SIZE));
		this.keyInverse = null;
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
		return ((obj instanceof Hill) ? this.equals((Hill) obj) : false);
	}

	/**
	 * @param other
	 *            the given Hill object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(Hill other) {
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
	 * @return <code>MathUtil.gcd(this.key.determinant(), CryptoTools.ENGLISH_ALPHABET_SIZE) == 1</code>.
	 */
	public boolean checkValidKey() {
		return (MathUtil.gcdFixedInput(this.key.determinant(), CryptoTools.ENGLISH_ALPHABET_SIZE) == 1L);
	}

	/**
	 * Check if the current cipher key is valid and has been prepared for use.
	 * 
	 * @return <code>this.keyInverse != null</code>.
	 */
	public boolean isValidKey() {
		return (this.keyInverse != null);
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
	 * Before and even after a call to this method, <code>this.keyInverse</code> may be
	 * <code>null</code>. If that is the case, then it means that the cipher key is invalid and as such
	 * no encryption or decryption may be performed.
	 * 
	 * @return The determinant of the cipher key.
	 */
	public int prepareKey() {
		final MatrixInt.InverseInfo tmp = this.key.detInvMod(CryptoTools.ENGLISH_ALPHABET_SIZE);
		// gcd(this.key.determinant(), CryptoTools.ENGLISH_ALPHABET_SIZE) must be 1.
		if (tmp.inverse != null) {
			/**
			 * The above check automatically ensures that the following is true:
			 * <code>MathUtil.gcd(tmp.determinant, CryptoTools.ENGLISH_ALPHABET_SIZE) == 1</code>. This is due
			 * to the postcondition of <code>MatrixInt.detInvMod(int)</code>.
			 */
			this.keyInverse = tmp.inverse;
		}
		return tmp.determinant;
	}

	/**
	 * Encrypt the given plaintext char array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>. <br>
	 * Precondition: <code>this.isValidKey()</code> <br>
	 * Precondition: <code>Arrays.equals(p, CryptoTools.clean(p))</code>
	 * 
	 * @param p
	 *            the given plaintext char array
	 * 
	 * @return The encrypted ciphertext char array.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>p.length != this.keySide()</code>
	 */
	protected char[] encryptFixedInput(char[] p) throws IllegalArgumentException {
		if (p.length != this.keySide()) {
			throw new IllegalArgumentException();
		}

		// [c] = [p] * [key] (mod CryptoTools.ENGLISH_ALPHABET_SIZE)
		final int[] data = new int[p.length];
		for (int i = 0; i != p.length; ++i) {
			data[i] = p[i] - 'A';
		}
		final MatrixInt result = MatrixInt.times(MatrixInt.row(data), this.key);
		final int[] c = result.modEquals(CryptoTools.ENGLISH_ALPHABET_SIZE).data[0];
		final char[] c_char = new char[c.length];
		for (int i = 0; i != c.length; ++i) {
			c_char[i] = (char) (c[i] + 'A');
		}
		return c_char;
	}

	/**
	 * Encrypt the given plaintext char array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>.
	 * 
	 * @param p
	 *            the given plaintext char array
	 * 
	 * @return The encrypted ciphertext char array.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isValidKey()</code>
	 * 
	 * @throws NullPointerException
	 *             If <code>p == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(p).length != this.keySide()</code>
	 */
	public char[] encrypt(char[] p) throws IllegalStateException, NullPointerException, IllegalArgumentException {
		this.validateKey();
		return this.encryptFixedInput(CryptoTools.clean(p));
	}

	/**
	 * Encrypt the given plaintext byte array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>. <br>
	 * Precondition: <code>this.isValidKey()</code> <br>
	 * Precondition: <code>Arrays.equals(p, CryptoTools.clean(p))</code>
	 * 
	 * @param p
	 *            the given plaintext byte array
	 * 
	 * @return The encrypted ciphertext byte array.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>p.length != this.keySide()</code>
	 */
	protected byte[] encryptFixedInput(byte[] p) throws IllegalArgumentException {
		if (p.length != this.keySide()) {
			throw new IllegalArgumentException();
		}

		// [c] = [p] * [key] (mod CryptoTools.ENGLISH_ALPHABET_SIZE)
		final int[] data = new int[p.length];
		for (int i = 0; i != p.length; ++i) {
			data[i] = p[i] - 'A';
		}
		final MatrixInt result = MatrixInt.times(MatrixInt.row(data), this.key);
		final int[] c = result.modEquals(CryptoTools.ENGLISH_ALPHABET_SIZE).data[0];
		final byte[] c_byte = new byte[c.length];
		for (int i = 0; i != c.length; ++i) {
			c_byte[i] = (byte) (c[i] + 'A');
		}
		return c_byte;
	}

	/**
	 * Encrypt the given plaintext byte array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>.
	 * 
	 * @param p
	 *            the given plaintext byte array
	 * 
	 * @return The encrypted ciphertext byte array.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isValidKey()</code>
	 * 
	 * @throws NullPointerException
	 *             If <code>p == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(p).length != this.keySide()</code>
	 */
	public byte[] encrypt(byte[] p) throws IllegalStateException, NullPointerException, IllegalArgumentException {
		this.validateKey();
		return this.encryptFixedInput(CryptoTools.clean(p));
	}

	/**
	 * Decrypt the given ciphertext char array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>. <br>
	 * Precondition: <code>this.isValidKey()</code> <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code>
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @return The decrypted plaintext char array.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>c.length != this.keySide()</code>
	 */
	protected char[] decryptFixedInput(char[] c) throws IllegalArgumentException {
		if (c.length != this.keySide()) {
			throw new IllegalArgumentException();
		}

		// [p] = [c] * [key^-1] (mod CryptoTools.ENGLISH_ALPHABET_SIZE)
		final int[] data = new int[c.length];
		for (int i = 0; i != c.length; ++i) {
			data[i] = c[i] - 'A';
		}
		final MatrixInt result = MatrixInt.times(MatrixInt.row(data), this.keyInverse);
		final int[] p = result.modEquals(CryptoTools.ENGLISH_ALPHABET_SIZE).data[0];
		final char[] p_char = new char[p.length];
		for (int i = 0; i != p.length; ++i) {
			p_char[i] = (char) (p[i] + 'A');
		}
		return p_char;
	}

	/**
	 * Decrypt the given ciphertext char array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>.
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @return The decrypted plaintext char array.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isValidKey()</code>
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(c).length != this.keySide()</code>
	 */
	public char[] decrypt(char[] c) throws IllegalStateException, NullPointerException, IllegalArgumentException {
		this.validateKey();
		return this.decryptFixedInput(CryptoTools.clean(c));
	}

	/**
	 * Decrypt the given ciphertext byte array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>. <br>
	 * Precondition: <code>this.isValidKey()</code> <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return The decrypted plaintext byte array.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>c.length != this.keySide()</code>
	 */
	protected byte[] decryptFixedInput(byte[] c) throws IllegalArgumentException {
		if (c.length != this.keySide()) {
			throw new IllegalArgumentException();
		}

		// [p] = [c] * [key^-1] (mod CryptoTools.ENGLISH_ALPHABET_SIZE)
		final int[] data = new int[c.length];
		for (int i = 0; i != c.length; ++i) {
			data[i] = c[i] - 'A';
		}
		final MatrixInt result = MatrixInt.times(MatrixInt.row(data), this.keyInverse);
		final int[] p = result.modEquals(CryptoTools.ENGLISH_ALPHABET_SIZE).data[0];
		final byte[] p_byte = new byte[p.length];
		for (int i = 0; i != p.length; ++i) {
			p_byte[i] = (byte) (p[i] + 'A');
		}
		return p_byte;
	}

	/**
	 * Decrypt the given ciphertext byte array. <code>this</code> must be successfully prepared using
	 * <code>prepareKey()</code>.
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return The decrypted plaintext byte array.
	 * 
	 * @throws IllegalStateException
	 *             If <code>!this.isValidKey()</code>
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(c).length != this.keySide()</code>
	 */
	public byte[] decrypt(byte[] c) throws IllegalStateException, NullPointerException, IllegalArgumentException {
		this.validateKey();
		return this.decryptFixedInput(CryptoTools.clean(c));
	}
}
