package com.aaasen.smsvis.util;

import java.util.ArrayList;

public class SMSStats {
	private ArrayList<SMS> messages;
	
	public SMSStats(ArrayList<SMS> messages) {
		this.setMessages(messages);
		
//		Multimap<String, SMS> msg = ArrayListMultimap.create();
	}

	public ArrayList<SMS> getMessages() { return messages; }
	public void setMessages(ArrayList<SMS> messages) { this.messages = messages; }
}
