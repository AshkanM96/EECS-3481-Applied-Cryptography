package util;

/**
 * Caesar cipher.
 * 
 * @author Ashkan Moatamed
 */
public class Caesar {
	/**
	 * Dependencies: <code>
	 * 		1. util.CryptoTools
	 * 		2. util.ShortPlaintextException
	 * 		3. util.ShortCiphertextException
	 * </code>
	 */

	/**
	 * Prevent instantiation.
	 */
	private Caesar() {
		// Empty by design.
	}

	/**
	 * Return the equivalent key to the given key <code>(mod CryptoTools.ENGLISH_ALPHABET_SIZE)</code>.
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @return The fixed key.
	 */
	public static int fixKey(int key) {
		// return MathUtil.mod(key, CryptoTools.ENGLISH_ALPHABET_SIZE);

		return (((key %= CryptoTools.ENGLISH_ALPHABET_SIZE) < 0) ? (key += CryptoTools.ENGLISH_ALPHABET_SIZE) : key);
	}

	/**
	 * Encrypt the given plaintext char with the given key. <br>
	 * Precondition: <code>key == Caesar.fixKey(key)</code> <br>
	 * Precondition: <code>CryptoTools.isUpperEnglish(p)</code>
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param p
	 *            the given plaintext char
	 * 
	 * @return The encrypted ciphertext char.
	 */
	protected static char encryptFixedInput(int key, int p) {
		return ((char) ((p - 'A' + key) % CryptoTools.ENGLISH_ALPHABET_SIZE + 'A'));
	}

	/**
	 * Encrypt the given plaintext char with the given key.
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param p
	 *            the given plaintext char
	 * 
	 * @return The encrypted ciphertext char.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!CryptoTools.isUpperEnglish(p)</code>
	 */
	public static char encrypt(int key, int p) throws IllegalArgumentException {
		return Caesar.encryptFixedInput(Caesar.fixKey(key), CryptoTools.upperEnglish(p));
	}

	/**
	 * Encrypt the given plaintext char array with the given key. <br>
	 * Precondition: <code>key == Caesar.fixKey(key)</code> <br>
	 * Precondition: <code>Arrays.equals(p, CryptoTools.clean(p))</code>
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param p
	 *            the given plaintext char array
	 * 
	 * @return The encrypted ciphertext char array.
	 */
	protected static char[] encryptFixedInput(int key, char[] p) {
		final char[] data = new char[p.length];
		for (int i = 0; i != p.length; ++i) {
			data[i] = Caesar.encryptFixedInput(key, p[i]);
		}
		return data;
	}

	/**
	 * Encrypt the given plaintext char array with the given key.
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param p
	 *            the given plaintext char array
	 * 
	 * @return The encrypted ciphertext char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>p == null</code>
	 */
	public static char[] encrypt(int key, char[] p) throws NullPointerException {
		return Caesar.encryptFixedInput(Caesar.fixKey(key), CryptoTools.clean(p));
	}

	/**
	 * Encrypt the given plaintext byte array with the given key. <br>
	 * Precondition: <code>key == Caesar.fixKey(key)</code> <br>
	 * Precondition: <code>Arrays.equals(p, CryptoTools.clean(p))</code>
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param p
	 *            the given plaintext byte array
	 * 
	 * @return The encrypted ciphertext byte array.
	 */
	protected static byte[] encryptFixedInput(int key, byte[] p) {
		final byte[] data = new byte[p.length];
		for (int i = 0; i != p.length; ++i) {
			data[i] = (byte) Caesar.encryptFixedInput(key, p[i]);
		}
		return data;
	}

	/**
	 * Encrypt the given plaintext byte array with the given key.
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param p
	 *            the given plaintext byte array
	 * 
	 * @return The encrypted ciphertext byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>p == null</code>
	 */
	public static byte[] encrypt(int key, byte[] p) throws NullPointerException {
		return Caesar.encryptFixedInput(Caesar.fixKey(key), CryptoTools.clean(p));
	}

	/**
	 * Decrypt the given ciphertext char with the given key. <br>
	 * Precondition: <code>key == Caesar.fixKey(key)</code> <br>
	 * Precondition: <code>CryptoTools.isUpperEnglish(c)</code>
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param c
	 *            the given ciphertext char
	 * 
	 * @return The decrypted plaintext char.
	 */
	protected static char decryptFixedInput(int key, int c) {
		return ((char) ((c - 'A' - key + CryptoTools.ENGLISH_ALPHABET_SIZE) % CryptoTools.ENGLISH_ALPHABET_SIZE + 'A'));
	}

	/**
	 * Decrypt the given ciphertext char with the given key.
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param c
	 *            the given ciphertext char
	 * 
	 * @return The decrypted plaintext char.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!CryptoTools.isUpperEnglish(c)</code>
	 */
	public static char decrypt(int key, int c) throws IllegalArgumentException {
		return Caesar.decryptFixedInput(Caesar.fixKey(key), CryptoTools.upperEnglish(c));
	}

	/**
	 * Decrypt the given ciphertext char array with the given key. <br>
	 * Precondition: <code>key == Caesar.fixKey(key)</code> <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code>
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @return The decrypted plaintext char array.
	 */
	protected static char[] decryptFixedInput(int key, char[] c) {
		final char[] data = new char[c.length];
		for (int i = 0; i != c.length; ++i) {
			data[i] = Caesar.decryptFixedInput(key, c[i]);
		}
		return data;
	}

	/**
	 * Decrypt the given ciphertext char array with the given key.
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @return The decrypted plaintext char array.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 */
	public static char[] decrypt(int key, char[] c) throws NullPointerException {
		return Caesar.decryptFixedInput(Caesar.fixKey(key), CryptoTools.clean(c));
	}

	/**
	 * Decrypt the given ciphertext byte array with the given key. <br>
	 * Precondition: <code>key == Caesar.fixKey(key)</code> <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code>
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return The decrypted plaintext byte array.
	 */
	protected static byte[] decryptFixedInput(int key, byte[] c) {
		final byte[] data = new byte[c.length];
		for (int i = 0; i != c.length; ++i) {
			data[i] = (byte) Caesar.decryptFixedInput(key, c[i]);
		}
		return data;
	}

	/**
	 * Decrypt the given ciphertext byte array with the given key.
	 * 
	 * @param key
	 *            the given key
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return The decrypted plaintext byte array.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 */
	public static byte[] decrypt(int key, byte[] c) throws NullPointerException {
		return Caesar.decryptFixedInput(Caesar.fixKey(key), CryptoTools.clean(c));
	}

	/**
	 * Given a single ciphertext char and its associated plaintext char, compute the appropriate caesar
	 * cipher key.
	 * 
	 * @param c
	 *            the given ciphertext char
	 * 
	 * @param p
	 *            the given plaintext char
	 * 
	 * @return The resulting caesar key.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(!CryptoTools.isUpperEnglish(c)) || (!CryptoTools.isUpperEnglish(p))</code>
	 */
	public static int key(int c, int p) throws IllegalArgumentException {
		return (CryptoTools.upperEnglish(c) - CryptoTools.upperEnglish(p) + CryptoTools.ENGLISH_ALPHABET_SIZE)
				% CryptoTools.ENGLISH_ALPHABET_SIZE;
	}

	/**
	 * Launch an exhaustive KCA against the given ciphertext assuming that it has been encrypted using a
	 * caesar cipher. <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code> <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.key == probableCaesarKey</code> <br>
	 * Postcondition: <code>Result.plaintext == probablePlaintext</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting CryptoInfoCaesar object.
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>c.length == 0</code>
	 */
	protected static CryptoInfoCaesar kcaExhaustiveFixedInput(byte[] c, boolean print) throws ShortCiphertextException {
		if (c.length == 0) {
			throw new ShortCiphertextException();
		}

		/*
		 * Try every possible key for the caesar cipher and for each key, compute the dot product of the
		 * letter probabilities of the decrypted text and the probabilities of the English letters. The most
		 * likely key is the one that has the maximum dot product. This method is basically finding the
		 * maximal dot product with the English alphabet which determines proximity to English.
		 */
		int probableCaesarKey = 0;
		double maxDotProduct = -1.0, dotProduct = 0.0;
		byte[] probablePlaintext = new byte[c.length], decrytedText = new byte[c.length];
		for (int caesarKey = 0; caesarKey != CryptoTools.ENGLISH_ALPHABET_SIZE; ++caesarKey) {
			// Decrypt the ciphertext using caesarKey.
			for (int i = 0; i != c.length; ++i) {
				decrytedText[i] = (byte) Caesar.decryptFixedInput(caesarKey, c[i]);
			}

			/*
			 * Compute dot product and keep track of the maximum dot product, the associated key and decrypted
			 * text.
			 */
			dotProduct = CryptoTools.getEnglishProbabilitiesDotProductFixedInput(decrytedText);
			if (Double.compare(maxDotProduct, dotProduct) < 0) { // i.e., maxDotProduct < dotProduct
				maxDotProduct = dotProduct;
				probableCaesarKey = caesarKey;
				System.arraycopy(decrytedText, 0, probablePlaintext, 0, c.length);
			}
			// Only print if requested.
			if (print) {
				System.out.println("Caesar key " + caesarKey + " gives dot product " + dotProduct + ".\n");
			}
		}

		// Only print if requested.
		if (print) {
			System.out.println("Probable caesar key is " + probableCaesarKey + ".\n");
			System.out.println("Probable plaintext is:");
			for (final byte b : probablePlaintext) {
				System.out.print((char) b);
			}
			System.out.println('\n');
		}
		return new CryptoInfoCaesar(probableCaesarKey, probablePlaintext);
	}

	/**
	 * Launch an exhaustive KCA against the given ciphertext assuming that it has been encrypted using a
	 * caesar cipher. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.key == probableCaesarKey</code> <br>
	 * Postcondition: <code>Result.plaintext == probablePlaintext</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return <code>Caesar.kcaExhaustiveFixedInput(CryptoTools.clean(c), print)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length == 0</code>
	 */
	public static CryptoInfoCaesar kcaExhaustive(byte[] c, boolean print)
			throws NullPointerException, ShortCiphertextException {
		return Caesar.kcaExhaustiveFixedInput(CryptoTools.clean(c), print);
	}

	/**
	 * Launch an exhaustive KCA against the given ciphertext assuming that it has been encrypted using a
	 * caesar cipher. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.key == probableCaesarKey</code> <br>
	 * Postcondition: <code>Result.plaintext == probablePlaintext</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return <code>Caesar.kcaExhaustiveFixedInput(CryptoTools.clean(c), false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length == 0</code>
	 */
	public static CryptoInfoCaesar kcaExhaustive(byte[] c) throws NullPointerException, ShortCiphertextException {
		return Caesar.kcaExhaustiveFixedInput(CryptoTools.clean(c), false);
	}

	/**
	 * Launch a cryptanalytic KCA against the given ciphertext assuming that it has been encrypted using
	 * a caesar cipher. <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code> <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.key == probableCaesarKey</code> <br>
	 * Postcondition: <code>Result.plaintext == probablePlaintext</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting CryptoInfoCaesar object.
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>c.length == 0</code>
	 */
	protected static CryptoInfoCaesar kcaCryptanalyticFixedInput(byte[] c, boolean print)
			throws ShortCiphertextException {
		if (c.length == 0) {
			throw new ShortCiphertextException();
		}

		/*
		 * 'E' is the most common letter in English and as such the letter with the highest probability is
		 * probably the mapping of 'E'. Save that mapping letter in maxProbLetter so that the caesar key can
		 * be found from it after the loop.
		 */
		char maxProbLetter = '\0', letter = '\0';
		double maxProb = -1.0, prob = 0.0;
		final int[] freq = CryptoTools.getFrequenciesFixedInput(c);
		for (int i = 0; i != CryptoTools.ENGLISH_ALPHABET_SIZE; ++i) {
			letter = (char) ('A' + i);
			prob = 100.0 * freq[i] / c.length;
			if (Double.compare(maxProb, prob) < 0) { // i.e., maxProb < prob
				maxProb = prob;
				maxProbLetter = letter;
			}
			// Only print if requested.
			if (print) {
				System.out.println("prob[\'" + letter + "\'] == " + prob + "\n");
			}
		}

		// Find the caesar key from the mapping of 'E' and then decrypt the ciphertext using it.
		final int probableCaesarKey = Caesar.key(maxProbLetter, 'E');
		final byte[] probablePlaintext = new byte[c.length];
		for (int i = 0; i != c.length; ++i) {
			probablePlaintext[i] = (byte) Caesar.decryptFixedInput(probableCaesarKey, c[i]);
		}
		// Only print if requested.
		if (print) {
			System.out.println("Probable caesar key is " + probableCaesarKey + ".\n");
			System.out.println("Probable plaintext is:");
			for (final byte b : probablePlaintext) {
				System.out.print((char) b);
			}
			System.out.println('\n');
		}
		return new CryptoInfoCaesar(probableCaesarKey, probablePlaintext);
	}

	/**
	 * Launch a cryptanalytic KCA against the given ciphertext assuming that it has been encrypted using
	 * a caesar cipher. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.key == probableCaesarKey</code> <br>
	 * Postcondition: <code>Result.plaintext == probablePlaintext</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return <code>Caesar.kcaCryptanalyticFixedInput(CryptoTools.clean(c), print)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length == 0</code>
	 */
	public static CryptoInfoCaesar kcaCryptanalytic(byte[] c, boolean print)
			throws NullPointerException, ShortCiphertextException {
		return Caesar.kcaCryptanalyticFixedInput(CryptoTools.clean(c), print);
	}

	/**
	 * Launch a cryptanalytic KCA against the given ciphertext assuming that it has been encrypted using
	 * a caesar cipher. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.key == probableCaesarKey</code> <br>
	 * Postcondition: <code>Result.plaintext == probablePlaintext</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return <code>Caesar.kcaCryptanalyticFixedInput(CryptoTools.clean(c), false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length == 0</code>
	 */
	public static CryptoInfoCaesar kcaCryptanalytic(byte[] c) throws NullPointerException, ShortCiphertextException {
		return Caesar.kcaCryptanalyticFixedInput(CryptoTools.clean(c), false);
	}

	// --------------------------------------------------
	// Nested wrapper class.
	// --------------------------------------------------

	/**
	 * Simple wrapper for decryption results. Saves possible caesar cipher key value and plaintext. <br>
	 * 
	 * This class does not encapsulate the information. It in fact saves direct mutable pointers passed
	 * to its ctors without creating copies therefore it is not "safe" in terms of OOP. <br>
	 * 
	 * It's main usage is for the return type of <code>Caesar::kcaExhaustive</code> and
	 * <code>Caesar::kcaCryptanalytic</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class CryptoInfoCaesar {
		/**
		 * Possible caesar cipher key value.
		 */
		public final int key;

		/**
		 * Possible plaintext.
		 */
		public final byte[] plaintext;

		/**
		 * @param key
		 *            the given caesar cipher key
		 * 
		 * @param plaintext
		 *            the given plaintext
		 * 
		 * @throws NullPointerException
		 *             If <code>plaintext == null</code>
		 * 
		 * @throws ShortPlaintextException
		 *             If <code>plaintext.length == 0</code>
		 */
		public CryptoInfoCaesar(int key, byte[] plaintext) throws NullPointerException, ShortPlaintextException {
			// Plaintext must be non-empty.
			if (plaintext.length == 0) {
				throw new ShortPlaintextException();
			}

			// Set this.
			this.key = key;
			this.plaintext = plaintext;
		}
	}
}
