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
    <link rel="stylesheet" href="static/css/dropzone.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

	<style>
		.green{
			color: green;
		}
	</style>
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
            	<div class="">
                    <h3 class="text-primary">Add ${food.name} Pic</h3>
                </div>
                
               	<form class="dropzone ml-4 mt-3" id="foodpicupload">
               
				</form>
                
                

				<input type="button" class="btn btn-primary mt-3 ml-4" id="btn" value="Upload Pic">
            </div>  
			
			<div id="success_box" class="container ml-5 d-none">
				<div class="row justify-content-center">
					<div id="success_box" class="col-5 p-0 mt-5 text-center">
						<div id="success">
							<h1 class="mb-0"><i class="fa fa-check green" aria-hidden="true"></i></h1>
							<h3 class="green pt-0 mt-0">Success</h3>
						</div>
						<div>
							<h4 class="lead mt-3 green">Food added successfully</h4>
						</div>
					</div>
				</div>
			</div>

        </div>
    </div>

    <%@ include file="footer.jsp" %>
    
    <script type="text/javascript" src="static/js/dropzone.min.js"></script>
    
	<script>
		Dropzone.autoDiscover = false;

		Dropzone.options.foodpicupload = {
			paramName: 'file',
			maxFilesize: 2,
			uploadMultiple: false,
			createImageThumbnails: true,
			maxFiles: 1,
			acceptedFiles: '.jpeg,.png,.gif,.jpg',
			addRemoveLinks: true,
			autoProcessQueue: false
		};

		let success_box = document.querySelector('#success_box');

		let dropzone = new Dropzone('#foodpicupload', {url: 'food_pic_upload.do'});
		dropzone.on('success', ()=>{
			console.log('Success');	
			success_box.classList.remove('d-none');
		});
		
		let btn = document.querySelector('#btn');
		btn.addEventListener('click',()=>{		
			dropzone.processQueue();
			success_box.classList.remove('d-none');
		});	
		
	</script>
</body>

</html>