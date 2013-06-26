package com.aaasen.smsvis;

import android.content.Context;
import android.os.AsyncTask;

import com.aaasen.smsvis.util.SMSRetriever;
import com.aaasen.smsvis.util.SMSStats;

public class FetchSMSTask extends AsyncTask<Void, Void, SMSStats> {
	private MainActivity activity;
	private Context context;
	
	public FetchSMSTask(MainActivity activity) {
		this.activity = activity;
		this.context = activity.getApplication();
	}
	
	@Override
	protected SMSStats doInBackground(Void... args) {
		SMSRetriever sms = new SMSRetriever(context);
		SMSStats stats = new SMSStats(context, sms.fetch());
		return stats;
	}

	@Override
	protected void onPostExecute(SMSStats result) {
		activity.onFetchSMS(result);
	}
}
