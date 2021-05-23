package src.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import src.model.Member;
import src.model.Ward;

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
	
	public boolean luuthanhvien(Member member){
	
        String query = "INSERT INTO `social_insurance`.`thanhvien` (`username`, `password`, `email`, `sdt`, `role`) VALUES (?,?,?,?,'business');";
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1,member.getUsername());
            ps.setString(2,member.getPassword());
            ps.setString(3,member.getEmail());
            ps.setString(4,member.getPhoneNumber());
            ps.executeUpdate();
             
        } catch (SQLException e) {
        	e.printStackTrace();
			return false;
        }
        return true;
    }
	public ArrayList<Member> getAllMember(){
        ArrayList<Member> result = new ArrayList<Member>();
        String sql = "SELECT * FROM social_insurance.thanhvien";
        try{
            CallableStatement cs = conn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
             
            while(rs.next()){
//                if(kq == null) kq = new ArrayList<Member>();
                Member m = new Member();
                m.setId(rs.getInt("id"));
                m.setUsername(rs.getString("username"));
                m.setPassword(rs.getString("password"));
                m.setEmail(rs.getString("email"));
                m.setPhoneNumber(rs.getString("phoneNumber"));
                m.setRole(rs.getString("role"));    
                result.add(m);
            }
        }catch(Exception e){
            e.printStackTrace();
//            kq = null;
        }   
        return result;
    }
	public int getIdThanhVien(String username , String password) {
		String sql = "SELECT * FROM Thanhvien WHERE USERNAME = ? AND PASSWORD = ?";
		int i=0;
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				i= rs.getInt("id");
			}
			
			}catch(Exception e) {
				return i;
			}
		return i;
}
	public Member getMemberByID(int key) {
		Member member = null;
		String sql = "SELECT * FROM social_insurance WHERE id=?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setId(rs.getInt("id"));
                member.setUsername(rs.getString("username"));
                member.setPassword(rs.getString("password"));
                member.setEmail(rs.getString("email"));
                member.setPhoneNumber(rs.getString("phoneNumber"));
                member.setRole(rs.getString("role")); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
}