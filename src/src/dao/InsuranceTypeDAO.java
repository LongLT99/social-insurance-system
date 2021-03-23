package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import src.model.InsuranceType;

public class InsuranceTypeDAO extends DAO{

	public InsuranceTypeDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList <InsuranceType> getInsuranceType() {
		ArrayList <InsuranceType> result = new ArrayList <InsuranceType>();
		
		String sql = "SELECT * FROM loaibaohiem";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				InsuranceType insuranceType = new InsuranceType();
				insuranceType.setId(rs.getInt("id"));
				insuranceType.setName(rs.getString("ten"));
				result.add(insuranceType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

}
