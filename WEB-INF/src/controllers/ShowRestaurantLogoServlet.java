package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRestaurantLogoServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String logo = request.getParameter("logo");
		
		
		OutputStream os = response.getOutputStream();
		
		InputStream is = getServletContext().getResourceAsStream("/WEB-INF/uploads/restaurants/"+logo);
		
		if(is==null) {
			is = getServletContext().getResourceAsStream("/static/images/logo.png");
		}
		
		int count = 0;
		byte[] arr = new byte[256];
		
		while((count=is.read(arr)) != -1) {
			os.write(arr);
		}
		
		os.flush();
		os.close();
	}
}