package com.unisofia.android.servicesexample.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class ToastService extends Service {

	private static final int TOAST_INTERVAL = 5000;

	private Handler mHandler;
	private ToastRunnable mToastRunnable;

	@Override
	public void onCreate() {
		super.onCreate();

		Toast.makeText(ToastService.this, "ToastService created!",
				Toast.LENGTH_SHORT).show();

		mHandler = new Handler();
		mToastRunnable = new ToastRunnable();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mHandler.postDelayed(new ToastRunnable(), TOAST_INTERVAL);

		return START_REDELIVER_INTENT;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		mHandler.removeCallbacks(mToastRunnable);

		Toast.makeText(ToastService.this, "ToastService destroyed!",
				Toast.LENGTH_SHORT).show();

		super.onDestroy();
	}

	private class ToastRunnable implements Runnable {

		@Override
		public void run() {
			Toast.makeText(ToastService.this, "Toast from the service!",
					Toast.LENGTH_SHORT).show();
			
			mHandler.postDelayed(mToastRunnable, TOAST_INTERVAL);
		}
	}
}
