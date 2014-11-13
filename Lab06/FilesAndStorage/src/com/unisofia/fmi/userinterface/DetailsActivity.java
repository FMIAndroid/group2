package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends Activity {

	private TextView mNameTextView;
	private TextView mEmailTextView;
	private TextView mPhoneTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		mNameTextView = (TextView) findViewById(R.id.textview_full_name);
		mEmailTextView = (TextView) findViewById(R.id.textview_email);
		mPhoneTextView = (TextView) findViewById(R.id.textview_phone);

//		TODO Get the params from SharedPreferences
//		Intent intent = getIntent();
//		String name = intent.getStringExtra(Extras.EXTRA_NAME);
//		String email = intent.getStringExtra(Extras.EXTRA_EMAIL);
//		String phone = intent.getStringExtra(Extras.EXTRA_PHONE);
//
//		mNameTextView.setText(name);
//		mEmailTextView.setText(email);
//		mPhoneTextView.setText(phone);
	}
}
