package com.unisofia.fmi.userinterface.storage;

import java.util.ArrayList;
import java.util.List;

import com.unisofia.fmi.userinterface.model.User;

public final class Storage {

	private static Storage sInstance;

	private List<User> mUsers;

	private Storage() {
		mUsers = new ArrayList<User>();
	}

	public static Storage getInstance() {
		if (sInstance == null) {
			sInstance = new Storage();
		}

		return sInstance;
	}

	public void saveUser(User user) {
		mUsers.add(user);
	}

	public List<User> getUsers() {
		return mUsers;
	}
}