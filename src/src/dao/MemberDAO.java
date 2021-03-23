package src.dao;

import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import src.model.Member;

public class MemberDAO extends DAO {

	public MemberDAO() {
		super();
	}

	public boolean checkLogin(Member member) {
		boolean result = false;
		if (member.getUsername().contains("true") || member.getUsername().contains("=")
				|| member.getPassword().contains("true") || member.getPassword().contains("="))
			return false;
		String sql = "SELECT * FROM Thanhvien WHERE USERNAME = ? AND PASSWORD = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, member.getUsername());
			ps.setString(2, member.getPassword());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				member.setId(rs.getInt("id"));
				member.setRole(rs.getString("role"));
				member.setPhoneNumber(rs.getString("sdt"));
				member.setEmail(rs.getString("email"));
				result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public void luuthanhvien(String username , String password ,String email ,String sdt){
        String query = "INSERT INTO `social_insurance`.`thanhvien` (`username`, `password`, `email`, `sdt`, `role`) VALUES (?,?,?,?,'business');";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,email);
            ps.setString(4,sdt);
            ps.executeUpdate();
             
        } catch (Exception e) {
        }
    }
}
