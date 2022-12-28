package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Deliverer;
import utils.EmailMessages;
import utils.EmailSender;
import utils.GoogleCaptcha;

public class DelivererSignUpServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.getRequestDispatcher("deliverer_signup.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		//getting captcha result
		boolean reCaptchaTestResult = GoogleCaptcha.reCaptchaTest(request);
		
		//if captcha result is true then only process
		if(reCaptchaTestResult) {
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String contact = request.getParameter("contact");
			
			Pattern pattern = null;
			Matcher matcher = null;
			
			boolean validation = true;
			String errors = "<ul>";
			
			//matching every field entered in form with agex pattern
			pattern = Pattern.compile("^[a-zA-Z ]{5,50}$");
			matcher = pattern.matcher(name);
			if(!matcher.matches()) {
				validation = false;
				errors += "<li>Enter valid User Name.</li>";
			}
			
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
			
			pattern = Pattern.compile("^[5-9][0-9]{9}$");
			matcher = pattern.matcher(contact);
			if(!matcher.matches()) {
				validation = false;
				errors += "<li>Enter valid Contact.</li>";
			}
			
			errors += "</ul>";
			
			//if all the fields are correctly entered ie matches with agex pattern then only validation is 
			//processed and constructor is called
			if(validation) {
				
				long activationCode = new Random().nextLong();
				if(activationCode<1) {
					activationCode *= -1;
				}
				
				Deliverer deliverer = new Deliverer(name,email,password,contact,Long.toString(activationCode));
				
				deliverer.signUp();
				
				ServletContext context = request.getServletContext();
				String parentPath = context.getRealPath("/WEB-INF/uploads/deliverers");
				File file = new File(parentPath, deliverer.getEmail());
				
				file.mkdir();
				
				String subject = "Foody :: Account Activation Mail";
				String message = EmailMessages.getDelivererAccountActivationMail(name, email, activationCode);
				
				EmailSender.sendEmail(email, subject, message);
				
				response.sendRedirect("signup_success.jsp");
			}else {
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("deliverer_signup.jsp").forward(request, response);
			}
		}else {
			//robo-attack
		}
		
	}

}
