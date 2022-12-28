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
import models.OrderedFood;
import models.Restaurant;
import models.User;

public class UserOrdersServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		int userId = user.getUserId();

		String nextPage = "user_orders.jsp";
		
		if(user == null){
			nextPage = "user_signin.jsp";
		}
		
		ArrayList<OrderedFood> orderedFoods = user.getAllOrders();
		request.setAttribute("orderedFoods", orderedFoods);
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
}
