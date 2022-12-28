package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class Restaurant {
	
	private Integer restaurantId;
	private String name;
	private String email;
	private String password;
	private String picPath;
	private String address;
	private City city;
	private String contact;
	private String food;
	private Status status;
	private String activationCode;
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//constructor called during SignUp process
	public Restaurant(String name, String email, String password, String contact, String activationCode) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.activationCode = activationCode;
	}
	
	public Restaurant() {
		super();
	}

	//constructor called during SignIn process
	public Restaurant(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Restaurant(Integer restaurantId) {
		super();
		this.restaurantId = restaurantId;
	}

	public Restaurant(Integer restaurantId, String name) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//-----------------------------------------Sign-Up Method---------------------------------------------
	public void signUp() {
		Connection con = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "INSERT INTO restaurants (name,email,password,contact,activation_code) values (?,?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);

			StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
			String encryptedPassword = encryptor.encryptPassword(password);
			preparedStatement.setString(3, encryptedPassword);
			preparedStatement.setString(4, contact);
			preparedStatement.setString(5, activationCode);
			
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//-----------------------------------------Activation Method------------------------------------------
	public static boolean activateAccount(String email, String activationCode) {
		boolean activated = false;
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE restaurants SET status_id=2, activation_code=NULL WHERE email=? AND activation_code=?";
			
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
		//return true iff account is activated otherwise return false
		return activated;
	}
	
	
	//-----------------------------------------Check-Email Method-----------------------------------------
	public static boolean checkEmail(String email) {
		boolean emailExists = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "select * from restaurants where email=?";
			
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
	
	
	//-----------------------------------------Sign-In Method-----------------------------------------
	public boolean signin() {
		boolean success = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT password, restaurant_id, name, pic_path, address, contact, status_id, i.city_id, city\r\n"
					+ "FROM restaurants AS i INNER JOIN cities AS c WHERE email=? AND i.city_id=c.city_id";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, email);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				String encryptedPassword = rs.getString(1);
				
				StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
				
				//if the password submitted during the signIn process matches the encrypted password which was saved during signUp
				//process...then only object will be created
				if(spe.checkPassword(password, encryptedPassword)) {
					restaurantId = rs.getInt(2);
					name = rs.getString(3);
					picPath = rs.getString(4);
					address = rs.getString(5);
					contact = rs.getString(6);
					status = new Status(rs.getInt(7));
					city = new City(rs.getInt(8), rs.getString(9));
					
					success = true;				
				}
			}
		}catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	
	//-----------------------------------------Update Profile Method-----------------------------------------
	public boolean updateProfile(String name, String address, City city, String contact) {
		boolean updateSuccess = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE restaurants SET name=?, address=?, city_id=?, contact=?, status_id=3 WHERE restaurant_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, name);
			pst.setString(2, address);
			pst.setInt(3, city.getCityId());
			pst.setString(4, contact);
			
			pst.setInt(5, restaurantId);
			
			int rowsAffected = pst.executeUpdate();
			
			if(rowsAffected == 1) {
				updateSuccess = true;
				
				this.name = name;
				this.address = address;
				this.city = city;
				this.contact = contact;
			}
			
			con.close();
		}catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return updateSuccess;
	}
	
	
	
	//-----------------------------------------Save logo Method-----------------------------------------
	public void saveLogo(String logo) {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE restaurants SET pic_path=? WHERE restaurant_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, logo);
			pst.setInt(2, restaurantId);
			
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
	
	
	//----------------------------------Get restaurant name from restaurantId----------------------------------
	public static String getFoodTypes(int foodTypeId){
		String foodTypeName = null;
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT food_type FROM food_types WHERE food_type_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, foodTypeId);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				foodTypeName = rs.getString(1);
			}
			
			con.close();
		}catch (SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return foodTypeName;
	}
	
	
	//------------------------------------Get recent orders Method-----------------------------------------
	public ArrayList<OrderedFood> getRecentOrders() {
		Connection con = null;
		ArrayList<OrderedFood> orderedFoods = new ArrayList<OrderedFood>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT z.order_id,z.booking_time,z.order_total,f.food_id,f.name,f.pic_path,x.units FROM orders AS z INNER JOIN ordered_food AS x INNER JOIN foods AS f WHERE z.order_id=x.order_id AND x.food_id=f.food_id AND x.order_status_id=5 AND z.restaurant_id=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, restaurantId);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				OrderedFood orderedFood = new OrderedFood();
				
				Order order = new Order();
				order.setOrderId(rs.getInt(1));
				order.setBookingTime(rs.getTime(2));
				order.setOrderTotal(rs.getInt(3));

				orderedFood.setOrder(order);
				
				Food food = new Food();
				food.setFoodId(rs.getInt(4));
				food.setName(rs.getString(5));
				food.setPicPath(rs.getString(6));
				
				orderedFood.setFood(food);
				
				orderedFood.setUnits(rs.getInt(7));
				
				orderedFoods.add(orderedFood);
			}
			
		}catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}			
		}
		return orderedFoods;
	}
	
	
	//------------------------------------accept order Method-----------------------------------------
	public void acceptOrder(int orderId) {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE ordered_food SET order_status_id=6 WHERE order_id=?";
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
	
	
	//------------------------------------reject order Method-----------------------------------------
	public void rejectOrder(int orderId) {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE ordered_food SET order_status_id=7 WHERE order_id=?";
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
	
	
	//------------------------------------Get all recent accepted orders Method-----------------------------------------
	public ArrayList<OrderedFood> getAllOrders() {
		Connection con = null;
		ArrayList<OrderedFood> orderedFoods = new ArrayList<OrderedFood>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT z.order_id,z.booking_time,z.order_total,f.food_id,f.name,f.pic_path,x.units,x.order_status_id FROM orders AS z INNER JOIN ordered_food AS x INNER JOIN foods AS f WHERE z.order_id=x.order_id AND x.food_id=f.food_id AND x.order_status_id>5 AND z.restaurant_id=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, restaurantId);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				OrderedFood orderedFood = new OrderedFood();
				
				Order order = new Order();
				order.setOrderId(rs.getInt(1));
				order.setBookingTime(rs.getTime(2));
				order.setOrderTotal(rs.getInt(3));

				orderedFood.setOrder(order);
				
				Food food = new Food();
				food.setFoodId(rs.getInt(4));
				food.setName(rs.getString(5));
				food.setPicPath(rs.getString(6));
				
				orderedFood.setFood(food);
				
				orderedFood.setUnits(rs.getInt(7));
				
				OrderStatus orderStatus = new OrderStatus();
				orderStatus.setOrderStatusId(rs.getInt(8));
				
				orderedFood.setOrderStatus(orderStatus);
				
				
				orderedFoods.add(orderedFood);
			}
			
		}catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}			
		}
		return orderedFoods;
	}
	
	
	
	//------------------------------------Process order Method-----------------------------------------
	public void processOrder(int orderId) {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE ordered_food SET order_status_id=8 WHERE order_id=?";
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
	
	
	//------------------------------------Processed order Method-----------------------------------------
	public void processedOrder(int orderId) {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE ordered_food SET order_status_id=12 WHERE order_id=?";
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
	
	
	//------------------------------------deliver order Method-----------------------------------------
	public void deliverOrder(int orderId) {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE ordered_food SET order_status_id=9 WHERE order_id=?";
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
	
	//------------------------------------Get all recent accepted orders Method-----------------------------------------
	public ArrayList<OrderedFood> getAllOnWayOrders() {
		Connection con = null;
		ArrayList<OrderedFood> orderedFoods = new ArrayList<OrderedFood>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT z.order_id,z.booking_time,z.order_total,f.food_id,f.name,f.pic_path,x.units,x.order_status_id,z.deliverer_id,d.deliverer_id,d.name,d.contact FROM orders AS z INNER JOIN ordered_food AS x INNER JOIN foods AS f INNER JOIN deliverers AS d WHERE z.order_id=x.order_id AND x.food_id=f.food_id AND x.order_status_id=9 AND z.deliverer_id=d.deliverer_id AND z.restaurant_id=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, restaurantId);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				OrderedFood orderedFood = new OrderedFood();
				
				Order order = new Order();
				order.setOrderId(rs.getInt(1));
				order.setBookingTime(rs.getTime(2));
				order.setOrderTotal(rs.getInt(3));
				
				Deliverer deliverer = new Deliverer();
				deliverer.setName(rs.getString(11));
				deliverer.setContact(rs.getString(12));
				
				order.setDeliverer(deliverer);

				orderedFood.setOrder(order);
				
				Food food = new Food();
				food.setFoodId(rs.getInt(4));
				food.setName(rs.getString(5));
				food.setPicPath(rs.getString(6));
				
				orderedFood.setFood(food);
				
				orderedFood.setUnits(rs.getInt(7));
				
				OrderStatus orderStatus = new OrderStatus();
				orderStatus.setOrderStatusId(rs.getInt(8));
				
				orderedFood.setOrderStatus(orderStatus);
				
				
				orderedFoods.add(orderedFood);
			}
			
		}catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}			
		}
		return orderedFoods;
	}
		
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Getter and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
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

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
}
