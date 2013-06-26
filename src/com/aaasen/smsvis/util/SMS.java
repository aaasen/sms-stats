package com.aaasen.smsvis.util;

import java.util.Date;

public class SMS {
	private String body, address;
	private Date date;
	
	public SMS(String body, String address, Date date) {
		this.setBody(body);
		this.setAddress(address);
		this.setDate(date);
	}
	
	public SMS(String body, String address, String date) {
		this(body, address, new Date(Long.parseLong(date)));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("time: " + date.toString());
		sb.append(" from: " + address);
		sb.append(" body: \"" + body + "\"");
		return sb.toString();
	}

	public String getBody() { return body; }
	public void setBody(String body) { this.body = body; }

	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }

	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }
}
