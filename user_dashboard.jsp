<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="head.jsp" %>

    <title>User : Dashboard</title>
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

    <div class="container">
        <div class="row">

            <%@ include file="user_nav.jsp" %>
            
            <div class="col-11 ml-0" id="body_menu">
	            <div class="header">
			        <div class="container jumbotron banner">
			            <div class="container">			
			                <div class="row mt-3">
			                    <h1 class="col text-center slogan">Foody</h1>
			                </div>
			
			                <div class="row mt-1">
			                    <h3 class="col text-center slogan">Order, Delivery and Take-out</h3>
			                </div>
			
			                <div class="row mt-1">
			                    <h6 class="col text-center slogan">Find Best Restaurants and Food Near You</h6>
			                </div>
			
			                <div class="row mt-3 justify-content-center">
			                    <div class="col-4 text-center slogan">
			                        <form action="search.do" id="search_form" class="form-inline pl-3 pb-1">
								      <input name="search_key" class="form-control mr-sm-2" type="search" placeholder="I would like to eat.." aria-label="Search">					      
								      <button class="btn btn-sm btn-danger mr-2 mt-1" type="submit">Search</button>
							    	</form>
			                    </div>
			                </div>
			
			                <div class="row slogan justify-content-around mt-4">
			                    <div class="col-2">1. Choose Restaurant</div>
			                    <div class="col-2">2. Order Food</div>
			                    <div class="col-2">3. Get it Delivered</div>
			                </div>
			
			            </div>
			        </div>
			
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