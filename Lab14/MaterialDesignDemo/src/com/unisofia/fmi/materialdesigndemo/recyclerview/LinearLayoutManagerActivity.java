package com.unisofia.fmi.materialdesigndemo.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;

public class LinearLayoutManagerActivity extends BaseRecyclerViewActivity {

	private LinearLayoutManager mLinearLayoutManager;

	@Override
	protected LayoutManager getLayoutManager() {
		// example of horizontally scrolling single column RecyclerView
		mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		return mLinearLayoutManager;
	}

	@Override
	protected void onOrientationChange(boolean isVertical) {
		int orientation = isVertical ? LinearLayoutManager.HORIZONTAL
				: LinearLayoutManager.VERTICAL;
		mLinearLayoutManager.setOrientation(orientation);
	}

	@Override
	protected void onDirectionChange(boolean isReverse) {
		mLinearLayoutManager.setReverseLayout(isReverse);
	}

}
