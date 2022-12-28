package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.OrderedFood;
import models.Restaurant;

public class RestaurantRecentOrdersServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		int restaurantId = restaurant.getRestaurantId();
		String nextPage = "restaurant_recent_orders.jsp";
		
		if(restaurant == null) {
			nextPage = "restaurant_signin.jsp";
		}
		
		ArrayList<OrderedFood> recentOrders = restaurant.getRecentOrders();
		
		request.setAttribute("recentOrders", recentOrders);
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
}
