package com.unisofia.fmi.clientserverexample.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.unisofia.fmi.clientserverexample.R;
import com.unisofia.fmi.clientserverexample.server.request.BaseRequest;

// Basic structure for sending requests.
public final class RequestExecutor {

	private static boolean sIsExecuting;

	public interface ResponseListener<T> {

		void onSucess(T response);

		void onError();

	}

	public static <T> boolean sendRequest(Context context, BaseRequest<T> request,
			ResponseListener<T> listener) {

		// TODO check for internet connection before executing the request

		if (sIsExecuting || request == null) {
			return false;
		}

		new ExecutorTask<T>(context, request, listener).execute();

		return true;
	}

	private static class ExecutorTask<T> extends AsyncTask<Void, Void, T> {

		private Context mContext;
		private BaseRequest<T> mRequest;
		private ResponseListener<T> mListener;

		private ProgressDialog mProgressDialog;

		public ExecutorTask(Context context, BaseRequest<T> request, ResponseListener<T> listener) {
			mContext = context;
			mRequest = request;
			mListener = listener;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			sIsExecuting = true;

			mProgressDialog = ProgressDialog.show(mContext,
					mContext.getString(R.string.progress_title),
					mContext.getString(R.string.progress_message), true, false);
		}

		@Override
		protected T doInBackground(Void... params) {
			// Create an HTTP client
			HttpClient client = new DefaultHttpClient();

			// Perform the request and check the status code
			try {
				HttpResponse response = client.execute(mRequest.getRequestObject());
				StatusLine statusLine = response.getStatusLine();

				int statusCode = statusLine.getStatusCode();
				if (statusCode >= 200 && statusCode < 300) {
					HttpEntity entity = response.getEntity();
					InputStream contentStream = entity.getContent();

					// Read the server response and attempt to parse it as JSON
					Reader reader = new InputStreamReader(contentStream);

					GsonBuilder gsonBuilder = new GsonBuilder();
					Gson gson = gsonBuilder.create();

					T jsonResponse = gson.fromJson(reader, mRequest.getResponseType());

					// close the stream
					contentStream.close();

					return jsonResponse;
				} else {
					Log.e("error", "Server responded with error status code: " + statusCode);
				}
			} catch (JsonParseException e) {
				Log.e("error", "Failed to parse JSON: " + e);
			} catch (ClientProtocolException e) {
				Log.e("error", "Failed to send HTTP request: " + e);
			} catch (IOException e) {
				Log.e("error", "Network error: " + e);
			}

			return null;
		}

		@Override
		protected void onPostExecute(T result) {
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			sIsExecuting = false;

			if (mListener == null) {
				return;
			}

			if (result != null) {
				mListener.onSucess(result);
			} else {
				mListener.onError();
			}
		}
	}
}
