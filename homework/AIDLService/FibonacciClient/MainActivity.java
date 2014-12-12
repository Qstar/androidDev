package com.android.fibonacciclient;

import com.android.fibonacciclient.R;
import com.android.fibonaccicommon.FibonacciRequest;
import com.android.fibonaccicommon.FibonacciResponse;
import com.android.fibonaccicommon.IFibonacciService;
import com.android.fibonaccicommon.FibonacciRequest.Type;

import android.os.RemoteException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements ServiceConnection {

	private static final String TAG = "FibonacciClient";
	private IFibonacciService service;
	Button button = (Button) findViewById(R.id.button1);

	private long n;
	private Type type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume()'ed'");
		super.onResume();
		if (!super.bindService(new Intent(IFibonacciService.class.getName()),
				this, BIND_AUTO_CREATE)) {
			Log.w(TAG, "Failed tp bind to service");
		}
	}

	@Override
	protected void onPause() {
		Log.d(TAG, "onPause()'ed'");
		super.onPause();
		super.unbindService(this);
	}
	
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		Log.d(TAG, "onServiceConnected()'ed' to" + name);
		this.service = IFibonacciService.Stub.asInterface(service);
		this.button.setEnabled(true);
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		Log.d(TAG, "onServiceDisconnected()'ed' to" + name);
		this.service = null;
		this.button.setEnabled(false);
	}

		final FibonacciRequest request = new FibonacciRequest(n, type);

		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				try {
					long totalTime = SystemClock.uptimeMillis();
					FibonacciResponse response = MainActivity.this.service
							.fib(request);
					totalTime = SystemClock.uptimeMillis() - totalTime;
				} catch (RemoteException e) {
					Log.wtf(TAG, "Failed to communicate with the service", e);
				}
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				if (result == null) {
					Toast.makeText(MainActivity.this, R.string.fib_error,
							Toast.LENGTH_LONG).show();
				} else {
					MainActivity.this.output.setText(result);
				}
			}
		}.execute();
}
