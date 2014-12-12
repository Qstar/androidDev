package com.android.fibonacciservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FibonacciService extends Service {
	private static final String TAG = "FibonacciService";

	private IFibonacciServiceImpl service;

	@Override
	public void onCreate() {
		super.onCreate();
		this.service = new IFibonacciServiceImpl();
		Log.d(TAG, "onCreate()'ed'");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind()'ed'");
		return this.service;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(TAG, "onUnbind()'ed'");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy()'ed'");
		this.service = null;
		super.onDestroy();
	}
}
