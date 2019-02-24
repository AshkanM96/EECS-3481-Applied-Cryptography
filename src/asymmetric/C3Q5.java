package asymmetric;

import java.math.BigInteger;

import util.CryptoTools;
import util.RSA;

/**
 * RSA decryption use case with manual BigInteger manipulations.
 * 
 * @author Ashkan Moatamed
 */
public class C3Q5 {
	/**
	 * Dependencies: <code>
	 * 		1. util.RSA
	 * 		2. util.CryptoTools
	 * </code>
	 */

	/**
	 * Known modulus.
	 */
	private static final BigInteger N = new BigInteger(
			"94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");

	/**
	 * Known public key.
	 */
	private static final BigInteger E = new BigInteger("74327");

	/**
	 * Known modulus prime factor.
	 */
	private static final BigInteger P = new BigInteger(
			"10358344307803887695931304169230543785620607743682421994532795393937342395753127888522373061586445417642355843316524942445924294144921649080401518286829171");

	/**
	 * Known ciphertext.
	 */
	private static final BigInteger CIPHERTEXT = new BigInteger(
			"10870101966939556606443697147757930290262227730644958783498257036423105365610629529910525828464329792615002602782366786531253275463358840412867833406256467153345139501952173409955322129689670345445632775574301781800376545448990332608558103266831217073027652061091790342124418143422318965525239492387183438956");

	/**
	 * Prevent instantiation.
	 */
	private C3Q5() {
		// Empty by design.
	}

	public static void main(String[] args) {
		// Create the cipher engine with the appropriate attributes.
		final RSA r = RSA.knownFactors(C3Q5.P, C3Q5.N.divide(C3Q5.P), C3Q5.E);

		// Decrypt the ciphertext using the engine to get the plaintext.
		final BigInteger plaintext = r.apply(C3Q5.CIPHERTEXT, false);
		System.out.println("The plaintext is:\n" + CryptoTools.toString(plaintext.toByteArray()) + "\n");
	}
}
