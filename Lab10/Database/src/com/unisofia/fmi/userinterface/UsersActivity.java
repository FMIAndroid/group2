package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.unisofia.fmi.userinterface.fragment.DetailsFragment;
import com.unisofia.fmi.userinterface.fragment.UsersListFragment.OnUserItemSelected;
import com.unisofia.fmi.userinterface.util.Constants;

public class UsersActivity extends Activity implements OnUserItemSelected {

	private static final String KEY_CURRENT_ID = "currentUserId";

	private long mCurrentUserId;
	private boolean mIsLandscape;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);

		if (savedInstanceState == null) {
			mCurrentUserId = 1;
		} else {
			mCurrentUserId = savedInstanceState.getInt(KEY_CURRENT_ID);
		}

		mIsLandscape = getResources().getBoolean(R.bool.is_landscape);
		if (mIsLandscape) {
			showDetailsFragment();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putLong(KEY_CURRENT_ID, mCurrentUserId);

		super.onSaveInstanceState(outState);
	}

	@Override
	public void onUserSelected(long userId) {
		handleOrientation(userId);
	}

	private void handleOrientation(long userId) {
		if (mIsLandscape) {
			if (mCurrentUserId != userId) {
				mCurrentUserId = userId;
				showDetailsFragment();
			}
		} else {
			mCurrentUserId = userId;
			openDetailsActivity();
		}
	}

	private void showDetailsFragment() {
		Bundle args = new Bundle();
		args.putLong(Constants.EXTRA_USER_ID, mCurrentUserId);

		DetailsFragment detailsFragment = new DetailsFragment();
		detailsFragment.setArguments(args);

		getFragmentManager().beginTransaction()
				.replace(R.id.container_details, detailsFragment).commit();
	}

	private void openDetailsActivity() {
		Intent detailsIntent = new Intent(this, DetailsActivity.class);
		detailsIntent.putExtra(Constants.EXTRA_USER_ID, mCurrentUserId);
		startActivity(detailsIntent);
	}
}
