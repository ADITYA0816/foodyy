package models;

public class OrderStatus {
	private Integer orderStatusId;
	private String status;
	
	public Integer getOrderStatusId() {
		return orderStatusId;
	}
	public void setOrderStatusId(Integer orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public OrderStatus() {
		super();
	}
	
	public OrderStatus(Integer orderStatusId) {
		super();
		this.orderStatusId = orderStatusId;
	}	
}
