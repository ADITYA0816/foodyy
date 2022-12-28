<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="models.Food" %>
<%@ page import="models.City, java.util.ArrayList" %>
<%@page import="models.FoodType" %>

<html lang="en">
	<head>
		<%@ include file="head.jsp" %>

		<title>User : Cart</title>
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
					<h6 class="ml-3">Your cart</h6>
					<div id="order_card" class="card-group">
						<div class="col-4 mt-2">
							<div class="card p-1" style="width: 290px">
								<img alt="" class="card-img" height="220px" width="280px"
									src="show_food_pic.do?food_pic_path=${cartFood.picPath}"
									alt="Card image cap">
								<div class="card-body" style="padding:.8rem">
									<h4 class="card-title">${cartFood.name}</h5>
										<h6 class="card-subtitle mb-1 text-muted">
											${cartFood.restaurant.name}</h6>
										<span
											class="font-weight-lighter m-0 p-0">${FoodType.getFoodTypes(cartFood.foodType.foodTypeId)}</span>
										<button id="order_food"
											class="btn btn-outline-success btn-sm mt-2 float-right">Pay
											${cartFood.pricePerUnit}</button>
								</div>
							</div>
						</div>
					</div>
					
					<div id="success_box" class="container d-none">
						<div class="row justify-content-center">
							<div id="success_box" class="col-5 p-0 mt-5 text-center">
								<div id="success">
									<h1 class="mb-0"><i class="fa fa-check green" aria-hidden="true"></i></h1>
									<h3 class="green pt-0 mt-0">Success</h3>
								</div>
								<div>
									<h4 class="lead mt-3 green">Food ordered successfully</h4>
								</div>
							</div>
						</div>
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