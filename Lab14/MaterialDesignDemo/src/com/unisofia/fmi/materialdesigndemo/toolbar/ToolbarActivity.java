package com.unisofia.fmi.materialdesigndemo.toolbar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;
import android.widget.Toast;

import com.unisofia.fmi.materialdesigndemo.BaseMaterialActivity;
import com.unisofia.fmi.materialdesigndemo.R;

public class ToolbarActivity extends BaseMaterialActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_toolbar);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("I am a toolbar");
		toolbar.inflateMenu(R.menu.menu_toolbar);
		toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				Toast.makeText(ToolbarActivity.this, "Clicked: " + menuItem.getTitle(),
						Toast.LENGTH_SHORT).show();
				return true;
			}
		});

	}
}
