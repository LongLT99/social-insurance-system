package src.model;

import java.util.Date;

/**
 * Class lưu giá trị tính toán phí BHXH tháng
 * @author Admin
 *
 */
public class BusinessInsuranceInfo extends BusinessUnit {
	private Date insuranceDate;
	
	//Phí bảo hiểm do người lao động đóng
	private float employeeSocialInsurance;
	private float employeeMedicalInsurance;
	private float employeeUnemployedInsurance;
	private float employeeUnionFee;
	
	//Phí bảo hiểm do doanh nghiệp đóng
	private float businessSocialInsurance;
	private float businessMedicalInsurance;
	private float businessUnemployedInsurance;
	private float businessUnionFee;
	public Date getInsuranceDate() {
		return insuranceDate;
	}
	public void setInsuranceDate(Date insuranceDate) {
		this.insuranceDate = insuranceDate;
	}
	public float getEmployeeSocialInsurance() {
		return employeeSocialInsurance;
	}
	public void setEmployeeSocialInsurance(float employeeSocialInsurance) {
		this.employeeSocialInsurance = employeeSocialInsurance;
	}
	public float getEmployeeMedicalInsurance() {
		return employeeMedicalInsurance;
	}
	public void setEmployeeMedicalInsurance(float employeeMedicalInsurance) {
		this.employeeMedicalInsurance = employeeMedicalInsurance;
	}
	public float getEmployeeUnemployedInsurance() {
		return employeeUnemployedInsurance;
	}
	public void setEmployeeUnemployedInsurance(float employeeUnemployedInsurance) {
		this.employeeUnemployedInsurance = employeeUnemployedInsurance;
	}
	public float getEmployeeUnionFee() {
		return employeeUnionFee;
	}
	public void setEmployeeUnionFee(float employeeUnionFee) {
		this.employeeUnionFee = employeeUnionFee;
	}
	public float getBusinessSocialInsurance() {
		return businessSocialInsurance;
	}
	public void setBusinessSocialInsurance(float businessSocialInsurance) {
		this.businessSocialInsurance = businessSocialInsurance;
	}
	public float getBusinessMedicalInsurance() {
		return businessMedicalInsurance;
	}
	public void setBusinessMedicalInsurance(float businessMedicalInsurance) {
		this.businessMedicalInsurance = businessMedicalInsurance;
	}
	public float getBusinessUnemployedInsurance() {
		return businessUnemployedInsurance;
	}
	public void setBusinessUnemployedInsurance(float businessUnemployedInsurance) {
		this.businessUnemployedInsurance = businessUnemployedInsurance;
	}
	public float getBusinessUnionFee() {
		return businessUnionFee;
	}
	public void setBusinessUnionFee(float businessUnionFee) {
		this.businessUnionFee = businessUnionFee;
	}
	
	
}
