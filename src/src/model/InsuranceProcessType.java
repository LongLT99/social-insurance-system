package src.model;

public class InsuranceProcessType {
	private int id;
	private int maxNumberOfDays;
	private int type;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxNumberOfDays() {
		return maxNumberOfDays;
	}

	public void setMaxNumberOfDays(int maxNumberOfDays) {
		this.maxNumberOfDays = maxNumberOfDays;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
