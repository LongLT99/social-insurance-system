package src.model;

import java.util.Date;

public class InsuranceProcess {
	private int id;
	
	private Labour labour;
	
	private Date startTime;
	private Date endTime;
	
	private Float insuranceSalary;
	
	private String position;
	private String division;
	private String contractCode;
	
	private InsuranceProcessType processType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Labour getLabour() {
		return labour;
	}
	public void setLabour(Labour labour) {
		this.labour = labour;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public InsuranceProcessType getProcessType() {
		return processType;
	}
	public void setProcessType(InsuranceProcessType processType) {
		this.processType = processType;
	}
	public Float getInsuranceSalary() {
		return insuranceSalary;
	}
	public void setInsuranceSalary(Float insuranceSalary) {
		this.insuranceSalary = insuranceSalary;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	
}
