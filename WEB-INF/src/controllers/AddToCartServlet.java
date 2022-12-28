package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Food;
import models.Order;
import models.OrderStatus;
import models.Restaurant;
import models.User;

public class AddToCartServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		int foodId = Integer.parseInt(request.getParameter("food_id"));
		String foodName = request.getParameter("food_name");
		String pricePerUnit = request.getParameter("price_per_unit");
		String picPath = request.getParameter("food_pic");
		
		int restaurantId = Integer.parseInt(request.getParameter("restaurant_id"));
		String restaurantName = request.getParameter("restaurant_name");
		Restaurant restaurant = new Restaurant(restaurantId,restaurantName);
		
		Food food = new Food(foodId,foodName,restaurant,pricePerUnit,picPath);
		
		session.setAttribute("cartFood", food);
		
		request.getRequestDispatcher("user_cart.jsp").forward(request, response);
	}
}
