package com.aaasen.smsvis.util;

import android.telephony.PhoneNumberUtils;

public class PhoneUtils {
	public static String formatNumber(String address) {
		address = PhoneNumberUtils.stripSeparators(address); 
		address = PhoneNumberUtils.formatNumber(address);
		
		if (address.startsWith("+1-")) {
			address = address.substring(3);
		}
		
		if (address.startsWith("1-")) {
			address = address.substring(2);
		}
		
		return address;
	}
}
