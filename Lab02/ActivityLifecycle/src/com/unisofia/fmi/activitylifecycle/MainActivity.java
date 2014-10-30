package com.unisofia.fmi.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private static final String TAG_STATE = "currentState";

	private TextView mConsoleTextView;
	private Button mClearButton;
	private Button mOpenNextButton;

	private StringBuilder mStringBuilder = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mConsoleTextView = (TextView) findViewById(R.id.textview_console);
		mClearButton = (Button) findViewById(R.id.button_clear);
		mOpenNextButton = (Button) findViewById(R.id.button_open_next);

		mClearButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clear();
			}
		});
		
		mOpenNextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openNextScreen();
			}
		});

		addLine("OnCreate");
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		clear();
		addLine(savedInstanceState.getString(TAG_STATE));
		addLine("onRestoreInstanceState");
	}

	@Override
	protected void onStart() {
		super.onStart();
		addLine("onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		addLine("onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		addLine("onResume");
	}

	@Override
	protected void onPause() {
		addLine("onPause");
		super.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		addLine("onSaveInstanceState");
		outState.putString(TAG_STATE, mStringBuilder.toString());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onStop() {
		addLine("onStop");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		addLine("onDestroy");
		super.onDestroy();
	}

	private void addLine(String text) {
		mStringBuilder.append(text).append("\n");
		mConsoleTextView.setText(mStringBuilder.toString());
	}

	private void clear() {
		mStringBuilder.setLength(0);
		mConsoleTextView.setText("");
	}
	
	private void openNextScreen() {
		Intent starSecondIntent = new Intent(this, SecondActivity.class);
		startActivity(starSecondIntent);
	}
}
