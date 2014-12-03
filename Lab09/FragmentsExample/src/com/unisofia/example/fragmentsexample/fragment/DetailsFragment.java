package com.unisofia.example.fragmentsexample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unisofia.example.fragmentsexample.R;
import com.unisofia.example.fragmentsexample.util.Extras;
import com.unisofia.example.fragmentsexample.widget.StrikeThroughTextView;

public class DetailsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_details, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		String item = getArguments().getString(Extras.EXTRA_ITEM);
		StrikeThroughTextView detailsTextView = (StrikeThroughTextView) view
				.findViewById(R.id.textview_details);
		detailsTextView.setText(item);
	}
}
