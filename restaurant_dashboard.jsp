<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="head.jsp" %>

    <title>Restaurant : Dashboard</title>
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

    <div class="container">
        <div class="row">
			<%@ include file="restaurant_nav.jsp" %>
			
           <div class="col-11 mt-5" id="body_menu">
                <div class="row justify-content-around">

                    <div class="col-2 btn btn-secondary text-left">
                        <a href="all_food.do" class="">
                            <i class="fa fa-cutlery ml-0 pl-0" style="color: rgb(221, 170, 31); font-size: 33px;"></i>
                            <div class="float-right my-1">
                                <span style="font-weight: bold; color:rgba(255, 255, 255, 0.774);">
                                    <a href="all_food.do" style="font-weight: bold;" class="noLink">Foods</a>
                                </span>
                            </div>
                        </a>
                    </div>

                    <div class="col-2 btn btn-secondary text-left">
                        <a href="restaurant_recent_orders.do" class="">
                            <i class="fa fa-shopping-cart ml-0 pl-0"
                                style="color: rgb(221, 170, 31); font-size: 33px;"></i>
                            <div class="float-right my-1">
                                <span style="font-weight: bold; color:rgba(255, 255, 255, 0.774);">
                                    <a href="restaurant_recent_orders.do" class="noLink">Orders</a>
                                </span>
                            </div>
                        </a>
                    </div>

                    <div class="col-2 btn btn-secondary text-left">
                        <div class="">
                            <i class="fa fa-comments ml-0 pl-0" style="color: rgb(221, 170, 31); font-size: 33px;"></i>
                            <div class="float-right my-1">
                                <span style="font-weight: bold; color:rgba(255, 255, 255, 0.774);">
                                    <a href="" class="noLink">Feedbacks</a>
                                </span>
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