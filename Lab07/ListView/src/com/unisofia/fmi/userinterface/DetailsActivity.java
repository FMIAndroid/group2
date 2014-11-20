package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.unisofia.fmi.userinterface.model.User;
import com.unisofia.fmi.userinterface.storage.Storage;
import com.unisofia.fmi.userinterface.util.Constants;

public class DetailsActivity extends Activity {

	private ImageView mProfileImageView;
	private TextView mNameTextView;
	private TextView mEmailTextView;
	private TextView mPhoneTextView;
	private Button mShowAllButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		mProfileImageView = (ImageView) findViewById(R.id.imageview_profile);
		mNameTextView = (TextView) findViewById(R.id.textview_full_name);
		mEmailTextView = (TextView) findViewById(R.id.textview_email);
		mPhoneTextView = (TextView) findViewById(R.id.textview_phone);
		mShowAllButton = (Button) findViewById(R.id.button_show_all);

		SharedPreferences prefs = getSharedPreferences(Constants.PREFS_NAME,
				Context.MODE_PRIVATE);
		String name = prefs.getString(Constants.EXTRA_NAME, "");
		String email = prefs.getString(Constants.EXTRA_EMAIL, "");
		String phone = prefs.getString(Constants.EXTRA_PHONE, "");
		String photoPath = prefs.getString(Constants.EXTRA_PHOTO_PATH, "");

		// save the user
		User newUser = new User(name, email, phone, photoPath);
		Storage.getInstance().saveUser(newUser);

		mNameTextView.setText(name);
		mEmailTextView.setText(email);
		mPhoneTextView.setText(phone);
		mProfileImageView.setImageBitmap(BitmapFactory.decodeFile(photoPath));

		mShowAllButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent allIntent = new Intent(DetailsActivity.this,
						UsersActivity.class);
				startActivity(allIntent);
			}
		});
	}
}
