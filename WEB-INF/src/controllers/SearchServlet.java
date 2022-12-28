package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.*;

@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		String searchKey = request.getParameter("search_key");
		
		ArrayList<Food> foods = Food.collectAllFoods(searchKey, user.getCity()); 
		request.setAttribute("all_foods", foods);
		request.setAttribute("searchKey", searchKey);
		
		request.getRequestDispatcher("search_result.jsp").forward(request, response);
	}
}
