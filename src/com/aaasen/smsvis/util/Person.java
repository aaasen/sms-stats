package com.aaasen.smsvis.util;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.PhoneLookup;
import android.util.Log;

public class Person implements Serializable {
	private static final long serialVersionUID = 7890061410624884251L;
	private static final String DEFAULT_NAME = "Unknown";
	
	private String name, address;
	private ArrayList<SMS> messages;
	
	public Person(String name, String address, ArrayList<SMS> messages) {
		this.name = name;
		this.address = address;
		this.messages = messages;
	}
	
	public Person(Context context, String address, ArrayList<SMS> messages) {
		this(getName(context, address), address, messages);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" name: " + name);
		sb.append(" address: " + address);
		sb.append(" messages: " + messages.size());
		return sb.toString();
	}
	
	public static String getName(Context context, String address) {
		String[] projection = new String[] {
		        PhoneLookup.DISPLAY_NAME,
		        PhoneLookup._ID};
		
		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(address));
		
		Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
		String name = DEFAULT_NAME;
		
		if (cursor.moveToFirst()) {
			name = cursor.getString(cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME));
		} else {
			Log.d("Person.getName", "no contact found for " + address);
		}
		
		cursor.close();
		return name;
	}

	public String getName() { return name; }
	
	public String getAddress() { return address; }

	public ArrayList<SMS> getMessages() { return messages; }
}
