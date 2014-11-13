package com.unisofia.fmi.storageexample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.unisofia.fmi.storageexample.util.FileConsts;
import com.unisofia.fmi.storageexample.util.SharedPrefsConsts;

public class SecondActivity extends Activity implements OnClickListener {

	private TextView mContentTextView;
	private Button mSharedPrefsButton;
	private Button mInternalStorageButton;
	private Button mExternalStorageButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayShowHomeEnabled(true);
		setContentView(R.layout.activity_second);

		mContentTextView = (TextView) findViewById(R.id.textview_content);
		mSharedPrefsButton = (Button) findViewById(R.id.button_shared_prefs);
		mInternalStorageButton = (Button) findViewById(R.id.button_internal_storage);
		mExternalStorageButton = (Button) findViewById(R.id.button_external_storage);

		mSharedPrefsButton.setOnClickListener(this);
		mInternalStorageButton.setOnClickListener(this);
		mExternalStorageButton.setOnClickListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_shared_prefs:
			readFromSharedPreferences();
			break;
		case R.id.button_internal_storage:
			readFromInternalStorage();
			break;
		case R.id.button_external_storage:
			readFromExternalStorage();
			break;
		}
	}

	private void readFromSharedPreferences() {
		SharedPreferences sharedPreferences = getSharedPreferences(
				SharedPrefsConsts.NAME, Context.MODE_PRIVATE);
		String text = sharedPreferences.getString(SharedPrefsConsts.KEY_TEXT,
				"");
		mContentTextView.setText(text);
	}

	private void readFromInternalStorage() {
		StringBuilder sb = new StringBuilder();
		FileInputStream fis = null;
		BufferedReader br = null;
		String line = null;
		try {
			fis = openFileInput(FileConsts.FILE_NAME);
			br = new BufferedReader(new InputStreamReader(fis));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		mContentTextView.setText(sb.toString());
	}

	private void readFromExternalStorage() {
		String state = Environment.getExternalStorageState();
		if (!Environment.MEDIA_MOUNTED.equals(state)) {
			Toast.makeText(this, "External storage not available!",
					Toast.LENGTH_SHORT).show();
			return;
		}

		File sdCard = Environment.getExternalStorageDirectory();
		File dir = new File(sdCard.getAbsolutePath() + File.separator
				+ FileConsts.FOLDER_NAME + File.separator
				+ FileConsts.FILE_NAME);

		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		String line = null;
		try {
			br = new BufferedReader(new FileReader(dir));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		mContentTextView.setText(sb.toString());
	}
}
