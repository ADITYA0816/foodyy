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

public class RestaurantAllOrdersServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		if(restaurant==null) {
			request.getRequestDispatcher("restaurant_login.do").forward(request, response);
		}
		
		ArrayList<OrderedFood> allOrders  = restaurant.getAllOrders();
		request.setAttribute("allOrders", allOrders);
		
		ArrayList<OrderedFood> allOnWayOrders  = restaurant.getAllOnWayOrders();
		request.setAttribute("allOnWayOrders", allOnWayOrders);
		
		request.getRequestDispatcher("restaurant_all_orders.jsp").forward(request, response);
	}
}
