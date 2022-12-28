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
import models.Restaurant;
import models.User;

public class OrderFoodServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();
		
		Food food = (Food)session.getAttribute("cartFood");
		int foodId = food.getFoodId();
		String foodName = food.getName();
		int pricePerUnit = Integer.parseInt(food.getPricePerUnit());
		String foodPicPath = food.getPicPath();
		
		int restaurantId = food.getRestaurant().getRestaurantId();
		String restaurantName = food.getRestaurant().getName();
		Restaurant restaurant = new Restaurant(restaurantId,restaurantName);		
		
		Order order = new Order(restaurant,user,pricePerUnit); 
		order.addOrder();
		order.orderFood(foodId);		
	}	
}

