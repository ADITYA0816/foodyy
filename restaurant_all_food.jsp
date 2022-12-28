<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="models.Food"%>
<%@page import="models.FoodType"%>

<html lang="en">

<head>
    <%@ include file="head.jsp" %>

    <title>Restaurant : AllFood</title>
    
	<link rel="stylesheet" href="static/css/common.css"><link>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body style="background-color: hsla(20, 50%, 98%, 0.733); margin-bottom: 200px;">

    <div class="header">
        <div class="container-fluid jumbotron banner" style="height: 70px;">
            <div class="container">

                <%@ include file="header.jsp" %>

            </div>
        </div>
    </div>

    <div id="page-container" class="container mt-2">
        <div class="row">
			<%@ include file="restaurant_nav.jsp" %>

            
            

                
					
				<div class="col-11 ml-0" id="body_menu">
				

	                <h4 class="text-primary" style="margin-left: 17px">All Dishes</h4>
	               
					<div class="card-group">
	      				<c:forEach var="food" items="${foods}">
	      					<div class="col-4 mt-2">
	      						<div class="card" style="text-align: center">
		      						<img alt="" class="card-img-top" height="220px" src="show_food_pic.do?food_pic_path=${food.picPath}">
		      						<div class="card-body" style="padding:.8rem">
		      							<h5 class="card-title mb-1" style="font-family: sans-serif;">${food.name}</h5>
		      							<span class="font-weight-lighter">${FoodType.getFoodTypes(food.foodType.foodTypeId)}</span>        							
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