package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Restaurant;

@SuppressWarnings("serial")
public class CheckRestaurantEmailServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String email = request.getParameter("email");
		
		boolean emailExists = Restaurant.checkEmail(email);
		
		response.getWriter().print(emailExists);		
	}
}
