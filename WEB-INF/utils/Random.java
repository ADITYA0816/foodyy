package utils;

public class Random {
	public static int generateOTP() {
		java.util.Random r = new java.util.Random();
		
		int min = 1001;
		int max = 9999;
		
		int otp = r.nextInt(max-min) + min;
		
		return otp;
	}
}