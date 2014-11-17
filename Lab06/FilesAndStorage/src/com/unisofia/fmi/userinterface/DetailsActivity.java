package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {

	private ImageView mProfileImageView;
	private TextView mNameTextView;
	private TextView mEmailTextView;
	private TextView mPhoneTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		mProfileImageView = (ImageView) findViewById(R.id.imageview_profile);
		mNameTextView = (TextView) findViewById(R.id.textview_full_name);
		mEmailTextView = (TextView) findViewById(R.id.textview_email);
		mPhoneTextView = (TextView) findViewById(R.id.textview_phone);

		SharedPreferences prefs = getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
		String name = prefs.getString(Constants.EXTRA_NAME, "");
		String email = prefs.getString(Constants.EXTRA_EMAIL, "");
		String phone = prefs.getString(Constants.EXTRA_PHONE, "");
		String photoPath = prefs.getString(Constants.EXTRA_PHOTO_PATH, "");

		mNameTextView.setText(name);
		mEmailTextView.setText(email);
		mPhoneTextView.setText(phone);
		mProfileImageView.setImageBitmap(BitmapFactory.decodeFile(photoPath));
	}
}
