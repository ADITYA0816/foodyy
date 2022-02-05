<!DOCTYPE html>
<%@page import="models.OrderedFood"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="models.Food" %>
<%@ page import="models.City, java.util.ArrayList" %>

<html lang="en">
	<head>
		<%@ include file="head.jsp" %>

		<title>Deliverer : All Delivered Orders</title>
		<link rel="stylesheet" href="static/css/restaurant_dashboard.css">
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

				<%@ include file="deliverer_nav.jsp" %>

				<div class="col-11 ml-0" id="body_menu">
					<h4 class="text-primary" style="margin-left: 17px">Delivered Orders :</h4>
					
					<% 
						ArrayList<OrderedFood> allDeliveredOrders = (ArrayList<OrderedFood>)request.getAttribute("allDeliveredOrders"); 
						OrderedFood orderedFood = new OrderedFood();
					%>
					
					<div class="card-group">
						
						<% for(OrderedFood order : allDeliveredOrders){ %>
							
			          		<div id="card_<%= order.getOrder().getOrderId() %>" class="col-4 mt-2">
	      						<div class="card p-1">
									<div class="row my-1">
										<div class="col-6 ml-1">
											<div style="font-size: 80%; font-weight: bold;">Order Id : #<%= order.getOrder().getOrderId() %></div>
										</div>
									</div>
		      						<img alt="" class="card-img-top p-1" height="220px" src="show_food_pic.do?food_pic_path=<%= order.getFood().getPicPath() %>">
		      						<div class="card-body ml-2">
		      							<h6 class="mb-0">Picked Up : </h6>
		      							<span class="d-block small" style="font-size: 90% ;">Food Name : <%= order.getFood().getName() %></span>
		      							<span class="d-block small" style="font-size: 90%">No. of units : <%= order.getUnits() %></span>      							
		      							<span class="d-block small" style="font-size: 90% ;">Restaurant Name : <%= order.getOrder().getRestaurant().getName() %></span>
		      							<span class="d-block small" style="font-size: 90% ;">Restaurant Address : <%= order.getOrder().getRestaurant().getAddress() %></span>
		      							<span class="d-block small" style="font-size: 90% ;">Contact : <%= order.getOrder().getRestaurant().getContact() %></span>
		      							<h6 class="mb-0 mt-2">Delivered To : </h6>
		      							<span class="d-block small" style="font-size: 90% ;">Name : <%= order.getOrder().getUser().getName() %></span>
		      							<span class="d-block small" style="font-size: 90% ;">Contact : <%= order.getOrder().getUser().getContact() %></span>
		      							<span class="d-block small" style="font-size: 90% ;">Address : <%= order.getOrder().getUser().getAddress() %></span>
		      							<span class="d-block small" style="font-size: 90% ;">Order Total : Rs.<%= order.getOrder().getOrderTotal() %></span>
		      						</div>
	      						</div>
	      					</div>      					
	      				<% } %>      				
	      			</div>
				</div>
			</div>
		</div>

		<%@ include file="footer.jsp" %>
		
		<script src="static/js/deliverer_all.js"></script>

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
			
			console.log(document.querySelector('varStatus').innerHTML);
			
		</script>
	</body>
</html>