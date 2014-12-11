package com.unisofia.fmi.databaseexample.database.model;

public class Book {

	private long mId;
	private String mTitle;
	private String mAuthor;
	private String mYear;
	private String mPrice;

	public Book(long id, String title, String author, String year, String price) {
		mId = id;
		mTitle = title;
		mAuthor = author;
		mYear = year;
		mPrice = price;
	}

	public long getId() {
		return mId;
	}

	public void setId(long id) {
		mId = id;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public String getAuthor() {
		return mAuthor;
	}

	public void setAuthor(String author) {
		mAuthor = author;
	}

	public String getYear() {
		return mYear;
	}

	public void setYear(String year) {
		mYear = year;
	}

	public String getPrice() {
		return mPrice;
	}

	public void setPrice(String price) {
		mPrice = price;
	}

	@Override
	public String toString() {
		return "Book [mId=" + mId + ", mTitle=" + mTitle + ", mAuthor=" + mAuthor + ", mYear="
				+ mYear + ", mPrice=" + mPrice + "]";
	}

}
