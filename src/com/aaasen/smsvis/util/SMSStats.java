package com.aaasen.smsvis.util;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SMSStats implements Serializable {
	private static final long serialVersionUID = 5476524672958650942L;
	private ArrayList<SMS> messages;
	private ArrayList<Person> people;

	public SMSStats(ArrayList<SMS> messages) {
		this.setMessages(messages);
		this.people = this.extractPeople(messages);
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
	public void setMessages(ArrayList<SMS> messages) { 
		this.messages = messages;
		this.people = this.extractPeople(messages);
	}
	
	public ArrayList<Person> getPeople() { return people; }
	
	private ArrayList<Person> extractPeople(ArrayList<SMS> messages) {
		Multimap<String, SMS> map = ArrayListMultimap.create();

		for (SMS message : messages) {
			map.put(message.getAddress(), message);
		}
		
		ArrayList<Person> people = new ArrayList<Person>();

		for (String address : map.keySet()) {
			people.add(new Person(address, new ArrayList<SMS>(map.get(address))));
		}
		
		return people;
	}
}
