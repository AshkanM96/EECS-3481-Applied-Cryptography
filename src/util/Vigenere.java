package util;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Vigenere cipher.
 * 
 * @author Ashkan Moatamed
 */
public class Vigenere implements Iterable<Character> {
	/**
	 * Dependencies: <code>
	 * 		1. util.CryptoTools
	 * 		2. util.Caesar
	 * 		3. util.BidirectionalIterator
	 * 		4. util.ShortPlaintextException
	 * 		5. util.ShortCiphertextException
	 * </code>
	 */

	/**
	 * Cipher key.
	 */
	protected char[] key;

	/**
	 * Key index.
	 */
	private int index;

	/**
	 * Construct a Vigenere object with the given English word key and the given index.
	 * 
	 * @param keyWord
	 *            the given English word key
	 * 
	 * @param index
	 *            the given index
	 * 
	 * @throws NullPointerException
	 *             If <code>keyWord == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(CryptoTools.clean(keyWord).length == 0)
	 *             || (index < 0) || (CryptoTools.clean(keyWord).length <= index)</code>
	 */
	public Vigenere(char[] keyWord, int index) throws NullPointerException, IllegalArgumentException {
		this.key(keyWord);
		this.index(index);
	}

	/**
	 * Construct a Vigenere object with the given English word key and <code>0</code> index.
	 * 
	 * @param keyWord
	 *            the given English word key
	 * 
	 * @throws NullPointerException
	 *             If <code>keyWord == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(keyWord).length == 0</code>
	 */
	public Vigenere(char[] keyWord) throws NullPointerException, IllegalArgumentException {
		this(keyWord, 0);
	}

	/**
	 * Copy ctor.
	 * 
	 * @param other
	 *            the given Vigenere object
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 */
	public Vigenere(Vigenere other) throws NullPointerException {
		this.key = new char[other.key.length];
		System.arraycopy(other.key, 0, this.key, 0, this.key.length);
		this.index = other.index;
	}

	/**
	 * Get the key representation of the given English word.
	 * 
	 * @param keyWord
	 *            the given English word key
	 * 
	 * @return The resulting key.
	 * 
	 * @throws NullPointerException
	 *             If <code>keyWord == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(keyWord).length == 0</code>
	 */
	public static char[] keyWordToKey(char[] keyWord) throws NullPointerException, IllegalArgumentException {
		final char[] key = CryptoTools.clean(keyWord);
		if (key.length == 0) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i != key.length; ++i) {
			/*
			 * key[i] - 'A' is already a "fixed" key since 'A' <= key[i] <= 'Z' is true for all valid i after
			 * the call to key = CryptoTools.clean(keyWord) and as such 0 <= key[i] - 'A' <= 25.
			 */
			key[i] = (char) (key[i] - 'A');
		}
		return key;
	}

	/**
	 * Get the English word representation of the given key.
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @return The resulting English word key.
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 */
	public static char[] keyToKeyWord(char[] key) throws NullPointerException {
		final char[] result = new char[key.length];
		for (int i = 0; i != key.length; ++i) {
			result[i] = (char) (key[i] + 'A');
		}
		return result;
	}

	/**
	 * @return A shallow copy of <code>this.key</code>.
	 */
	public char[] key() {
		final char[] result = new char[this.key.length];
		System.arraycopy(this.key, 0, result, 0, result.length);
		return result;
	}

	/**
	 * @return The English word key.
	 */
	public char[] keyWord() {
		return Vigenere.keyToKeyWord(this.key);
	}

	/**
	 * Set the calling object's key to the given English word key.
	 * 
	 * @param keyWord
	 *            the given English word key
	 * 
	 * @throws NullPointerException
	 *             If <code>keyWord == null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(keyWord).length == 0</code>
	 */
	public void key(char[] keyWord) throws NullPointerException, IllegalArgumentException {
		this.key = Vigenere.keyWordToKey(keyWord);
		this.index = 0;
	}

	/**
	 * @return <code>this.key.length</code>.
	 */
	public int keyLength() {
		return this.key.length;
	}

	/**
	 * @param i
	 *            the given index
	 * 
	 * @return <code>this.key[i]</code>.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(i < 0) || (this.key.length <= i)</code>
	 */
	public char key(int i) throws IndexOutOfBoundsException {
		return this.key[i];
	}

	/**
	 * <code>this.key[i] = (char) (CryptoTools.upperEnglish(c) - 'A')</code>.
	 * 
	 * @param i
	 *            the given index
	 * 
	 * @param c
	 *            the given character
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(i < 0) || (this.key.length <= i)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!CryptoTools.isUpperEnglish(c)</code>
	 */
	public void key(int i, int c) throws IndexOutOfBoundsException, IllegalArgumentException {
		this.key[i] = (char) (CryptoTools.upperEnglish(c) - 'A');
	}

	/**
	 * @param i
	 *            the given index
	 * 
	 * @return <code>this.key[i] + 'A'</code>.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(i < 0) || (this.key.length <= i)</code>
	 */
	public char keyWord(int i) throws IndexOutOfBoundsException {
		return ((char) (this.key[i] + 'A'));
	}

	/**
	 * @param index
	 *            the given index
	 * 
	 * @return <code>(0 <= index) && (index < this.key.length)</code>.
	 */
	public boolean isValidIndex(int index) {
		return ((0 <= index) && (index < this.key.length));
	}

	/**
	 * @param index
	 *            the given index
	 * 
	 * @return <code>index</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!this.isValidIndex(index)</code>
	 */
	public int validateIndex(int index) throws IllegalArgumentException {
		if (!this.isValidIndex(index)) {
			throw new IllegalArgumentException();
		}
		return index;
	}

	/**
	 * @return <code>this.index</code>.
	 */
	public int index() {
		return this.index;
	}

	/**
	 * Set the calling object's index to the given index and return the old index.
	 * 
	 * @param index
	 *            the given index
	 * 
	 * @return The old index.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!this.isValidIndex(index)</code>
	 */
	public int index(int index) throws IllegalArgumentException {
		index = this.validateIndex(index);
		final int oldIndex = this.index;
		this.index = index;
		return oldIndex;
	}

	/**
	 * Reset the calling object's index to <code>0</code> and return the old index.
	 * 
	 * @return The old index.
	 */
	public int resetIndex() {
		final int oldIndex = this.index;
		this.index = 0;
		return oldIndex;
	}

	/**
	 * @return <code>new String(this.keyWord())</code>.
	 */
	@Override
	public String toString() {
		return new String(this.keyWord());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.key);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return ((obj instanceof Vigenere) ? this.equals((Vigenere) obj) : false);
	}

	/**
	 * @param other
	 *            the given Vigenere object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(Vigenere other) {
		return ((other == null) ? false : ((this == other) ? true : Arrays.equals(this.key, other.key)));
	}

	/**
	 * @param supportsMutation
	 *            indicates whether the iterator can mutate <code>this</code>
	 * 
	 * @return <code>new VigenereIterator(this, supportsMutation)</code>.
	 */
	public VigenereIterator iterator(boolean supportsMutation) {
		return new VigenereIterator(this, supportsMutation);
	}

	/**
	 * @return <code>new VigenereIterator(this, true)</code>.
	 */
	@Override
	public VigenereIterator iterator() {
		return new VigenereIterator(this, true);
	}

	/**
	 * Encrypt the given plaintext char. <br>
	 * Precondition: <code>CryptoTools.isUpperEnglish(p)</code>
	 * 
	 * @param p
	 *            the given plaintext char
	 * 
	 * @return The encrypted ciphertext char.
	 */
	protected char encryptFixedInput(int p) {
		final char result = Caesar.encryptFixedInput(this.key[this.index], p);
		if (++this.index == this.key.length) {
			this.index = 0;
		}
		// this.index = (this.index + 1) % this.key.length;
		return result;
	}

	/**
	 * Encrypt the given plaintext char.
	 * 
	 * @param p
	 *            the given plaintext char
	 * 
	 * @return The encrypted ciphertext char.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!CryptoTools.isUpperEnglish(p)</code>
	 */
	public char encrypt(int p) throws IllegalArgumentException {
		return this.encryptFixedInput(CryptoTools.upperEnglish(p));
	}

	/**
	 * Encrypt the given plaintext char array. <br>
	 * Precondition: <code>Arrays.equals(p, CryptoTools.clean(p))</code>
	 * 
	 * @param p
	 *            the given plaintext char array
	 * 
	 * @return The encrypted ciphertext char array.
	 */
	protected char[] encryptFixedInput(char[] p) {
		final char[] data = new char[p.length];
		for (int i = 0; i != p.length; ++i) {
			data[i] = this.encryptFixedInput(p[i]);
		}
		return data;
	}

	/**
	 * Encrypt the given plaintext char array.
	 * 
	 * @param p
	 *            the given plaintext char array
	 * 
	 * @return The encrypted ciphertext char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>p == null</code>
	 */
	public char[] encrypt(char[] p) throws NullPointerException {
		return this.encryptFixedInput(CryptoTools.clean(p));
	}

	/**
	 * Encrypt the given plaintext byte array. <br>
	 * Precondition: <code>Arrays.equals(p, CryptoTools.clean(p))</code>
	 * 
	 * @param p
	 *            the given plaintext byte array
	 * 
	 * @return The encrypted ciphertext byte array.
	 */
	protected byte[] encryptFixedInput(byte[] p) {
		final byte[] data = new byte[p.length];
		for (int i = 0; i != p.length; ++i) {
			data[i] = (byte) this.encryptFixedInput(p[i]);
		}
		return data;
	}

	/**
	 * Encrypt the given plaintext byte array.
	 * 
	 * @param p
	 *            the given plaintext byte array
	 * 
	 * @return The encrypted ciphertext byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>p == null</code>
	 */
	public byte[] encrypt(byte[] p) throws NullPointerException {
		return this.encryptFixedInput(CryptoTools.clean(p));
	}

	/**
	 * Decrypt the given ciphertext char assuming it was the most recent plaintext char encrypted by the
	 * calling object. <br>
	 * Precondition: <code>CryptoTools.isUpperEnglish(c)</code>
	 * 
	 * @param c
	 *            the given ciphertext char
	 * 
	 * @return The decrypted plaintext char.
	 */
	protected char decryptFixedInput(int c) {
		final int index = (this.index == 0 ? this.key.length - 1 : this.index - 1);
		final char result = Caesar.decryptFixedInput(this.key[index], c);
		this.index = index;
		return result;
	}

	/**
	 * Decrypt the given ciphertext char assuming it was the most recent plaintext char encrypted by the
	 * calling object.
	 * 
	 * @param c
	 *            the given ciphertext char
	 * 
	 * @return The decrypted plaintext char.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!CryptoTools.isUpperEnglish(c)</code>
	 */
	public char decrypt(int c) throws IllegalArgumentException {
		return this.decryptFixedInput(CryptoTools.upperEnglish(c));
	}

	/**
	 * Decrypt the given ciphertext char array assuming it was the most recent plaintext char array
	 * encrypted by the calling object. <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code>
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @return The decrypted plaintext char array.
	 */
	protected char[] decryptFixedInput(char[] c) {
		final char[] data = new char[c.length];
		for (int i = c.length - 1; i != -1; --i) {
			data[i] = this.decryptFixedInput(c[i]);
		}
		return data;
	}

	/**
	 * Decrypt the given ciphertext char array assuming it was the most recent plaintext char array
	 * encrypted by the calling object.
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @return The decrypted plaintext char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 */
	public char[] decrypt(char[] c) throws NullPointerException {
		return this.decryptFixedInput(CryptoTools.clean(c));
	}

	/**
	 * Decrypt the given ciphertext char array with the given English word key starting from the given
	 * index. <br>
	 * Precondition:
	 * <code>Arrays.equals(keyWord, Vigenere.keyToKeyWord(Vigenere.keyWordToKey(keyWord)))</code> <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code> <br>
	 * Precondition: <code>(0 <= index) && (index < keyWord.length)</code>
	 * 
	 * @param keyWord
	 *            the given English word key
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @param index
	 *            the given index
	 * 
	 * @return The decrypted plaintext char array.
	 */
	protected static char[] decryptFixedInput(char[] keyWord, char[] c, int index) {
		final char[] key = new char[keyWord.length];
		for (int i = 0; i != keyWord.length; ++i) {
			key[i] = (char) (keyWord[i] - 'A');
		}

		final char[] data = new char[c.length];
		for (int i = 0; i != c.length; ++i) {
			data[i] = Caesar.decryptFixedInput(key[index], c[i]);
			if (++index == key.length) {
				index = 0;
			}
			// index = (index + 1) % key.length;
		}
		return data;
	}

	/**
	 * Decrypt the given ciphertext char array with the given English word key starting from the given
	 * index.
	 * 
	 * @param keyWord
	 *            the given English word key
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @param index
	 *            the given index
	 * 
	 * @return The decrypted plaintext char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(keyWord == null) || (c == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(keyWord).length == 0</code>
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(index < 0) || (CryptoTools.clean(keyWord).length <= index)</code>
	 */
	public static char[] decrypt(char[] keyWord, char[] c, int index)
			throws NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {
		return Vigenere.decryptFixedInput(Vigenere.keyToKeyWord(Vigenere.keyWordToKey(keyWord)), CryptoTools.clean(c),
				index);
	}

	/**
	 * Decrypt the given ciphertext char array with the given English word key starting from the first
	 * index.
	 * 
	 * @param keyWord
	 *            the given English word key
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @return The decrypted plaintext char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(keyWord == null) || (c == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(keyWord).length == 0</code>
	 */
	public static char[] decrypt(char[] keyWord, char[] c) throws NullPointerException, IllegalArgumentException {
		return Vigenere.decryptFixedInput(Vigenere.keyToKeyWord(Vigenere.keyWordToKey(keyWord)), CryptoTools.clean(c),
				0);
	}

	/**
	 * Decrypt the given ciphertext byte array assuming it was the most recent plaintext byte array
	 * encrypted by the calling object. <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return The decrypted plaintext byte array.
	 */
	protected byte[] decryptFixedInput(byte[] c) {
		final byte[] data = new byte[c.length];
		for (int i = c.length - 1; i != -1; --i) {
			data[i] = (byte) this.decryptFixedInput(c[i]);
		}
		return data;
	}

	/**
	 * Decrypt the given ciphertext byte array assuming it was the most recent plaintext byte array
	 * encrypted by the calling object.
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return The decrypted plaintext byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 */
	public byte[] decrypt(byte[] c) throws NullPointerException {
		return this.decryptFixedInput(CryptoTools.clean(c));
	}

	/**
	 * Decrypt the given ciphertext byte array with the given English word key starting from the given
	 * index. <br>
	 * Precondition:
	 * <code>Arrays.equals(keyWord, Vigenere.keyToKeyWord(Vigenere.keyWordToKey(keyWord)))</code> <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code> <br>
	 * Precondition: <code>(0 <= index) && (index < keyWord.length)</code>
	 * 
	 * @param keyWord
	 *            the given English word key
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param index
	 *            the given index
	 * 
	 * @return The decrypted plaintext byte array.
	 */
	protected static byte[] decryptFixedInput(char[] keyWord, byte[] c, int index) {
		final char[] key = new char[keyWord.length];
		for (int i = 0; i != keyWord.length; ++i) {
			key[i] = (char) (keyWord[i] - 'A');
		}

		final byte[] data = new byte[c.length];
		for (int i = 0; i != c.length; ++i) {
			data[i] = (byte) Caesar.decryptFixedInput(key[index], c[i]);
			if (++index == key.length) {
				index = 0;
			}
			// index = (index + 1) % key.length;
		}
		return data;
	}

	/**
	 * Decrypt the given ciphertext byte array with the given English word key starting from the given
	 * index.
	 * 
	 * @param keyWord
	 *            the given English word key
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param index
	 *            the given index
	 * 
	 * @return The decrypted plaintext byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(keyWord == null) || (c == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(keyWord).length == 0</code>
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(index < 0) || (CryptoTools.clean(keyWord).length <= index)</code>
	 */
	public static byte[] decrypt(char[] keyWord, byte[] c, int index)
			throws NullPointerException, IllegalArgumentException, IndexOutOfBoundsException {
		return Vigenere.decryptFixedInput(Vigenere.keyToKeyWord(Vigenere.keyWordToKey(keyWord)), CryptoTools.clean(c),
				index);
	}

	/**
	 * Decrypt the given ciphertext byte array with the given English word key starting from the first
	 * index.
	 * 
	 * @param keyWord
	 *            the given English word key
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @return The decrypted plaintext byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>(keyWord == null) || (c == null)</code>
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>CryptoTools.clean(keyWord).length == 0</code>
	 */
	public static byte[] decrypt(char[] keyWord, byte[] c) throws NullPointerException, IllegalArgumentException {
		return Vigenere.decryptFixedInput(Vigenere.keyToKeyWord(Vigenere.keyWordToKey(keyWord)), CryptoTools.clean(c),
				0);
	}

	/**
	 * Launch an exhaustive KCA against the given ciphertext assuming that it has been encrypted using a
	 * vigenere cipher to find only the key length. <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code> <br>
	 * Postcondition:
	 * <code>(Result == 0) implies (Unable to find any possible vigenere cipher key length)</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The probable vigenere key length.
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>c.length < 2</code>
	 */
	protected static int keyLengthExhaustiveAttackFixedInput(byte[] c, boolean print) throws ShortCiphertextException {
		if (c.length < 2) {
			throw new ShortCiphertextException();
		}

		/*
		 * The maximum possible length of the vigenere cipher key. If the key is any longer then it just
		 * cannot be determined through this method.
		 */
		final int maxKeyLength = c.length / 2;

		/*
		 * Try every possible key length for the vigenere cipher. For each key length, take the appropriate
		 * subset and compute the Index of Coincidence. The first key length that gives an Index of
		 * Coincidence within the range of English is the probable key length. If key length gives such an
		 * Index of Coincidence then return 0.
		 */
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] subset = null;
		double IC = 0.0;
		for (int keyLength = 1; keyLength != maxKeyLength; ++keyLength) {
			for (int i = 0; i < c.length; i += keyLength) {
				baos.write(c[i]);
			}
			subset = baos.toByteArray();
			baos.reset();
			IC = CryptoTools.getICFixedInput(subset);
			// Only print if requested.
			if (print) {
				System.out.println("Vigenere key length " + keyLength + " gives Index of Coincidence " + IC + ".\n");
			}

			if (CryptoTools.isEnglishIC(IC)) {
				// Only print if requested.
				if (print) {
					System.out.println("Probable vigenere key length is " + keyLength + ".\n");
				}
				return keyLength;
			}
		}

		// Only print if requested.
		if (print) {
			System.out.println(
					"Unable to find any possible vigenere cipher key length in range [1, " + maxKeyLength + "].\n");
		}
		return 0;
	}

	/**
	 * Launch an exhaustive KCA against the given ciphertext assuming that it has been encrypted using a
	 * vigenere cipher to find only the key length. <br>
	 * Postcondition:
	 * <code>(Result == 0) implies (Unable to find any possible vigenere cipher key length)</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return <code>Vigenere.keyLengthExhaustiveAttackFixedInput(CryptoTools.clean(c), print)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length < 2</code>
	 */
	public static int keyLengthExhaustiveAttack(byte[] c, boolean print)
			throws NullPointerException, ShortCiphertextException {
		return Vigenere.keyLengthExhaustiveAttackFixedInput(CryptoTools.clean(c), print);
	}

	/**
	 * Launch an exhaustive KCA against the given ciphertext assuming that it has been encrypted using a
	 * vigenere cipher to find only the key length. <br>
	 * Postcondition:
	 * <code>(Result == 0) implies (Unable to find any possible vigenere cipher key length)</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return <code>Vigenere.keyLengthExhaustiveAttackFixedInput(CryptoTools.clean(c), false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length < 2</code>
	 */
	public static int keyLengthExhaustiveAttack(byte[] c) throws NullPointerException, ShortCiphertextException {
		return Vigenere.keyLengthExhaustiveAttackFixedInput(CryptoTools.clean(c), false);
	}

	/**
	 * Launch a cryptanalytic KCA against the given ciphertext assuming that it has been encrypted using
	 * a vigenere cipher. The key length, however, will be attacked exhaustively. <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code> <br>
	 * Postcondition:
	 * <code>(Result == null) if and only if (Unable to find any possible vigenere cipher key length)</code>
	 * <br>
	 * Postcondition: <code>(Result != null) implies (Result.keyWord == probableVigenereKeyWord)</code>
	 * <br>
	 * Postcondition: <code>(Result != null) implies (Result.plaintext == probablePlaintext)</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting CryptoInfoVigenere object.
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>c.length < 2</code>
	 */
	protected static CryptoInfoVigenere kcaCryptanalyticFixedInput(byte[] c, boolean print)
			throws ShortCiphertextException {
		final int keyLength = Vigenere.keyLengthExhaustiveAttackFixedInput(c, print);
		if (keyLength == 0) {
			// Only print if requested.
			if (print) {
				System.out.println("keyLength == 0\n");
			}
			return null;
		}

		/*
		 * Take subsets of length keyLength from the ciphertext and find the most frequent letter. That
		 * letter is probably the mapping of 'E' since 'E' is the most common letter in English and the
		 * subset is just English letters shifted by the same character of the vigenere cipher key.
		 */
		final char[] keyWord = new char[keyLength];
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int[] freq = null;
		int maxFreq = 0;
		char maxFreqLetter = '\0';
		for (int keyWordFilledIndex = 0; keyWordFilledIndex != keyLength; ++keyWordFilledIndex) {
			// Cannot use i != c.length as loop condition since incrementing i by keyLength instead of by 1.
			for (int i = keyWordFilledIndex; i < c.length; i += keyLength) {
				baos.write(c[i]);
			}
			freq = CryptoTools.getFrequenciesFixedInput(baos.toByteArray());
			baos.reset();

			/*
			 * 'E' is the most common letter in English and as such the letter with the highest frequency is
			 * probably the mapping of 'E'. Save that mapping letter in maxFreqLetter so that the
			 * keyWordFilledIndex^th character of the vigenere key can be found from it.
			 */
			maxFreq = freq[0];
			maxFreqLetter = (char) ('A' + 0);
			for (int i = 1, f = 0; i != freq.length; ++i) {
				// The following is meant to be an assignment of f.
				if (maxFreq < (f = freq[i])) {
					maxFreq = f;
					maxFreqLetter = (char) ('A' + i);
				}
			}
			keyWord[keyWordFilledIndex] = (char) (Caesar.key(maxFreqLetter, 'E') + 'A');
			// Only print if requested.
			if (print) {
				System.out.println("Probable mapping of \'E\' is \'" + maxFreqLetter + "\'.");
				System.out.println("keyWord[" + keyWordFilledIndex + "] == \'" + keyWord[keyWordFilledIndex] + "\'\n");
			}
		}

		// Decrypt the ciphertext using keyWord.
		final byte[] probablePlaintext = Vigenere.decryptFixedInput(keyWord, c, 0);
		// Only print if requested.
		if (print) {
			System.out.println("Probable vigenere key is " + (new String(keyWord)) + ".\n");
			System.out.println("Probable plaintext is:");
			for (final byte b : probablePlaintext) {
				System.out.print((char) b);
			}
			System.out.println('\n');
		}
		return new CryptoInfoVigenere(keyWord, probablePlaintext);
	}

	/**
	 * Launch a cryptanalytic KCA against the given ciphertext assuming that it has been encrypted using
	 * a vigenere cipher. The key length, however, will be attacked exhaustively. <br>
	 * Postcondition:
	 * <code>(Result == null) if and only if (Unable to find any possible vigenere cipher key length)</code>
	 * <br>
	 * Postcondition: <code>(Result != null) implies (Result.keyWord == probableVigenereKeyWord)</code>
	 * <br>
	 * Postcondition: <code>(Result != null) implies (Result.plaintext == probablePlaintext)</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return <code>Vigenere.kcaCryptanalyticFixedInput(CryptoTools.clean(c), print)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length < 2</code>
	 */
	public static CryptoInfoVigenere kcaCryptanalytic(byte[] c, boolean print)
			throws NullPointerException, ShortCiphertextException {
		return Vigenere.kcaCryptanalyticFixedInput(CryptoTools.clean(c), print);
	}

	/**
	 * Launch a cryptanalytic KCA against the given ciphertext assuming that it has been encrypted using
	 * a vigenere cipher. The key length, however, will be attacked exhaustively. <br>
	 * Postcondition:
	 * <code>(Result == null) if and only if (Unable to find any possible vigenere cipher key length)</code>
	 * <br>
	 * Postcondition: <code>(Result != null) implies (Result.keyWord == probableVigenereKeyWord)</code>
	 * <br>
	 * Postcondition: <code>(Result != null) implies (Result.plaintext == probablePlaintext)</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return <code>Vigenere.kcaCryptanalyticFixedInput(CryptoTools.clean(c), false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length < 2</code>
	 */
	public static CryptoInfoVigenere kcaCryptanalytic(byte[] c) throws NullPointerException, ShortCiphertextException {
		return Vigenere.kcaCryptanalyticFixedInput(CryptoTools.clean(c), false);
	}

	// --------------------------------------------------
	// Nested iterator class.
	// --------------------------------------------------

	/**
	 * Simple iterator class. <br>
	 * 
	 * It's main usage is for the return type of <code>Vigenere.iterator(boolean)</code> and
	 * <code>Vigenere.iterator()</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class VigenereIterator implements BidirectionalIterator<Character>, Iterator<Character> {
		/**
		 * Indicates whether <code>this</code> can mutate the iterated over Vigenere object.
		 */
		protected final boolean supportsMutation;

		/**
		 * Cipher key.
		 */
		protected final char[] key;

		/**
		 * Current cursor index.
		 */
		protected int index;

		/**
		 * Construct a VigenereIterator object from the given Vigenere object.
		 * 
		 * @param v
		 *            the given Vigenere object
		 * 
		 * @param supportsMutation
		 *            indicates whether the iterator can mutate the given Vigenere object
		 * 
		 * @throws NullPointerException
		 *             If <code>v == null</code>
		 */
		public VigenereIterator(Vigenere v, boolean supportsMutation) throws NullPointerException {
			// The following is meant to be an assignment of this.supportsMutation and this.key.
			this.key = (this.supportsMutation = supportsMutation) ? v.key : v.key();
			this.begin();
		}

		/**
		 * Construct a VigenereIterator object from the given Vigenere object.
		 * 
		 * @param v
		 *            the given Vigenere object
		 * 
		 * @throws NullPointerException
		 *             If <code>v == null</code>
		 */
		public VigenereIterator(Vigenere v) throws NullPointerException {
			this(v, true);
		}

		/**
		 * Copy ctor.
		 * 
		 * @param other
		 *            the given VigenereIterator object
		 * 
		 * @throws NullPointerException
		 *             If <code>other == null</code>
		 */
		public VigenereIterator(VigenereIterator other) throws NullPointerException {
			// The following is meant to be an assignment of this.supportsMutation.
			if (this.supportsMutation = other.supportsMutation) {
				this.key = other.key;
			} else {
				this.key = new char[other.key.length];
				System.arraycopy(other.key, 0, this.key, 0, this.key.length);
			}
			this.index = other.index;
		}

		@Override
		public boolean supportsMutation() {
			return this.supportsMutation;
		}

		@Override
		public VigenereIterator begin() {
			this.index = 0;
			return this;
		}

		@Override
		public VigenereIterator end() {
			this.index = this.key.length;
			return this;
		}

		@Override
		public boolean hasNext() {
			return (this.index != this.key.length);
		}

		/**
		 * Postcondition: <code>Result != null</code>
		 */
		@Override
		public Character next() throws NoSuchElementException {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			// Return the element and then move the cursor.
			return ((char) (this.key[this.index++] + 'A'));
		}

		/**
		 * Postcondition: <code>Result != null</code>
		 * 
		 * @throws NullPointerException
		 *             If <code>t == null</code>
		 * 
		 * @throws IllegalArgumentException
		 *             If <code>!CryptoTools.isUpperEnglish(t)</code>
		 */
		@Override
		public Character next(Character t) throws UnsupportedOperationException, NoSuchElementException,
				NullPointerException, IllegalArgumentException {
			if (!this.supportsMutation()) {
				throw new UnsupportedOperationException();
			} else if (!this.hasNext()) {
				throw new NoSuchElementException();
			} else if (t == null) {
				throw new NullPointerException();
			} else if (!CryptoTools.isUpperEnglish(t)) {
				throw new IllegalArgumentException();
			}

			// Set the element, return the old value and then move the cursor.
			final char result = (char) (this.key[this.index] + 'A');
			this.key[this.index] = (char) (t - 'A');
			++this.index;
			return result;
		}

		@Override
		public boolean hasPrev() {
			return (this.index != 0);
		}

		/**
		 * Postcondition: <code>Result != null</code>
		 */
		@Override
		public Character prev() throws NoSuchElementException {
			if (!this.hasPrev()) {
				throw new NoSuchElementException();
			}
			// Move the cursor and then return the element.
			return ((char) (this.key[--this.index] + 'A'));
		}

		/**
		 * Postcondition: <code>Result != null</code>
		 * 
		 * @throws NullPointerException
		 *             If <code>t == null</code>
		 * 
		 * @throws IllegalArgumentException
		 *             If <code>!CryptoTools.isUpperEnglish(t)</code>
		 */
		@Override
		public Character prev(Character t) throws UnsupportedOperationException, NoSuchElementException,
				NullPointerException, IllegalArgumentException {
			if (!this.supportsMutation()) {
				throw new UnsupportedOperationException();
			} else if (!this.hasPrev()) {
				throw new NoSuchElementException();
			} else if (t == null) {
				throw new NullPointerException();
			} else if (!CryptoTools.isUpperEnglish(t)) {
				throw new IllegalArgumentException();
			}

			// Move the cursor, set the element and then return the old value.
			--this.index;
			final char result = (char) (this.key[this.index] + 'A');
			this.key[this.index] = (char) (t - 'A');
			return result;
		}
	}

	// --------------------------------------------------
	// Nested wrapper class.
	// --------------------------------------------------

	/**
	 * Simple wrapper for decryption results. Saves possible vigenere cipher English word key and
	 * plaintext. <br>
	 * 
	 * This class does not encapsulate the information. It in fact saves direct mutable pointers passed
	 * to its ctors without creating copies therefore it is not "safe" in terms of OOP. <br>
	 * 
	 * It's main usage is for the return type of <code>Vigenere::kcaCryptanalytic</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class CryptoInfoVigenere {
		/**
		 * Possible vigenere cipher English word key.
		 */
		public final char[] keyWord;

		/**
		 * Possible plaintext.
		 */
		public final byte[] plaintext;

		/**
		 * @param keyWord
		 *            the given English word key
		 * 
		 * @param plaintext
		 *            the given plaintext
		 * 
		 * @throws NullPointerException
		 *             If <code>(keyWord == null) || (plaintext == null)</code>
		 * 
		 * @throws ShortPlaintextException
		 *             If <code>plaintext.length == 0</code>
		 * 
		 * @throws IllegalArgumentException
		 *             If <code>keyWord.length == 0</code>
		 */
		public CryptoInfoVigenere(char[] keyWord, byte[] plaintext)
				throws NullPointerException, ShortPlaintextException, IllegalArgumentException {
			// All arrays must be non-empty.
			if (keyWord.length == 0) {
				throw new IllegalArgumentException();
			} else if (plaintext.length == 0) {
				throw new ShortPlaintextException();
			}

			// Set this.
			this.keyWord = keyWord;
			this.plaintext = plaintext;
		}
	}
}
