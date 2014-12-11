package com.unisofia.fmi.databaseexample.adapter;

import java.util.List;

import com.unisofia.fmi.databaseexample.R;
import com.unisofia.fmi.databaseexample.database.model.Book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BooksAdapter extends BaseAdapter {

	private List<Book> mBooks;

	public BooksAdapter(List<Book> books) {
		mBooks = books;
	}

	@Override
	public int getCount() {
		return mBooks.size();
	}

	@Override
	public Book getItem(int position) {
		return mBooks.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.list_item_book, parent, false);

			holder = new ViewHolder();
			holder.mIdTextView = (TextView) convertView.findViewById(R.id.textview_id);
			holder.mTitleTextView = (TextView) convertView.findViewById(R.id.textview_title);
			holder.mAuthorTextView = (TextView) convertView.findViewById(R.id.textview_author);
			holder.mYearTextView = (TextView) convertView.findViewById(R.id.textview_year);
			holder.mPriceTextView = (TextView) convertView.findViewById(R.id.textview_price);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Book book = getItem(position);

		holder.mIdTextView.setText(String.valueOf(book.getId()));
		holder.mTitleTextView.setText(book.getTitle());
		holder.mAuthorTextView.setText(book.getAuthor());
		holder.mYearTextView.setText(book.getYear());
		holder.mPriceTextView.setText(book.getPrice());
		
		return convertView;
	}

	static class ViewHolder {
		TextView mIdTextView;
		TextView mTitleTextView;
		TextView mAuthorTextView;
		TextView mYearTextView;
		TextView mPriceTextView;
	}

}
