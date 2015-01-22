package com.unisofia.fmi.materialdesigndemo.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.unisofia.fmi.materialdesigndemo.BaseMaterialActivity;
import com.unisofia.fmi.materialdesigndemo.R;

public abstract class BaseRecyclerViewActivity extends BaseMaterialActivity {

	private RecyclerView mRecyclerView;
	private RecyclerViewAdapter mAdapter;

	protected abstract LayoutManager getLayoutManager();

	protected abstract void onOrientationChange(boolean isHorizontal);

	protected abstract void onDirectionChange(boolean isReverse);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recyclerview);

		List<String> items = new ArrayList<String>();
		for (int i = 0; i < 50; i++) {
			items.add("Item " + (i + 1));
		}

		mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		mRecyclerView.setLayoutManager(getLayoutManager());
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setHasFixedSize(true);
		mAdapter = new RecyclerViewAdapter(items);
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_recycler, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_orientation:
			item.setChecked(!item.isChecked());
			onOrientationChange(item.isChecked());
			return true;
		case R.id.action_direction:
			item.setChecked(!item.isChecked());
			onDirectionChange(item.isChecked());
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
