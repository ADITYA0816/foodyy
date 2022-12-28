package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.City;
import models.User;

@SuppressWarnings("serial")
public class UserProfileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String nextPage = "user_profile.jsp";
		
		User user = (User)session.getAttribute("user");
		
		if(user == null) {
			nextPage = "user_signin.jsp";
		}
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		String nextPage = "user_signin.jsp";
		
		User user = (User)session.getAttribute("user");
		
		if(user != null) {
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			int cityId = Integer.parseInt(request.getParameter("city"));
			City city = new City(cityId);
			String contact = request.getParameter("contact");
			
			if(user.updateProfile(name, address, city, contact)) {			
				nextPage = "user_profile.do";
			}
		}
		
		response.sendRedirect(nextPage);
	}
}