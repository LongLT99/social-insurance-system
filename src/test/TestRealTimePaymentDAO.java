package test;

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

	static RealTimePaymentDAO rtpDAO;
	
	@Test
	void testConfirmPayment1() throws ParseException {	
		boolean rtPayCheck = false;
       RealTimePayment rtPay = new RealTimePayment("Chuyển khoản", 22950000, "BIDV", "43255432", "thanh toán BHYT", 2, 1, 1);
       try {
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


}
