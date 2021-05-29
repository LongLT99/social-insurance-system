package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;

import src.dao.BusinessInsuranceInfoDAO;
import src.dao.DAO;
import src.model.BusinessInsuranceInfo;
import src.model.BusinessUnit;

public class BusinessInsuranceInfoDAOTests extends DAO {
	private BusinessInsuranceInfoDAO infoDao;

	@Before
	public void setUp() {
		infoDao = new BusinessInsuranceInfoDAO();
	}

	@Test
	public void testInvalidBusinessUnit() {
		BusinessUnit unit = null;
		String insuranceMonth = "2021-03";
		BusinessInsuranceInfo info = infoDao.getInsuranceInfo(unit, insuranceMonth);
		assertNotNull(info);
	}
	
	@Test
	public void testInvalidInsuranceMonth() {
		BusinessUnit unit = new BusinessUnit();
		unit.setId(1);
		unit.setUsername("tnhhA");
		String insuranceMonth = "";
		BusinessInsuranceInfo info = infoDao.getInsuranceInfo(unit, insuranceMonth);
		assertNotNull(info);
	}
	
	@Test
	public void testNullInsuranceMonth() {
		BusinessUnit unit = new BusinessUnit();
		unit.setId(1);
		unit.setUsername("tnhhA");
		String insuranceMonth = null;
		BusinessInsuranceInfo info = infoDao.getInsuranceInfo(unit, insuranceMonth);
		assertNotNull(info);
	}
	
	/**
	 * Test phi dong bao hiem xa hoi cua doanh nghiep
	 */
	@Test
	public void testBusinessSocialInsuranceFee() {
		BusinessUnit unit = new BusinessUnit();
		unit.setId(1);
		String insuranceMonth = "2021-03";
		BusinessInsuranceInfo info = infoDao.getInsuranceInfo(unit, insuranceMonth);
		
		String sql = "SELECT\r\n" + 
				"	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH END) AS bhxh_doanh_nghiep_dong\r\n" + 
				"FROM laodong ld\r\n" + 
				"INNER JOIN baotangggiam bt ON bt.laodongid = ld.id AND bt.thogianbatdau IN (SELECT MIN(bt.thogianbatdau) FROM baotangggiam bt WHERE IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n" + 
				"INNER JOIN loaitanggiam ltg ON ltg.id = bt.Loaitangid\r\n" + 
				"INNER JOIN (SELECT bt.*, ld.maBHXH, ld.DonviBHId FROM baotangggiam bt INNER JOIN laodong ld ON ld.id = bt.laodongid) bt_yt ON bt_yt.maBHXH = ld.maBHXH \r\n" + 
				"	AND bt_yt.luongBH IN (SELECT MAX(bt_yt1.luongBH) FROM baotangggiam bt_yt1 WHERE DATE(CONCAT(?, '-01')) BETWEEN bt_yt1.thogianbatdau AND IFNULL(bt_yt1.thoigianketthuc, DATE(CONCAT(?, '-01'))) AND bt_yt1.laodongid = ld.id)\r\n" + 
				"INNER JOIN donvibh dvbh ON dvbh.id = ld.donvibhid\r\n" + 
				"INNER JOIN nguoidongbhxh ndbh ON ndbh.Laodongid = ld.id\r\n" + 
				"INNER JOIN loaibaohiem lbh ON lbh.id = ndbh.Loaibaohiemid\r\n" + 
				"INNER JOIN phantrambh ptbh ON ptbh.Loaibaohiemid = lbh.id\r\n" + 
				"INNER JOIN coquanbh cqbh ON cqbh.id = dvbh.CoquanBHid\r\n" + 
				"INNER JOIN quan q ON q.id = cqbh.Quanid\r\n" + 
				"INNER JOIN khuvuc kv ON kv.id = q.Khuvucid\r\n" + 
				"INNER JOIN luongminmax ltd ON ltd.LoaibaohiemId = lbh.id AND ltd.Khuvucid = kv.id\r\n" + 
				"WHERE ltg.loai = 1\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt_yt.thogianbatdau AND IFNULL(bt_yt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt.thogianbatdau AND IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATEDIFF(IFNULL(bt.thoigianketthuc, LAST_DAY(DATE(CONCAT(?, '-01')))), DATE(CONCAT(?, '-01'))) >= IFNULL(ltg.songaytoidanghi, 0)\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN ltd.thoigianbatdau AND IFNULL(ltd.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND bt.luongBH <= ltd.luongtoida\r\n" + 
				"AND bt.luongBH >= ltd.luongmin\r\n" + 
				"AND dvbh.id = ?\r\n" + 
				"GROUP BY dvbh.id";
		
		PreparedStatement ps;
		Float result = 0.0F;
		Float socialInsurance = 0.0F;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			for(int i = 1; i <= 12; i++) {
				ps.setString(i, insuranceMonth);
			}
			ps.setInt(13, unit.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getFloat("bhxh_doanh_nghiep_dong");
			}
			sql = "SELECT * FROM LOAIBAOHIEM lbh\r\n" + 
					"INNER JOIN phantrambh pt ON pt.Loaibaohiemid = lbh.id\r\n" + 
					"WHERE DATE(CONCAT(?,'-01')) AND pt.thoigianbatdau AND IFNULL(pt.thoigianketthuc, DATE(CONCAT(?,'-01')))\r\n" + 
					"AND lbh.id = 1";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, insuranceMonth);
			ps.setString(2, insuranceMonth);
			rs = ps.executeQuery();
			if(rs.next()) {
				socialInsurance = rs.getFloat("tyledndong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(info.getBusinessSocialInsurance(),result*socialInsurance, 0.001);
	}
	
	/**
	 * Test phi dong bao hiem xa hoi trich luong cua nguoi lao dong
	 */
	@Test
	public void testLabourSocialInsuranceFee() {
		BusinessUnit unit = new BusinessUnit();
		unit.setId(1);
		String insuranceMonth = "2021-03";
		BusinessInsuranceInfo info = infoDao.getInsuranceInfo(unit, insuranceMonth);
		
		String sql = "SELECT\r\n" + 
				"	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH END) AS bhxh_doanh_nghiep_dong\r\n" + 
				"FROM laodong ld\r\n" + 
				"INNER JOIN baotangggiam bt ON bt.laodongid = ld.id AND bt.thogianbatdau IN (SELECT MIN(bt.thogianbatdau) FROM baotangggiam bt WHERE IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n" + 
				"INNER JOIN loaitanggiam ltg ON ltg.id = bt.Loaitangid\r\n" + 
				"INNER JOIN (SELECT bt.*, ld.maBHXH, ld.DonviBHId FROM baotangggiam bt INNER JOIN laodong ld ON ld.id = bt.laodongid) bt_yt ON bt_yt.maBHXH = ld.maBHXH \r\n" + 
				"	AND bt_yt.luongBH IN (SELECT MAX(bt_yt1.luongBH) FROM baotangggiam bt_yt1 WHERE DATE(CONCAT(?, '-01')) BETWEEN bt_yt1.thogianbatdau AND IFNULL(bt_yt1.thoigianketthuc, DATE(CONCAT(?, '-01'))) AND bt_yt1.laodongid = ld.id)\r\n" + 
				"INNER JOIN donvibh dvbh ON dvbh.id = ld.donvibhid\r\n" + 
				"INNER JOIN nguoidongbhxh ndbh ON ndbh.Laodongid = ld.id\r\n" + 
				"INNER JOIN loaibaohiem lbh ON lbh.id = ndbh.Loaibaohiemid\r\n" + 
				"INNER JOIN phantrambh ptbh ON ptbh.Loaibaohiemid = lbh.id\r\n" + 
				"INNER JOIN coquanbh cqbh ON cqbh.id = dvbh.CoquanBHid\r\n" + 
				"INNER JOIN quan q ON q.id = cqbh.Quanid\r\n" + 
				"INNER JOIN khuvuc kv ON kv.id = q.Khuvucid\r\n" + 
				"INNER JOIN luongminmax ltd ON ltd.LoaibaohiemId = lbh.id AND ltd.Khuvucid = kv.id\r\n" + 
				"WHERE ltg.loai = 1\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt_yt.thogianbatdau AND IFNULL(bt_yt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt.thogianbatdau AND IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATEDIFF(IFNULL(bt.thoigianketthuc, LAST_DAY(DATE(CONCAT(?, '-01')))), DATE(CONCAT(?, '-01'))) >= IFNULL(ltg.songaytoidanghi, 0)\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN ltd.thoigianbatdau AND IFNULL(ltd.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND bt.luongBH <= ltd.luongtoida\r\n" + 
				"AND bt.luongBH >= ltd.luongmin\r\n" + 
				"AND dvbh.id = ?\r\n" + 
				"GROUP BY dvbh.id";
		
		PreparedStatement ps;
		Float result = 0.0F;
		Float socialInsurance = 0.0F;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			for(int i = 1; i <= 12; i++) {
				ps.setString(i, insuranceMonth);
			}
			ps.setInt(13, unit.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getFloat("bhxh_doanh_nghiep_dong");
			}
			sql = "SELECT * FROM LOAIBAOHIEM lbh\r\n" + 
					"INNER JOIN phantrambh pt ON pt.Loaibaohiemid = lbh.id\r\n" + 
					"WHERE DATE(CONCAT(?,'-01')) AND pt.thoigianbatdau AND IFNULL(pt.thoigianketthuc, DATE(CONCAT(?,'-01')))\r\n" + 
					"AND lbh.id = 1";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, insuranceMonth);
			ps.setString(2, insuranceMonth);
			rs = ps.executeQuery();
			if(rs.next()) {
				socialInsurance = rs.getFloat("tylenlddong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(info.getEmployeeSocialInsurance(),result*socialInsurance, 0.001);
	}
	
	/**
	 * Test phi dong bao hiem y te cua doanh nghiep
	 */
	@Test
	public void testBusinessHealthInsuranceFee() {
		BusinessUnit unit = new BusinessUnit();
		unit.setId(1);
		String insuranceMonth = "2021-03";
		BusinessInsuranceInfo info = infoDao.getInsuranceInfo(unit, insuranceMonth);
		
		String sql = "SELECT\r\n" + 
				"	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH END) AS bhxh_doanh_nghiep_dong\r\n" + 
				"FROM laodong ld\r\n" + 
				"INNER JOIN baotangggiam bt ON bt.laodongid = ld.id AND bt.thogianbatdau IN (SELECT MIN(bt.thogianbatdau) FROM baotangggiam bt WHERE IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n" + 
				"INNER JOIN loaitanggiam ltg ON ltg.id = bt.Loaitangid\r\n" + 
				"INNER JOIN (SELECT bt.*, ld.maBHXH, ld.DonviBHId FROM baotangggiam bt INNER JOIN laodong ld ON ld.id = bt.laodongid) bt_yt ON bt_yt.maBHXH = ld.maBHXH \r\n" + 
				"	AND bt_yt.luongBH IN (SELECT MAX(bt_yt1.luongBH) FROM baotangggiam bt_yt1 WHERE DATE(CONCAT(?, '-01')) BETWEEN bt_yt1.thogianbatdau AND IFNULL(bt_yt1.thoigianketthuc, DATE(CONCAT(?, '-01'))) AND bt_yt1.laodongid = ld.id)\r\n" + 
				"INNER JOIN donvibh dvbh ON dvbh.id = ld.donvibhid\r\n" + 
				"INNER JOIN nguoidongbhxh ndbh ON ndbh.Laodongid = ld.id\r\n" + 
				"INNER JOIN loaibaohiem lbh ON lbh.id = ndbh.Loaibaohiemid\r\n" + 
				"INNER JOIN phantrambh ptbh ON ptbh.Loaibaohiemid = lbh.id\r\n" + 
				"INNER JOIN coquanbh cqbh ON cqbh.id = dvbh.CoquanBHid\r\n" + 
				"INNER JOIN quan q ON q.id = cqbh.Quanid\r\n" + 
				"INNER JOIN khuvuc kv ON kv.id = q.Khuvucid\r\n" + 
				"INNER JOIN luongminmax ltd ON ltd.LoaibaohiemId = lbh.id AND ltd.Khuvucid = kv.id\r\n" + 
				"WHERE ltg.loai = 1\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt_yt.thogianbatdau AND IFNULL(bt_yt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt.thogianbatdau AND IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATEDIFF(IFNULL(bt.thoigianketthuc, LAST_DAY(DATE(CONCAT(?, '-01')))), DATE(CONCAT(?, '-01'))) >= IFNULL(ltg.songaytoidanghi, 0)\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN ltd.thoigianbatdau AND IFNULL(ltd.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND bt.luongBH <= ltd.luongtoida\r\n" + 
				"AND bt.luongBH >= ltd.luongmin\r\n" + 
				"AND dvbh.id = ?\r\n" + 
				"GROUP BY dvbh.id";
		
		PreparedStatement ps;
		Float result = 0.0F;
		Float healthInsurance = 0.0F;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			for(int i = 1; i <= 12; i++) {
				ps.setString(i, insuranceMonth);
			}
			ps.setInt(13, unit.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getFloat("bhxh_doanh_nghiep_dong");
			}
			sql = "SELECT * FROM LOAIBAOHIEM lbh\r\n" + 
					"INNER JOIN phantrambh pt ON pt.Loaibaohiemid = lbh.id\r\n" + 
					"WHERE DATE(CONCAT(?,'-01')) AND pt.thoigianbatdau AND IFNULL(pt.thoigianketthuc, DATE(CONCAT(?,'-01')))\r\n" + 
					"AND lbh.id = 2";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, insuranceMonth);
			ps.setString(2, insuranceMonth);
			rs = ps.executeQuery();
			if(rs.next()) {
				healthInsurance = rs.getFloat("tyledndong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(info.getBusinessMedicalInsurance(),result*healthInsurance, 0.001);
	}
	
	/**
	 * Test phi dong bao hiem y te trich luong cua nguoi lao dong
	 */
	@Test
	public void testLabourHealthInsuranceFee() {
		BusinessUnit unit = new BusinessUnit();
		unit.setId(1);
		String insuranceMonth = "2021-03";
		BusinessInsuranceInfo info = infoDao.getInsuranceInfo(unit, insuranceMonth);
		
		String sql = "SELECT\r\n" + 
				"	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH END) AS bhxh_doanh_nghiep_dong\r\n" + 
				"FROM laodong ld\r\n" + 
				"INNER JOIN baotangggiam bt ON bt.laodongid = ld.id AND bt.thogianbatdau IN (SELECT MIN(bt.thogianbatdau) FROM baotangggiam bt WHERE IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n" + 
				"INNER JOIN loaitanggiam ltg ON ltg.id = bt.Loaitangid\r\n" + 
				"INNER JOIN (SELECT bt.*, ld.maBHXH, ld.DonviBHId FROM baotangggiam bt INNER JOIN laodong ld ON ld.id = bt.laodongid) bt_yt ON bt_yt.maBHXH = ld.maBHXH \r\n" + 
				"	AND bt_yt.luongBH IN (SELECT MAX(bt_yt1.luongBH) FROM baotangggiam bt_yt1 WHERE DATE(CONCAT(?, '-01')) BETWEEN bt_yt1.thogianbatdau AND IFNULL(bt_yt1.thoigianketthuc, DATE(CONCAT(?, '-01'))) AND bt_yt1.laodongid = ld.id)\r\n" + 
				"INNER JOIN donvibh dvbh ON dvbh.id = ld.donvibhid\r\n" + 
				"INNER JOIN nguoidongbhxh ndbh ON ndbh.Laodongid = ld.id\r\n" + 
				"INNER JOIN loaibaohiem lbh ON lbh.id = ndbh.Loaibaohiemid\r\n" + 
				"INNER JOIN phantrambh ptbh ON ptbh.Loaibaohiemid = lbh.id\r\n" + 
				"INNER JOIN coquanbh cqbh ON cqbh.id = dvbh.CoquanBHid\r\n" + 
				"INNER JOIN quan q ON q.id = cqbh.Quanid\r\n" + 
				"INNER JOIN khuvuc kv ON kv.id = q.Khuvucid\r\n" + 
				"INNER JOIN luongminmax ltd ON ltd.LoaibaohiemId = lbh.id AND ltd.Khuvucid = kv.id\r\n" + 
				"WHERE ltg.loai = 1\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt_yt.thogianbatdau AND IFNULL(bt_yt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt.thogianbatdau AND IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATEDIFF(IFNULL(bt.thoigianketthuc, LAST_DAY(DATE(CONCAT(?, '-01')))), DATE(CONCAT(?, '-01'))) >= IFNULL(ltg.songaytoidanghi, 0)\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN ltd.thoigianbatdau AND IFNULL(ltd.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND bt.luongBH <= ltd.luongtoida\r\n" + 
				"AND bt.luongBH >= ltd.luongmin\r\n" + 
				"AND dvbh.id = ?\r\n" + 
				"GROUP BY dvbh.id";
		
		PreparedStatement ps;
		Float result = 0.0F;
		Float healthInsurance = 0.0F;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			for(int i = 1; i <= 12; i++) {
				ps.setString(i, insuranceMonth);
			}
			ps.setInt(13, unit.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getFloat("bhxh_doanh_nghiep_dong");
			}
			sql = "SELECT * FROM LOAIBAOHIEM lbh\r\n" + 
					"INNER JOIN phantrambh pt ON pt.Loaibaohiemid = lbh.id\r\n" + 
					"WHERE DATE(CONCAT(?,'-01')) AND pt.thoigianbatdau AND IFNULL(pt.thoigianketthuc, DATE(CONCAT(?,'-01')))\r\n" + 
					"AND lbh.id = 2";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, insuranceMonth);
			ps.setString(2, insuranceMonth);
			rs = ps.executeQuery();
			if(rs.next()) {
				healthInsurance = rs.getFloat("tylenlddong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(info.getEmployeeMedicalInsurance(),result*healthInsurance, 0.001);
	}
	
	/**
	 * Test phi dong bao hiem that nghiep cua doanh nghiep
	 */
	@Test
	public void testBusinessUnemploymentInsuranceFee() {
		BusinessUnit unit = new BusinessUnit();
		unit.setId(1);
		String insuranceMonth = "2021-03";
		BusinessInsuranceInfo info = infoDao.getInsuranceInfo(unit, insuranceMonth);
		
		String sql = "SELECT\r\n" + 
				"	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH END) AS bhxh_doanh_nghiep_dong\r\n" + 
				"FROM laodong ld\r\n" + 
				"INNER JOIN baotangggiam bt ON bt.laodongid = ld.id AND bt.thogianbatdau IN (SELECT MIN(bt.thogianbatdau) FROM baotangggiam bt WHERE IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n" + 
				"INNER JOIN loaitanggiam ltg ON ltg.id = bt.Loaitangid\r\n" + 
				"INNER JOIN (SELECT bt.*, ld.maBHXH, ld.DonviBHId FROM baotangggiam bt INNER JOIN laodong ld ON ld.id = bt.laodongid) bt_yt ON bt_yt.maBHXH = ld.maBHXH \r\n" + 
				"	AND bt_yt.luongBH IN (SELECT MAX(bt_yt1.luongBH) FROM baotangggiam bt_yt1 WHERE DATE(CONCAT(?, '-01')) BETWEEN bt_yt1.thogianbatdau AND IFNULL(bt_yt1.thoigianketthuc, DATE(CONCAT(?, '-01'))) AND bt_yt1.laodongid = ld.id)\r\n" + 
				"INNER JOIN donvibh dvbh ON dvbh.id = ld.donvibhid\r\n" + 
				"INNER JOIN nguoidongbhxh ndbh ON ndbh.Laodongid = ld.id\r\n" + 
				"INNER JOIN loaibaohiem lbh ON lbh.id = ndbh.Loaibaohiemid\r\n" + 
				"INNER JOIN phantrambh ptbh ON ptbh.Loaibaohiemid = lbh.id\r\n" + 
				"INNER JOIN coquanbh cqbh ON cqbh.id = dvbh.CoquanBHid\r\n" + 
				"INNER JOIN quan q ON q.id = cqbh.Quanid\r\n" + 
				"INNER JOIN khuvuc kv ON kv.id = q.Khuvucid\r\n" + 
				"INNER JOIN luongminmax ltd ON ltd.LoaibaohiemId = lbh.id AND ltd.Khuvucid = kv.id\r\n" + 
				"WHERE ltg.loai = 1\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt_yt.thogianbatdau AND IFNULL(bt_yt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt.thogianbatdau AND IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATEDIFF(IFNULL(bt.thoigianketthuc, LAST_DAY(DATE(CONCAT(?, '-01')))), DATE(CONCAT(?, '-01'))) >= IFNULL(ltg.songaytoidanghi, 0)\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN ltd.thoigianbatdau AND IFNULL(ltd.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND bt.luongBH <= ltd.luongtoida\r\n" + 
				"AND bt.luongBH >= ltd.luongmin\r\n" + 
				"AND dvbh.id = ?\r\n" + 
				"GROUP BY dvbh.id";
		
		PreparedStatement ps;
		Float result = 0.0F;
		Float unemploymentInsurance = 0.0F;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			for(int i = 1; i <= 12; i++) {
				ps.setString(i, insuranceMonth);
			}
			ps.setInt(13, unit.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getFloat("bhxh_doanh_nghiep_dong");
			}
			sql = "SELECT * FROM LOAIBAOHIEM lbh\r\n" + 
					"INNER JOIN phantrambh pt ON pt.Loaibaohiemid = lbh.id\r\n" + 
					"WHERE DATE(CONCAT(?,'-01')) AND pt.thoigianbatdau AND IFNULL(pt.thoigianketthuc, DATE(CONCAT(?,'-01')))\r\n" + 
					"AND lbh.id = 3";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, insuranceMonth);
			ps.setString(2, insuranceMonth);
			rs = ps.executeQuery();
			if(rs.next()) {
				unemploymentInsurance = rs.getFloat("tyledndong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(info.getBusinessUnemployedInsurance(),result*unemploymentInsurance, 0.001);
	}
	
	/**
	 * Test phi dong bao hiem that nghiep trich luong cua nguoi lao dong
	 */
	@Test
	public void testLabourUnemploymentInsuranceFee() {
		BusinessUnit unit = new BusinessUnit();
		unit.setId(1);
		String insuranceMonth = "2021-03";
		BusinessInsuranceInfo info = infoDao.getInsuranceInfo(unit, insuranceMonth);
		
		String sql = "SELECT\r\n" + 
				"	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH END) AS bhxh_doanh_nghiep_dong\r\n" + 
				"FROM laodong ld\r\n" + 
				"INNER JOIN baotangggiam bt ON bt.laodongid = ld.id AND bt.thogianbatdau IN (SELECT MIN(bt.thogianbatdau) FROM baotangggiam bt WHERE IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n" + 
				"INNER JOIN loaitanggiam ltg ON ltg.id = bt.Loaitangid\r\n" + 
				"INNER JOIN (SELECT bt.*, ld.maBHXH, ld.DonviBHId FROM baotangggiam bt INNER JOIN laodong ld ON ld.id = bt.laodongid) bt_yt ON bt_yt.maBHXH = ld.maBHXH \r\n" + 
				"	AND bt_yt.luongBH IN (SELECT MAX(bt_yt1.luongBH) FROM baotangggiam bt_yt1 WHERE DATE(CONCAT(?, '-01')) BETWEEN bt_yt1.thogianbatdau AND IFNULL(bt_yt1.thoigianketthuc, DATE(CONCAT(?, '-01'))) AND bt_yt1.laodongid = ld.id)\r\n" + 
				"INNER JOIN donvibh dvbh ON dvbh.id = ld.donvibhid\r\n" + 
				"INNER JOIN nguoidongbhxh ndbh ON ndbh.Laodongid = ld.id\r\n" + 
				"INNER JOIN loaibaohiem lbh ON lbh.id = ndbh.Loaibaohiemid\r\n" + 
				"INNER JOIN phantrambh ptbh ON ptbh.Loaibaohiemid = lbh.id\r\n" + 
				"INNER JOIN coquanbh cqbh ON cqbh.id = dvbh.CoquanBHid\r\n" + 
				"INNER JOIN quan q ON q.id = cqbh.Quanid\r\n" + 
				"INNER JOIN khuvuc kv ON kv.id = q.Khuvucid\r\n" + 
				"INNER JOIN luongminmax ltd ON ltd.LoaibaohiemId = lbh.id AND ltd.Khuvucid = kv.id\r\n" + 
				"WHERE ltg.loai = 1\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt_yt.thogianbatdau AND IFNULL(bt_yt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN bt.thogianbatdau AND IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND DATEDIFF(IFNULL(bt.thoigianketthuc, LAST_DAY(DATE(CONCAT(?, '-01')))), DATE(CONCAT(?, '-01'))) >= IFNULL(ltg.songaytoidanghi, 0)\r\n" + 
				"AND DATE(CONCAT(?, '-01')) BETWEEN ltd.thoigianbatdau AND IFNULL(ltd.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n" + 
				"AND bt.luongBH <= ltd.luongtoida\r\n" + 
				"AND bt.luongBH >= ltd.luongmin\r\n" + 
				"AND dvbh.id = ?\r\n" + 
				"GROUP BY dvbh.id";
		
		PreparedStatement ps;
		Float result = 0.0F;
		Float unemploymentInsurance = 0.0F;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			for(int i = 1; i <= 12; i++) {
				ps.setString(i, insuranceMonth);
			}
			ps.setInt(13, unit.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getFloat("bhxh_doanh_nghiep_dong");
			}
			sql = "SELECT * FROM LOAIBAOHIEM lbh\r\n" + 
					"INNER JOIN phantrambh pt ON pt.Loaibaohiemid = lbh.id\r\n" + 
					"WHERE DATE(CONCAT(?,'-01')) AND pt.thoigianbatdau AND IFNULL(pt.thoigianketthuc, DATE(CONCAT(?,'-01')))\r\n" + 
					"AND lbh.id = 3";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, insuranceMonth);
			ps.setString(2, insuranceMonth);
			rs = ps.executeQuery();
			if(rs.next()) {
				unemploymentInsurance = rs.getFloat("tylenlddong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(info.getEmployeeUnemployedInsurance(),result*unemploymentInsurance, 0.001);
	}
}
