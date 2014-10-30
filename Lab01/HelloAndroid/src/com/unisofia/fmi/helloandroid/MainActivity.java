package com.unisofia.fmi.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView mTextView;
	String string;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextView = (TextView) findViewById(R.id.textView1);
		Button button = (Button) findViewById(R.id.button1);

		string = getString(R.string.some_text);
		button.setOnClickListener(new View.OnClickListener() {
			

			@Override
			public void onClick(View v) {
				mTextView.setText(string);
			}
		});
	}
}
