package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class Deliverer {
	private Integer delivererId;
	private String name;
	private String email;
	private String password;
	private String contact;
	private Boolean delivererStatus;
	private String activationCode;
	
	
	public Deliverer() {
		super();
	}

	public Deliverer(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Deliverer(String name, String email, String password, String contact, String activationCode) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.activationCode = activationCode;
	}
	
	
	//-------------------------------------------------Methods-----------------------------------------------------
	
	//-----------------------------------------------Sign Up Method---------------------------------------------
	public void signUp() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "INSERT INTO deliverers (name,email,password,contact,activation_code) values (?,?,?,?,?)";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			
			StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
			String encryptedPassword = encryptor.encryptPassword(password);
			preparedStatement.setString(3, encryptedPassword);
			preparedStatement.setString(4, contact);
			preparedStatement.setString(5, activationCode);
			
			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------------------Check-Email Method---------------------------------------------
	public static boolean checkEmail(String email) {
		boolean emailExists = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "select * from deliverers where email=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, email);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				emailExists = true;
			}
			
		}catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return emailExists;
	}
	
	
	//------------------------------------------------Activation Method---------------------------------------------
	public static boolean activateAccount(String email, String activationCode) {
		boolean activated = false;
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE deliverers SET activation_code=NULL WHERE email=? AND activation_code=?";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, activationCode);
			
			//executeUpdate returns the number of rows affected
			int rows = preparedStatement.executeUpdate();
			
			//if rows==1 that means query updated a row record
			if(rows==1) {
				activated = true;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return activated;
	}
	
	
	//------------------------------------------------Sign-In Method-----------------------------------------
	public boolean signin() {
		boolean success = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT deliverer_id, name, password, contact FROM deliverers WHERE email=?";
			
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, email);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				String encryptedPassword = rs.getString(3);
				
				StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
				
				//if the password submitted during the signIn process matches the encrypted password which was saved during signUp
				//process...then only object will be created
				if(spe.checkPassword(password, encryptedPassword)) {
					delivererId = rs.getInt(1);
					name = rs.getString(2);
					contact = rs.getString(4);
					success = true;				
				}
			}
					
		}catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	
	
	//------------------------------------------------Active Status Method-----------------------------------------
	public boolean activeStatus() {
		boolean success = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "update deliverers set deliverer_status=1 where deliverer_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, delivererId);
			
			pst.executeUpdate();
			success = true;
					
		}catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	
	
	//------------------------------------------------Inactive Status Method-----------------------------------------
	public boolean inactiveStatus() {
		boolean success = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "update deliverers set deliverer_status=0 where deliverer_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, delivererId);
			
			pst.executeUpdate();
			success = true;
					
		}catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	
	
	//------------------------------------Accept Order Delivery Method-----------------------------------
	public boolean acceptOrderDelivery(int orderId) {
		boolean success = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "update orders set deliverer_id=? where order_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, delivererId);
			pst.setInt(2, orderId);
			
			pst.executeUpdate();
			success = true;
					
		}catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	
	
	

	//------------------------------------Get All Undelivered Order Method-----------------------------------
	public ArrayList<OrderedFood> getAllUndeliveredOrders() {
		Connection con = null; 
		ArrayList<OrderedFood> allUndeliveredOrders = new ArrayList<OrderedFood>(); 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT a.user_id,a.name,a.address,a.contact,r.restaurant_id,r.name,r.contact,r.address,z.order_id,z.expected_deliverey_time,z.order_total,z.user_id,f.food_id,f.name,f.pic_path,x.units FROM orders AS z INNER JOIN ordered_food AS x INNER JOIN foods AS f INNER JOIN users AS a INNER JOIN restaurants AS r WHERE z.user_id=a.user_id AND z.order_id=x.order_id AND x.food_id=f.food_id AND x.order_status_id=9 AND z.restaurant_id=r.restaurant_id AND z.deliverer_id=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, delivererId);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				OrderedFood orderedFood = new OrderedFood();
				
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAddress(rs.getString(3));
				user.setContact(rs.getString(4));
				
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantId(rs.getInt(5));
				restaurant.setName(rs.getString(6));	
				restaurant.setContact(rs.getString(7));	
				restaurant.setAddress(rs.getString(8));	
				
				Order order = new Order();
				order.setOrderId(rs.getInt(9));
				order.setExpectedDeliveryTime(rs.getTime(10));
				order.setOrderTotal(rs.getInt(11));
				
				order.setRestaurant(restaurant);
				
				order.setUser(user);

				orderedFood.setOrder(order);
				
				Food food = new Food();
				food.setFoodId(rs.getInt(13));
				food.setName(rs.getString(14));
				food.setPicPath(rs.getString(15));
				
				orderedFood.setFood(food);
				
				orderedFood.setUnits(rs.getInt(16));
				
				allUndeliveredOrders.add(orderedFood);
			}
					
		}catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return allUndeliveredOrders;
	}
	
	
	
	//------------------------------------order delivered Method-----------------------------------------
	public void deliveredOrder(int orderId) {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE ordered_food SET order_status_id=11 WHERE order_id=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, orderId);
			
			pst.executeUpdate();
			
		}catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	
	
	//------------------------------------Get All Undelivered Order Method-----------------------------------
	public ArrayList<OrderedFood> getAllDeliveredOrders() {
		Connection con = null; 
		ArrayList<OrderedFood> allUndeliveredOrders = new ArrayList<OrderedFood>(); 
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT a.user_id,a.name,a.address,a.contact,r.restaurant_id,r.name,r.contact,r.address,z.order_id,z.expected_deliverey_time,z.order_total,z.user_id,f.food_id,f.name,f.pic_path,x.units FROM orders AS z INNER JOIN ordered_food AS x INNER JOIN foods AS f INNER JOIN users AS a INNER JOIN restaurants AS r WHERE z.user_id=a.user_id AND z.order_id=x.order_id AND x.food_id=f.food_id AND x.order_status_id=11 AND z.restaurant_id=r.restaurant_id AND z.deliverer_id=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, delivererId);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				OrderedFood orderedFood = new OrderedFood();
				
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setAddress(rs.getString(3));
				user.setContact(rs.getString(4));
				
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantId(rs.getInt(5));
				restaurant.setName(rs.getString(6));	
				restaurant.setContact(rs.getString(7));	
				restaurant.setAddress(rs.getString(8));	
				
				Order order = new Order();
				order.setOrderId(rs.getInt(9));
				order.setExpectedDeliveryTime(rs.getTime(10));
				order.setOrderTotal(rs.getInt(11));
				
				order.setRestaurant(restaurant);
				
				order.setUser(user);

				orderedFood.setOrder(order);
				
				Food food = new Food();
				food.setFoodId(rs.getInt(13));
				food.setName(rs.getString(14));
				food.setPicPath(rs.getString(15));
				
				orderedFood.setFood(food);
				
				orderedFood.setUnits(rs.getInt(16));
				
				allUndeliveredOrders.add(orderedFood);
			}
					
		}catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return allUndeliveredOrders;
	}
		
	
	
	
	public Integer getDelivererId() {
		return delivererId;
	}
	public void setDelivererId(Integer delivererId) {
		this.delivererId = delivererId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Boolean getDelivererStatus() {
		return delivererStatus;
	}
	public void setDelivererStatus(Boolean delivererStatus) {
		this.delivererStatus = delivererStatus;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
}
