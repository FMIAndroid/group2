package com.unisofia.fmi.materialdesigndemo.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;

public class GridLayoutManagerActivity extends BaseRecyclerViewActivity {

	private GridLayoutManager mGridLayoutManager;

	@Override
	protected LayoutManager getLayoutManager() {
		// example of horizontally scrolling 3 columns RecyclerView
		mGridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
		return mGridLayoutManager;
	}

	@Override
	protected void onOrientationChange(boolean isVertical) {
		int orientation = isVertical ? GridLayoutManager.HORIZONTAL : GridLayoutManager.VERTICAL;
		mGridLayoutManager.setOrientation(orientation);
	}

	@Override
	protected void onDirectionChange(boolean isReverse) {
		mGridLayoutManager.setReverseLayout(isReverse);
	}

}
