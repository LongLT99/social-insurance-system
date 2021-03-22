package src.dao;

import static src.dao.DAO.conn;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import src.model.District;
public class QuanDAO extends DAO{
    public QuanDAO(){
        super();
    }
    
    public ArrayList<District> getQuan(){
        ArrayList<District> kq=null;
        String sql = "SELECT * FROM social_insurance.quan";
        try{
            CallableStatement cs = conn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
             
            while(rs.next()){
                if(kq == null) kq = new ArrayList<District>();
                District p = new District();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("ten"));
             
               
                kq.add(p);
            }
        }catch(Exception e){
            e.printStackTrace();
            kq = null;
        }   
        return kq;
    }
    
}
