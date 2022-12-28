package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;

@SuppressWarnings("serial")
public class UserAccountActivationServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String nextPage = "index.jsp";
		
		String email = request.getParameter("email");
		String activationCode = request.getParameter("activation_code");
		
		//if activate account returns true i.e if account is activated using email link
		if(User.activateAccount(email, activationCode)) {
			nextPage = "user_signin.do";
		}
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
}