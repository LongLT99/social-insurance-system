package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import src.dao.PhuongDAO;
import src.model.Ward;

class TestPhuongDAO {
	PhuongDAO phuongDAO = new PhuongDAO();
	@Test
	void testGetPhuong() {
		ArrayList<Ward> listPhuong = phuongDAO.getPhuong();
		Assert.assertNotNull(listPhuong);
		return;
	}

	@Test
	void testGetPhuongByQuan() {
		int quanID = 1 ;
		ArrayList<Ward> listPhuongbyQuan = phuongDAO.getPhuongByQuan(quanID);
		Assert.assertNotNull(listPhuongbyQuan);
	}

}
