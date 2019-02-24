package util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Symmetric Cipher Engine. A simple wrapper over Java's Cipher class which only supports the
 * Symmetric Ciphers.
 * 
 * @author Ashkan Moatamed
 */
public class SymCipherEng {
	/**
	 * Dependencies: <code>
	 * 		1. util.CipherEngUtil
	 * </code>
	 */

	/**
	 * DES block size in bytes.
	 */
	public static final int DES_BLOCK_SIZE = 8;

	/**
	 * AES block size in bytes.
	 */
	public static final int AES_BLOCK_SIZE = 16;

	/**
	 * Cipher engine.
	 */
	protected Cipher engine;

	/**
	 * All supported Symmetric Cipher algorithms.
	 * 
	 * @author Ashkan Moatamed
	 */
	public static enum ALGO_SYM {
		DES, DESede, AES;
	};

	/**
	 * @param algo
	 *            the given cipher algorithm
	 * 
	 * @return <code>KeyGenerator.getInstance(algo.name())</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>algo == null</code>
	 */
	public static KeyGenerator getKeyGenerator(ALGO_SYM algo) throws NullPointerException {
		try {
			return KeyGenerator.getInstance(algo.name());
		} catch (NoSuchAlgorithmException ex) {
			throw new ExceptionInInitializerError(algo.name() + " key generator is not provided!");
		}
	}

	/**
	 * Cipher key generators.
	 */
	protected static KeyGenerator[] keyGenerators = null;

	/**
	 * Cipher algorithm.
	 */
	private ALGO_SYM algo;

	/**
	 * Cipher secret key.
	 */
	protected SecretKey key;

	/**
	 * Mode of operation.
	 */
	private CipherEngUtil.OPMODE opmode;

	/**
	 * Padding algorithm.
	 */
	private CipherEngUtil.PADDING padding;

	/**
	 * Initialization vector.
	 */
	protected IvParameterSpec iv;

	/**
	 * Construct a SymCipherEng object from the given attributes.
	 * 
	 * @param algo
	 *            the given cipher algorithm
	 * 
	 * @param key
	 *            the given cipher secret key
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding algorithm
	 * 
	 * @param iv
	 *            the given initialization vector
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(algo == null) || (key == null) || (opmode == null) || (padding == null) || (iv == null)</code>
	 */
	public SymCipherEng(ALGO_SYM algo, byte[] key, CipherEngUtil.OPMODE opmode, CipherEngUtil.PADDING padding,
			byte[] iv) throws NullPointerException {
		try {
			// The following is meant to be an assignment of
			// this.engine, this.algo, this.opmode, and this.padding.
			this.engine = CipherEngUtil.getEngine((this.algo = algo).name(), this.opmode = opmode,
					this.padding = padding);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
			throw new ExceptionInInitializerError();
		}
		this.key(key);
		this.iv(iv);
	}

	/**
	 * Construct a SymCipherEng object from the given attributes.
	 * 
	 * @param algo
	 *            the given cipher algorithm
	 * 
	 * @param key
	 *            the given cipher secret key
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @param padding
	 *            the given padding algorithm
	 * 
	 * @throws NullPointerException
	 *             If
	 *             <code>(algo == null) || (key == null) || (opmode == null) || (padding == null)</code>
	 */
	public SymCipherEng(ALGO_SYM algo, byte[] key, CipherEngUtil.OPMODE opmode, CipherEngUtil.PADDING padding)
			throws NullPointerException {
		try {
			// The following is meant to be an assignment of
			// this.engine, this.algo, this.opmode, and this.padding.
			this.engine = CipherEngUtil.getEngine((this.algo = algo).name(), this.opmode = opmode,
					this.padding = padding);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
			throw new ExceptionInInitializerError();
		}
		this.key(key);
		this.iv = null;
	}

	/**
	 * Construct a SymCipherEng object from the given attributes.
	 * 
	 * @param algo
	 *            the given cipher algorithm
	 * 
	 * @param key
	 *            the given cipher secret key
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @throws NullPointerException
	 *             If <code>(algo == null) || (key == null) || (opmode == null)</code>
	 */
	public SymCipherEng(ALGO_SYM algo, byte[] key, CipherEngUtil.OPMODE opmode) throws NullPointerException {
		this(algo, key, opmode, CipherEngUtil.DEFAULT_PADDING);
	}

	/**
	 * Construct a SymCipherEng object from the given attributes.
	 * 
	 * @param algo
	 *            the given cipher algorithm
	 * 
	 * @param key
	 *            the given cipher secret key
	 * 
	 * @throws NullPointerException
	 *             If <code>(algo == null) || (key == null)</code>
	 */
	public SymCipherEng(ALGO_SYM algo, byte[] key) throws NullPointerException {
		this(algo, key, CipherEngUtil.DEFAULT_MODE);
	}

	/**
	 * Copy ctor.
	 * 
	 * @param other
	 *            the given SymCipherEng object
	 * 
	 * @throws NullPointerException
	 *             If <code>other == null</code>
	 */
	public SymCipherEng(SymCipherEng other) throws NullPointerException {
		this.engine = other.engine; // Since Cipher is singleton (accessed through getInstance).
		this.algo = other.algo; // Since enum type assignment is a deep enough copy.
		this.key = SymCipherEng.key(other.key.getEncoded(), this.algo);
		this.opmode = other.opmode; // Since enum type assignment is a deep enough copy.
		this.padding = other.padding; // Since enum type assignment is a deep enough copy.
		this.iv = (other.iv == null) ? null : new IvParameterSpec(other.iv.getIV());
	}

	/**
	 * Initialize <code>SymCipherEng.keyGenerators[algo.ordinal()]</code> using the following:
	 * 
	 * <pre>
	 * <code>
	 * if (SymCipherEng.keyGenerators == null) {
	 * 	SymCipherEng.keyGenerators = new KeyGenerator[ALGO_SYM.values().length];
	 * }
	 * 
	 * final int index = algo.ordinal();
	 * if (SymCipherEng.keyGenerators[index] == null) {
	 * 	SymCipherEng.keyGenerators[index] = SymCipherEng.getKeyGenerator(algo);
	 * }
	 * 
	 * if (random == null) {
	 * 	SymCipherEng.keyGenerators[index].init(keySize);
	 * } else {
	 * 	SymCipherEng.keyGenerators[index].init(keySize, random);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param algo
	 *            the given cipher algorithm
	 * 
	 * @param keySize
	 *            algorithm-specific metric, such as key length, specified in number of bits
	 * 
	 * @param random
	 *            the source of randomness
	 * 
	 * @return <code>SymCipherEng.keyGenerators[algo.ordinal()].generateKey()</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>algo == null</code>
	 */
	public static SecretKey generateKey(ALGO_SYM algo, int keySize, SecureRandom random) throws NullPointerException {
		// Construct SymCipherEng.keyGenerators if needed. Executed at most once.
		if (SymCipherEng.keyGenerators == null) {
			SymCipherEng.keyGenerators = new KeyGenerator[ALGO_SYM.values().length];
		}

		// Construct SymCipherEng.keyGenerators[index] if needed. Executed at most once.
		final int index = algo.ordinal();
		KeyGenerator keyGenerator = SymCipherEng.keyGenerators[index];
		if (keyGenerator == null) {
			keyGenerator = SymCipherEng.keyGenerators[index] = SymCipherEng.getKeyGenerator(algo);
		}

		// Initialize the key generator and return the generated key.
		if (random == null) {
			keyGenerator.init(keySize);
		} else {
			keyGenerator.init(keySize, random);
		}
		return keyGenerator.generateKey();
	}

	/**
	 * @param algo
	 *            the given cipher algorithm
	 * 
	 * @param keySize
	 *            algorithm-specific metric, such as key length, specified in number of bits
	 * 
	 * @return <code>SymCipherEng.generateKey(algo, keySize, null)</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>algo == null</code>
	 */
	public static SecretKey generateKey(ALGO_SYM algo, int keySize) throws NullPointerException {
		return SymCipherEng.generateKey(algo, keySize, null);
	}

	/**
	 * Initialize <code>SymCipherEng.keyGenerators[algo.ordinal()]</code> using the following:
	 * 
	 * <pre>
	 * <code>
	 * if (SymCipherEng.keyGenerators == null) {
	 * 	SymCipherEng.keyGenerators = new KeyGenerator[ALGO_SYM.values().length];
	 * }
	 * 
	 * final int index = algo.ordinal();
	 * if (SymCipherEng.keyGenerators[index] == null) {
	 * 	SymCipherEng.keyGenerators[index] = SymCipherEng.getKeyGenerator(algo);
	 * }
	 * 
	 * if (random != null) {
	 * 	SymCipherEng.keyGenerators[index].init(random);
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param algo
	 *            the given cipher algorithm
	 * 
	 * @param random
	 *            the source of randomness
	 * 
	 * @return <code>SymCipherEng.keyGenerators[algo.ordinal()].generateKey()</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>algo == null</code>
	 */
	public static SecretKey generateKey(ALGO_SYM algo, SecureRandom random) throws NullPointerException {
		// Construct SymCipherEng.keyGenerators if needed. Executed at most once.
		if (SymCipherEng.keyGenerators == null) {
			SymCipherEng.keyGenerators = new KeyGenerator[ALGO_SYM.values().length];
		}

		// Construct SymCipherEng.keyGenerators[index] if needed. Executed at most once.
		final int index = algo.ordinal();
		KeyGenerator keyGenerator = SymCipherEng.keyGenerators[index];
		if (keyGenerator == null) {
			keyGenerator = SymCipherEng.keyGenerators[index] = SymCipherEng.getKeyGenerator(algo);
		}

		// Initialize the key generator and return the generated key.
		if (random != null) {
			keyGenerator.init(random);
		}
		return keyGenerator.generateKey();
	}

	/**
	 * @return <code>this.algo</code>.
	 */
	public ALGO_SYM algo() {
		return this.algo;
	}

	/**
	 * Set the calling object's cipher algorithm to the given cipher algorithm.
	 * 
	 * @param algo
	 *            the given cipher algorithm
	 * 
	 * @throws NullPointerException
	 *             If <code>algo == null</code>
	 */
	public void algo(ALGO_SYM algo) throws NullPointerException {
		// The following is meant to be an assignment of this.engine, and this.algo.
		try {
			this.engine = CipherEngUtil.getEngine(algo.name(), this.opmode, this.padding);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
			throw new ExceptionInInitializerError();
		}
		this.key = SymCipherEng.key(this.key.getEncoded(), this.algo = algo);
	}

	/**
	 * @param key
	 *            the given cipher secret key
	 * 
	 * @param algo
	 *            the given cipher algorithm
	 * 
	 * @return <code>new SecretKeySpec(key, algo.name())</code>.
	 * 
	 * @throws NullPointerException
	 *             If <code>(key == null) || (algo == null)</code>
	 */
	public static SecretKey key(byte[] key, ALGO_SYM algo) throws NullPointerException {
		if (key == null) {
			throw new NullPointerException();
		}
		return new SecretKeySpec(key, algo.name());
	}

	/**
	 * @return <code>this.key.getEncoded()</code>.
	 */
	public byte[] key() {
		/*
		 * With a simple test, we can determine that the getEncoded method returns a copy and not the actual
		 * pointer. Therefore, it is safe to just return what it returns without making a copy since it has
		 * made the copy.
		 */
		return this.key.getEncoded();
	}

	/**
	 * Set the calling object's cipher secret key to the given cipher secret key.
	 * 
	 * @param key
	 *            the given cipher secret key
	 * 
	 * @throws NullPointerException
	 *             If <code>key == null</code>
	 */
	public void key(byte[] key) throws NullPointerException {
		this.key = SymCipherEng.key(key, this.algo);
	}

	/**
	 * @return <code>this.opmode</code>.
	 */
	public CipherEngUtil.OPMODE opmode() {
		return this.opmode;
	}

	/**
	 * Set the calling object's mode of operation to the given mode of operation.
	 * 
	 * @param opmode
	 *            the given mode of operation
	 * 
	 * @throws NullPointerException
	 *             If <code>opmode == null</code>
	 */
	public void opmode(CipherEngUtil.OPMODE opmode) throws NullPointerException {
		// The following is meant to be an assignment of this.engine, and this.opmode.
		try {
			this.engine = CipherEngUtil.getEngine(this.algo.name(), opmode, this.padding);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
			throw new ExceptionInInitializerError();
		}
		this.opmode = opmode;
	}

	/**
	 * @return <code>this.padding</code>.
	 */
	public CipherEngUtil.PADDING padding() {
		return this.padding;
	}

	/**
	 * Set the calling object's padding algorithm to the given padding algorithm.
	 * 
	 * @param padding
	 *            the given padding algorithm
	 * 
	 * @throws NullPointerException
	 *             If <code>padding == null</code>
	 */
	public void padding(CipherEngUtil.PADDING padding) throws NullPointerException {
		// The following is meant to be an assignment of this.engine, and this.padding.
		try {
			this.engine = CipherEngUtil.getEngine(this.algo.name(), this.opmode, padding);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
			throw new ExceptionInInitializerError();
		}
		this.padding = padding;
	}

	/**
	 * <code>this.iv = null</code>.
	 */
	public void ivReset() {
		this.iv = null;
	}

	/**
	 * Set the calling object's initialization vector to the given initialization vector.
	 * 
	 * @param iv
	 *            the given initialization vector
	 * 
	 * @throws NullPointerException
	 *             If <code>iv == null</code>
	 */
	public void iv(byte[] iv) throws NullPointerException {
		if (iv == null) {
			throw new NullPointerException();
		}
		this.iv = new IvParameterSpec(iv);
	}

	/**
	 * Since Cipher, SecretKey, and IvParameterSpec do not redeclare toString, hashCode, and equals then
	 * neither will SymCipherEng.
	 */

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
	 * 
	 * @throws InvalidKeyException
	 *             Thrown by <code>Cipher.init(Cipher.ENCRYPT_MODE, this.key)</code> or
	 *             <code>Cipher.init(Cipher.ENCRYPT_MODE, this.key, this.iv)</code>
	 * 
	 * @throws InvalidAlgorithmParameterException
	 *             Thrown by <code>Cipher.init(Cipher.ENCRYPT_MODE, this.key, this.iv)</code>
	 * 
	 * @throws BadPaddingException
	 *             Thrown by <code>Cipher.doFinal(p)</code>
	 * 
	 * @throws IllegalBlockSizeException
	 *             Thrown by <code>Cipher.doFinal(p)</code>
	 */
	public byte[] encrypt(byte[] p) throws NullPointerException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (this.iv == null) {
			this.engine.init(Cipher.ENCRYPT_MODE, this.key);
		} else {
			this.engine.init(Cipher.ENCRYPT_MODE, this.key, this.iv);
		}
		return this.engine.doFinal(p);
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
	 * 
	 * @throws InvalidKeyException
	 *             Thrown by <code>Cipher.init(Cipher.DECRYPT_MODE, this.key)</code> or
	 *             <code>Cipher.init(Cipher.DECRYPT_MODE, this.key, this.iv)</code>
	 * 
	 * @throws InvalidAlgorithmParameterException
	 *             Thrown by <code>Cipher.init(Cipher.DECRYPT_MODE, this.key, this.iv)</code>
	 * 
	 * @throws BadPaddingException
	 *             Thrown by <code>Cipher.doFinal(c)</code>
	 * 
	 * @throws IllegalBlockSizeException
	 *             Thrown by <code>Cipher.doFinal(c)</code>
	 */
	public byte[] decrypt(byte[] c) throws NullPointerException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (this.iv == null) {
			this.engine.init(Cipher.DECRYPT_MODE, this.key);
		} else {
			this.engine.init(Cipher.DECRYPT_MODE, this.key, this.iv);
		}
		return this.engine.doFinal(c);
	}
}
