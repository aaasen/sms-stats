package com.aaasen.smsvis.util;

import java.io.Serializable;
import java.util.ArrayList;

public class SMSStats implements Serializable {
	private static final long serialVersionUID = 5476524672958650942L;
	private ArrayList<SMS> messages;
	
	public SMSStats(ArrayList<SMS> messages) {
		this.setMessages(messages);
		
//		Multimap<String, SMS> msg = ArrayListMultimap.create();
	}

	public int getNumSent() {
		return 0;
	}
	
	public int getNumReceived() {
		return 0;
	}
	
	public int getNumTotal() {
		return 0;
	}
	
	public ArrayList<SMS> getMessages() { return messages; }
	public void setMessages(ArrayList<SMS> messages) { this.messages = messages; }
}
