package src.dao;
 
import static src.dao.DAO.conn;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import src.model.Address;
public class DiachiDAO extends DAO {
     public DiachiDAO() {
        super();
        // TODO Auto-generated constructor stub
    }
     
    public void luudiachi(String chitiet,int phuongid){
        String query = "INSERT INTO `social_insurance`.`diachi` (`chitiet`, `phuongid`) VALUES (?,?);";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1,chitiet);
            ps.setInt(2,phuongid);
            ps.executeUpdate();
             
        } catch (Exception e) {
        }
    }
}
