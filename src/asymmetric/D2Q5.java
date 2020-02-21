package asymmetric;

import java.math.BigInteger;

/**
 * Diffie-Hellman key agreement protocol use case.
 * 
 * @author Ashkan Moatamed
 */
public class D2Q5 {
	/**
	 * No dependencies.
	 */

	/**
	 * Known public prime.
	 */
	private static final BigInteger P = new BigInteger(
			"13232376895198612407547930718267435757728527029623408872245156039757713029036368719146452186041204237350521785240337048752071462798273003935646236777459223");

	/**
	 * Known public base.
	 */
	private static final BigInteger G = new BigInteger(
			"5421644057436475141609648488325705128047428394380474376834667300766108262613900542681289080713724597310673074119355136085795982097390670890367185141189796");

	/**
	 * Prevent instantiation.
	 */
	private D2Q5() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) {
		final BigInteger alice_x = new BigInteger(
				"35231692989156503979546454037473634095881844065093413538198180176131787025013952129436607166120764958611422838336479");
		final BigInteger alice_X = new BigInteger(
				"6261182090531222482790161474830084183843073036196011672331657226835874178518281471466910307562395801718650412197674209915260863180007579746088604690974854");
		assert (alice_X.equals(D2Q5.G.modPow(alice_x, D2Q5.P)));

		final BigInteger bob_Y = new BigInteger(
				"12585258782021319592841714687786356360594766271531515278791155841138244764864157868807366386253699146717465248426227128304169369155873702261011732861473721");

		final BigInteger alice_K = bob_Y.modPow(alice_x, D2Q5.P);
		System.out.println("alice_K is: " + alice_K + ".\n");
	}
}
