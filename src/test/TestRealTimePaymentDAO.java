package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import src.dao.DAO;

import src.dao.RealTimePaymentDAO;

import src.model.RealTimePayment;

class TestRealTimePaymentDAO {
	
	@Test
	void testConfirmPayment1() throws ParseException {
		RealTimePaymentDAO rtpDAO = new RealTimePaymentDAO();
		boolean rtPayCheck = false;
       RealTimePayment rtPay = new RealTimePayment("Chuyển khoản", 22950000, "BIDV", "43255432", "thanh toán BHYT", 2, 1, 1);
       try {
    	   DAO.conn.setAutoCommit(false);
			rtpDAO.ConfirmPayment(rtPay, "2021-04-01", "2021-04");
			Assert.assertNotNull(rtPay);
			// test hang moi duoc insert
			rtPayCheck = rtpDAO.CheckPayment(1, "2021-04", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DAO.conn.rollback();
				DAO.conn.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
       
       assertTrue(rtPayCheck);
	}
	
	@Test
	void testCheckPayment1() throws ParseException{
		RealTimePaymentDAO rtpDAO = new RealTimePaymentDAO();
		boolean check;
		String time = "2021-03";
		check = rtpDAO.CheckPayment(1, time, 1);
		assertTrue(check);
		
	}
	
	@Test
	void testCheckPayment2() throws ParseException{
		RealTimePaymentDAO rtpDAO = new RealTimePaymentDAO();
		boolean check2;
		String time2 = "2022-05";
		check2 = rtpDAO.CheckPayment(1, time2, 1);
		assertFalse(check2);
	}
	
	


}
