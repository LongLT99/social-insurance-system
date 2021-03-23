package src.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import src.dao.DAO;
import src.model.InsuranceCompany;
public class InsuranceCompanyDAO extends DAO{
    public InsuranceCompanyDAO(){
        super();
    }
    
    public ArrayList<InsuranceCompany> getCoquanbh(){
        ArrayList<InsuranceCompany> kq=null;
        String sql = "select * from coquanbh";
        try{
            CallableStatement cs = conn.prepareCall(sql);
            ResultSet rs = cs.executeQuery();
             
            while(rs.next()){
                if(kq == null) kq = new ArrayList<InsuranceCompany>();
                InsuranceCompany cq = new InsuranceCompany();
                cq.setId(rs.getInt("id"));
                cq.setName(rs.getString("ten"));
                cq.setPhoneNumber(rs.getString("sdt"));
                cq.setEmail(rs.getString("email"));
               
                kq.add(cq);
            }
        }catch(Exception e){
            e.printStackTrace();
            kq = null;
        }   
        return kq;
    }
    
}
