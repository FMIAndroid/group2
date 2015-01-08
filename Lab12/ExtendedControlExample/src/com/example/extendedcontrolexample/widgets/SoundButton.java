package com.example.extendedcontrolexample.widgets;

import com.example.extendedcontrolexample.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class SoundButton extends Button {

	public SoundButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SoundButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SoundButton(Context context) {
		super(context);
	}

	// override setOnClickListener to add custom behavior
	@Override
	public void setOnClickListener(final OnClickListener l) {
		super.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// do your stuff, in this case play sound
				playSound();

				// delegate the click event to the initial listener
				l.onClick(v);
			}
		});
	}

	private void playSound() {
		MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.sound_button_click2);
		mediaPlayer.start();
	}
}
