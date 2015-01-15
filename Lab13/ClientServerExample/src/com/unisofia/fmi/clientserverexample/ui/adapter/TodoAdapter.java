package com.unisofia.fmi.clientserverexample.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.unisofia.fmi.clientserverexample.R;
import com.unisofia.fmi.clientserverexample.server.model.Todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TodoAdapter extends BaseAdapter {

	private List<Todo> mTodos;

	public TodoAdapter(List<Todo> todos) {
		updateTodos(todos);
	}

	public void updateTodos(List<Todo> todos) {
		mTodos = todos != null ? todos : new ArrayList<Todo>();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mTodos.size();
	}

	@Override
	public Todo getItem(int position) {
		return mTodos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.list_item_todo, parent, false);

			holder = new ViewHolder();
			holder.mIdTextView = (TextView) convertView.findViewById(R.id.textview_id);
			holder.mTitleTextView = (TextView) convertView.findViewById(R.id.textview_title);
			holder.mCompletedTextView = (TextView) convertView.findViewById(R.id.textview_completed);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Todo todo = getItem(position);
		holder.mIdTextView.setText(String.valueOf(todo.getId()));
		holder.mTitleTextView.setText(todo.getTitle());
		holder.mCompletedTextView.setText(String.valueOf(todo.isCompleted()));

		if (position % 2 == 0) {
			convertView.setBackgroundResource(R.color.even);
		} else {
			convertView.setBackgroundResource(R.color.odd);
		}

		return convertView;
	}

	static class ViewHolder {
		TextView mIdTextView;
		TextView mTitleTextView;
		TextView mCompletedTextView;
	}

}
