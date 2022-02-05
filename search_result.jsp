<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="models.Food"%>
<%@ page import="models.City, java.util.ArrayList" %>
<%@page import="models.FoodType"%>

<html lang="en">

<head>
    <%@ include file="head.jsp" %>

    <title>User : Search</title>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
            	<h6 class="ml-3">Search result for "${searchKey}"</h6>
            	<div class="card-group">
      				<c:forEach var="food" items="${all_foods}">
      					<div class="col-4 mt-2">
      						<div class="card p-1" style="width: 290px">
	      						<img alt="" class="card-img-top" height="220px" src="show_food_pic.do?food_pic_path=${food.picPath}" alt="Card image cap">
	      						<div class="card-body" style="padding:.8rem">
	      							<h4 class="card-title">${food.name}</h5>
					            	<h6 class="card-subtitle mb-1 text-muted">${food.restaurant.name}</h6>
					            	<span class="font-weight-lighter m-0 p-0">${FoodType.getFoodTypes(food.foodType.foodTypeId)}</span>
	      							<a href="add_to_cart.do?food_id=${food.foodId}&food_name=${food.name}&food_pic=${food.picPath}&price_per_unit=${food.pricePerUnit}&restaurant_id=${food.restaurant.restaurantId}&restaurant_name=${food.restaurant.name}" class="btn btn-outline-success btn-sm mt-2 float-right">Add to Cart</a>       							
	      						</div>
      						</div>
      					</div>
      				</c:forEach>
      			</div> 
		    </div>
        </div>
    </div>
    
    <%@ include file="footer.jsp" %>
    
    <script>
    	let sideMenu = document.querySelector('#side_menu');
		let bodyMenu = document.querySelector('#body_menu');
		let navBtn = document.querySelector('#nav_btn');
	
		navBtn.addEventListener('click', (event) => {	
			if(sideMenu.classList.contains('col-1')){
				sideMenu.classList.replace('col-1', 'col-2');
				bodyMenu.classList.replace('col-11', 'col-10');
			}else{
				sideMenu.classList.replace('col-2', 'col-1');
				bodyMenu.classList.replace('col-10', 'col-11');
			}
		});
    </script>
</body>

</html>