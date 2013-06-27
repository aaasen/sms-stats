package com.aaasen.smsvis.util;

public class DataSingleton {
	private static DataSingleton instance = null;
	private static SMSStats stats = null;
	
	protected DataSingleton() {

	}
	
	public synchronized static DataSingleton getInstance() {
		if(instance == null) {
			instance = new DataSingleton();
		}
		
		return instance;
	}

	public static SMSStats getStats() { return stats; }
	public static void setStats(SMSStats stats) { DataSingleton.stats = stats; }
}