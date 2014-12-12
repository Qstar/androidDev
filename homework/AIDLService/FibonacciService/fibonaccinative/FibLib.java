package com.android.fibonaccinative;

public class FibLib {
	public static long fibJR(long n) {
		return n <= 0 ? 0 : n == 1 ? 1 : fibJR(n - 1) + fibJR(n - 2);
	}

	public static long fibJI(long n) {
		long previous = -1;
		long result = 1;
		for (long i = 0; i <= n; i++) {
			long sum = result + previous;
			previous = result;
			result = sum;
		}
		return result;
	}
	
	public native static long fibNR(long n);
	
	public native static long fibNI(long n);
	
	static {
		System.loadLibrary("com_android_fibonaccinative_FibLib");
	}
}
