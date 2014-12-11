package com.unisofia.fmi.databaseexample.database;

import android.provider.BaseColumns;

public class BooksContract {

	public static final String DB_NAME = "Books.db";
	
	// If you change the database schema you must increment version number
	public static final int DB_VERSION = 1;

	private BooksContract() {
		// forbid instantiation
	}

	/**
	 * By implementing the BaseColumns interface, {@link BookEntry} inherits a
	 * primary key field called _ID that some Android classes such as cursor
	 * adaptors use.
	 */
	public static class BookEntry implements BaseColumns {
		
		public static final String TABLE_NAME = "Books";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_AUTHOR = "author";
		public static final String COLUMN_NAME_YEAR = "year";
		public static final String COLUMN_NAME_PRICE = "price";
		
		private BookEntry() {
			// forbid instantiation
		}
	}

}
