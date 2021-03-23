package src.dao;

import static src.dao.DAO.conn;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import src.model.Ward;
public class PhuongDAO extends DAO{
    public PhuongDAO(){
        super();
    }
    
    public ArrayList<Ward> getPhuong(){
        ArrayList<Ward> kq=null;
        String sql = "SELECT * FROM social_insurance.phuong";
        try{
            CallableStatement cs = conn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
             
            while(rs.next()){
                if(kq == null) kq = new ArrayList<Ward>();
                Ward p = new Ward();
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
    
    public ArrayList<Ward> getPhuongByQuan(int quanId){
        ArrayList<Ward> kq=null;
        String sql = "SELECT * FROM social_insurance.phuong where quanid = ?";
        try{
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, quanId);
            ResultSet rs = cs.executeQuery();
            while(rs.next()){
                if(kq == null) kq = new ArrayList<Ward>();
                Ward p = new Ward();
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
