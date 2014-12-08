package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.os.Bundle;

import com.unisofia.fmi.userinterface.fragment.DetailsFragment;

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		Bundle args = getIntent().getExtras();

		DetailsFragment detailsFragment = new DetailsFragment();
		detailsFragment.setArguments(args);

		getFragmentManager().beginTransaction()
				.replace(R.id.container_details, detailsFragment).commit();

	}
}
