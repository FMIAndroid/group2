package com.unisofia.fmi.userinterface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int REQUEST_CODE_CAMERA = 564;
	private static final int REQUEST_CODE_GALLERY = 965;

	private ImageView mProfileImageView;
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

		mProfileImageView = (ImageView) findViewById(R.id.imageview_profile);
		mFirstNameEditText = (EditText) findViewById(R.id.edittext_first_name);
		mLastNameEditText = (EditText) findViewById(R.id.edittext_last_name);
		mEmailEditText = (EditText) findViewById(R.id.edittext_email);
		mPhoneEditText = (EditText) findViewById(R.id.edittext_phone);
		mPasswordEditText = (EditText) findViewById(R.id.edittext_password);
		mPasswordAgainEditText = (EditText) findViewById(R.id.edittext_password_again);
		mSubmitButton = (Button) findViewById(R.id.button_submit);

		mProfileImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openImageChooserDialog();
			}
		});

		mSubmitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isInputValid()) {
					mPasswordEditText
							.setError(getString(R.string.error_invalid_password));
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}

		switch (requestCode) {
		case REQUEST_CODE_CAMERA:
			// TODO save the captured image to the SD card and set the full
			// sized image as profile, save the path to it to shared preference
			break;
		case REQUEST_CODE_GALLERY:
			// TODO Set the selected image as profile and save the path to it in
			// shared preferences
			break;
		}

	}

	private void openImageChooserDialog() {
		AlertDialog.Builder chooserDialogBuilder = new AlertDialog.Builder(this);
		chooserDialogBuilder.setNeutralButton(R.string.alert_chooser_cancel,
				null);
		chooserDialogBuilder.setTitle(R.string.alert_chooser_title).setItems(
				R.array.alert_chooser_items,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent imageIntent = null;
						int requestCode = 0;
						switch (which) {
						// Camera
						case 0:
							imageIntent = createCameraIntent();
							requestCode = REQUEST_CODE_CAMERA;
							break;
						// Gallery
						default:
							imageIntent = createGalleryIntent();
							requestCode = REQUEST_CODE_GALLERY;
							break;
						}
						if (imageIntent.resolveActivity(getPackageManager()) != null) {
							startActivityForResult(imageIntent, requestCode);
						} else {
							Toast.makeText(getApplicationContext(),
									R.string.error_unhandled_intent,
									Toast.LENGTH_SHORT).show();
						}
					}

				});
		chooserDialogBuilder.show();
	}

	private Intent createCameraIntent() {
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(/* Your file here */));
		return cameraIntent;
	}

	private Intent createGalleryIntent() {
		Intent galleryIntent = new Intent(Intent.ACTION_PICK);
		galleryIntent.setType("image/*");
		return galleryIntent;
	}

	private boolean isInputValid() {
		String firstName = mFirstNameEditText.getText().toString();
		String lastName = mLastNameEditText.getText().toString();
		String email = mEmailEditText.getText().toString();
		String phone = mPhoneEditText.getText().toString();
		String password = mPasswordEditText.getText().toString();
		String passwordAgain = mPasswordAgainEditText.getText().toString();

		// TODO HOMEWORK: validate all data

		if (!password.equals(passwordAgain)) {
			return false;
		}

		StringBuilder name = new StringBuilder();
		name.append(firstName).append(" ").append(lastName);

		Intent detailsIntent = new Intent(this, DetailsActivity.class);
		// TODO pass save the params in SharedPreferences instead of passing
		// them as intent extras

		// detailsIntent.putExtra(Extras.EXTRA_NAME, name.toString());
		// detailsIntent.putExtra(Extras.EXTRA_EMAIL, email);
		// detailsIntent.putExtra(Extras.EXTRA_PHONE, phone);

		startActivity(detailsIntent);

		return true;
	}
}
