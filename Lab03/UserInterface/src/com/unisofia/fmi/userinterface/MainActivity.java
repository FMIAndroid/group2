package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button_submit).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openDetails();
			}
		});

	}
	
	private void openDetails() {
		Intent intent = new Intent(this, DetailsActivity.class);
		startActivity(intent);
	}

}
