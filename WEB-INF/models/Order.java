package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

public class Order {
	private Integer orderId;
	private Restaurant restaurant;
	private User user;
	private Deliverer deliverer;
	private Date date;
	private Integer orderTotal;
	private Time bookingTime;
	private Time expectedDeliveryTime;	
	
	public Order(){
		super();
	}
	
	public Order(Restaurant restaurant, User user, Integer orderTotal) {
		super();
		this.restaurant = restaurant;
		this.user = user;
		this.orderTotal = orderTotal;
	}


	//---------------------------------------Methods--------------------------------------
	//-------------------------------------ADD Order Method--------------------------------------
	public boolean addOrder(){
		Connection con  = null;
		boolean flag = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "INSERT INTO orders (restaurant_id,user_id,date,order_total,booking_time,expected_deliverey_time) values (?,?,?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, restaurant.getRestaurantId());
			pst.setInt(2, user.getUserId());
			
			java.util.Date utilDate = new java.util.Date();
            Date sqlDate = new java.sql.Date(utilDate.getTime());
            Time sqlTime = new Time(utilDate.getTime());
			
			pst.setDate(3, sqlDate);
			pst.setInt(4, orderTotal);

			pst.setTime(5, sqlTime);
			
			sqlTime.setMinutes(sqlTime.getMinutes()+30);
			pst.setTime(6, sqlTime);
			
			int rows = pst.executeUpdate();
			
			if(rows==1) {
				ResultSet rs = pst.getGeneratedKeys();
				if(rs.next()) {
					orderId = rs.getInt(1);
				}
			}
			
			flag = true;
			
		} catch (SQLException|ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
		}
		return flag;
	}
	
	
	public void orderFood(int foodId){
		Connection con  = null;
		boolean flag = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "INSERT INTO ordered_food (order_id,food_id) values (?,?)";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, orderId);
			pst.setInt(2, foodId);
			
			pst.executeUpdate();
			
		} catch (SQLException|ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
		}
	}
	
	
	
	public static ArrayList<OrderedFood> getAllProcessedOrders(){
		Connection con  = null;
		ArrayList<OrderedFood> allProcessedOrders = new ArrayList<OrderedFood>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT a.user_id,a.name,a.address,a.contact,r.restaurant_id,r.name,r.contact,r.address,z.order_id,z.expected_deliverey_time,z.order_total,"
					+ "z.user_id,f.food_id,f.name,f.pic_path,x.units FROM orders AS z INNER JOIN ordered_food "
					+ "AS x INNER JOIN foods AS f INNER JOIN users AS a INNER JOIN restaurants AS r WHERE z.user_id=a.user_id "
					+ "AND z.order_id=x.order_id AND x.food_id=f.food_id AND x.order_status_id=12 "
					+ "AND z.restaurant_id=r.restaurant_id AND z.deliverer_id IS NULL;";
			
			
			PreparedStatement pst = con.prepareStatement(query);
			
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
				
				allProcessedOrders.add(orderedFood);
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
		return allProcessedOrders;
	}
	
	//---------------------------------------Getter Setters--------------------------------------
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Deliverer getDeliverer() {
		return deliverer;
	}
	public void setDeliverer(Deliverer deliverer) {
		this.deliverer = deliverer;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}
	public Time getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(Time bookingTime) {
		this.bookingTime = bookingTime;
	}
	public Time getExpectedDeliveryTime() {
		return expectedDeliveryTime;
	}
	public void setExpectedDeliveryTime(Time expectedDeliveryTime) {
		this.expectedDeliveryTime = expectedDeliveryTime;
	}
}
