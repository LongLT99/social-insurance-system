package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.mysql.jdbc.PreparedStatement;

import src.dao.DAO;
import src.dao.InsuranceTypeDAO;
import src.model.InsuranceType;

class TestInsuranceTypeDAO  extends DAO{

	@Test
	void testgetInsuranceType() {
		ArrayList <InsuranceType> actual = new ArrayList <InsuranceType>();
		ArrayList <InsuranceType> expected = new ArrayList <InsuranceType>();
		InsuranceTypeDAO dao = new InsuranceTypeDAO ();
		actual = dao.getInsuranceType();
		
		String sql = "SELECT * FROM loaibaohiem";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				InsuranceType insuranceType = new InsuranceType();
				insuranceType.setId(rs.getInt("id"));
				insuranceType.setName(rs.getString("ten"));
				expected.add(insuranceType);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected.size(), actual.size());
		
	}
	

}
