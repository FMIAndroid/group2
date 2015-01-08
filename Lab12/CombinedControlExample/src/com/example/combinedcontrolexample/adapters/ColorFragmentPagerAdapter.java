package com.example.combinedcontrolexample.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.combinedcontrolexample.fragments.ColorFragment;

public class ColorFragmentPagerAdapter extends FragmentPagerAdapter {

	private static final int FRAGMENTS_COUNT = 9;

	public ColorFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return new ColorFragment();
	}

	@Override
	public int getCount() {
		return FRAGMENTS_COUNT;
	}
}