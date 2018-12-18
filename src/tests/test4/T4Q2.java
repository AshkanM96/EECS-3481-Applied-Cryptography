package tests.test4;

import java.util.HashMap;

public class T4Q2 {
	private static long choose(int n, int k) {
		return ((k == 0) ? 1 : (n * choose(n - 1, k - 1) / k));
	}

	private static final HashMap<Integer, Long> FACTORIALS = new HashMap<Integer, Long>(20);
	static {
		// 0! == 1
		T4Q2.FACTORIALS.put(0, 1l);
	}

	private static long factorial(int n) {
		Long result = T4Q2.FACTORIALS.get(n);
		if (result == null) {
			result = n * factorial(n - 1);
			T4Q2.FACTORIALS.put(n, result);
		}
		return result;
	}

	private static long stirling2(int n, int k) {
		if ((n == k) || ((k == 1) && (n >= 1))) {
			return 1;
		}

		long result = 0;
		for (int j = 0; j <= k; ++j) {
			result += ((int) Math.pow(-1, k - j)) * choose(k, j) * ((long) Math.pow(j, n));
		}
		return (result / factorial(k));
	}

	private static double twoSetBirthday(int d, int m, int n) {
		double probNoCollisions = 0, tmp = 0;
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				tmp = stirling2(m, i) * stirling2(n, j);
				for (int k = 0; k <= i + j - 1; ++k) {
					tmp *= (d - k);
				}
				probNoCollisions += tmp;
			}
		}
		probNoCollisions /= Math.pow(d, ((double) m) + n);
		return ((1 - probNoCollisions) * 100);
	}

	public static void main(String[] args) {
		System.out.println(twoSetBirthday(365, 16, 16));
	}
}
