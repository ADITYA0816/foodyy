package controllers;

import java.io.File;
import java.io.IOException;
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

import models.Restaurant;

public class RestaurantLogoUploadServlet extends HttpServlet{
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
				
				String uploadPath = getServletContext().getRealPath("/WEB-INF/uploads/restaurants/");
			
				File file = new File(uploadPath + restaurant.getEmail()+"/"+fileName);
				
				if(restaurant.getPicPath()!=null) {
					File delFile = new File(uploadPath+restaurant.getPicPath());
					delFile.delete();
				}
				
				try {						
					fileItem.write(file);
					
					String logoPath = restaurant.getEmail()+"/"+fileName; 
					
					restaurant.saveLogo(logoPath);
					
					restaurant.setPicPath(logoPath);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
	}
}