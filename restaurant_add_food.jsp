<!DOCTYPE html>
<%@page import="models.Food"%>
<%@page import="models.FoodType"%>
<html lang="en">

<head>
    <%@ include file="head.jsp" %>

    <title>Restaurant : AddFood</title>
    <link rel="stylesheet" href="static/css/form.css">
	<link rel="stylesheet" href="static/css/common.css"><link>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>

<body style="background-color: hsla(20, 50%, 98%, 0.733);">

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

            
            <div class="col-7 p-0 ml-5 justify-content-around">


				<h4 class="text-primary">Add a new Dish</h4>


                <form action="add_food.do" method="post" class=" mt-3" id="add_food_form" >
                	
                    <div class="form-group">
          				<label for="id_food_name" class="mb-0">Food Name</label>
          				<input type="text" name="food_name" id="id_food_name" class="form-control">
          			</div>
          				
       				<div class="form-group">
       					<label for="id_unit_price" class="mb-0">Enter Unit Price</label>
       					<input type="text" name="unit_price" id="id_unit_price" class="form-control">
       				</div>
       				
       				<div class="form-group">
       						<% 
                        		ArrayList<FoodType> foodTypes = FoodType.getAllFoodTypes(); 
                        	%>
							<label for="id_food_type" class="mb-0">Food Type</label>
							<select class="form-control" name="food_type_id" id="id_food_type">
								<option>Select Food Type</option>
								<% for(FoodType foodType : foodTypes){ %>
									
										<option value="<%= foodType.getFoodTypeId() %>">
											<%= foodType.getFoodTypeId()+ " ("+foodType.getFoodType()+")" %>
										</option>
								<%} %>
							</select>
						</div>
          			
          			<div class="form-group mt-3">
						<label for="id_ingridients" class="mb-0">Ingridients</label>
						<textarea rows="3" name="food_ingridients" id="id_ingridients"
							class="form-control" minlength="10"></textarea>
					</div>
          				
        			<input type="submit" id="new_food_button" value="Add Food" class="btn btn-primary mt-3">
                </form>
            </div>
            
            
            
        </div>
    </div>

    <%@ include file="footer.jsp" %>
    <script>
    	let sideMenu = document.querySelector('#side_menu');
		let navBtn = document.querySelector('#nav_btn');
	
		navBtn.addEventListener('click', (event) => {	
			if(sideMenu.classList.contains('col-1')){
				sideMenu.classList.replace('col-1', 'col-2');
			}else{
				sideMenu.classList.replace('col-2', 'col-1');
			}
		});
    </script>
</body>

</html>