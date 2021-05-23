package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import src.dao.QuanDAO;
import src.model.District;

class TestQuanDAO {

	QuanDAO quandao = new QuanDAO();
	@Test
	void testGetQuan() {
		ArrayList<District> quan = quandao.getQuan();
		Assert.assertNotNull(quan);
		return;
	}

	@Test
	void testGetQuanByTinh() {
		int tinhID = 2 ;
		ArrayList<District> quanbyTinh = quandao.getQuanByTinh(tinhID);
		Assert.assertNotNull(quanbyTinh);
		return;
		
	}

}
