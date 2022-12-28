package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Restaurant;

@SuppressWarnings("serial")
public class RestaurantAccountActivationServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String nextPage = "index.jsp";
		
		String email = request.getParameter("email");
		String activationCode = request.getParameter("activation_code");
		
		//if activate account returns true ie if account is activated using email link
		if(Restaurant.activateAccount(email, activationCode)) {
			nextPage = "restaurant_signin.do";
		}
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
}