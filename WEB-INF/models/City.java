package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class City {	
	
	private Integer cityId;
	private String city;
	
	public City() {
		super();
	}
	
	public City(Integer cityId, String city) {
		super();
		this.cityId = cityId;
		this.city = city;
	}
	
	public City(Integer cityId) {
		super();
		this.cityId = cityId;
	}
	
	public static ArrayList<City> getAllCities(){
		ArrayList<City> cities = new ArrayList<City>();
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ofos?user=root&password=1234");
			
			String query = "SELECT city_id, city FROM cities";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				City city = new City(rs.getInt(1), rs.getString(2));
				cities.add(city);
			}
			
			con.close();
		}catch (SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return cities;
	}
	
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
