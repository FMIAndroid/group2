package com.example.combinedcontrolexample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.combinedcontrolexample.adapters.ColorFragmentPagerAdapter;
import com.example.combinedcontrolexample.pagertransformers.ZoomOutPageTransformer;
import com.example.combinedcontrolexample.widgets.NavigationView;

public class MainActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private NavigationView mNavigationView;
	private ColorFragmentPagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mViewPager = (ViewPager) findViewById(R.id.view_pager);
		mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
		mPagerAdapter = new ColorFragmentPagerAdapter(getSupportFragmentManager());
		
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		mNavigationView.setViewPager(mViewPager);
	}
}