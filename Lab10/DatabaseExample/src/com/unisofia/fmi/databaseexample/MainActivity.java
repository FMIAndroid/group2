package com.unisofia.fmi.databaseexample;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.unisofia.fmi.databaseexample.database.BooksContract.BookEntry;
import com.unisofia.fmi.databaseexample.database.BooksDatabaseHelper;

public class MainActivity extends Activity implements OnClickListener {

	private EditText mTitleEditText;
	private EditText mAuthorEditText;
	private EditText mYearEditText;
	private EditText mPriceEditText;
	private Button mSaveButton;
	private Button mShowallButton;
	private TextView mStatusTextView;

	private BooksDatabaseHelper mBooksDatabaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitleEditText = (EditText) findViewById(R.id.edittext_title);
		mAuthorEditText = (EditText) findViewById(R.id.edittext_author);
		mYearEditText = (EditText) findViewById(R.id.edittext_year);
		mPriceEditText = (EditText) findViewById(R.id.edittext_price);
		mSaveButton = (Button) findViewById(R.id.button_save);
		mShowallButton = (Button) findViewById(R.id.button_all);
		mStatusTextView = (TextView) findViewById(R.id.textview_status);

		mBooksDatabaseHelper = new BooksDatabaseHelper(this);

		mSaveButton.setOnClickListener(this);
		mShowallButton.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		mBooksDatabaseHelper.close();
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_save:
			saveBookToDatabase();
			break;
		case R.id.button_all:
		default:
			showAllBooks();
			break;
		}

	}

	private void showAllBooks() {
		Intent booksIntent = new Intent(this, BooksActivity.class);
		startActivity(booksIntent);
	}

	private void saveBookToDatabase() {
		String title = mTitleEditText.getText().toString();
		String author = mAuthorEditText.getText().toString();
		String year = mYearEditText.getText().toString();
		String price = "$ " + mPriceEditText.getText().toString();

		// TODO validate input

		// Create new map of values
		ContentValues values = new ContentValues();
		values.put(BookEntry.COLUMN_NAME_TITLE, title);
		values.put(BookEntry.COLUMN_NAME_AUTHOR, author);
		values.put(BookEntry.COLUMN_NAME_YEAR, year);
		values.put(BookEntry.COLUMN_NAME_PRICE, price);

		SQLiteDatabase db = mBooksDatabaseHelper.getWritableDatabase();
		long rowId = db.insert(BookEntry.TABLE_NAME, null, values);
		if (rowId == -1) {
			mStatusTextView.setTextColor(Color.RED);
			mStatusTextView.setText("Error writing values!");
		} else {
			mStatusTextView.setTextColor(Color.GREEN);
			mStatusTextView.setText(String.format("\"%s\" was saved successfully.", title));
			clearValues();
		}

	}

	private void clearValues() {
		mTitleEditText.setText(null);
		mAuthorEditText.setText(null);
		mYearEditText.setText(null);
		mPriceEditText.setText(null);
	}

}
