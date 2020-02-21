package hash;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Digital Signature Algorithm (i.e., DSA) use case.
 * 
 * @author Ashkan Moatamed
 */
public class D3Q3 {
	/**
	 * No dependencies.
	 */

	/**
	 * Known prime p.
	 */
	private static final BigInteger P = new BigInteger(
			"1325381145334539358629448518279679008904176563248846688977280754468055426387578974281147797624669024229219594573053241551724763638929327015294360424366549");

	/**
	 * Known q which is a divisor of p - 1 such that the number of bits in q == hash size.
	 */
	private static final BigInteger Q = new BigInteger("760221963115420466361881663545019104442925559693");

	/**
	 * Known alpha (i.e., generator for the subgroup of order q in mod p).
	 */
	private static final BigInteger ALPHA = new BigInteger(
			"1189764068877937363846146167338638286425739196572993692965682569366341097141069998263395570909274633255291948889699631666011157726138016295598882944190605");

	/**
	 * Known beta (i.e., alpha raised to the power of the secret in mod p).
	 */
	private static final BigInteger BETA = new BigInteger(
			"238798031115639984887276107391389446213082107084695411039149128034604179189303382254769606917989223743615871451102658150814951925710110771527560977845711");

	/**
	 * Known message.
	 */
	private static final String MESSAGE = "On Dec 1st, sell all RBC shares in the portfolio and buy 5,750 BMO shares.";

	/**
	 * Known signature pair first element (i.e., the secret raised to the power of a random integer k in
	 * [2, q - 1] in mod p in mod q).
	 */
	private static final BigInteger R = new BigInteger("657085232116258040181430472141362000021747681660");

	/**
	 * Known signature pair second element (i.e., ((hash of message plus secret times r) times inverse
	 * of k in mod q) in mod q).
	 */
	private static final BigInteger S = new BigInteger("490067537914838692497407674888646687207464807296");

	/**
	 * Inverse of s in mod q.
	 */
	private static final BigInteger S_INVERSE = D3Q3.S.modInverse(D3Q3.Q);

	/**
	 * Prevent instantiation.
	 */
	private D3Q3() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		final MessageDigest digester = MessageDigest.getInstance("SHA-1");

		// Compute the MAC (i.e., the hash of the message).
		final BigInteger MAC = new BigInteger(digester.digest(D3Q3.MESSAGE.getBytes()));

		// u1 = MAC / s (mod q)
		final BigInteger u1 = MAC.multiply(D3Q3.S_INVERSE).mod(D3Q3.Q);

		// u2 = r / s (mod q)
		final BigInteger u2 = D3Q3.R.multiply(D3Q3.S_INVERSE).mod(D3Q3.Q);

		// v = ((alpha ^ u1) * (beta ^ u2) (mod p)) (mod q)
		final BigInteger alpha_to_u1 = D3Q3.ALPHA.modPow(u1, D3Q3.P);
		final BigInteger beta_to_u2 = D3Q3.BETA.modPow(u2, D3Q3.P);
		final BigInteger v = alpha_to_u1.multiply(beta_to_u2).mod(D3Q3.P).mod(D3Q3.Q);

		// Check whether the message is intact or not.
		final boolean isIntact = v.equals(D3Q3.R);
		System.out.println("The message is" + (isIntact ? "" : " not") + " intact.");
		if (isIntact) {
			System.out.println("The message is: \"" + D3Q3.MESSAGE + "\".\n");
		} else {
			System.out.println("v == " + v + "\nr == " + D3Q3.R + "\n");
		}
	}
}
