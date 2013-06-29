package com.aaasen.smsvis;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ListView;

import com.aaasen.smsvis.graph.EmbeddedWebViewClient;
import com.aaasen.smsvis.util.Person;
import com.aaasen.smsvis.util.SMS;
import com.aaasen.smsvis.util.SMSSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageStatsActivity extends Activity {
	private static final String MESSAGES_IDENTIFIER = "{messages}";
	private PeopleArrayAdapter adapter;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_stats);
		setupActionBar();

		Person person = (Person) getIntent().getSerializableExtra(MainActivity.PERSON_EXTRA);
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(person);
		adapter = new PeopleArrayAdapter(this, R.id.people_list, people);

		ListView listview = (ListView) findViewById(R.id.people_list);
		listview.setAdapter(adapter);

		WebView webview = (WebView) findViewById(R.id.webview);
		webview.setWebViewClient(new EmbeddedWebViewClient());
		webview.getSettings().setJavaScriptEnabled(true);
		
	    Gson gson = new GsonBuilder().registerTypeAdapter(SMS.class, new SMSSerializer()).create();
	    String json = gson.toJson(person.getMessages());
		
		AssetManager manager = this.getAssets();

		BufferedInputStream fileStream = null;
		try {
			fileStream = new BufferedInputStream(manager.open("graphs/index.html"));
			java.util.Scanner s = new java.util.Scanner(fileStream, "UTF-8").useDelimiter("\\A");
		    String file = s.hasNext() ? s.next() : "";
			file = file.replace(MESSAGES_IDENTIFIER, json);
			
			webview.loadDataWithBaseURL("file:///android_asset/graphs/", file, "text/html", "UTF-8", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message_stats, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
