<!DOCTYPE html>
<%@page import="models.OrderStatus"%>
<%@page import="models.OrderedFood"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="models.Food" %>
<%@ page import="models.City, java.util.ArrayList" %>

<html lang="en">
	<head>
		<%@ include file="head.jsp" %>

		<title>Restaurant : Recent Orders</title>
		
		<link>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
		<style>
			.green{
				color: rgb(69, 185, 69);
			}
		</style>
			
	</head>

	<body style="background-color: hsla(20, 50%, 98%, 0.733); margin-bottom: 70px">
		<div class="header">
			<div class="container-fluid jumbotron banner" style="height: 70px;">
				<div class="container">

					<%@ include file="header.jsp" %>

				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">

				<%@ include file="restaurant_nav.jsp" %>

				<div class="col-11 ml-0" id="body_menu">
					<h4 class="text-primary" style="margin-left: 17px">All Orders</h4>
					
					<% 
						ArrayList<OrderedFood> allOrders = (ArrayList<OrderedFood>)request.getAttribute("allOrders"); 
						ArrayList<OrderedFood> allOnWayOrders = (ArrayList<OrderedFood>)request.getAttribute("allOnWayOrders"); 
						OrderedFood orderedFood = new OrderedFood();
					%>

					<span class="d-inline-block ml-3 mt-1" style="font-size: 85%; font-weight: 500;">Recently Accepted Orders :</span>
					<div class="card-group px-2 mb-2">
	      				<% for(OrderedFood order : allOrders){ %>
							<% if(order.getOrderStatus().getOrderStatusId()==6){ %>
							
			          				<div id="id_card_<%= order.getOrder().getOrderId() %>" class="col-4">
			          				<div class="card p-1 my-2">
									<div class="row my-1">
										<div class="col-6 ml-1">
											<div style="font-size: 80%; font-weight: bold;">Order Id : #<%= order.getOrder().getOrderId() %></div>
										</div>
									</div>
		      						<img alt="" class="card-img-top p-1" height="220px" src="show_food_pic.do?food_pic_path=<%= order.getFood().getPicPath() %>">
		      						<div class="card-body ml-2">
		      							<h5 class="card-title mb-1" style="font-family: sans-serif;">${orderedFood.food.name}</h5>
										<div class="row">
											<div class="col-7">
												<span style="font-size: 85%">No. of units : <%= order.getUnits() %></span>      							
											</div>
											<div class="col-5">
												<span style="font-size: 85%">Total : Rs. <%= order.getOrder().getOrderTotal() %></span>
											</div>
										</div>
		      						</div>
	      							<div class="py-2 px-4">
	      								<button id="id_<%= order.getOrder().getOrderId() %>" class="btn btn-sm btn-success proocessing_btn float-right" onClick="processing(this.id)">Process Order</button>
	      							</div>
	      						</div>	
	      						</div>
	      				
	      					<% } %>
	      				<% } %>
	      			</div>
	      			
	      			<span class="d-inline-block ml-3 mt-1" style="font-size: 85%; font-weight: 500;">On-Process Orders :</span>
	      			<div class="card-group px-2 mb-2">
	      				<% for(OrderedFood order : allOrders){ %>
							<% if(order.getOrderStatus().getOrderStatusId()==8){ %>
							
			          				<div class="col-4">
			          				<div id="id_card_<%= order.getOrder().getOrderId() %>" class="card p-1 my-2">
									<div class="row my-1">
										<div class="col-6 ml-1">
											<div style="font-size: 80%; font-weight: bold;">Order Id : #<%= order.getOrder().getOrderId() %></div>
										</div>
									</div>
		      						<img alt="" class="card-img-top p-1" height="220px" src="show_food_pic.do?food_pic_path=<%= order.getFood().getPicPath() %>">
		      						<div class="card-body ml-2">
		      							<h5 class="card-title mb-1" style="font-family: sans-serif;">${orderedFood.food.name}</h5>
										<div class="row">
											<div class="col-7">
												<span style="font-size: 85%">No. of units : <%= order.getUnits() %></span>      							
											</div>
											<div class="col-5">
												<span style="font-size: 85%">Total : Rs. <%= order.getOrder().getOrderTotal() %></span>
											</div>
										</div>
		      						</div>
	      							<div class="py-2 px-4">
	      								<button id="id_<%= order.getOrder().getOrderId() %>" class="btn btn-sm btn-success proocessing_btn float-right" onClick="processed(this.id)">Processed Order</button>
	      							</div>
	      						</div>	
	      						</div>
	      				
	      					<% } %>
	      
	      				<% } %>
	      			</div>


					<span class="d-inline-block ml-3 mt-1" style="font-size: 85%; font-weight: 500;">Processed Orders :</span>
	      			<div class="card-group px-2 mb-2">
	      				<% for(OrderedFood order : allOrders){ %>
							<% if(order.getOrderStatus().getOrderStatusId()==12){ %>
							
			          				<div id="id_card_<%= order.getOrder().getOrderId() %>" class="col-4">
			          				<div class="card p-1 my-2">
									<div class="row my-1">
										<div class="col-6 ml-1">
											<div style="font-size: 80%; font-weight: bold;">Order Id : #<%= order.getOrder().getOrderId() %></div>
										</div>
									</div>
		      						<img alt="" class="card-img-top p-1" height="220px" src="show_food_pic.do?food_pic_path=<%= order.getFood().getPicPath() %>">
		      						<div class="card-body ml-2">
		      							<h5 class="card-title mb-1" style="font-family: sans-serif;">${orderedFood.food.name}</h5>
										<div class="row">
											<div class="col-7">
												<span style="font-size: 85%">No. of units : <%= order.getUnits() %></span>      							
											</div>
											<div class="col-5">
												<span style="font-size: 85%">Total : Rs. <%= order.getOrder().getOrderTotal() %></span>
											</div>
										</div>
		      						</div>
		      						<div class="py-2 px-4">
	      								<button id="id_<%= order.getOrder().getOrderId() %>" class="btn btn-sm btn-success float-right" onClick="deliver(this.id)">Deliver Order</button>
	      							</div>
	      						</div>	
	      						</div>
	      				
	      					<% } %>
	      
	      				<% } %>
	      			</div>	      			


					<span class="d-inline-block ml-3 mt-1" style="font-size: 85%; font-weight: 500;">Orders Out For Delivery :</span>
	      			<div class="card-group px-2 mb-2">
	      				<% for(OrderedFood orderOnWay : allOnWayOrders){ %>
							<% if(orderOnWay.getOrderStatus().getOrderStatusId()==9){ %>
							
			          				<div class="col-4">
			          				<div id="id_card_<%= orderOnWay.getOrder().getOrderId() %>" class="card p-1 my-2">
									<div class="row my-1">
										<div class="col-6 ml-1">
											<div style="font-size: 80%; font-weight: bold;">Order Id : #<%= orderOnWay.getOrder().getOrderId() %></div>
										</div>
									</div>
		      						<img alt="" class="card-img-top p-1" height="220px" src="show_food_pic.do?food_pic_path=<%= orderOnWay.getFood().getPicPath() %>">
		      						<div class="card-body ml-2">
		      							<h5 class="card-title mb-1" style="font-family: sans-serif;">${orderedFood.food.name}</h5>
										<div class="row">
											<div class="col-7">
												<span style="font-size: 85%">No. of units : <%= orderOnWay.getUnits() %></span>      							
											</div>
											<div class="col-5">
												<span style="font-size: 85%">Total : Rs. <%= orderOnWay.getOrder().getOrderTotal() %></span>
											</div>
										</div>
		      						</div>
		      						<div style="font-size: 85%">
		      							Deliverer : 
		      						</div>
		      						<div style="font-size: 85%">Name : <%= orderOnWay.getOrder().getDeliverer().getName() %></div>
		      						<div style="font-size: 85%">Contact : <%= orderOnWay.getOrder().getDeliverer().getContact() %></div>
	      						</div>	
	      						</div>
	      				
	      					<% } %>
	      
	      				<% } %>
	      			</div>

					
					<span class="d-inline-block ml-3 mt-1" style="font-size: 85%; font-weight: 500;">Delivered Orders :</span>
	      			<div class="card-group px-2 mb-2">
	      				<% for(OrderedFood order : allOrders){ %>
							<% if(order.getOrderStatus().getOrderStatusId()==11){ %>
							
			          				<div class="col-4">
			          				<div id="id_card_<%= order.getOrder().getOrderId() %>" class="card p-1 my-2">
									<div class="row my-1">
										<div class="col-6 ml-1">
											<div style="font-size: 80%; font-weight: bold;">Order Id : #<%= order.getOrder().getOrderId() %></div>
										</div>
									</div>
		      						<img alt="" class="card-img-top p-1" height="220px" src="show_food_pic.do?food_pic_path=<%= order.getFood().getPicPath() %>">
		      						<div class="card-body ml-2">
		      							<h5 class="card-title mb-1" style="font-family: sans-serif;">${orderedFood.food.name}</h5>
										<div class="row">
											<div class="col-7">
												<span style="font-size: 85%">No. of units : <%= order.getUnits() %></span>      							
											</div>
											<div class="col-5">
												<span style="font-size: 85%">Total : Rs. <%= order.getOrder().getOrderTotal() %></span>
											</div>
										</div>
		      						</div>
	      						</div>	
	      						</div>
	      				
	      					<% } %>
	      
	      				<% } %>
	      			</div>
				</div>
			</div>
		</div>

		<%@ include file="footer.jsp" %>
		<script src="static/js/restaurant_all_orders.js"></script>

		<script>
			let sideMenu = document.querySelector('#side_menu');
			let bodyMenu = document.querySelector('#body_menu');
			let navBtn = document.querySelector('#nav_btn');
			let varStatus = document.querySelector('varStatus');

			navBtn.addEventListener('click', (event) => {
				if (sideMenu.classList.contains('col-1')) {
					sideMenu.classList.replace('col-1', 'col-2');
					bodyMenu.classList.replace('col-11', 'col-10');
				} else {
					sideMenu.classList.replace('col-2', 'col-1');
					bodyMenu.classList.replace('col-10', 'col-11');
				}
			});
			
		</script>
	</body>
</html>