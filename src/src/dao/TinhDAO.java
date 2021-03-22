package src.dao;


import static src.dao.DAO.conn;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import src.model.Province;

public class TinhDAO  extends DAO{

    public TinhDAO() {
        super();
    }
    public ArrayList<Province> getTinh(){
        ArrayList<Province> kq=null;
        String sql = "SELECT * FROM social_insurance.tinh";
        try{
            CallableStatement cs = conn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
             
            while(rs.next()){
                if(kq == null) kq = new ArrayList<Province>();
                Province t = new Province();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("ten"));
             
                kq.add(t);
            }
        }catch(Exception e){
            e.printStackTrace();
            kq = null;
        }   
        return kq;
    }
}
