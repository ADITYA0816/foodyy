package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Deliverer;
import models.OrderedFood;

public class DelivererAllDeliveredOrdersServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		Deliverer deliverer = (Deliverer)session.getAttribute("deliverer");
		if(deliverer==null) {
			request.getRequestDispatcher("deliverer_login.jsp").forward(request, response);
		}
		
		ArrayList<OrderedFood> allDeliveredOrders = deliverer.getAllDeliveredOrders();
		request.setAttribute("allDeliveredOrders", allDeliveredOrders);
		
		request.getRequestDispatcher("deliverer_all_delivered_orders.jsp").forward(request, response);
	}
}
