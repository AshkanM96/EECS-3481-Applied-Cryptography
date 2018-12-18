package tests.test4;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Digital Signature Algorithm (i.e., DSA) use case.
 * 
 * @author Ashkan Moatamed
 */
public class T4Q1 {
	/**
	 * No dependencies.
	 */

	/**
	 * Known prime p.
	 */
	private static final BigInteger P = new BigInteger(
			"132538114533453935862944851827967900890417656324884668897728075446805542638757897428114779762466902422921959457305324155172476363892932701529436042436");

	/**
	 * Known q which is a divisor of p - 1 such that the number of bits in q == hash size.
	 */
	private static final BigInteger Q = new BigInteger("760221963115420466361881663545019104442925559693");

	/**
	 * Known alpha (i.e., generator for the subgroup of order q in mod p).
	 */
	private static final BigInteger ALPHA = new BigInteger(
			"118976406887793736384614616733863828642573919657299369296568256936634109714106999826339557090927463325529194888969963166601115772613801629559888294419");

	/**
	 * Known beta (i.e., alpha raised to the power of the secret in mod p).
	 */
	private static final BigInteger BETA = new BigInteger(
			"1038891241506638407800079882631168004466147649936425637139088531023308925014572900038200210088391529093199794551987338995014968927250662946657464591180799");

	/**
	 * Known message.
	 */
	private static final String MESSAGE = "Meet me at York Lanes";

	/**
	 * Known signature pair first element (i.e., the secret raised to the power of a random integer k in
	 * [2, q - 1] in mod p in mod q).
	 */
	private static final BigInteger R = new BigInteger("657085232116258040181430472141362000021747681660");

	/**
	 * Known signature pair second element (i.e., ((hash of message plus secret times r) times inverse
	 * of k in mod q) in mod q).
	 */
	private static final BigInteger S = new BigInteger("195640651948973788813998353120863956686599552282");

	/**
	 * Inverse of s in mod q.
	 */
	private static final BigInteger S_INVERSE = T4Q1.S.modInverse(T4Q1.Q);

	/**
	 * Prevent instantiation.
	 */
	private T4Q1() {
		// Empty by design.
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		final MessageDigest digester = MessageDigest.getInstance("SHA-1");

		// Compute the MAC (i.e., the hash of the message).
		final BigInteger MAC = new BigInteger(digester.digest(T4Q1.MESSAGE.getBytes()));

		// u1 = MAC / s (mod q)
		final BigInteger u1 = MAC.multiply(T4Q1.S_INVERSE).mod(T4Q1.Q);

		// u2 = r / s (mod q)
		final BigInteger u2 = T4Q1.R.multiply(T4Q1.S_INVERSE).mod(T4Q1.Q);

		// v = ((alpha ^ u1) * (beta ^ u2) (mod p)) (mod q)
		final BigInteger alpha_to_u1 = T4Q1.ALPHA.modPow(u1, T4Q1.P);
		final BigInteger beta_to_u2 = T4Q1.BETA.modPow(u2, T4Q1.P);
		final BigInteger v = alpha_to_u1.multiply(beta_to_u2).mod(T4Q1.P).mod(T4Q1.Q);

		// Check whether the message is intact or not.
		final boolean isIntact = v.equals(T4Q1.R);
		System.out.println("The message is" + (isIntact ? "" : " not") + " intact.");
		if (isIntact) {
			System.out.println("The message is: \"" + T4Q1.MESSAGE + "\".\n");
		} else {
			System.out.println("v == " + v + "\nr == " + T4Q1.R + "\n");
		}
	}
}
