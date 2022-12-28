package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Restaurant;

public class RestaurantRejectOrderServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		if(restaurant==null) {
			request.getRequestDispatcher("restaurant_login.do").forward(request, response);
		}
		
		int orderId = Integer.parseInt(request.getParameter("order_id"));
		restaurant.rejectOrder(orderId);
	}
}
