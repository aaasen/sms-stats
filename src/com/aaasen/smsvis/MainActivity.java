package com.aaasen.smsvis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.aaasen.smsvis.util.SMSStats;

public class MainActivity extends Activity {
	public static final String STAT_EXTRA = "com.aaasen.smsvis.stats";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FetchSMSTask task = new FetchSMSTask(this);
		task.execute();
	}

	protected void onFetchSMS(SMSStats stats) {
	    Intent intent = new Intent(this, PeopleStatsActivity.class);
	    intent.putExtra(STAT_EXTRA, stats);
	    startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

