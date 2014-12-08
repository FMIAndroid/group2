package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.unisofia.fmi.userinterface.fragment.DetailsFragment;
import com.unisofia.fmi.userinterface.fragment.UsersListFragment.OnUserItemSelected;
import com.unisofia.fmi.userinterface.util.Constants;

public class UsersActivity extends Activity implements OnUserItemSelected {

	private static final String KEY_CURRENT_POS = "currentPosition";

	private int mCurrentPosition;
	private boolean mIsLandscape;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);

		if (savedInstanceState == null) {
			mCurrentPosition = 0;
		} else {
			mCurrentPosition = savedInstanceState.getInt(KEY_CURRENT_POS);
		}

		mIsLandscape = getResources().getBoolean(R.bool.is_landscape);
		if (mIsLandscape) {
			showDetailsFragment();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt(KEY_CURRENT_POS, mCurrentPosition);

		super.onSaveInstanceState(outState);
	}

	@Override
	public void onUserSelected(int position) {
		handleOrientation(position);
	}

	private void handleOrientation(int position) {
		if (mIsLandscape) {
			if (mCurrentPosition != position) {
				mCurrentPosition = position;
				showDetailsFragment();
			}
		} else {
			mCurrentPosition = position;
			openDetailsActivity();
		}
	}

	private void showDetailsFragment() {
		Bundle args = new Bundle();
		args.putInt(Constants.EXTRA_USER_POSITION, mCurrentPosition);

		DetailsFragment detailsFragment = new DetailsFragment();
		detailsFragment.setArguments(args);

		getFragmentManager().beginTransaction()
				.replace(R.id.container_details, detailsFragment).commit();
	}

	private void openDetailsActivity() {
		Intent detailsIntent = new Intent(this, DetailsActivity.class);
		detailsIntent.putExtra(Constants.EXTRA_USER_POSITION, mCurrentPosition);
		startActivity(detailsIntent);
	}
}
