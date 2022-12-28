package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Deliverer;

public class DelivererLogoutServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Deliverer deliverer = (Deliverer)session.getAttribute("deliverer");
	
		if(deliverer==null) {
			request.getRequestDispatcher("deliverer_login.jsp").forward(request, response);
		}
		
		deliverer.inactiveStatus();
		
		session.invalidate();
		
		response.sendRedirect("deliverer_signin.do");
	}
}
