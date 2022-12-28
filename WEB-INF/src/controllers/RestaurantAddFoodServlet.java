package controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Food;
import models.FoodType;
import models.Restaurant;

public class RestaurantAddFoodServlet extends HttpServlet{
	public void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		String nextPage = "restaurant_signin.do";
		
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		if(restaurant != null)
			nextPage = "restaurant_add_food.jsp";
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
	
	public void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		
		String name = request.getParameter("food_name");
		String pricePerUnit = request.getParameter("unit_price");
		String ingridients = request.getParameter("food_ingridients");
		int food_type_id = Integer.parseInt(request.getParameter("food_type_id"));
		System.out.println(food_type_id);
		
		FoodType foodType = new FoodType(food_type_id);
		Food food = new Food(name, pricePerUnit, foodType , ingridients);
		
		session.setAttribute("food", food);
		
		food.addFood(restaurant);
		
		String foodsFolderPath = getServletContext().getRealPath("/WEB-INF/uploads/restaurants/"+restaurant.getEmail()+"/foods");
		
		String foodName = food.getName();
		foodName = foodName.replace(" ", "_");
		
		File file = new File(foodsFolderPath, foodName);
		file.mkdir();
		
		request.getRequestDispatcher("restaurant_add_food_pic.jsp").forward(request, response);
	}
}
