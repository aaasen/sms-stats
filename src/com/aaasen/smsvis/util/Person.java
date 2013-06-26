package com.aaasen.smsvis.util;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
	private static final long serialVersionUID = 7890061410624884251L;
	private String name, address;
	private ArrayList<SMS> messages;
	
	public Person(String name, String address, ArrayList<SMS> messages) {
		this.name = name;
		this.address = address;
		this.messages = messages;
	}
	
	public Person(String address, ArrayList<SMS> messages) {
		this(getName(address), address, messages);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" name: " + name);
		sb.append(" address: " + address);
		sb.append(" messages: " + messages.size());
		return sb.toString();
	}
	
	public static String getName(String address) {
		return "[name]";
	}

	public String getName() { return name; }
	
	public String getAddress() { return address; }

	public ArrayList<SMS> getMessages() { return messages; }
}
