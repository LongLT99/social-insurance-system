package src.dao;

import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;


import src.model.RealTimePayment;

public class RealTimePaymentDAO extends DAO{

	public RealTimePaymentDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//confirm payment
	//	Hàm nhập các thông tin đóng bào hiểm 
	//	kết quả tạo ra bản ghi mới
	public boolean ConfirmPayment(RealTimePayment rtPay, String realtime, String insuranceMonth) {
		boolean result = false;
		String sql = "INSERT INTO thoigiandongthucte (thoigiandong, phuongthuc, sotien, tennganhang, magiaodich, noidunggd, Nhanvienid, Loaibaohiemid, DonViBHId, handong ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, realtime);
			ps.setString(2, rtPay.getPhuongthuc());
			ps.setFloat(3, rtPay.getSotien());
			ps.setString(4, rtPay.getTennganhang());
			ps.setString(5, rtPay.getMagiaodich());
			ps.setString(6, rtPay.getNoidunggd());
			ps.setInt(7, rtPay.getNhanvienid());
			ps.setInt(8, rtPay.getLoaibhid());
			ps.setInt(9, rtPay.getIddonvibh());
			ps.setString(10, insuranceMonth+"-01");
			
			ps.executeUpdate();

			result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		return result;
	}
	
	//Check confirm payment
	public boolean CheckPayment(int idDV, String insuranceMonth,int idit) {
		boolean result = false;
		String sql = "SELECT * FROM thoigiandongthucte WHERE DonViBHId = ? AND handong = ? AND Loaibaohiemid = ?";
		try {
    		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
    		ps.setInt(1, idDV);
    		ps.setString(2, insuranceMonth+"-01");
    		ps.setInt(3, idit);
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()) {
    			return result;
    		}
    		result= true;    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		return result;
    	}
		System.out.println(result);
		return result;
	}
	


}
