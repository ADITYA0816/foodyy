package models;

public class Status {
	private Integer statusId;
	private String status;
	
	public static final int INACTIVE = 1;
	public static final int PROFILE_INCOMPLETE = 2;
	public static final int ACTIVE = 3;
	public static final int BLOCKED = 4;
	public static final int ENDED = 5;
		
	public Status() {
		super();
	}	
	
	public Status(Integer statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}

	public Status(Integer statusId) {
		super();
		this.statusId = statusId;
	}

	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}