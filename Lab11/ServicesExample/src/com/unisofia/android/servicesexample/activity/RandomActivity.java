package com.unisofia.android.servicesexample.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.unisofia.android.servicesexample.R;
import com.unisofia.android.servicesexample.service.RandomService;
import com.unisofia.android.servicesexample.service.RandomService.LocalBinder;

public class RandomActivity extends Activity {

	private RandomService mRandomService;
	private boolean mIsBound = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_random);

		Button mRandomButton = (Button) findViewById(R.id.button_generate_rand_num);
		mRandomButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mIsBound) {
					int num = mRandomService.generateRandomNumber();
					Toast.makeText(RandomActivity.this,
							"Random number: " + num, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		Intent intent = new Intent(this, RandomService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mIsBound) {
			unbindService(mConnection);
			mIsBound = false;
		}
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			LocalBinder binder = (LocalBinder) service;
			mRandomService = binder.getService();
			mIsBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mIsBound = false;
		}
	};
}
