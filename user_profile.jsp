<!DOCTYPE html>
<%@page import="models.User" %>
<%@ page import="models.City, java.util.ArrayList" %>
<html lang="en">

	<head>
		<%@ include file="head.jsp" %>

			<title>User : Profile</title>
			<link rel="stylesheet" href="static/css/form.css">
			<link rel="stylesheet" href="static/css/dropzone.min.css">
			<script src="https://www.google.com/recaptcha/api.js" async defer></script>

			<style type="text/css">
				#picupload {
					margin-top: 30px;
					margin-bottom: 20px;
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
			<div class="row justify-content-center">

				<div class="col-5 text-center border-right border-light pt-1 px-5">
					<img alt="" src="show_user_logo.do?logo=${user.picPath}" height="200">
                
                	<hr>
					<form class="dropzone" id="picupload">
					</form>

					<input type="button" class="btn btn-primary" id="btn" value="Upload">
				</div>

				<div class="col-6 p-0 m-0">

					<% String errors=(String)request.getAttribute("errors"); %>

					<% if(errors!=null){ %>
						<h6 class="error-box">
							<%= errors %>
						</h6>
					<% } %>

					<form action="user_profile.do" method="post" class="w-75 ml-3" id="profile_form">

						<div class="form-group">
							<label for="id_name">Name</label>
							<input type="text" name="name" id="id_name" value="${user.name}"
								class="form-control">
							<small id="name_help" class="form-text text-muted pl-1">Characters Allowed:
								a-z, A-z, 0-9 and spaces.</small>
							<small id="name_error" class="form-text text-danger pl-1 hide">Invalid Name.
								Please enter valid Name.</small>
						</div>

						<div class="form-group">
							<label for="id_address">Address</label>
							<textarea rows="3" name="address" id="id_address"
								class="form-control">${user.address}</textarea>
							<small id="email_help" class="form-text text-muted pl-1">Enter valid
								email.</small>
							<small id="email_error" class="form-text text-danger pl-1 hide">Invalid
								Email. Please enter valid Email.</small>
						</div>

						<div class="form-group">
							<label for="id_city">City</label>
							<select class="form-control" name="city" id="id_city">
								<option>Select City</option>
								<% for(City city : cities){
									if(city.getCityId()==user.getCity().getCityId()){ %>
									<option value="<%= city.getCityId() %>" selected>
										<%= city.getCity()%>
									</option>
									<% } else { %>
										<option value="<%= city.getCityId() %>">
											<%= city.getCity()%>
										</option>
										<% }} %>
							</select>
						</div>

						<div class="form-group">
						    <label for="id_contact" style="display: block">Contact No.</label>
							<input type="text" name="contact" value="${user.contact}" id="id_contact"
							class="form-control" minlength="10" maxlength="10" autocomplete="off"  style="width: 75% ; display: inline;">
							
							<input type="button" id="id_sendotp" class="btn btn-sm btn-secondary mt-2 py-1" style="float: right; display: inline-block" value="Send OTP">
							
							<small id="contact_help" class="form-text text-muted pl-1">Characters
								Allowed: 0-9 and must start with 5-9.</small>
							<small id="contact_error" class="form-text text-danger pl-1 hide">Invalid
								Contact. Please enter valid Contact.</small>
						</div>
						
						<div class="form-group" id="otp_box">
                        	<label for="id_otp">Enter OTP</label>
                        	<div class="row">
                        		<div class="col-3">
                        			<input type="text" name="otp" id="id_otp" class="form-control">                        	
                        		</div>
                        		<div class="col-9">
                        			<input type="button" value="Check OTP" id="check_otp" class="btn btn-sm btn-primary mt-2">
                        		</div>
                        	</div>
                        </div>

						<input type="submit" id="id_savedetails" class="btn btn-primary mt-2" value="Save Details">
					</form>
				</div>
			</div>
		</div>

		<%@ include file="footer.jsp" %>

		<script type="text/javascript" src="static/js/dropzone.min.js"></script>
		<script>
			Dropzone.autoDiscover = false;

			Dropzone.options.picupload = {
				paramName: 'file',
				maxFilesize: 2,
				uploadMultiple: false,
				createImageThumbnails: true,
				maxFiles: 1,
				acceptedFiles: '.jpeg,.png,.gif,.jpg',
				addRemoveLinks: true,
				autoProcessQueue: false
			};


			let dropzone = new Dropzone('#picupload', { url: 'user_pic_upload.do' });

			let btn = document.querySelector('#btn');
			btn.addEventListener('click', () => {
				dropzone.processQueue();
				document.querySelector('#user_logo').style.display = 'none'; 
			});
		</script>
		
		<script type="text/javascript" src="static/js/profile_form.js"></script>
		
	</body>

</html>