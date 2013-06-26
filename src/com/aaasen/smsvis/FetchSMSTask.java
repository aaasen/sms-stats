package com.aaasen.smsvis;

import android.os.AsyncTask;

import com.aaasen.smsvis.util.SMSRetriever;
import com.aaasen.smsvis.util.SMSStats;

public class FetchSMSTask extends AsyncTask<Void, Void, SMSStats> {
	private MainActivity activity;
	
	public FetchSMSTask(MainActivity activity) {
		this.activity = activity;
	}
	
	@Override
	protected SMSStats doInBackground(Void... args) {
		SMSRetriever sms = new SMSRetriever(activity.getApplication());
		return new SMSStats(sms.fetch());
	}

	@Override
	protected void onPostExecute(SMSStats result) {
		activity.onFetchSMS(result);
	}
}
