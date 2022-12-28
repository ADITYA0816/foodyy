package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Restaurant;

public class RestaurantDashboardServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		String nextPage = "restaurant_dashboard.jsp";
		
		if(restaurant == null) {
			nextPage = "restaurant_signin.jsp";
		}
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
}
