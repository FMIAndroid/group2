package com.unisofia.fmi.listviewexample;

import java.util.ArrayList;

import com.unisofia.fmi.listviewexample.adapters.CustomArrayAdapter;
import com.unisofia.fmi.listviewexample.adapters.CustomBaseAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ListView mListView;
	private BaseAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ArrayList<String> data = new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			data.add("Item " + (i + 1));
		}

		mListView = (ListView) findViewById(R.id.listview);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(MainActivity.this,
						"Clicked: " + data.get(position), Toast.LENGTH_SHORT)
						.show();
			}
		});

		/* ArrayAdapter */
		mAdapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, data);

		/* Custom ArrayAdapter */
		// mAdapter = new CustomArrayAdapter(this, data);

		/* Custom BaseAdapter */
		// mAdapter = new CustomBaseAdapter(this, data);

		/* CursorAdapter... others */

		// set the adapter to the ListView
		mListView.setAdapter(mAdapter);
	}
}
