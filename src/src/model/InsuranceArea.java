package src.model;

public class InsuranceArea {
	private int id;
	private InsuranceType insuranceType;
	private float proportion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public InsuranceType getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(InsuranceType insuranceType) {
		this.insuranceType = insuranceType;
	}

	public float getProportion() {
		return proportion;
	}

	public void setProportion(float proportion) {
		this.proportion = proportion;
	}

}
