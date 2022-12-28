package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Deliverer;
import models.Order;
import models.OrderedFood;

public class DelivererAllUndeliveredOrdersServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Deliverer deliverer = (Deliverer)session.getAttribute("deliverer");
		int delivererId = deliverer.getDelivererId();
		
		ArrayList<OrderedFood>allUndeliveredOrders = deliverer.getAllUndeliveredOrders();
		request.setAttribute("allUndeliveredOrders", allUndeliveredOrders);
		
		request.getRequestDispatcher("deliverer_all_undelivered_orders.jsp").forward(request, response);
	}
}
