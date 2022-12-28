package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SMSSender{
	 public static final String ACCOUNT_SID = "AC030b77e8f77604cc0e39bfe400b006a3";
	 public static final String AUTH_TOKEN = "248e023f4c5524d16d804bdbe0fbfdd1";
	
	 public static void sendOTP(String contact, String msg) {
	     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 System.out.println(msg);
	     Message message = Message.creator(
			                   new com.twilio.type.PhoneNumber("+91"+contact),
			                   new com.twilio.type.PhoneNumber("+17278886304"),
			                   msg)
			         	   .create();
	 }
}