package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodType {
	private Integer foodTypeId;
	private String foodType;
	private boolean veg;
	private String description;
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//----------------------------------Get all food method----------------------------------
	public static ArrayList<FoodType> getAllFoodTypes(){
		ArrayList<FoodType> foodTypes = new ArrayList<FoodType>();
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT food_type_id, food_type FROM food_types";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				FoodType foodType = new FoodType(rs.getInt(1), rs.getString(2));
				foodTypes.add(foodType);
			}
			
			con.close();
		}catch (SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return foodTypes;
	}
	
	//----------------------------------Get food_type from foodTypeId----------------------------------
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
	
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public FoodType() {
		super();
	}
	
	public FoodType(Integer foodTypeId, String foodType) {
		super();
		this.foodTypeId = foodTypeId;
		this.foodType = foodType;
	}

	public FoodType(String foodType) {
		super();
		this.foodType = foodType;
	}

	public FoodType(Integer foodTypeId) {
		super();
		this.foodTypeId = foodTypeId;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Getter and setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public Integer getFoodTypeId() {
		return foodTypeId;
	}
	public void setFoodTypeId(Integer foodTypeId) {
		this.foodTypeId = foodTypeId;
	}
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public boolean isVeg() {
		return veg;
	}
	public void setVeg(boolean veg) {
		this.veg = veg;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
