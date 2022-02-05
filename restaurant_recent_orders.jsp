<!DOCTYPE html>
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
				
					<h4 class="text-primary" style="margin-left: 17px">Recent Orders</h4>
					
					<div class="card-group">
	      				<c:forEach var="recentOrderedFood" items="${recentOrders}">
	      					<div id="id_card_${recentOrderedFood.order.orderId}" class="col-4 mt-2">
	      						<div class="card p-1">
									<div class="row my-1">
										<div class="col-6 ml-1">
											<div style="font-size: 80%; font-weight: bold;">Order Id : #${recentOrderedFood.order.orderId}</div>
										</div>
									</div>
		      						<img alt="" class="card-img-top p-1" height="220px" src="show_food_pic.do?food_pic_path=${recentOrderedFood.food.picPath}">
		      						<div class="card-body ml-2">
		      							<h5 class="card-title mb-1" style="font-family: sans-serif;">${recentOrderedFood.food.name}</h5>
										<div class="row">
											<div class="col-7">
												<span style="font-size: 85%">No. of units : ${recentOrderedFood.units}</span>      							
											</div>
											<div class="col-5">
												<span style="font-size: 85%">Total : Rs. ${recentOrderedFood.order.orderTotal}</span>
											</div>
										</div>
		      						</div>
	      							<div class="py-2 px-4">
	      								<button id="id_${recentOrderedFood.order.orderId}" class="btn btn-sm btn-success accept_btn" onClick="accept(this.id)">Accept Order</button>
	      								<div id="id_${recentOrderedFood.order.orderId}" class="btn btn-sm btn-danger float-right" onClick="reject(this.id)">Reject Order</div>
	      							</div>
	      						</div>
	      					</div>
	      				</c:forEach>
	      			</div>
				</div>
			</div>
		</div>

		<%@ include file="footer.jsp" %>
		<script src="static/js/restaurant_recent_orders.js"></script>

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