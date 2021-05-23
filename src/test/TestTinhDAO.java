package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import src.dao.TinhDAO;
import src.model.Province;

class TestTinhDAO {
	TinhDAO tinhDAO = new TinhDAO();
	@Test
	void testGetTinh() {
		ArrayList<Province> listTinh = tinhDAO.getTinh();
		Assert.assertNotNull(listTinh);
	}

}
