package com.unisofia.fmi.userinterface.fragment;

import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.unisofia.fmi.userinterface.R;
import com.unisofia.fmi.userinterface.adapter.UsersAdapter;
import com.unisofia.fmi.userinterface.model.User;
import com.unisofia.fmi.userinterface.storage.Storage;

public class UsersListFragment extends Fragment {

	public interface OnUserItemSelected {

		void onUserSelected(int position);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_users, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ListView usersListView = (ListView) view.findViewById(R.id.listview);
		List<User> users = Storage.getInstance().getUsers();
		UsersAdapter usersAdapter = new UsersAdapter(users);
		usersListView.setAdapter(usersAdapter);

		usersListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Activity parentActivity = getActivity();
				if (parentActivity instanceof OnUserItemSelected) {
					((OnUserItemSelected) parentActivity)
							.onUserSelected(position);
				} else {
					throw new IllegalStateException(
							"Parent activity must implement OnUserItemSelected");
				}
			}
		});
	}
}
