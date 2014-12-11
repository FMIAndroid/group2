package com.unisofia.fmi.databaseexample;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.unisofia.fmi.databaseexample.adapter.BooksAdapter;
import com.unisofia.fmi.databaseexample.database.BooksContract.BookEntry;
import com.unisofia.fmi.databaseexample.database.BooksDatabaseHelper;
import com.unisofia.fmi.databaseexample.database.model.Book;

public class BooksActivity extends ListActivity {

	private static final String[] PROJECTION = new String[] { 
			BookEntry._ID,
			BookEntry.COLUMN_NAME_TITLE, 
			BookEntry.COLUMN_NAME_AUTHOR, 
			BookEntry.COLUMN_NAME_YEAR,
			BookEntry.COLUMN_NAME_PRICE };


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books);

		BooksDatabaseHelper dbHelper = new BooksDatabaseHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query(BookEntry.TABLE_NAME, PROJECTION, null, null, null, null, null);

		/***** Iterating over database entries *****/
		// Usually this is done in a background thread
		List<Book> books = new ArrayList<Book>();
		try {
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				long id = cursor.getLong(cursor.getColumnIndexOrThrow(BookEntry._ID));
				String title = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_NAME_TITLE));
				String author = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_NAME_AUTHOR));
				String year = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_NAME_YEAR));
				String price = cursor.getString(cursor.getColumnIndexOrThrow(BookEntry.COLUMN_NAME_PRICE));

				Book book = new Book(id, title, author, year, price);
				books.add(book);
				cursor.moveToNext();
			}
		} finally {
			// make sure to close the cursor
			cursor.close();
		}

		BooksAdapter booksAdapter = new BooksAdapter(books);
		setListAdapter(booksAdapter);
		
		/***** Simple cursor adapter way *****/
//		int[] to = new int[] { 
//				R.id.textview_id, 
//				R.id.textview_title, 
//				R.id.textview_author,
//				R.id.textview_year, 
//				R.id.textview_price };
//		
//		SimpleCursorAdapter booksSimpleAdapter = new SimpleCursorAdapter(this, R.layout.list_item_book,
//				cursor, PROJECTION, to, 0);
//		
//		setListAdapter(booksSimpleAdapter);
	}
}
