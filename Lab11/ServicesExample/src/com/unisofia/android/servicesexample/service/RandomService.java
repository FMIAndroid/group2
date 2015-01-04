package com.unisofia.android.servicesexample.service;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class RandomService extends Service {

	private final IBinder mBinder = new LocalBinder();
	private final Random mGenerator = new Random();

	@Override
	public void onCreate() {
		super.onCreate();

		Toast.makeText(this, "RandomService created!", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "RandomService destroyed!", Toast.LENGTH_SHORT)
				.show();
		super.onDestroy();
	}

	public int generateRandomNumber() {
		return mGenerator.nextInt(100);
	}

	public class LocalBinder extends Binder {
		
		public RandomService getService() {
			return RandomService.this;
		}
	}
}
