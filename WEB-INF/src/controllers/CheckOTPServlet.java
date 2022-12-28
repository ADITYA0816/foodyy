package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Restaurant;
import models.User;

public class CheckOTPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		String resp = "signin";
		
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		User user = (User)session.getAttribute("user");
		
		if(restaurant != null) {
			String otpEntered = request.getParameter("otp");
			String otpSaved = session.getAttribute("otp").toString();
			
			if(otpSaved.equals(otpEntered)) {
				resp = "true";
			}else {
				resp = "false";
			}
		}
		
		if(user != null) {
			String otpEntered = request.getParameter("otp");
			String otpSaved = session.getAttribute("otp").toString();
			
			if(otpSaved.equals(otpEntered)) {
				resp = "true";
			}else {
				resp = "false";
			}
		}
		
		response.getWriter().write(resp);
	}
}