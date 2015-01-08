package com.example.combinedcontrolexample.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.combinedcontrolexample.R;

public class NavigationView extends LinearLayout {

	public interface INavigationListener {

		public void onLeft();

		public void onRight();
		
		public void onPageScrolled(int position);
	}

	private static final int FIRST_PAGE = 0;

	private INavigationListener mListener;

	private ViewPager mViewPager;
	private View mToLeftButton;
	private View mToRightButton;
	private TextView mInfoView;

	public NavigationView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater.from(context).inflate(R.layout.navigation_layout, this, true);

		init();
	}

	public ViewPager getViewPager() {
		return mViewPager;
	}

	public void setViewPager(ViewPager viewPager) {
		if (viewPager == null) {
			throw new IllegalArgumentException("ViewPager can not have null value...");
		}

		this.mViewPager = viewPager;
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int state) {}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				updateState();
				
				if(mListener != null) {
					mListener.onPageScrolled(position);
				}
			}

			@Override
			public void onPageScrollStateChanged(int position) {}
		});

		setupNavigationButtons();
		updateState();
	}

	public void setNavigationListener(INavigationListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException("INavigationListener can not have null value...");
		}

		this.mListener = listener;
	}

	private void init() {
		mToLeftButton = findViewById(R.id.previousButton);
		mToRightButton = findViewById(R.id.nextButton);
		mInfoView = (TextView) findViewById(R.id.pageIndicatorTextView);
	}

	private void setupNavigationButtons() {
		mToLeftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
				updateState();

				if (mListener != null) {
					mListener.onLeft();
				}
			}
		});

		mToRightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
				updateState();

				if (mListener != null) {
					mListener.onRight();
				}
			}
		});
	}

	private void updateState() {
		int currentPage = mViewPager.getCurrentItem();
		int totalPages = mViewPager.getAdapter().getCount();

		Object[] params = new String[] { String.valueOf(currentPage + 1),
				String.valueOf(totalPages) };
		mInfoView.setText(getContext().getString(R.string._1_s_of_2_s, params));

		manageNavigationButtonsState(currentPage, totalPages);
		invalidate();
	}

	private void manageNavigationButtonsState(int currentPage, int totalPages) {
		int leftVisibility = currentPage == FIRST_PAGE ? View.INVISIBLE : View.VISIBLE;
		int rightVisibility = currentPage == totalPages - 1 ? View.INVISIBLE : View.VISIBLE;
		mToLeftButton.setVisibility(leftVisibility);
		mToRightButton.setVisibility(rightVisibility);
	}
}
