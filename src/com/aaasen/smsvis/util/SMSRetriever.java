package com.aaasen.smsvis.util;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class SMSRetriever {
	private Context context;

	public SMSRetriever(Context context) {
		this.context = context;
	}

	public ArrayList<SMS> fetch() {
		Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"),
				null, null, null, null);
		cursor.moveToFirst();

		ArrayList<SMS> messages = new ArrayList<SMS>();

		do {
			HashMap<String, String> message = new HashMap<String, String>();

			for (int i = 0; i < cursor.getColumnCount(); i++) {
				message.put(cursor.getColumnName(i), cursor.getString(i));
			}

			messages.add(new SMS(
					message.get("body"),
					message.get("address"),
					message.get("date")
					));

		} while(cursor.moveToNext());

		return messages;
	}
}
