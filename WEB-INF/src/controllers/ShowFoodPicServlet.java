package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowFoodPicServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String foodPicPath = request.getParameter("food_pic_path");
		InputStream is = getServletContext().getResourceAsStream("/WEB-INF/uploads/restaurants/"+foodPicPath);
		
		OutputStream os = response.getOutputStream();
		
		int count = 0;
		byte[] arr = new byte[1024];
		while((count=is.read(arr))!=-1) {
			os.write(arr);
		}				
		
		os.flush();
		os.close();
	}
}