package com.unisofia.fmi.materialdesigndemo;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.unisofia.fmi.materialdesigndemo.drawerlayout.DrawerLayoutActivity;
import com.unisofia.fmi.materialdesigndemo.edittext.EditTextActivity;
import com.unisofia.fmi.materialdesigndemo.recyclerview.GridLayoutManagerActivity;
import com.unisofia.fmi.materialdesigndemo.recyclerview.LinearLayoutManagerActivity;
import com.unisofia.fmi.materialdesigndemo.switchcompat.SwitchCompatActivity;
import com.unisofia.fmi.materialdesigndemo.toolbar.ToolbarActivity;

public class MainActivity extends ListActivity {

	private Map<String, Class<? extends Activity>> mExamples;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mExamples = new TreeMap<String, Class<? extends Activity>>();
		setupExamples();

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new ArrayList<String>(mExamples.keySet()));
		getListView().setAdapter(arrayAdapter);
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MainActivity.this, mExamples.get(arrayAdapter
						.getItem(position)));
				startActivity(intent);
			}
		});
	}

	private void setupExamples() {
		mExamples.put(getString(R.string.title_linear_layout_manager),
				LinearLayoutManagerActivity.class);
		mExamples.put(getString(R.string.title_grid_layout_manager),
				GridLayoutManagerActivity.class);
		mExamples.put(getString(R.string.title_switch_compat), SwitchCompatActivity.class);
		mExamples.put(getString(R.string.title_toolbar_activity), ToolbarActivity.class);
		mExamples.put(getString(R.string.title_edittext_activity), EditTextActivity.class);
		mExamples.put(getString(R.string.title_drawer_layout_activity), DrawerLayoutActivity.class);
	}

}
