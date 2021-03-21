package src.model;

public class LabourChangeType {
	
	private int id;
	private int type;
	private int maxLeaveDay;
	
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getMaxLeaveDay() {
		return maxLeaveDay;
	}
	public void setMaxLeaveDay(int maxLeaveDay) {
		this.maxLeaveDay = maxLeaveDay;
	}
	
}
