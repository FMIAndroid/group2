package com.unisofia.fmi.userinterface;

import java.util.List;

import com.unisofia.fmi.userinterface.adapter.UsersAdapter;
import com.unisofia.fmi.userinterface.model.User;
import com.unisofia.fmi.userinterface.storage.Storage;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class UsersActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);
		
		ListView usersListView = (ListView) findViewById(R.id.listview);
		List<User> users = Storage.getInstance().getUsers();
		UsersAdapter usersAdapter = new UsersAdapter(users);
		usersListView.setAdapter(usersAdapter);
	}
}
