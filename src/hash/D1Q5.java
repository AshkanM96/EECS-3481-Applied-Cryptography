package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import util.Hex;

/**
 * MD5 (1st) Pre-Image Resistance use case.
 * 
 * @author Ashkan Moatamed
 */
public class D1Q5 {
	/**
	 * Dependencies: <code>
	 * 		1. util.Hex
	 * </code>
	 */

	/**
	 * Known MD5 hash of password.
	 */
	private static final byte[] PASSWORD_MD5_HASH = Hex.toBytes("5ae9b7f211e23aac3df5f2b8f3b8eada");

	/**
	 * Prevent instantiation.
	 */
	private D1Q5() {
		// Empty by design.
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		final MessageDigest digester = MessageDigest.getInstance("MD5");

		// Using https://crackstation.net/, can crack D1Q5.PASSWORD_MD5_HASH to "crypto".
		System.out.println(Arrays.equals(digester.digest("crypto".getBytes()), D1Q5.PASSWORD_MD5_HASH) + "\n");
	}
}
