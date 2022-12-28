package controllers;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Restaurant;
import models.Status;
import utils.GoogleCaptcha;

@SuppressWarnings("serial")
public class RestaurantSignInServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.getRequestDispatcher("restaurant_signin.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String nextPage = "restaurant_signin.jsp";
		boolean validation = true;
		
		if(GoogleCaptcha.reCaptchaTest(request)) {		
			HttpSession session = request.getSession();
						
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			//------------------------------------Validation------------------------------------
			Pattern pattern = null;
			Matcher matcher = null;
						
			String errors = "<ul>";
			
			
			pattern = Pattern.compile("^([a-zA-Z][a-zA-Z\\d-_\\.]*)@([a-zA-Z\\d-_]{2,})\\.([a-zA-Z]{2,5})(\\.[a-zA-Z]{2,5})?$");
			matcher = pattern.matcher(email);
			if(!matcher.matches()) {
				validation = false;
				errors += "<li>Enter valid Email.</li>";
			}
			
			pattern = Pattern.compile("^[a-zA-Z\\d_-]{6,20}$");
			matcher = pattern.matcher(password);
			if(!matcher.matches()) {
				validation = false;
				errors += "<li>Enter valid Password.</li>";
			}						
						
			if(validation) {
				Restaurant restaurant = new Restaurant(email, password);
				
				if(restaurant.signin()){	
					int statusId = restaurant.getStatus().getStatusId();
					if(statusId == Status.INACTIVE) {
						
					}else if(statusId == Status.PROFILE_INCOMPLETE) {
						session.setAttribute("restaurant", restaurant);
						nextPage = "restaurant_profile.do";
					}else if(statusId == Status.BLOCKED) {
						
					}else{
						session.setAttribute("restaurant", restaurant);
						nextPage = "restaurant_dashboard.do";
					}
				}else {
					errors += "<li>Account with the given email doesn't exist.</li></ul>";
					request.setAttribute("errors", errors);
					request.getRequestDispatcher(nextPage).forward(request, response);
					validation = false;
				}
			}else {
				errors += "</ul>";
				request.setAttribute("errors", errors);
				request.getRequestDispatcher(nextPage).forward(request, response);
			}
		}
		
		if(validation) {
			response.sendRedirect(nextPage);
		}
	}
}