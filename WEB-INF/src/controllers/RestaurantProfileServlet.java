package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.City;
import models.Restaurant;

@SuppressWarnings("serial")
public class RestaurantProfileServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String nextPage = "restaurant_profile.jsp";
		
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		
		if(restaurant == null) {
			nextPage = "restaurant_signin.jsp";
		}
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		String nextPage = "restaurant_signin.jsp";
		
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		
		if(restaurant != null) {
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			int cityId = Integer.parseInt(request.getParameter("city"));
			City city = new City(cityId);
			String contact = request.getParameter("contact");
			
			if(restaurant.updateProfile(name, address, city, contact)){			
				nextPage = "restaurant_profile.do";
		
			}
		}
		
		response.sendRedirect(nextPage);
	}
}