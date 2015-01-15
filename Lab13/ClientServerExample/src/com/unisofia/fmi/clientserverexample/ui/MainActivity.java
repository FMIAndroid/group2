package com.unisofia.fmi.clientserverexample.ui;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.unisofia.fmi.clientserverexample.R;
import com.unisofia.fmi.clientserverexample.server.RequestExecutor;
import com.unisofia.fmi.clientserverexample.server.model.Todo;
import com.unisofia.fmi.clientserverexample.server.request.BaseRequest;
import com.unisofia.fmi.clientserverexample.ui.adapter.TodoAdapter;

public class MainActivity extends Activity {

	private TodoAdapter mTodosAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTodosAdapter = new TodoAdapter(null);
		ListView todosListView = (ListView) findViewById(R.id.listview_todos);
		todosListView.setAdapter(mTodosAdapter);
		todosListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Todo todo = mTodosAdapter.getItem(position);
				showTodoDetails(todo);
			}
		});

		Button sendRequestButton = (Button) findViewById(R.id.button_send);
		sendRequestButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateTodos();
			}
		});

	}

	protected void updateTodos() {
		// Create new request
		BaseRequest<List<Todo>> request = new BaseRequest<List<Todo>>() {

			@Override
			public HttpUriRequest getRequestObject() {
				return new HttpGet("http://jsonplaceholder.typicode.com/todos");
			}

			@Override
			public Type getResponseType() {
				return new TypeToken<List<Todo>>() {}.getType();
			}
		};

		// Send the request and handle the result
		RequestExecutor.sendRequest(this, request,
				new RequestExecutor.ResponseListener<List<Todo>>() {

					@Override
					public void onSucess(List<Todo> response) {
						// on successful response, update todos list
						mTodosAdapter.updateTodos(response);
					}

					@Override
					public void onError() {
						// on error, show error message
						Toast.makeText(MainActivity.this,
								"Error executing request.", Toast.LENGTH_SHORT)
								.show();
					}
				});
	}

	private void showTodoDetails(Todo todo) {
		String message = String.format(Locale.getDefault(), "%d\n%s\n%b", todo.getId(), todo.getTitle(), todo.isCompleted());
		Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
	}
}
