package com.unisofia.fmi.materialdesigndemo.switchcompat;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import com.unisofia.fmi.materialdesigndemo.BaseMaterialActivity;
import com.unisofia.fmi.materialdesigndemo.R;

public class SwitchCompatActivity extends BaseMaterialActivity {

	private SwitchCompat mSwitch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_switch_compat);

		mSwitch = (SwitchCompat) findViewById(R.id.switch_compat);
		mSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Toast.makeText(SwitchCompatActivity.this, "Checked: " + isChecked,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
