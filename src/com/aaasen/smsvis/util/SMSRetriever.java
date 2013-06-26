package com.aaasen.smsvis.util;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class SMSRetriever {
	public static final String DEFAULT_PROVIDER = "content://sms/";
	private Context context;

	public SMSRetriever(Context context) {
		this.context = context;
	}

	public ArrayList<SMS> fetch(String provider) {
		Cursor cursor = context.getContentResolver().query(Uri.parse(provider),
				null, null, null, null);
		cursor.moveToFirst();

		ArrayList<SMS> messages = new ArrayList<SMS>();

		do {
			HashMap<String, String> message = new HashMap<String, String>();

//			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < cursor.getColumnCount(); i++) {
//				sb.append(" {" + cursor.getColumnName(i) + " : " + cursor.getString(i) + "} ");
				message.put(cursor.getColumnName(i), cursor.getString(i));
			}
			
//			Log.d("SMS", sb.toString());
			
			messages.add(new SMS(
					message.get("body"),
					message.get("address"),
					message.get("date"),
					message.get("type")
					));

		} while(cursor.moveToNext());

		cursor.close();
		
		return messages;
	}
	
	public ArrayList<SMS> fetch() {
		return this.fetch(DEFAULT_PROVIDER);
	}
}
