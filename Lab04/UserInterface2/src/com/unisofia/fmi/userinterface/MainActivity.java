package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText mFirstNameEditText;
	private EditText mLastNameEditText;
	private EditText mEmailEditText;
	private EditText mPhoneEditText;
	private EditText mPasswordEditText;
	private EditText mPasswordAgainEditText;
	private Button mSubmitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFirstNameEditText = (EditText) findViewById(R.id.edittext_first_name);
		mLastNameEditText = (EditText) findViewById(R.id.edittext_last_name);
		mEmailEditText = (EditText) findViewById(R.id.edittext_email);
		mPhoneEditText = (EditText) findViewById(R.id.edittext_phone);
		mPasswordEditText = (EditText) findViewById(R.id.edittext_password);
		mPasswordAgainEditText = (EditText) findViewById(R.id.edittext_password_again);
		mSubmitButton = (Button) findViewById(R.id.button_submit);

		mSubmitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isInputValid()) {
					mPasswordEditText
							.setError(getString(R.string.error_passwords_not_match));
				}
			}
		});
	}

	private boolean isInputValid() {
		String firstName = mFirstNameEditText.getText().toString();
		String lastName = mLastNameEditText.getText().toString();
		String email = mEmailEditText.getText().toString();
		String phone = mPhoneEditText.getText().toString();
		String password = mPasswordEditText.getText().toString();
		String passwordAgain = mPasswordAgainEditText.getText().toString();

//		TODO HOMEWORK: Validate all data
		if (!password.equals(passwordAgain)) {
			return false;
		}

		StringBuilder fullName = new StringBuilder();
		fullName.append(firstName).append(" ").append(lastName);

		Intent detaisIntent = new Intent(this, DetailsActivity.class);
		detaisIntent.putExtra(Extras.EXTRA_FULL_NAME, fullName.toString());
		detaisIntent.putExtra(Extras.EXTRA_EMAIL, email);
		detaisIntent.putExtra(Extras.EXTRA_PHONE, phone);

		startActivity(detaisIntent);

		return true;
	}
}