package com.unisofia.fmi.userinterface.fragment;

import com.unisofia.fmi.userinterface.R;
import com.unisofia.fmi.userinterface.model.User;
import com.unisofia.fmi.userinterface.storage.Storage;
import com.unisofia.fmi.userinterface.util.Constants;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
		
		int userPosition = getArguments().getInt(
				Constants.EXTRA_USER_POSITION, -1);
		if (userPosition == -1) {
			return;
		}

		User user = Storage.getInstance().getUsers().get(userPosition);
		if (user == null) {
			return;
		}

		mProfileImageView = (ImageView) view.findViewById(R.id.imageview_profile);
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
