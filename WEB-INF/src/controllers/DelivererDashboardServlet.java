package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Deliverer;

public class DelivererDashboardServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String nextPage = "deliverer_dashboard.jsp";
		HttpSession session = request.getSession();
		Deliverer deliverer = (Deliverer)session.getAttribute("deliverer");
		
		if(deliverer==null)
			nextPage = "deliverer_signin.jsp";
		
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
}
