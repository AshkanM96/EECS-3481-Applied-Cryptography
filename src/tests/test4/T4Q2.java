package tests.test4;

import util.StatUtil;

public class T4Q2 {
	/**
	 * Dependencies: <code>
	 * 		1. util.StatUtil
	 * </code>
	 */

	/**
	 * Prevent instantiation.
	 */
	private T4Q2() {
		// Empty by design.
	}

	@Override
	protected Object clone() throws CloneNotSupportedException { // semi-copy
		throw new CloneNotSupportedException();
	}

	public static void main(String[] args) {
		final int d = 365;
		System.out.println(StatUtil.birthday2Linear(d, 16, 16));
		System.out.println(StatUtil.birthday2Linear(d, 43, 6));
	}
}
