package com.unisofia.fmi.clientserverexample.server.model;

import com.google.gson.annotations.SerializedName;

public class Todo {

	private Long id;
	private String title;
	// when the variable name and the JSON key are different use SerializedName
	// annotation
	@SerializedName("completed")
	private boolean isCompleted;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

}
