package com.aaasen.smsvis;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.aaasen.smsvis.util.SMSStats;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FetchSMSTask task = new FetchSMSTask(this);
		task.execute();
	}

	protected void onFetchSMS(SMSStats stats) {
		TextView title = (TextView) getWindow().findViewById(R.id.text);
	    title.setText(stats.getMessages().subList(0, 5).toString());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

