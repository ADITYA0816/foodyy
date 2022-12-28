package controllers;

import java.io.File;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.IOException;
import models.Food;
import models.Restaurant;

public class FoodPicUploadServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		
		
		if(restaurant!=null && ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			
			ServletFileUpload sfu = new ServletFileUpload(dfif);
						
			try {
				List<FileItem> items = sfu.parseRequest(request);
				
				FileItem fileItem = items.get(0);
				
				String fileName = fileItem.getName();
				
				Food food = (Food)session.getAttribute("food");
				String foodName = food.getName().replace(" ", "_");
				
				String uploadPath = getServletContext().getRealPath("/WEB-INF/uploads/restaurants/"+restaurant.getEmail()+"/foods/"+foodName);
			
				File file = new File(uploadPath, fileName);
				
				if(food.getPicPath()!=null) {
					File delFile = new File(file, food.getPicPath());
					delFile.delete();
				}
				
				try {						
					fileItem.write(file);
					
					String foodPicPath = restaurant.getEmail()+"/foods/"+foodName+"/"+fileName; 
					System.out.println(foodPicPath);
										
					food.saveFoodPic(foodPicPath , foodName.replace("_"," "));
					
					food.setPicPath(foodPicPath);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("```````````````");
		}
	}
}