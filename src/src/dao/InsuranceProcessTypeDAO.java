package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import src.model.InsuranceProcessType;

public class InsuranceProcessTypeDAO extends DAO {
	
	public ArrayList<InsuranceProcessType> getTypes() {
		ArrayList<InsuranceProcessType> types = new ArrayList<InsuranceProcessType>();
		String sql = "SELECT * FROM loaitanggiam";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				InsuranceProcessType type = new InsuranceProcessType();
				type.setId(rs.getInt("id"));
				type.setName(rs.getString("tenloaitang"));
				type.setType(rs.getInt("loai"));
				type.setMaxNumberOfDays(rs.getInt("songaytoidanghi"));
				types.add(type);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return types;
	}
}
