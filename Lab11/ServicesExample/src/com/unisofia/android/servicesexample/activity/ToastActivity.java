package com.unisofia.android.servicesexample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.unisofia.android.servicesexample.R;
import com.unisofia.android.servicesexample.service.ToastService;

public class ToastActivity extends Activity {

	private Button mStartServiceButton;
	private Button mStopServiceButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toast);

		mStartServiceButton = (Button) findViewById(R.id.button_start_service);
		mStopServiceButton = (Button) findViewById(R.id.button_stop_service);

		mStartServiceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent startIntent = new Intent(ToastActivity.this,
						ToastService.class);
				startService(startIntent);
			}
		});

		mStopServiceButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent stopIntent = new Intent(ToastActivity.this,
						ToastService.class);
				stopService(stopIntent);
			}
		});
	}

}
