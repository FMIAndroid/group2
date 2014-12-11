package com.unisofia.fmi.userinterface.fragment;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.unisofia.fmi.userinterface.R;
import com.unisofia.fmi.userinterface.model.User;
import com.unisofia.fmi.userinterface.util.Constants;

public class DetailsFragment extends Fragment {

	private ImageView mProfileImageView;
	private TextView mNameTextView;
	private TextView mEmailTextView;
	private TextView mPhoneTextView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_details, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		long userId = getArguments().getInt(Constants.EXTRA_USER_ID, -1);
		if (userId == -1) {
			return;
		}

		// TODO get user's data from the database by his id
		User user = null;

		mProfileImageView = (ImageView) view
				.findViewById(R.id.imageview_profile);
		mNameTextView = (TextView) view.findViewById(R.id.textview_full_name);
		mEmailTextView = (TextView) view.findViewById(R.id.textview_email);
		mPhoneTextView = (TextView) view.findViewById(R.id.textview_phone);

		mNameTextView.setText(user.getName());
		mEmailTextView.setText(user.getEmail());
		mPhoneTextView.setText(user.getPhone());
		mProfileImageView.setImageBitmap(BitmapFactory.decodeFile(user
				.getPhotoUrl()));
	}
}
