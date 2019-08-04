package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Affine cipher.
 * 
 * @author Ashkan Moatamed
 */
public class Affine {
	/**
	 * Dependencies: <code>
	 * 		1. util.CryptoTools
	 * 		2. util.MathUtil
	 * 		3. util.ShortCiphertextException
	 * </code>
	 */

	/**
	 * <code>1</code>.
	 */
	public static final int MIN_ALPHA_VALUE = 1;

	/**
	 * <code>0</code>.
	 */
	public static final int MIN_BETA_VALUE = 0;

	/**
	 * <code>CryptoTools.ENGLISH_ALPHABET_SIZE - 1</code>.
	 */
	public static final int MAX_CIPHER_KEY_VALUE = CryptoTools.ENGLISH_ALPHABET_SIZE - 1;

	/**
	 * An array of all valid alpha values in sorted order. The following is true for all valid
	 * <code>i</code>: <br>
	 * <code>(Affine.MIN_ALPHA_VALUE <= Affine.VALID_ALPHA_VALUES[i])
	 * && (Affine.VALID_ALPHA_VALUES[i] <= Affine.MAX_CIPHER_KEY_VALUE)</code> <br>
	 * <code>gcd(Affine.VALID_ALPHA_VALUES[i], CryptoTools.ENGLISH_ALPHABET_SIZE) == 1</code>
	 */
	private static final int[] VALID_ALPHA_VALUES = { 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25 };

	/**
	 * Cipher key.
	 */
	private int alpha;

	/**
	 * <code>1 / this.alpha (mod CryptoTools.ENGLISH_ALPHABET_SIZE)</code>.
	 */
	private int alphaModInverse;

	/**
	 * Cipher key.
	 */
	private int beta;

	/**
	 * Construct an Affine object with the given alpha and beta.
	 * 
	 * @param alpha
	 *            the given alpha
	 * 
	 * @param beta
	 *            the given beta
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(!Affine.isValidAlpha(alpha)) || (!Affine.isValidBeta(beta))</code>
	 */
	public Affine(int alpha, int beta) throws IllegalArgumentException {
		this.alpha(alpha);
		this.beta(beta);
	}

	/**
	 * Construct an Affine object with the given alpha and the minimum beta.
	 * 
	 * @param alpha
	 *            the given alpha
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!Affine.isValidAlpha(alpha)</code>
	 */
	public Affine(int alpha) throws IllegalArgumentException {
		this(alpha, Affine.MIN_BETA_VALUE);
	}

	/**
	 * Default ctor: construct an Affine object with the minimum alpha and the minimum beta.
	 */
	public Affine() {
		this(Affine.MIN_ALPHA_VALUE);
	}

	/**
	 * Copy ctor.
	 * 
	 * @param other
	 *            the given Affine object
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 */
	public Affine(Affine other) throws NullPointerException {
		this.alpha = other.alpha;
		this.alphaModInverse = other.alphaModInverse;
		this.beta = other.beta;
	}

	/**
	 * @return A shallow copy of <code>Affine.VALID_ALPHA_VALUES</code>.
	 */
	public static int[] validAlphaValues() {
		final int[] result = new int[Affine.VALID_ALPHA_VALUES.length];
		System.arraycopy(Affine.VALID_ALPHA_VALUES, 0, result, 0, result.length);
		return result;
	}

	/**
	 * @return <code>Affine.VALID_ALPHA_VALUES.length</code>.
	 */
	public static int validAlphaValuesLength() {
		return Affine.VALID_ALPHA_VALUES.length;
	}

	/**
	 * @param i
	 *            the given index
	 * 
	 * @return <code>Affine.VALID_ALPHA_VALUES[i]</code>.
	 * 
	 * @throws IndexOutOfBoundsException
	 *             If <code>(i < 0) || (Affine.VALID_ALPHA_VALUES.length <= i)</code>
	 */
	public static int validAlphaValues(int i) throws IndexOutOfBoundsException {
		return Affine.VALID_ALPHA_VALUES[i];
	}

	/**
	 * The return value is equivalent to the following: <br>
	 * <code>(Affine.MIN_ALPHA_VALUE <= alpha) && (alpha <= Affine.MAX_CIPHER_KEY_VALUE)
	 * && (MathUtil.gcd(alpha, CryptoTools.ENGLISH_ALPHABET_SIZE) == 1)</code>
	 * 
	 * @param alpha
	 *            the given alpha
	 * 
	 * @return <code>0 <= Arrays.binarySearch(Affine.VALID_ALPHA_VALUES, alpha)</code>.
	 */
	public static boolean isValidAlpha(int alpha) {
		return (0 <= Arrays.binarySearch(Affine.VALID_ALPHA_VALUES, alpha));
	}

	/**
	 * @param alpha
	 *            the given alpha
	 * 
	 * @return <code>alpha</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!Affine.isValidAlpha(alpha)</code>
	 */
	public static int validateAlpha(int alpha) throws IllegalArgumentException {
		if (!Affine.isValidAlpha(alpha)) {
			throw new IllegalArgumentException();
		}
		return alpha;
	}

	/**
	 * @return <code>this.alpha</code>.
	 */
	public int alpha() {
		return this.alpha;
	}

	/**
	 * @return <code>this.alphaModInverse</code>.
	 */
	public int alphaModInverse() {
		return this.alphaModInverse;
	}

	/**
	 * Set the calling object's alpha to the given alpha.
	 * 
	 * @param alpha
	 *            the given alpha
	 * 
	 * @return The old alpha.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!Affine.isValidAlpha(alpha)</code>
	 */
	public int alpha(int alpha) throws IllegalArgumentException {
		alpha = Affine.validateAlpha(alpha);
		final int oldAlpha = this.alpha;
		// The following is meant to be an assignment of this.alpha and this.alphaModInverse.
		this.alphaModInverse = MathUtil.modInverse(this.alpha = alpha, CryptoTools.ENGLISH_ALPHABET_SIZE);
		return oldAlpha;
	}

	/**
	 * @param beta
	 *            the given beta
	 * 
	 * @return <code>(Affine.MIN_BETA_VALUE <= beta) && (beta <= Affine.MAX_CIPHER_KEY_VALUE)</code>.
	 */
	public static boolean isValidBeta(int beta) {
		return ((Affine.MIN_BETA_VALUE <= beta) && (beta <= Affine.MAX_CIPHER_KEY_VALUE));
	}

	/**
	 * @param beta
	 *            the given beta
	 * 
	 * @return <code>beta</code>.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!Affine.isValidBeta(beta)</code>
	 */
	public static int validateBeta(int beta) throws IllegalArgumentException {
		if (!Affine.isValidBeta(beta)) {
			throw new IllegalArgumentException();
		}
		return beta;
	}

	/**
	 * @return <code>this.beta</code>.
	 */
	public int beta() {
		return this.beta;
	}

	/**
	 * Set the calling object's beta to the given beta.
	 * 
	 * @param beta
	 *            the given beta
	 * 
	 * @return The old beta.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>!Affine.isValidBeta(beta)</code>
	 */
	public int beta(int beta) throws IllegalArgumentException {
		beta = Affine.validateBeta(beta);
		final int oldBeta = this.beta;
		this.beta = beta;
		return oldBeta;
	}

	/**
	 * @param alpha
	 *            the given alpha
	 * 
	 * @param beta
	 *            the given beta
	 * 
	 * @return <code>"(" + alpha + ", " + beta + ")"</code>.
	 */
	public static String toString(int alpha, int beta) {
		final StringBuilder sb = new StringBuilder();
		sb.append('(').append(alpha).append(", ").append(beta).append(')');
		return sb.toString();
	}

	/**
	 * @return <code>Affine.toString(this.alpha, this.beta)</code>.
	 */
	@Override
	public String toString() {
		return Affine.toString(this.alpha, this.beta);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.alpha;
		result = prime * result + this.beta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return ((obj instanceof Affine) ? this.equals((Affine) obj) : false);
	}

	/**
	 * @param other
	 *            the given Affine object
	 * 
	 * @see #equals(Object)
	 */
	public boolean equals(Affine other) {
		if (other == null) {
			return false;
		} else if (this == other) {
			return true;
		}
		return ((this.alpha == other.alpha) && (this.beta == other.beta));
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
		return ((char) ((this.alpha * (p - 'A') + this.beta) % CryptoTools.ENGLISH_ALPHABET_SIZE + 'A'));
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
	 * Decrypt the given ciphertext char. <br>
	 * Precondition: <code>CryptoTools.isUpperEnglish(c)</code>
	 * 
	 * @param c
	 *            the given ciphertext char
	 * 
	 * @return The decrypted plaintext char.
	 */
	protected char decryptFixedInput(int c) {
		return ((char) (((c - 'A' - this.beta + CryptoTools.ENGLISH_ALPHABET_SIZE) * this.alphaModInverse)
				% CryptoTools.ENGLISH_ALPHABET_SIZE + 'A'));
	}

	/**
	 * Decrypt the given ciphertext char.
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
	 * Decrypt the given ciphertext char array. <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code>
	 * 
	 * @param c
	 *            the given ciphertext char array
	 * 
	 * @return The decrypted plaintext char array.
	 * 
	 */
	protected char[] decryptFixedInput(char[] c) {
		final char[] data = new char[c.length];
		for (int i = 0; i != c.length; ++i) {
			data[i] = this.decryptFixedInput(c[i]);
		}
		return data;
	}

	/**
	 * Decrypt the given ciphertext char array.
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
	 * Decrypt the given ciphertext byte array. <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return The decrypted plaintext byte array.
	 */
	protected byte[] decryptFixedInput(byte[] c) {
		final byte[] data = new byte[c.length];
		for (int i = 0; i != c.length; ++i) {
			data[i] = (byte) this.decryptFixedInput(c[i]);
		}
		return data;
	}

	/**
	 * Decrypt the given ciphertext byte array.
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
	 * Given two pairs of ciphertext char and its associated plaintext char, compute all appropriate
	 * affine cipher keys. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.length == 2</code> <br>
	 * Postcondition: <code>Result[0] == possibleAlphaValues</code> <br>
	 * Postcondition: <code>Result[1] == possibleBetaValues</code> <br>
	 * Postcondition: <code>Result[0].length == Result[1].length</code> <br>
	 * Postcondition:
	 * <code>(valid i) implies (Result[0][i], Result[1][i]) is a valid affine key that satisfies the equations</code>
	 * 
	 * @param c1
	 *            the first given ciphertext char
	 * 
	 * @param p1
	 *            the first given plaintext char
	 * 
	 * @param c2
	 *            the second given ciphertext char
	 * 
	 * @param p2
	 *            the second given plaintext char
	 * 
	 * @return The resulting two dimensional integer array.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(!CryptoTools.isUpperEnglish(c1)) || (!CryptoTools.isUpperEnglish(p1))
	 *             || (!CryptoTools.isUpperEnglish(c2)) || (!CryptoTools.isUpperEnglish(p2))
	 *             || (c1 == c2) || (p1 == p2)</code>
	 */
	public static int[][] key(int c1, int p1, int c2, int p2) throws IllegalArgumentException {
		// Fix and validate arguments.
		c1 = CryptoTools.upperEnglish(c1) - 'A';
		p1 = CryptoTools.upperEnglish(p1) - 'A';
		c2 = CryptoTools.upperEnglish(c2) - 'A';
		p2 = CryptoTools.upperEnglish(p2) - 'A';
		if ((c1 == c2) || (p1 == p2)) {
			throw new IllegalArgumentException();
		}

		/**
		 * <code>Let m = CryptoTools.ENGLISH_ALPHABET_SIZE.</code>
		 * <code>Step 1: c1 = alpha * p1 + beta (mod m)</code>
		 * <code>Step 2: c2 = alpha * p2 + beta (mod m)</code>
		 * <code>Step 3: c1 - c2 (mod m) = alpha * (p1 - p2) (mod m)</code>
		 */

		// Precompute known parts of the equation in step 3.
		final int m = CryptoTools.ENGLISH_ALPHABET_SIZE;
		final int lhs = (c1 - c2 + m) % m;
		final int rhs_not_multiplied_by_alpha = (p1 - p2 + m) % m;

		// TreeMap of all associated alpha and beta values satisfying equation in step 3.
		final TreeMap<Integer, Integer> affineKey = new TreeMap<Integer, Integer>();
		// TreeMap is used instead of HashMap so that the keys are sorted by alpha values.

		// Iterate through all valid alpha values and check whether they satisfy the equation in step 3.
		int tmp = 0, beta = 0;
		for (final int alpha : Affine.VALID_ALPHA_VALUES) {
			if (lhs == (alpha * rhs_not_multiplied_by_alpha) % m) {
				// The current value of alpha satisfies the equation.
				// Now find the associated beta value.
				tmp = (c1 - alpha * p1) % m;
				beta = ((tmp < 0) ? (tmp + m) : tmp);

				// Store the current affine key.
				affineKey.put(alpha, beta);
			}
		}

		// Create and fill the resulting integer arrays.
		final int size = affineKey.size();
		final int[] possibleAlphaValues = new int[size];
		final int[] possibleBetaValues = new int[size];
		if (size != 0) {
			final Iterator<Map.Entry<Integer, Integer>> it = affineKey.entrySet().iterator();
			Map.Entry<Integer, Integer> e = null;
			for (int index = 0; index != size; ++index) {
				e = it.next();
				possibleAlphaValues[index] = e.getKey();
				possibleBetaValues[index] = e.getValue();
			}
		}
		return new int[][] { possibleAlphaValues, possibleBetaValues };
	}

	/**
	 * Given the beta affine key and a single ciphertext char and its associated plaintext char, compute
	 * all appropriate alpha affine keys.
	 * 
	 * @param beta
	 *            the given beta
	 * 
	 * @param c
	 *            the given ciphertext char
	 * 
	 * @param p
	 *            the given plaintext char
	 * 
	 * @return The resulting integer array.
	 * 
	 * @throws IllegalArgumentException
	 *             If
	 *             <code>(!Affine.isValidBeta(beta)) || (!CryptoTools.isUpperEnglish(c)) || (!CryptoTools.isUpperEnglish(p))
	 *             || ((p == 'A') && (c - 'A' != beta))</code>
	 */
	public static int[] keyAlpha(int beta, int c, int p) throws IllegalArgumentException {
		// Fix and validate arguments.
		beta = Affine.validateBeta(beta);
		c = CryptoTools.upperEnglish(c) - 'A';
		p = CryptoTools.upperEnglish(p) - 'A';

		// Handle the simple special case.
		if (p == 0) {
			if (c != beta) {
				throw new IllegalArgumentException();
			}
			return Affine.validAlphaValues();
		}

		/**
		 * <code>Let m = CryptoTools.ENGLISH_ALPHABET_SIZE.</code>
		 * <code>Step 1: c = alpha * p + beta (mod m)</code>
		 * <code>Step 2: c - beta (mod m) = alpha * p (mod m)</code>
		 */

		// Precompute known parts of the equation in step 2.
		final int m = CryptoTools.ENGLISH_ALPHABET_SIZE;
		final int lhs = (c - beta + m) % m;

		// Stores all valid alpha values that satisfy the equation in step 2.
		final ArrayList<Integer> possibleAlphaValues = new ArrayList<Integer>();

		// Iterate through all valid alpha values and check whether they satisfy the equation in step 2.
		for (final int alpha : Affine.VALID_ALPHA_VALUES) {
			if (lhs == (alpha * p) % m) {
				// The current value of alpha satisfies the equation.
				possibleAlphaValues.add(alpha);
			}
		}

		// Create and fill the resulting integer array.
		final int[] result = new int[possibleAlphaValues.size()];
		for (int i = 0; i != result.length; ++i) {
			result[i] = possibleAlphaValues.get(i);
		}
		return result;
	}

	/**
	 * Given the alpha affine key and a single ciphertext char and its associated plaintext char,
	 * compute the appropriate beta affine key.
	 * 
	 * @param alpha
	 *            the given alpha
	 * 
	 * @param c
	 *            the given ciphertext char
	 * 
	 * @param p
	 *            the given plaintext char
	 * 
	 * @return The resulting beta affine key.
	 * 
	 * @throws IllegalArgumentException
	 *             If <code>(!Affine.isValidAlpha(alpha))
	 *             || (!CryptoTools.isUpperEnglish(c)) || (!CryptoTools.isUpperEnglish(p))</code>
	 */
	public static int keyBeta(int alpha, int c, int p) throws IllegalArgumentException {
		// Fix and validate arguments.
		alpha = Affine.validateAlpha(alpha);
		c = CryptoTools.upperEnglish(c) - 'A';
		p = CryptoTools.upperEnglish(p) - 'A';

		/**
		 * <code>Let m = CryptoTools.ENGLISH_ALPHABET_SIZE.</code>
		 * <code>Step 1: c = alpha * p + beta (mod m)</code>
		 * <code>Step 2: c - alpha * p (mod m) = beta</code>
		 */

		return ((int) MathUtil.modFixedInput(c - alpha * p, CryptoTools.ENGLISH_ALPHABET_SIZE));
	}

	/**
	 * Launch an exhaustive KCA against the given ciphertext assuming that it has been encrypted using
	 * an affine cipher. <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code> <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.numPossibilities() == 1</code> <br>
	 * Postcondition: <code>Result.plaintextLength() == c.length</code> <br>
	 * Postcondition: <code>Result.alphas[0] == probableAlpha</code> <br>
	 * Postcondition: <code>Result.betas[0] == probableBeta</code> <br>
	 * Postcondition: <code>Result.plaintexts[0] == probablePlaintext</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting CryptoInfoAffine object.
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>c.length < 2</code>
	 */
	protected static CryptoInfoAffine kcaExhaustiveFixedInput(byte[] c, boolean print) throws ShortCiphertextException {
		if (c.length < 2) {
			throw new ShortCiphertextException();
		}

		/*
		 * Try every possible key pair for the affine cipher and for each key, compute the dot product of
		 * the letter probabilities of the decrypted text and the probabilities of the English letters. The
		 * most likely key is the one that has the maximum dot product. This method is basically finding the
		 * maximal dot product with the English alphabet which determines proximity to English.
		 */
		int probableAlpha = 0, probableBeta = 0;
		double maxDotProduct = -1.0, dotProduct = 0.0;
		byte[] probablePlaintext = new byte[c.length], decrytedText = new byte[c.length];
		final Affine a = new Affine();
		for (final int alpha : Affine.VALID_ALPHA_VALUES) {
			a.alpha(alpha); // Set the affine object's alpha attribute.
			for (int beta = Affine.MIN_BETA_VALUE; beta != Affine.MAX_CIPHER_KEY_VALUE; ++beta) {
				a.beta(beta); // Set the affine object's beta attribute.
				// Decrypt the ciphertext using alpha and beta.
				for (int i = 0; i != c.length; ++i) {
					decrytedText[i] = (byte) a.decryptFixedInput(c[i]);
				}

				/*
				 * Compute dot product and keep track of the maximum dot product, the associated key and decrypted
				 * text.
				 */
				dotProduct = CryptoTools.getEnglishProbabilitiesDotProductFixedInput(decrytedText);
				if (Double.compare(maxDotProduct, dotProduct) < 0) { // i.e., maxDotProduct < dotProduct
					maxDotProduct = dotProduct;
					probableAlpha = alpha;
					probableBeta = beta;
					System.arraycopy(decrytedText, 0, probablePlaintext, 0, c.length);
				}
				// Only print if requested.
				if (print) {
					System.out.println("Affine key " + a.toString() + " gives dot product " + dotProduct + ".\n");
				}
			}
		}

		// Only print if requested.
		if (print) {
			System.out.println("Probable affine key is (" + probableAlpha + ", " + probableBeta + ").\n");
			System.out.println("Probable plaintext is:");
			for (final byte b : probablePlaintext) {
				System.out.print((char) b);
			}
			System.out.println('\n');
		}
		return new CryptoInfoAffine(probableAlpha, probableBeta, probablePlaintext);
	}

	/**
	 * Launch an exhaustive KCA against the given ciphertext assuming that it has been encrypted using
	 * an affine cipher. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.numPossibilities() == 1</code> <br>
	 * Postcondition: <code>Result.plaintextLength() == c.length</code> <br>
	 * Postcondition: <code>Result.alphas[0] == probableAlpha</code> <br>
	 * Postcondition: <code>Result.betas[0] == probableBeta</code> <br>
	 * Postcondition: <code>Result.plaintexts[0] == probablePlaintext</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return <code>Affine.kcaExhaustiveFixedInput(CryptoTools.clean(c), print)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length < 2</code>
	 */
	public static CryptoInfoAffine kcaExhaustive(byte[] c, boolean print)
			throws NullPointerException, ShortCiphertextException {
		return Affine.kcaExhaustiveFixedInput(CryptoTools.clean(c), print);
	}

	/**
	 * Launch an exhaustive KCA against the given ciphertext assuming that it has been encrypted using
	 * an affine cipher. <br>
	 * Postcondition: <code>Result != null</code> <br>
	 * Postcondition: <code>Result.numPossibilities() == 1</code> <br>
	 * Postcondition: <code>Result.plaintextLength() == c.length</code> <br>
	 * Postcondition: <code>Result.alphas[0] == probableAlpha</code> <br>
	 * Postcondition: <code>Result.betas[0] == probableBeta</code> <br>
	 * Postcondition: <code>Result.plaintexts[0] == probablePlaintext</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return <code>Affine.kcaExhaustiveFixedInput(CryptoTools.clean(c), false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length < 2</code>
	 */
	public static CryptoInfoAffine kcaExhaustive(byte[] c) throws NullPointerException, ShortCiphertextException {
		return Affine.kcaExhaustiveFixedInput(CryptoTools.clean(c), false);
	}

	/**
	 * Launch a cryptanalytic KCA against the given ciphertext assuming that it has been encrypted using
	 * an affine cipher. <br>
	 * Precondition: <code>Arrays.equals(c, CryptoTools.clean(c))</code> <br>
	 * Postcondition:
	 * <code>(Result == null) implies (Unable to find any possible affine cipher keys)</code> <br>
	 * Postcondition: <code>(Result != null) implies (Result.alphas == probableAlphas)</code> <br>
	 * Postcondition: <code>(Result != null) implies (Result.betas == probableBetas)</code> <br>
	 * Postcondition: <code>(Result != null) implies (Result.plaintexts == probablePlaintexts)</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return The resulting CryptoInfoAffine object.
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>c.length < 2</code>
	 */
	protected static CryptoInfoAffine kcaCryptanalyticFixedInput(byte[] c, boolean print)
			throws ShortCiphertextException {
		if (c.length < 2) {
			throw new ShortCiphertextException();
		}

		/*
		 * 'E' and 'T' are respectively the most common and the 2nd most common letters in English and as
		 * such the letter with the highest probability is probably the mapping of 'E' and the letter with
		 * the 2nd highest probability is probably the mapping of 'T'. Save the mappings in maxProbLetter
		 * and maxProbLetter2 respectively so that the affine key can be found from them after the loop.
		 */
		char maxProbLetter = '\0', maxProbLetter2 = '\0', letter = '\0';
		double maxProb = -1.0, maxProb2 = -1.0, prob = 0.0;
		int maxProb_prob_cmp = 0;
		final int[] freq = CryptoTools.getFrequenciesFixedInput(c);
		for (int i = 0; i != CryptoTools.ENGLISH_ALPHABET_SIZE; ++i) {
			letter = (char) ('A' + i);
			prob = 100.0 * freq[i] / c.length;
			maxProb_prob_cmp = Double.compare(maxProb, prob);
			if (maxProb_prob_cmp < 0) { // i.e., maxProb < prob
				maxProb2 = maxProb;
				maxProb = prob;
				maxProbLetter2 = maxProbLetter;
				maxProbLetter = letter;
			} else if (maxProb_prob_cmp != 0) { // i.e., maxProb != prob
				if (Double.compare(maxProb2, prob) < 0) { // i.e., maxProb2 < prob
					maxProb2 = prob;
					maxProbLetter2 = letter;
				}
			}
			// Only print if requested.
			if (print) {
				System.out.println("prob[\'" + letter + "\'] == " + prob + "\n");
			}
		}
		// Only print if requested.
		if (print) {
			System.out.println("Probable mapping of \'E\' is \'" + maxProbLetter + "\'.\n");
			System.out.println("Probable mapping of \'T\' is \'" + maxProbLetter2 + "\'.\n");
		}

		// Find all possible affine keys from the mappings of 'E' and 'T'.
		final int[][] keys = Affine.key(maxProbLetter, 'E', maxProbLetter2, 'T');
		final int numKeys = keys[0].length;
		if (numKeys == 0) {
			// Only print if requested.
			if (print) {
				System.out.println("Unable to find any possible affine cipher keys.\n");
			}
			return null;
		}

		// Decrypt the ciphertext using each found affine key.
		final byte[][] probablePlaintexts = new byte[numKeys][c.length];
		final Affine a = new Affine();
		for (int keyNum = 0; keyNum != numKeys; ++keyNum) {
			// Set the affine object's alpha and beta attributes.
			a.alpha(keys[0][keyNum]);
			a.beta(keys[1][keyNum]);
			// Decrypt the ciphertext using the affine key.
			for (int i = 0; i != c.length; ++i) {
				probablePlaintexts[keyNum][i] = (byte) a.decryptFixedInput(c[i]);
			}
			// Only print if requested.
			if (print) {
				System.out.println("Probable affine key " + (keyNum + 1) + " is " + a.toString() + ".\n");
				System.out.println("Probable plaintext " + (keyNum + 1) + " is:");
				for (final byte b : probablePlaintexts[keyNum]) {
					System.out.print((char) b);
				}
				System.out.println('\n');
			}
		}
		return new CryptoInfoAffine(keys[0], keys[1], probablePlaintexts);
	}

	/**
	 * Launch a cryptanalytic KCA against the given ciphertext assuming that it has been encrypted using
	 * an affine cipher. <br>
	 * Postcondition:
	 * <code>(Result == null) implies (Unable to find any possible affine cipher keys)</code> <br>
	 * Postcondition: <code>(Result != null) implies (Result.alphas == probableAlphas)</code> <br>
	 * Postcondition: <code>(Result != null) implies (Result.betas == probableBetas)</code> <br>
	 * Postcondition: <code>(Result != null) implies (Result.plaintexts == probablePlaintexts)</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @param print
	 *            specifies whether intermediate messages should be printed to the standard output
	 *            stream
	 * 
	 * @return <code>Affine.kcaCryptanalyticFixedInput(CryptoTools.clean(c), print)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length < 2</code>
	 */
	public static CryptoInfoAffine kcaCryptanalytic(byte[] c, boolean print)
			throws NullPointerException, ShortCiphertextException {
		return Affine.kcaCryptanalyticFixedInput(CryptoTools.clean(c), print);
	}

	/**
	 * Launch a cryptanalytic KCA against the given ciphertext assuming that it has been encrypted using
	 * an affine cipher. <br>
	 * Postcondition:
	 * <code>(Result == null) implies (Unable to find any possible affine cipher keys)</code> <br>
	 * Postcondition: <code>(Result != null) implies (Result.alphas == probableAlphas)</code> <br>
	 * Postcondition: <code>(Result != null) implies (Result.betas == probableBetas)</code> <br>
	 * Postcondition: <code>(Result != null) implies (Result.plaintexts == probablePlaintexts)</code>
	 * 
	 * @param c
	 *            the given ciphertext byte array
	 * 
	 * @return <code>Affine.kcaCryptanalyticFixedInput(CryptoTools.clean(c), false)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>c == null</code>
	 * 
	 * @throws ShortCiphertextException
	 *             If <code>CryptoTools.clean(c).length < 2</code>
	 */
	public static CryptoInfoAffine kcaCryptanalytic(byte[] c) throws NullPointerException, ShortCiphertextException {
		return Affine.kcaCryptanalyticFixedInput(CryptoTools.clean(c), false);
	}

	// --------------------------------------------------
	// Nested wrapper class.
	// --------------------------------------------------

	/**
	 * Simple wrapper for decryption results. Saves possible affine cipher key values and plaintexts.
	 * <br>
	 * 
	 * This class does not encapsulate the information. It in fact saves direct mutable pointers passed
	 * to its ctors without creating copies therefore it is not "safe" in terms of OOP. <br>
	 * 
	 * It's main usage is for the return type of <code>Affine::kcaExhaustive</code> and
	 * <code>Affine::kcaCryptanalytic</code>.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static class CryptoInfoAffine {
		/**
		 * All possible alpha values.
		 */
		public final int[] alphas;

		/**
		 * All possible beta values.
		 */
		public final int[] betas;

		/**
		 * All possible plaintexts.
		 */
		public final byte[][] plaintexts;

		/**
		 * @param alphas
		 *            the given alphas array
		 * 
		 * @param betas
		 *            the given betas array
		 * 
		 * @param plaintexts
		 *            the given plaintexts array
		 * 
		 * @throws NullPointerException
		 *             If <code>(alphas == null) || (betas == null) || (plaintexts == null)</code>
		 * 
		 * @throws IllegalArgumentException
		 *             If
		 *             <code>(alphas.length == 0) || (alphas.length != betas.length) || (alphas.length != plaintexts.length)
		 *             || ((valid i, j) implies (plaintexts[i].length != plaintexts[j].length))</code>
		 */
		public CryptoInfoAffine(int[] alphas, int[] betas, byte[][] plaintexts)
				throws NullPointerException, IllegalArgumentException {
			// All arrays must be non-empty and have the same length.
			if (alphas.length == 0) {
				throw new IllegalArgumentException();
			} else if (alphas.length != betas.length) {
				throw new IllegalArgumentException();
			} else if (alphas.length != plaintexts.length) {
				throw new IllegalArgumentException();
			}

			// All entries of plaintexts must have the same length.
			final int length = plaintexts[0].length;
			for (int i = 1; i != plaintexts.length; ++i) {
				if (plaintexts[i].length != length) {
					throw new IllegalArgumentException();
				}
			}

			// Set this.
			this.alphas = alphas;
			this.betas = betas;
			this.plaintexts = plaintexts;
		}

		/**
		 * @param alpha
		 *            the given alpha
		 * 
		 * @param beta
		 *            the given beta
		 * 
		 * @param plaintext
		 *            the given plaintext
		 * 
		 * @throws NullPointerException
		 *             If <code>plaintext == null</code>
		 */
		public CryptoInfoAffine(int alpha, int beta, byte[] plaintext) throws NullPointerException {
			if (plaintext == null) {
				throw new NullPointerException();
			}

			// Set this.
			this.alphas = new int[] { alpha };
			this.betas = new int[] { beta };
			this.plaintexts = new byte[][] { plaintext };
		}

		/**
		 * @return The number of different possible affine cipher keys and plaintexts.
		 */
		public int numPossibilities() {
			return this.alphas.length;
		}

		/**
		 * @return The common length of all possible plaintexts.
		 */
		public int plaintextLength() {
			return this.plaintexts[0].length;
		}
	}
}
