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

import models.User;

public class UserLogoUploadServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		
		if(user!=null && ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			
			ServletFileUpload sfu = new ServletFileUpload(dfif);
						
			try {
				List<FileItem> items = sfu.parseRequest(request);
				
				FileItem fileItem = items.get(0);
				
				String fileName = fileItem.getName();
				
				String uploadPath = getServletContext().getRealPath("/WEB-INF/uploads/users/"+user.getEmail());
			
				File file = new File(uploadPath, fileName);
				
				try {
					fileItem.write(file);
					
					String logoPath = user.getEmail()+"/"+fileName; 
					
					user.saveLogo(logoPath);
					
					user.setPicPath(logoPath);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
	}
}