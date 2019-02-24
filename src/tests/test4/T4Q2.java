package tests.test4;

import util.StatUtil;

public class T4Q2 {
	/**
	 * Dependencies: <code>
	 * 		1. util.StatUtil
	 * </code>
	 */

	public static void main(String[] args) {
		final int d = 365;
		System.out.println(StatUtil.birthday2Linear(d, 16, 16));
		System.out.println(StatUtil.birthday2Linear(d, 43, 6));
	}
}
