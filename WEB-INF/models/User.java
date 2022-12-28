package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class User {
	
	private Integer userId;
	private Integer userType;
	private String name;
	private String email;
	private String password;
	private String picPath;
	private String address;
	private City city;
	private String contact;
	private Status status;
	private String activationCode;
	
	
	//-------------------------------------------Constructors-------------------------------------------
	
	
	//constructor called during SignUp process
	public User(String name, String email, String password, String contact, String activationCode) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.activationCode = activationCode;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructor called during SignIn process
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	

	//-------------------------------------------------Methods-----------------------------------------------------
	
	//-----------------------------------------------Sign Up Method---------------------------------------------
	public void signUp() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "INSERT INTO users (name,email,password,contact,activation_code) values (?,?,?,?,?)";
			
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
	
	//------------------------------------------------Activation Method---------------------------------------------
	public static boolean activateAccount(String email, String activationCode) {
		boolean activated = false;
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE users SET status_id=2, activation_code=NULL WHERE email=? AND activation_code=?";
			
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
	
	//------------------------------------------------Check-Email Method---------------------------------------------
	public static boolean checkEmail(String email) {
		boolean emailExists = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "select * from users where email=?";
			
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
	
	//------------------------------------------------Sign-In Method-----------------------------------------
	public boolean signin() {
		boolean success = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT password, user_id, user_type, name, pic_path, address, contact, status_id, i.city_id, city\r\n"
					+ "FROM users AS i INNER JOIN cities AS c WHERE email=? AND i.city_id=c.city_id";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, email);
			
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				String encryptedPassword = rs.getString(1);
				
				StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
				
				//if the password submitted during the signIn process matches the encrypted password which was saved during signUp
				//process...then only object will be created
				if(spe.checkPassword(password, encryptedPassword)) {
					userId = rs.getInt(2);
					userType = rs.getInt(3);
					name = rs.getString(4);
					picPath = rs.getString(5);
					address = rs.getString(6);
					contact = rs.getString(7);
					status = new Status(rs.getInt(8));
					city = new City(rs.getInt(9), rs.getString(10));
					
					success = true;				
				}
			}
					
		}catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	//---------------------------------------------Update Profile Method-----------------------------------------
	public boolean updateProfile(String name, String address, City city, String contact) {
		boolean updateSuccess = false;
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE users SET name=?, address=?, city_id=?, contact=?, status_id=3 WHERE user_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, name);
			pst.setString(2, address);
			pst.setInt(3, city.getCityId());
			pst.setString(4, contact);
			
			pst.setInt(5, userId);
			
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
	
	
	//---------------------------------------------Save logo Method-----------------------------------------
	public void saveLogo(String logo) {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE users SET pic_path=? WHERE user_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, logo);
			pst.setInt(2, userId);
			
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
	
	
	//---------------------------------------------Get all orders Method-----------------------------------------
	public ArrayList<OrderedFood> getAllOrders() {
		Connection con = null;
		ArrayList<OrderedFood> orderedFoods = new ArrayList<OrderedFood>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT f.food_id,f.name,f.pic_path,o.order_id,o.restaurant_id,r.name,x.order_status_id,y.status,x.units FROM ordered_food AS x INNER JOIN orders AS o INNER JOIN order_status AS y INNER JOIN foods AS f INNER JOIN restaurants AS r WHERE o.restaurant_id=r.restaurant_id AND o.order_id=x.order_id AND x.food_id=f.food_id AND x.order_status_id=y.order_status_id AND user_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, userId);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				OrderedFood orderedFood = new OrderedFood();
				
				Food food = new Food();
				food.setFoodId(rs.getInt(1));
				food.setName(rs.getString(2));
				food.setPicPath(rs.getString(3));
				
				orderedFood.setFood(food);
				
				Order order = new Order();
				order.setOrderId(rs.getInt(4));
				
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantId(rs.getInt(5));
				restaurant.setName(rs.getString(6));
				order.setRestaurant(restaurant);
				
				orderedFood.setOrder(order);
				
				OrderStatus orderStatus = new OrderStatus();
				orderStatus.setOrderStatusId(rs.getInt(7));
				orderStatus.setStatus(rs.getString(8));
				
				orderedFood.setOrderStatus(orderStatus);
				
				orderedFood.setUnits(rs.getInt(9));
				
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
		
		
	
	
    //----------------------------------------------Getter and Setters---------------------------------------------
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
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
