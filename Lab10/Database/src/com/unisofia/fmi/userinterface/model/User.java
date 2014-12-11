package com.unisofia.fmi.userinterface.model;

public class User {

	private long mId;
	private String mName;
	private String mEmail;
	private String mPhone;
	private String mPhotoUrl;

	public User(long id, String name, String email, String phone,
			String photoUrl) {
		mId = id;
		mName = name;
		mEmail = email;
		mPhone = phone;
		mPhotoUrl = photoUrl;
	}

	public long getId() {
		return mId;
	}

	public void setId(long id) {
		mId = id;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		mEmail = email;
	}

	public String getPhone() {
		return mPhone;
	}

	public void setPhone(String phone) {
		mPhone = phone;
	}

	public String getPhotoUrl() {
		return mPhotoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		mPhotoUrl = photoUrl;
	}

	@Override
	public String toString() {
		return "User [mId=" + mId + ", mName=" + mName + ", mEmail=" + mEmail
				+ ", mPhone=" + mPhone + ", mPhotoUrl=" + mPhotoUrl + "]";
	}

}
