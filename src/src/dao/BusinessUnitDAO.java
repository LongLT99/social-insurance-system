package src.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import src.model.BusinessUnit;
import src.model.Member;

public class BusinessUnitDAO extends DAO {
	
	public BusinessUnit getBusinessUnit(Member member) {
		BusinessUnit busUnit = new BusinessUnit();
		String sql = "SELECT * FROM Donvibh WHERE Thanhvienid = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, member.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				busUnit.setId(rs.getInt("id"));
				System.out.println(rs.getInt("id"));
				busUnit.setName(rs.getString("ten"));
				busUnit.setTaxCode(rs.getString("masothue"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return busUnit;
	}
	
	public BusinessUnit getBusinessUnitbyId(int id) {
		BusinessUnit busUnit = new BusinessUnit();
		String sql = "SELECT * FROM Donvibh WHERE id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				busUnit.setId(rs.getInt("id"));
				busUnit.setName(rs.getString("ten"));
				busUnit.setTaxCode(rs.getString("masothue"));
				busUnit.setUnitCode(rs.getString("madonvi"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return busUnit;
	}
	
	public ArrayList<BusinessUnit> searchBusinessUnit(String BusName) {
		ArrayList<BusinessUnit> result = new ArrayList<BusinessUnit>();
		String sql = "SELECT * FROM social_insurance.donvibh where ten like Concat('%',?,'%')";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, BusName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BusinessUnit busUnit = new BusinessUnit();
				busUnit.setId(rs.getInt("id"));
				busUnit.setName(rs.getString("ten"));
				busUnit.setTaxCode(rs.getString("masothue"));
				busUnit.setUnitCode(rs.getString("madonvi"));
				result.add(busUnit);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public void luudonvibh(String madonvi , String ten , String masothue,int CoquanBHid, int idTV){
        String query = "INSERT INTO `social_insurance`.`donvibh` (`madonvi`, `ten`, `masothue`, `thanhvienid`, `diachiid`, `CoquanBHid`) VALUES (?,?,?,?, '1',?);";
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) conn.prepareStatement(query);
    
            ps.setString(1,madonvi);
            ps.setString(2,ten);
            ps.setString(3,masothue);
            ps.setInt(4,idTV);
            ps.setInt(5,CoquanBHid);
            ps.executeUpdate();
             
        } catch (Exception e) {
        }
    }
}
