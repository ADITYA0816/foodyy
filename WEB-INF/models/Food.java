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

import javax.servlet.http.HttpSession;

public class Food {
	private Integer foodId;
	private String name;
	private Restaurant restaurant;
	private String pricePerUnit;
	private String picPath;
	private FoodType foodType;
	private boolean status;
	private String ingredients;
	private Integer starRank;
	private Integer starRankCount;

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public Food() {
		super();
	}

	public Food(String name, String pricePerUnit, FoodType foodType, String ingredients) {
		super();
		this.name = name;
		this.pricePerUnit = pricePerUnit;
		this.ingredients = ingredients;
		this.foodType = foodType;
	}	
	
	public Food(Integer foodId, Restaurant restaurant, String pricePerUnit) {
		super();
		this.foodId = foodId;
		this.restaurant = restaurant;
		this.pricePerUnit = pricePerUnit;
	}

	public Food(Integer foodId) {
		super();
		this.foodId = foodId;
	}

	public Food(Integer foodId, String name, Restaurant restaurant, String pricePerUnit, String picPath) {
		super();
		this.foodId = foodId;
		this.name = name;
		this.restaurant = restaurant;
		this.pricePerUnit = pricePerUnit;
		this.picPath = picPath;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	

	

	//------------------------------------------ADD FOOD Method--------------------------------------
	public void addFood(Restaurant restaurant){ 
		Connection con = null;
  
	  	try { 
			Class.forName("com.mysql.cj.jdbc.Driver");
	  
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
		
			System.out.println("--------------------1");
			String query = "INSERT INTO foods (name, price_per_unit, food_type_id, ingredients, restaurant_id) value (?,?,?,?,?)";
			System.out.println("--------------------2");
			PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, name); 
			pst.setString(2, pricePerUnit); 
			pst.setInt(3, foodType.getFoodTypeId());
			pst.setString(4, ingredients);
			pst.setInt(5, restaurant.getRestaurantId());	

			int rows = pst.executeUpdate();
			
			if(rows==1) {
				ResultSet rs = pst.getGeneratedKeys();
				if(rs.next()) {
					foodId = rs.getInt(1);
				}
			}
		}catch (SQLException|ClassNotFoundException e) { 
			e.printStackTrace();
		}finally{ 
			try{ 
				con.close(); 
			}catch(SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
 
	//----------------------------------------SAVE FOOD PIC Method----------------------------------------
	public void saveFoodPic(String foodPicPath, String foodName) {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "UPDATE foods SET pic_path=? WHERE name=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, foodPicPath);
			pst.setString(2, foodName);
			
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
	  
	// -------------------------------GET ALL FOOD of a particular restaurant Method-----------------------------
	public static ArrayList<Food> getAllFoods(Restaurant restaurant) {
		ArrayList<Food> foods = new ArrayList<Food>();
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT * FROM foods WHERE restaurant_id=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, restaurant.getRestaurantId());
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Food food = new Food();
				food.setFoodId(rs.getInt(1));
				food.setRestaurant(restaurant);
				food.setName(rs.getString(3));
				food.setPricePerUnit(rs.getString(4));
				food.setPicPath(rs.getString(5));
				food.setFoodType(new FoodType(rs.getInt(6)));
				food.setStatus(rs.getBoolean(7));
				food.setIngredients(rs.getString(8));
				food.setStarRank(rs.getInt(9));
				food.setStarRankCount(rs.getInt(10));
				
				foods.add(food);
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
		
		return foods;
	}
	
	// -------------------------------GET ALL FOOD of a all restaurant Method-----------------------------
	public static ArrayList<Food> getAllFoods(){
		ArrayList<Food> foodss = new ArrayList<Food>();
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT * FROM foods";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Food food = new Food();
				food.setFoodId(rs.getInt(1));
				food.setRestaurant(new Restaurant(rs.getInt(2)));
				food.setName(rs.getString(3));
				food.setPricePerUnit(rs.getString(4));
				food.setPicPath(rs.getString(5));
				food.setFoodType(new FoodType(rs.getInt(6)));
				food.setStatus(rs.getBoolean(7));
				food.setIngredients(rs.getString(8));
				food.setStarRank(rs.getInt(9));
				food.setStarRankCount(rs.getInt(10));
				
				foodss.add(food);
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
		
		return foodss;
	}		
	
	
	//--------------------------------------COLLECT ALL FOOD Method----------------------------------	
	public static ArrayList<Food> collectAllFoods(String searchKey, City city){
		Connection con  = null;
		ArrayList<Food> foods = new ArrayList<Food>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT f.food_id,r.restaurant_id,r.name,f.name,f.price_per_unit,f.pic_path,f.food_type_id,f.ingredients,ct.city FROM foods as f INNER JOIN restaurants AS r INNER JOIN cities AS ct WHERE f.restaurant_id=r.restaurant_id AND r.city_id=ct.city_id AND ct.city_id=? AND f.name LIKE '%"+searchKey+"%'";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, city.getCityId());
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Food food = new Food();
				food.setFoodId(rs.getInt("food_id"));
				food.setName(rs.getString(4));
				
				Restaurant restaurant = new Restaurant(rs.getInt("restaurant_id"),rs.getString(3));
				food.setRestaurant(restaurant);
				
				food.setPricePerUnit(rs.getString("price_per_unit"));
				food.setPicPath(rs.getString("pic_path"));

				FoodType foodType = new FoodType(rs.getInt("food_type_id"));
				food.setFoodType(foodType);
				
				food.setIngredients(rs.getString("ingredients"));
				
				foods.add(food);
			}
			
		} catch (SQLException|ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
		}
		
		return foods;
	}	
	
	
	
	
	//--------------------------------------COLLECT ALL FOOD Method----------------------------------	
	public static ArrayList<Food> collectOrderedFoods(int foodId){
		Connection con  = null;
		ArrayList<Food> orderedFoods = new ArrayList<Food>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT r.restaurant_id,r.name,f.name,f.price_per_unit,f.pic_path,f.food_type_id,f.ingredients FROM foods as f INNER JOIN restaurants AS r WHERE food_id="+foodId;
			
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Food food = new Food();
				food.setFoodId(foodId);
				food.setName(rs.getString(4));
				
				Restaurant restaurant = new Restaurant(rs.getInt("restaurant_id"),rs.getString(3));
				food.setRestaurant(restaurant);
				
				food.setPricePerUnit(rs.getString("price_per_unit"));
				food.setPicPath(rs.getString("pic_path"));

				FoodType foodType = new FoodType(rs.getInt("food_type_id"));
				food.setFoodType(foodType);
				
				food.setIngredients(rs.getString("ingredients"));
				
				orderedFoods.add(food);
			}
			
		} catch (SQLException|ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
		}
		
		return orderedFoods;
	}	
	
	  
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Getter and setter~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(String pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public Integer getStarRank() {
		return starRank;
	}

	public void setStarRank(Integer starRank) {
		this.starRank = starRank;
	}

	public Integer getStarRankCount() {
		return starRankCount;
	}

	public void setStarRankCount(Integer starRankCount) {
		this.starRankCount = starRankCount;
	}
}
