package com.unisofia.fmi.userinterface.model;

public class User {

	private String mName;
	private String mEmail;
	private String mPhone;
	private String mPhotoUrl;

	public User(String name, String email, String phone, String photoUrl) {
		this.mName = name;
		this.mEmail = email;
		this.mPhone = phone;
		this.mPhotoUrl = photoUrl;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		this.mEmail = email;
	}

	public String getPhone() {
		return mPhone;
	}

	public void setPhone(String phone) {
		this.mPhone = phone;
	}

	public String getPhotoUrl() {
		return mPhotoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.mPhotoUrl = photoUrl;
	}

	@Override
	public String toString() {
		return "User [mName=" + mName + ", mEmail=" + mEmail + ", mPhone="
				+ mPhone + ", mPhotoUrl=" + mPhotoUrl + "]";
	}

}
