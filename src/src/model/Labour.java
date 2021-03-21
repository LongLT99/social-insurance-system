package src.model;

import java.util.Date;

public class Labour {
	private int id;
	/*gender: giới tính, 1 là nam, 2 là nữ*/
	private int gender;
	/*Đánh dấu người lao động có tham gia công đoàn không, 1 là có, 0 là không*/
	private int isUnion;
	
	private Date dateOfBirth;
	
	private String taxCode;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private String idNumber;
	private String insuranceCode;
	private String ethnic;
	private String nationality;
	private String position;
	private String familyCode;
	
	private Float insuranceSalary;
	
	private Float socialInsurance;
	private Float medicalInsurance;
	private Float unemployedInsurance;
	
	private Float businessSocialInsurance;
	private Float businessMedicalInsurance;
	private Float businessUnemployedInsurance;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getIsUnion() {
		return isUnion;
	}
	public void setIsUnion(int isUnion) {
		this.isUnion = isUnion;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public void setInsuranceSalary(Float insuranceSalary) {
		this.insuranceSalary = insuranceSalary;
	}
	public void setSocialInsurance(Float socialInsurance) {
		this.socialInsurance = socialInsurance;
	}
	public void setMedicalInsurance(Float medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}
	public void setUnemployedInsurance(Float unemployedInsurance) {
		this.unemployedInsurance = unemployedInsurance;
	}
	public void setBusinessSocialInsurance(Float businessSocialInsurance) {
		this.businessSocialInsurance = businessSocialInsurance;
	}
	public void setBusinessMedicalInsurance(Float businessMedicalInsurance) {
		this.businessMedicalInsurance = businessMedicalInsurance;
	}
	public void setBusinessUnemployedInsurance(Float businessUnemployedInsurance) {
		this.businessUnemployedInsurance = businessUnemployedInsurance;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getInsuranceCode() {
		return insuranceCode;
	}
	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}
	public String getEthnic() {
		return ethnic;
	}
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getFamilyCode() {
		return familyCode;
	}
	public void setFamilyCode(String familyCode) {
		this.familyCode = familyCode;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Float getInsuranceSalary() {
		return insuranceSalary;
	}
	public void setInsuranceSalary(float insuranceSalary) {
		this.insuranceSalary = insuranceSalary;
	}
	public Float getSocialInsurance() {
		return socialInsurance;
	}
	public void setSocialInsurance(float socialInsurance) {
		this.socialInsurance = socialInsurance;
	}
	public Float getMedicalInsurance() {
		return medicalInsurance;
	}
	public void setMedicalInsurance(float medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}
	public Float getUnemployedInsurance() {
		return unemployedInsurance;
	}
	public void setUnemployedInsurance(float unemployedInsurance) {
		this.unemployedInsurance = unemployedInsurance;
	}
	public Float getBusinessSocialInsurance() {
		return businessSocialInsurance;
	}
	public void setBusinessSocialInsurance(float businessSocialInsurance) {
		this.businessSocialInsurance = businessSocialInsurance;
	}
	public Float getBusinessMedicalInsurance() {
		return businessMedicalInsurance;
	}
	public void setBusinessMedicalInsurance(float businessMedicalInsurance) {
		this.businessMedicalInsurance = businessMedicalInsurance;
	}
	public Float getBusinessUnemployedInsurance() {
		return businessUnemployedInsurance;
	}
	public void setBusinessUnemployedInsurance(float businessUnemployedInsurance) {
		this.businessUnemployedInsurance = businessUnemployedInsurance;
	}
	
}
