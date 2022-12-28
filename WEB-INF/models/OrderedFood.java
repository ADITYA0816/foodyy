package models;

import java.sql.Date;
import java.sql.Time;

public class OrderedFood {
	private Integer orderedFoodId;
	private Order order;
	private Food food;
	private OrderStatus orderStatus;
	private Integer units;
	
	
	public Integer getOrderedFoodId() {
		return orderedFoodId;
	}
	public void setOrderedFoodId(Integer orderedFoodId) {
		this.orderedFoodId = orderedFoodId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
}
