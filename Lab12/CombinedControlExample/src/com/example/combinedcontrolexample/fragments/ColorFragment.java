package com.example.combinedcontrolexample.fragments;

import java.util.Random;

import com.example.combinedcontrolexample.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ColorFragment extends Fragment {
	
	private static Random mRandomGenerator = new Random();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		int red = mRandomGenerator.nextInt(252);
		int green = mRandomGenerator.nextInt(252);
		int blue = mRandomGenerator.nextInt(252);
		
		int randomColor = Color.rgb(red, green, blue);
		
		View content = inflater.inflate(R.layout.fragment_color, container, false);
		content.setBackgroundColor(randomColor);
		return content;
	}
}