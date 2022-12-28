<%@page import="java.util.ArrayList"%>
<%@page import="models.City"%>
<%@page import="models.Restaurant"%>
<%@page import="models.User"%>
<%@page import="models.Deliverer"%>

<% 
	Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
    ArrayList<City> cities = City.getAllCities(); 
%>
<% 
	User user = (User)session.getAttribute("user");
	Deliverer deliverer = (Deliverer)session.getAttribute("deliverer");
%>

<div class="row header">
    <div class="col-6">
    	<div class="row">
    		<div class="col-6">
                <a href="index.jsp" id="homeLink" class="d-block">
                    <h3 class="pt-2 pl-2"><i class="fa fa-cutlery mr-3"></i>Foody</h3>
                </a>
            </div>

            <div class="col-6 mt-1">
                <div class="form-group city-select mb-0 pt-1">
                    <select class="form-control-sm" name="city" id="id_city">
                        <option>Select City</option>
                        <% for(City city : cities){ 
                              if(restaurant != null && city.getCityId() == restaurant.getCity().getCityId()){	
                        %>
                              <option value="<%= city.getCityId() %>" selected><%= city.getCity() %></option>
                        <% }if(user != null && city.getCityId() == user.getCity().getCityId()){ %>	
                        <option value="<%= city.getCityId() %>" selected><%= city.getCity() %></option>
                        
                        <% }else { %>
                                <option value="<%= city.getCityId() %>"><%= city.getCity() %></option>
                        <% }} %>
                    </select>
                </div>
            </div>
    	</div>
    </div>

    <div class="col-6 mt-25 text-right">
        <% if(restaurant == null && user==null && deliverer==null){ %>
            <a href="restaurant_signin.do" class="btn btn-sm btn-dark text-white mr-1 font-weight-bold">Restaurant</a>
            <a href="user_signin.do" class="btn btn-sm btn-dark text-white mr-1 font-weight-bold">User</a>
            <a href="deliverer_signin.do" class="btn btn-sm btn-dark text-white mr-3 font-weight-bold">Deliverer</a>
        <% }if(restaurant!=null){ %>
            <a href="restaurant_profile.do" class="btn btn-sm btn-dark text-white mr-1 font-weight-bold"><i class="fa fa-user mr-1"></i>Profile</a>
            <a href="restaurant_logout.do" class="btn btn-sm btn-dark text-white mr-1 font-weight-bold"><i class="fa fa-sign-out"></i> Logout</a>
        <% }if(user!=null){ %>
        	<a href="user_profile.do" class="btn btn-sm btn-dark text-white mr-1 font-weight-bold"><i class="fa fa-user mr-1"></i>Profile</a>
        	<a href="user_logout.do" class="btn btn-sm btn-dark text-white mr-1 font-weight-bold"><i class="fa fa-sign-out"></i> Logout</a>
    	<% } if(deliverer!=null){ %>
        	<a href="deliverer_profile.do" class="btn btn-sm btn-dark text-white mr-1 font-weight-bold"><i class="fa fa-user mr-1"></i>Profile</a>
        	<a href="deliverer_logout.do" class="btn btn-sm btn-dark text-white mr-1 font-weight-bold"><i class="fa fa-sign-out"></i> Logout</a>
    	<% } %>
    </div>
</div>