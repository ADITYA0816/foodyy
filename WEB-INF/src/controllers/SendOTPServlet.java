package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Restaurant;
import models.User;
import utils.Random;
import utils.SMSSender;

public class SendOTPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		User user = (User)session.getAttribute("user");
		
		if(restaurant!=null) {
			String contact = request.getParameter("contact");
			
			int otp = Random.generateOTP();
			//SMSSender.sendOTP(contact, "Welcome to Foody : Your contact confirmation code is : "+otp);
			System.out.println("Welcome to Foody : Your contact confirmation code is : "+otp);
			session.setAttribute("otp", otp);
		}
		
		if(user!=null) {
			String contact = request.getParameter("contact");
			
			int otp = Random.generateOTP();
			//SMSSender.sendOTP(contact, "Welcome to Foody : Your contact confirmation code is : "+otp);
			System.out.println("Welcome to Foody : Your contact confirmation code is : "+otp);
			session.setAttribute("otp", otp);
		}	
		
		response.getWriter().write("done");
	}
}