package com.example.extendedcontrolexample;

import com.example.extendedcontrolexample.widgets.SoundButton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	private SoundButton mSoundButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSoundButton = (SoundButton) findViewById(R.id.sound_button);

		mSoundButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, R.string.clicked, Toast.LENGTH_SHORT).show();
			}
		});
	}

}
