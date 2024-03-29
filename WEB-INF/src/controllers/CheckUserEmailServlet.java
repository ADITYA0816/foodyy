package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;;

@SuppressWarnings("serial")
public class CheckUserEmailServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String email = request.getParameter("email");
		
		boolean emailExists = User.checkEmail(email);
		
		response.getWriter().print(emailExists);		
	}
}
