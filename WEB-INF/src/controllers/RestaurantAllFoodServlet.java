package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Food;
import models.Restaurant;

public class RestaurantAllFoodServlet extends HttpServlet{
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		String nextPage = "restaurant_signin.do";
		
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		if(restaurant != null) {
			ArrayList<Food> foods = Food.getAllFoods(restaurant);
			request.setAttribute("foods", foods);
			
			nextPage = "restaurant_all_food.jsp";
		}	
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
}
