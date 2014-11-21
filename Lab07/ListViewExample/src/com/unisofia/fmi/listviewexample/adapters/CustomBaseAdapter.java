package com.unisofia.fmi.listviewexample.adapters;

import java.util.List;

import com.unisofia.fmi.listviewexample.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {

	private Context mContext;
	private List<String> mData;

	public CustomBaseAdapter(Context context, List<String> data) {
		mContext = context;
		mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public String getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			// inflate layout
			LayoutInflater inflater = LayoutInflater.from(mContext);
				convertView = inflater.inflate(R.layout.listview_item_dark,
						parent, false);
		
			// setup ViewHolder
			viewHolder = new ViewHolder();
			viewHolder.mTextView = (TextView) convertView
					.findViewById(R.id.textview);

			// save view holder
			convertView.setTag(viewHolder);
		} else {
			// restore view holder
			viewHolder = (ViewHolder) convertView.getTag();
		}

		// setup UI
		viewHolder.mTextView.setText(getItem(position));

		return convertView;
	}

	private  class ViewHolder {
		TextView mTextView;
	}

}
