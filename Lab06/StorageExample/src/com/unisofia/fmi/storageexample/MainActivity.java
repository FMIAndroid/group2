package com.unisofia.fmi.storageexample;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unisofia.fmi.storageexample.util.FileConsts;
import com.unisofia.fmi.storageexample.util.SharedPrefsConsts;

public class MainActivity extends Activity implements OnClickListener {

	private EditText mContentEditText;
	private Button mSharedPrefsButton;
	private Button mInternalStorageButton;
	private Button mExternalStorageButton;
	private Button mOpenNextScreenButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContentEditText = (EditText) findViewById(R.id.edittext_content);
		mSharedPrefsButton = (Button) findViewById(R.id.button_shared_prefs);
		mInternalStorageButton = (Button) findViewById(R.id.button_internal_storage);
		mExternalStorageButton = (Button) findViewById(R.id.button_external_storage);
		mOpenNextScreenButton = (Button) findViewById(R.id.button_next);

		mSharedPrefsButton.setOnClickListener(this);
		mInternalStorageButton.setOnClickListener(this);
		mExternalStorageButton.setOnClickListener(this);
		mOpenNextScreenButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_shared_prefs:
			writeInSharedPreferences();
			break;
		case R.id.button_internal_storage:
			writeInInternalStorage();
			break;
		case R.id.button_external_storage:
			writeInExternalStorage();
			break;
		case R.id.button_next:
			openNextScreen();
			break;
		}
	}

	private void writeInSharedPreferences() {
		String text = mContentEditText.getText().toString();
		SharedPreferences sharedPreferences = getSharedPreferences(
				SharedPrefsConsts.NAME, Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(SharedPrefsConsts.KEY_TEXT, text);
		editor.commit();
		// OR
		// editor.apply();
		// What is the difference?
	}

	private void writeInInternalStorage() {
		String text = mContentEditText.getText().toString();
		FileOutputStream fos = null;
		try {
			fos = openFileOutput(FileConsts.FILE_NAME, Context.MODE_PRIVATE);
			fos.write(text.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void writeInExternalStorage() {
		String text = mContentEditText.getText().toString();
		String state = Environment.getExternalStorageState();
		if (!Environment.MEDIA_MOUNTED.equals(state)) {
			Toast.makeText(this, "External storage not available!",
					Toast.LENGTH_SHORT).show();
			return;
		}

		File sdCard = Environment.getExternalStorageDirectory();
		File dir = new File(sdCard.getAbsolutePath() + File.separator
				+ FileConsts.FOLDER_NAME);
		dir.mkdirs();
		File file = new File(dir, FileConsts.FILE_NAME);

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(text.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void openNextScreen() {
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
	}
}
