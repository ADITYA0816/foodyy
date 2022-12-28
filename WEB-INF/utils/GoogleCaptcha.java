package utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class GoogleCaptcha {
	public static boolean reCaptchaTest(HttpServletRequest request) throws IOException {
				
		String reCaptchaResponse = request.getParameter("g-recaptcha-response");
		
		String secretKey = "6LeoXUgcAAAAAPwh5vP6VnmkHqe9gXHXu-x3NeqV";
		
		String url = "https://www.google.com/recaptcha/api/siteverify?secret="+secretKey+"&response="+reCaptchaResponse;
		
		URL reCaptchaURL = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection)reCaptchaURL.openConnection();
		
		con.setRequestMethod("POST");
		con.setDoOutput(true);

		DataOutputStream outputStream = new DataOutputStream(con.getOutputStream());
		outputStream.flush();
		outputStream.close();
		
		int statusCode = con.getResponseCode();
		InputStream inputStream = null;
		
		if(statusCode == HttpURLConnection.HTTP_OK){
			inputStream = con.getInputStream();
		}else {
			inputStream = con.getErrorStream();
		}
		
		JsonReader jsonReader = Json.createReader(inputStream);
		JsonObject jsonObject = jsonReader.readObject();
		
		boolean reCaptchaTestResult = jsonObject.getBoolean("success");
		
		return reCaptchaTestResult;
	}
}