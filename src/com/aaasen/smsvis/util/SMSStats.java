package com.aaasen.smsvis.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class SMSStats implements Serializable {
	private static final long serialVersionUID = 5476524672958650942L;
	private ArrayList<SMS> messages;
	private ArrayList<Person> people;

	public SMSStats(Context context, ArrayList<SMS> messages) {
		this.messages = messages;
		this.people = this.extractPeople(context, messages);
	}

	public int getNumSent() {
		//TODO: implement getNumSent()
		return 0;
	}

	public int getNumReceived() {
		//TODO: implement getNumReceived()
		return 0;
	}

	public int getNumTotal() {
		//TODO: implement getNumTotal()
		return 0;
	}

	public ArrayList<SMS> getMessages() { return messages; }
	
	public ArrayList<Person> getPeople() { return people; }
	
	private ArrayList<Person> extractPeople(Context context, ArrayList<SMS> messages) {
		Multimap<String, SMS> map = ArrayListMultimap.create();

		for (SMS message : messages) {
			map.put(message.getAddress(), message);
		}
		
		ArrayList<Person> people = new ArrayList<Person>();

		for (String address : map.keySet()) {
			people.add(new Person(context, address, new ArrayList<SMS>(map.get(address))));
		}
		
		Collections.sort(people);
		return people;
	}
}
