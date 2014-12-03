package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		int userPosition = getIntent().getIntExtra(
				Constants.EXTRA_USER_POSITION, -1);
		if (userPosition == -1) {
			return;
		}

		User user = Storage.getInstance().getUsers().get(userPosition);
		if (user == null) {
			return;
		}

		mProfileImageView = (ImageView) findViewById(R.id.imageview_profile);
		mNameTextView = (TextView) findViewById(R.id.textview_full_name);
		mEmailTextView = (TextView) findViewById(R.id.textview_email);
		mPhoneTextView = (TextView) findViewById(R.id.textview_phone);

		mNameTextView.setText(user.getName());
		mEmailTextView.setText(user.getEmail());
		mPhoneTextView.setText(user.getPhone());
		mProfileImageView.setImageBitmap(BitmapFactory.decodeFile(user
				.getPhotoUrl()));
	}
}
