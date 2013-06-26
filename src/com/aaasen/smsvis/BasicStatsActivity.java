package com.aaasen.smsvis;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aaasen.smsvis.util.SMSStats;

public class BasicStatsActivity extends Activity {
	private SMSStats stats;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic_stats);
		setupActionBar();
		
		stats = (SMSStats) getIntent().getSerializableExtra(MainActivity.STAT_EXTRA);
		
		TextView timeView = (TextView) findViewById(R.id.time_period);
		timeView.setText(stats.getMessages().get(0).getDate().toString());
		
		TextView sentView = (TextView) findViewById(R.id.sent_messages);
		sentView.setText(Integer.toString(stats.getNumSent()));
	    
		TextView receivedView = (TextView) findViewById(R.id.received_messages);
		receivedView.setText(Integer.toString(stats.getNumReceived()));
		
		TextView totalView = (TextView) findViewById(R.id.total_messages);
		totalView.setText(Integer.toString(stats.getNumTotal()));
		
		TextView analysisView = (TextView) findViewById(R.id.analysis);
		analysisView.setText("you cool");

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
		getMenuInflater().inflate(R.menu.basic_stats, menu);
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
