<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="head.jsp" %>

    <title>Deliverer : Dashboard</title>
    
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

            <%@ include file="deliverer_nav.jsp" %>
            
            <div class="col-11" id="body_menu">            
                <div class="row justify-content-around mt-5">

                    <div class="btn btn-secondary text-left">
                        <a href="deliverer_all_processed_orders.do" class="">
                            <i class="fa fa-gift ml-0 pl-0" style="color: rgb(221, 170, 31); font-size: 33px;"></i>
                            <div class="float-right ml-4 my-1">
                                <span style="font-weight: bold; color:rgba(255, 255, 255, 0.774);">
                                    <a href="deliverer_all_processed_orders.do" style="font-weight: bold;" class="noLink">Processed Orders</a>
                                </span>
                            </div>
                        </a>
                    </div>

                    <div class="btn btn-secondary text-left">
                        <a href="deliverer_all_undelivered_orders.do" class="">
                            <i class="fa fa-motorcycle ml-0 pl-0"
                                style="color: rgb(221, 170, 31); font-size: 33px;"></i>
                            <div class="float-right ml-4 my-1">
                                <span style="font-weight: bold; color:rgba(255, 255, 255, 0.774);">
                                    <a href="deliverer_all_undelivered_orders.do" style="font-weight: bold;"class="noLink">Undelivered Orders</a>
                                </span>
                            </div>
                        </a>
                    </div>

                    <div class="btn btn-secondary text-left">
                        <a href="deliverer_all_delivered_orders.do">
                            <i class="fa fa-envelope ml-0 pl-0" style="color: rgb(221, 170, 31); font-size: 33px;"></i>
                            <div class="float-right ml-4 my-1">
                                <span style="font-weight: bold; color:rgba(255, 255, 255, 0.774);">
                                    <a href="deliverer_all_delivered_orders.do" class="noLink">Delivered Orders</a>
                                </span>
                            </div>
                        </a>
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