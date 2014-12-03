package com.unisofia.example.fragmentsexample.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuFragment extends ListFragment {

	public interface OnMenuItemSelectListener {

		void onItemSelected(String item);
	}

	private String[] mItems;
	

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		view.setBackgroundColor(Color.BLACK);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mItems = new String[15];
		for (int i = 0; i < mItems.length; i++) {
			mItems[i] = "Item " + (i + 1);
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, mItems);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		if (getActivity() instanceof OnMenuItemSelectListener) {
			((OnMenuItemSelectListener) getActivity())
					.onItemSelected(mItems[position]);
		} else {
			throw new IllegalStateException(
					"Perent activity must implement OnMenuItemSelectListener!");
		}
	}
}