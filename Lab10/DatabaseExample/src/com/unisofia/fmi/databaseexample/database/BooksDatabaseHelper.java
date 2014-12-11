package com.unisofia.fmi.databaseexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.unisofia.fmi.databaseexample.database.BooksContract.BookEntry;

public class BooksDatabaseHelper extends SQLiteOpenHelper {

	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + BookEntry.TABLE_NAME + " ("
			+ BookEntry._ID + " INTEGER PRIMARY KEY, " 
			+ BookEntry.COLUMN_NAME_TITLE + " TEXT UNIQUE, " 
			+ BookEntry.COLUMN_NAME_AUTHOR + " TEXT NOT NULL, "
			+ BookEntry.COLUMN_NAME_YEAR +  " TEXT NOT NULL, " 
			+ BookEntry.COLUMN_NAME_PRICE + " TEXT NOT NULL"
			+ " )";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + BookEntry.TABLE_NAME;

	public BooksDatabaseHelper(Context context) {
		super(context, BooksContract.DB_NAME, null, BooksContract.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Database", "Creating database: " + SQL_CREATE_ENTRIES);
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// delete the tables
		db.execSQL(SQL_DELETE_ENTRIES);
		// recreate the tables with the new schema
		onCreate(db);
	}
}