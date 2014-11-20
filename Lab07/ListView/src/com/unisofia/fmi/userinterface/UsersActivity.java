package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.os.Bundle;

public class UsersActivity extends Activity {
	
//	TODO create ListView and Adapter to show all users
//	To get all users call Storage.getInstance().getUsers();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);
	}
}
