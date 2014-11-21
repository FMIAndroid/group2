package com.unisofia.fmi.listviewexample.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.unisofia.fmi.listviewexample.R;

public class CustomArrayAdapter extends ArrayAdapter<String> {

	private Context mContext;

	public CustomArrayAdapter(Context context, List<String> objects) {
		super(context, R.layout.listview_item_dark, objects);

		mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View row = null;
		if (position % 2 == 0) {
			row = inflater.inflate(R.layout.listview_item_dark, parent, false);
		} else {
			row = inflater.inflate(R.layout.listview_item_light, parent, false);
		}

		TextView textView = (TextView) row.findViewById(R.id.textview);
		textView.setText(getItem(position));

		// ViewHolder?

		return row;
	}
}
