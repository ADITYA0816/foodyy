<!DOCTYPE html>
<%@page import="models.OrderedFood"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="models.Food" %>
<%@ page import="models.City, java.util.ArrayList" %>

<html lang="en">
	<head>
		<%@ include file="head.jsp" %>

		<title>User : Orders</title>
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

				<%@ include file="user_nav.jsp" %>

				<div class="col-11 ml-0" id="body_menu">
					<h4 class="text-primary" style="margin-left: 17px">My Orders :</h4>
					
					<% 
						ArrayList<OrderedFood> allOrderedFoods = (ArrayList<OrderedFood>)request.getAttribute("orderedFoods"); 
						OrderedFood orderedFood = new OrderedFood();
					%>
					
					<div class="card-group">
						
						<% for(OrderedFood order : allOrderedFoods){ %>
							
			          				<div class="col-4 mt-2">
	      						<div class="card p-1">
									<div class="row my-1">
										<div class="col-6 ml-1">
											<div style="font-size: 80%; font-weight: bold;">Order Id : #<%= order.getOrder().getOrderId() %></div>
										</div>
										<div class="col-5 mr-0 pr-0">
											<% if(order.getOrderStatus().getOrderStatusId()==5){ %>
												<div id="" style="font-size: 80%; font-weight: bold; float: right;color: yellow;"><i class="fa fa-hourglass-end mr-1"></i><%= order.getOrderStatus().getStatus() %></div>
											<% } %>	
											<% if(order.getOrderStatus().getOrderStatusId()==6){ %>
												<div id="" style="font-size: 80%; font-weight: bold; float: right;color: rgb(183, 212, 140);"><%= order.getOrderStatus().getStatus() %></div>
											<% } %>	 
											<% if(order.getOrderStatus().getOrderStatusId()==7){ %>
												<div id="" style="font-size: 80%; font-weight: bold; float: right;color: red;"><i class="fa fa-ban mr-1"></i><%= order.getOrderStatus().getStatus() %></div>
											<% } %>	 
											<% if(order.getOrderStatus().getOrderStatusId()==8){ %>
												<div id="" style="font-size: 80%; font-weight: bold; float: right;color: rgb(149, 201, 76);"><i class="fa fa-spinner mr-1"></i><%= order.getOrderStatus().getStatus() %></div>
											<% } %>	
											<% if(order.getOrderStatus().getOrderStatusId()==12){ %>
												<div id="" style="font-size: 80%; font-weight: bold; float: right;color: green;"><i class="fa fa-envelope mr-1"></i><%= order.getOrderStatus().getStatus() %></div>
											<% } %>	
											<% if(order.getOrderStatus().getOrderStatusId()==9){ %>
												<div id="" style="font-size: 80%; font-weight: bold; float: right;color: rgb(90, 142, 209)"><i class="fa fa-motorcycle mr-1"></i><%= order.getOrderStatus().getStatus() %></div>
											<% } %>	  
											<% if(order.getOrderStatus().getOrderStatusId()==11){ %>
												<div id="" style="font-size: 80%; font-weight: bold; float: right;color: rgb(148, 109, 36)"><i class="fa fa-gift mr-1"></i><%= order.getOrderStatus().getStatus() %></div>
											<% } %>	  
										</div>
									</div>
		      						<img alt="" class="card-img-top p-1" height="220px" src="show_food_pic.do?food_pic_path=<%= order.getFood().getPicPath() %>">
		      						<div class="card-body ml-2">
		      							<h5 class="card-title mb-2" style="font-family: sans-serif;"><%= order.getFood().getName() %></h5>
		      							<h6 class="card-subtitle text-muted"><%= order.getOrder().getRestaurant().getName() %></h6>
		      							<span style="font-size: 80%">no. of units : <%= order.getUnits() %></span>      							
		      						</div>
	      						</div>
	      					</div>
	      				
	      					
	      
	      				<% } %>      				
	      			</div>
				</div>
			</div>
		</div>

		<%@ include file="footer.jsp" %>
		<script src="static/js/user_cart.js"></script>

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