package com.unisofia.fmi.userinterface;

import java.util.List;

import com.unisofia.fmi.userinterface.adapter.UsersAdapter;
import com.unisofia.fmi.userinterface.model.User;
import com.unisofia.fmi.userinterface.storage.Storage;
import com.unisofia.fmi.userinterface.util.Constants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
		
		usersListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent detailsIntent = new Intent(UsersActivity.this, DetailsActivity.class);
				detailsIntent.putExtra(Constants.EXTRA_USER_POSITION, position);
				startActivity(detailsIntent);
			}
		});
	}
}
