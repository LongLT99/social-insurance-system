package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import src.dao.DAO;
import src.dao.LabourDAO;
import src.model.BusinessUnit;
import src.model.Labour;
import src.model.LabourInsuranceInfo;

public class LabourDAOTests extends DAO {

	static LabourDAO labourDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		labourDao = new LabourDAO();
	}

	@Test
	public void testInvalidBusinessId() {
		ArrayList<LabourInsuranceInfo> labourInfo = labourDao.getInsuranceInfo(-1, "2021-03");
		assertNotNull(labourInfo);
	}

	@Test
	public void testInvalidInsuranceMonth() {
		ArrayList<LabourInsuranceInfo> labourInfo = labourDao.getInsuranceInfo(1, "");
		assertNotNull(labourInfo);
	}

	@Test
	public void testInvalidInsuranceMonth2() {
		ArrayList<LabourInsuranceInfo> labourInfo = labourDao.getInsuranceInfo(1, "ahsndbv");
		assertNotNull(labourInfo);
	}

	@Test
	public void testInsuranceInfoMinSalary() {
		HashMap<Integer, Float> expected = new HashMap<Integer, Float>();
		ArrayList<LabourInsuranceInfo> actual = labourDao.getInsuranceInfo(1, "2021-03");
		String sql = "SELECT ld.id, ltd.luongmin \r\n" + "FROM laodong ld\r\n"
				+ "INNER JOIN baotangggiam bt ON bt.laodongid = ld.id AND bt.thogianbatdau IN (SELECT MIN(bt.thogianbatdau) FROM baotangggiam bt WHERE IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n"
				+ "INNER JOIN loaitanggiam ltg ON ltg.id = bt.Loaitangid\r\n"
				+ "INNER JOIN donvibh dvbh ON dvbh.id = ld.donvibhid\r\n"
				+ "INNER JOIN nguoidongbhxh ndbh ON ndbh.Laodongid = ld.id\r\n"
				+ "INNER JOIN loaibaohiem lbh ON lbh.id = ndbh.Loaibaohiemid\r\n"
				+ "INNER JOIN coquanbh cqbh ON cqbh.id = dvbh.CoquanBHid\r\n"
				+ "INNER JOIN quan q ON q.id = cqbh.Quanid\r\n" + "INNER JOIN khuvuc kv ON kv.id = q.Khuvucid\r\n"
				+ "INNER JOIN luongminmax ltd ON ltd.LoaibaohiemId = lbh.id AND ltd.Khuvucid = kv.id\r\n"
				+ "WHERE ltg.loai = 1\r\n"
				+ "AND DATE(CONCAT(?, '-01')) BETWEEN bt.thogianbatdau AND IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n"
				+ "AND DATEDIFF(IFNULL(bt.thoigianketthuc, LAST_DAY(DATE(CONCAT(?, '-01')))), DATE(CONCAT(?, '-01'))) >= IFNULL(ltg.songaytoidanghi, 0)\r\n"
				+ "AND DATE(CONCAT(?, '-01')) BETWEEN ltd.thoigianbatdau AND IFNULL(ltd.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n"
				+ "AND dvbh.id = ?\r\n" + "GROUP BY ld.id";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			for (int i = 1; i <= 8; i++) {
				ps.setString(i, "2021-03");
			}
			ps.setInt(9, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				expected.put(rs.getInt("id"), rs.getFloat("luongmin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean result = true;
		for (LabourInsuranceInfo labour : actual) {
			if (labour.getInsuranceSalary() <= expected.get(labour.getId())) {
				result = false;
			}
			;
		}
		assertTrue(result);

	}

	@Test
	public void testInsuranceInfoMaxSalary() {
		HashMap<Integer, Float> expected = new HashMap<Integer, Float>();
		ArrayList<LabourInsuranceInfo> actual = labourDao.getInsuranceInfo(1, "2021-03");
		String sql = "SELECT ld.id, ltd.luongtoida \r\n" + "FROM laodong ld\r\n"
				+ "INNER JOIN baotangggiam bt ON bt.laodongid = ld.id AND bt.thogianbatdau IN (SELECT MIN(bt.thogianbatdau) FROM baotangggiam bt WHERE IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n"
				+ "INNER JOIN loaitanggiam ltg ON ltg.id = bt.Loaitangid\r\n"
				+ "INNER JOIN donvibh dvbh ON dvbh.id = ld.donvibhid\r\n"
				+ "INNER JOIN nguoidongbhxh ndbh ON ndbh.Laodongid = ld.id\r\n"
				+ "INNER JOIN loaibaohiem lbh ON lbh.id = ndbh.Loaibaohiemid\r\n"
				+ "INNER JOIN coquanbh cqbh ON cqbh.id = dvbh.CoquanBHid\r\n"
				+ "INNER JOIN quan q ON q.id = cqbh.Quanid\r\n" + "INNER JOIN khuvuc kv ON kv.id = q.Khuvucid\r\n"
				+ "INNER JOIN luongminmax ltd ON ltd.LoaibaohiemId = lbh.id AND ltd.Khuvucid = kv.id\r\n"
				+ "WHERE ltg.loai = 1\r\n"
				+ "AND DATE(CONCAT(?, '-01')) BETWEEN bt.thogianbatdau AND IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n"
				+ "AND DATEDIFF(IFNULL(bt.thoigianketthuc, LAST_DAY(DATE(CONCAT(?, '-01')))), DATE(CONCAT(?, '-01'))) >= IFNULL(ltg.songaytoidanghi, 0)\r\n"
				+ "AND DATE(CONCAT(?, '-01')) BETWEEN ltd.thoigianbatdau AND IFNULL(ltd.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n"
				+ "AND dvbh.id = ?\r\n" + "GROUP BY ld.id";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			for (int i = 1; i <= 8; i++) {
				ps.setString(i, "2021-03");
			}
			ps.setInt(9, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				expected.put(rs.getInt("id"), rs.getFloat("luongtoida"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean result = true;
		for (LabourInsuranceInfo labour : actual) {
			if (labour.getInsuranceSalary() > expected.get(labour.getId())) {
				result = false;
			}
			;
		}
		assertTrue(result);

	}

	@Test
	public void testValidAddLabour() {
		int actual = 0;
		Labour labour = new Labour();
		labour.setId(0);
		labour.setAddress("123");
		labour.setDateOfBirth(new Date(0));
		labour.setName("abc");
		labour.setGender(1);
		labour.setInsuranceCode("test123");
		BusinessUnit busUnit = new BusinessUnit();
		busUnit.setId(1);
		labourDao.addLabour(labour, busUnit);

		Labour actualLabour = labourDao.getLabourByInsuranceCode("test123");
		assertEquals("abc", actualLabour.getName());
		String expectedSql = "SELECT count(*) as count FROM nguoidongbhxh WHERE LAODONGID = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(expectedSql);
			ps.setInt(1, actualLabour.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				actual = rs.getInt("count");
			}
			assertEquals(3, actual);
			String sql = "DELETE FROM nguoidongbhxh WHERE laodongid = ?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, actualLabour.getId());
			ps.executeUpdate();
			sql = "DELETE FROM laodong WHERE id = ?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, actualLabour.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testInvalidGetLaboursOfBusiness() {
		ArrayList<Labour> labours = labourDao.getLaboursOfBusinessUnit(new BusinessUnit());
		assertEquals(0, labours.size());
	}
	
	@Test
	public void testValidGetLaboursOfBusiness() {
		BusinessUnit unit = new BusinessUnit();
		unit.setId(1);
		unit.setName("tnhhA");
		ArrayList<Labour> labours = labourDao.getLaboursOfBusinessUnit(unit);
		boolean result = true;
		for(Labour labour : labours) {
			if(labour.getBusinessUnit().getId() != unit.getId()) {
				result = false;
			}
		}
		assertTrue(result);
	}

}
