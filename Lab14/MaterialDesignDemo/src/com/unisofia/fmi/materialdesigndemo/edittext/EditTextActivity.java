package com.unisofia.fmi.materialdesigndemo.edittext;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.unisofia.fmi.materialdesigndemo.BaseMaterialActivity;
import com.unisofia.fmi.materialdesigndemo.R;

public class EditTextActivity extends BaseMaterialActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edittext);

		LinearLayout root = (LinearLayout) findViewById(R.id.root);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.bottomMargin = 20;

		EditText editText = new EditText(this);
		editText.setHint("Standard EditText in code");
		editText.setLayoutParams(params);
		root.addView(editText);
		
		CustomEditText customEditText = new CustomEditText(this);
		customEditText.setHint("Custom EditText in code");
		customEditText.setLayoutParams(params);
		root.addView(customEditText);
		
		EditText inflatedEditText = (EditText) getLayoutInflater().inflate(R.layout.view_edittext, null);
		root.addView(inflatedEditText);
	}
}
