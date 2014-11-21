package com.unisofia.fmi.userinterface.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.unisofia.fmi.userinterface.R;
import com.unisofia.fmi.userinterface.model.User;

public class UsersAdapter extends BaseAdapter {

	private List<User> mUsers;

	public UsersAdapter(List<User> users) {
		mUsers = users;
	}

	@Override
	public int getCount() {
		return mUsers.size();
	}

	@Override
	public Object getItem(int position) {
		return mUsers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.list_item_user, parent,
					false);

			holder = new ViewHolder();
			holder.mProfileImageView = (ImageView) convertView
					.findViewById(R.id.imageview_profile);
			holder.mNameTextView = (TextView) convertView
					.findViewById(R.id.textview_name);
			holder.mEmailTextView = (TextView) convertView
					.findViewById(R.id.textview_email);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		User user = (User) getItem(position);
		// Do not show the image, because the resolution is too high, may cause
		// OutOfMemoryError
		// Try scaling down before that
		// holder.mProfileImageView.setImageBitmap(BitmapFactory.decodeFile(user.getPhotoUrl()));
		holder.mNameTextView.setText(user.getName());
		holder.mEmailTextView.setText(user.getEmail());
		
		return convertView;
	}

	static class ViewHolder {

		ImageView mProfileImageView;
		TextView mNameTextView;
		TextView mEmailTextView;

	}

}
