package src.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	
	public static Connection conn;
	
	public DAO(){
        if(conn == null){
            String dbUrl = "jdbc:mysql://localhost:3307/social_insurance?autoReconnect=true&useSSL=false";
            String dbClass = "com.mysql.jdbc.Driver";
 
            try {
                Class.forName(dbClass);
                conn = DriverManager.getConnection (dbUrl, "root", "1qazXSW@");
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
