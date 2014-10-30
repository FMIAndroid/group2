package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		TextView fullNameTextView = (TextView) findViewById(R.id.textview_full_name);
		TextView emailTextView = (TextView) findViewById(R.id.textview_email);
		TextView phoneTextView = (TextView) findViewById(R.id.textview_phone);
		
		Intent intent = getIntent();
		String fullName = intent.getStringExtra(Extras.EXTRA_FULL_NAME);
		String email = intent.getStringExtra(Extras.EXTRA_EMAIL);
		String phone = intent.getStringExtra(Extras.EXTRA_PHONE);
		
		fullNameTextView.setText(fullName);
		emailTextView.setText(email);
		phoneTextView.setText(phone);
	}
}
