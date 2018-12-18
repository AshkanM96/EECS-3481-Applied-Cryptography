package hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Signature hashing use case.
 * 
 * @author Ashkan Moatamed
 */
public class D3Q1 {
	/**
	 * No dependencies.
	 */

	/**
	 * Known modulus.
	 */
	private static final BigInteger N = new BigInteger(
			"94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");

	/**
	 * Known public key.
	 */
	// private static final BigInteger E = new BigInteger("74327");

	/**
	 * Known private key.
	 */
	private static final BigInteger D = new BigInteger(
			"7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");

	/**
	 * Known plaintext.
	 */
	private static final BigInteger PLAINTEXT = new BigInteger("Meet me at 5 pm tomorrow".getBytes());

	/**
	 * Prevent instantiation.
	 */
	private D3Q1() {
		// Empty by design.
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		final MessageDigest digester = MessageDigest.getInstance("SHA-512");

		// Encrypt the plaintext with the private key (i.e., sign it).
		final BigInteger signature = D3Q1.PLAINTEXT.modPow(D3Q1.D, D3Q1.N);

		// Hash the signature with SHA2 with 512bits.
		final byte[] hashed_signature = digester.digest(signature.toByteArray());
		System.out.println(new String(hashed_signature));
	}
}
