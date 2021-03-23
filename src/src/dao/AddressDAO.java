package src.dao;

import java.sql.PreparedStatement;

import src.dao.DAO;

public class AddressDAO extends DAO {
     public AddressDAO() {
        super();
        // TODO Auto-generated constructor stub
    }
     
    public void luudiachi(String chitiet,int phuongid){
        String query = "INSERT INTO `social_insurance`.`diachi` (`chitiet`, `phuongid`) VALUES (?,?);";
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) conn.prepareStatement(query);
            ps.setString(1,chitiet);
            ps.setInt(2,phuongid);
            ps.executeUpdate();
             
        } catch (Exception e) {
        }
    }
}
