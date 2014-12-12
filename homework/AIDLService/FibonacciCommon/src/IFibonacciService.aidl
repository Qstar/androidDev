package com.android.fibonaccicommon;

import com.android.fibonaccicommon.FibonacciRequest;
import com.android.fibonaccicommon.FibonacciResponse;

interface IFibonacciService{
		long fibJR(in long n);
		long fibJI(in long n);
		long fibNR(in long n);
		long fibNI(in long n);
		FibonacciResponse fib(in FibonacciRequest request);
}