package com.aaasen.smsvis;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.aaasen.smsvis.util.SMS;
import com.aaasen.smsvis.util.SMSRetriever;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SMSRetriever sms = new SMSRetriever(getApplicationContext());
		ArrayList<SMS> messages = sms.fetch();
		
		TextView title = (TextView) getWindow().findViewById(R.id.text);
	    title.setText(messages.subList(0, 5).toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
