package com.example.newcontrolexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newcontrolexample.widgets.JoyStickView;
import com.example.newcontrolexample.widgets.JoyStickView.IJoystickListener;

public class MainActivity extends Activity implements IJoystickListener {

	private JoyStickView mJoyStickView;
	private LinearLayout mInfoContainerLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mJoyStickView = (JoyStickView) findViewById(R.id.joystick_view);
		mJoyStickView.setListener(this);

		mInfoContainerLayout = (LinearLayout) findViewById(R.id.info_container_layout);
	}
	
	@Override
	public void onClick() {
		addView(R.string.on_click);
	}

	@Override
	public void onLeft() {
		addView(R.string.on_left);
	}

	@Override
	public void onRight() {
		addView(R.string.on_right);
	}

	@Override
	public void onUp() {
		addView(R.string.on_up);
	}

	@Override
	public void onDown() {
		addView(R.string.on_down);
	}

	private void addView(int textResource) {
		TextView tv = new TextView(this);
		tv.setText(textResource);
		mInfoContainerLayout.addView(tv, 0);
	}

}
