package src.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import src.model.BusinessUnit;
import src.model.Labour;
import src.model.LabourInsuranceInfo;

public class LabourDAO extends DAO {

	/**
	 * Hàm trả lại danh sách lao động của doanh nghiệp trong tháng và phí BHXH mà
	 * doanh nghiệp và người lao động phải đóng
	 * 
	 * @param businessId:     id của doanh nghiệp
	 * @param insuranceMonth: tháng tính BHXH, có định dạng yyyy-MM
	 * @return Danh sách lao động cùng với các loại phí BHXH phải nộp trong tháng
	 */
	public ArrayList<LabourInsuranceInfo> getInsuranceInfo(Integer businessId, String insuranceMonth) {
		ArrayList<LabourInsuranceInfo> labours = new ArrayList<LabourInsuranceInfo>();
		String sql = "SELECT ld.*, bt.luongBH, \r\n" + 
				"	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH * (ptbh.tyledndong) END) AS bhxh_doanh_nghiep_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH * (ptbh.tylenlddong) END) AS bhxh_nguoi_lao_dong_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 2 AND bt_yt.donvibhid = dvbh.id THEN bt.luongBH * (ptbh.tyledndong) END) AS bhyt_doanh_nghiep_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 2 AND bt_yt.donvibhid = dvbh.id THEN bt_yt.luongBH * (ptbh.tylenlddong) END) AS bhyt_nguoi_lao_dong_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 4 THEN bt.luongBH * (ptbh.tyledndong) END) AS kpcd_doanh_nghiep_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 4 THEN bt.luongBH * (ptbh.tylenlddong) END) AS kpcd_nguoi_lao_dong_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 3 THEN bt_yt.luongBH * (ptbh.tyledndong) END) AS bhtn_doanh_nghiep_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 3 THEN bt.luongBH * (ptbh.tylenlddong) END) AS bhtn_nguoi_lao_dong_dong\r\n" + 
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
				"GROUP BY ld.id";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			for (int i = 1; i <= 12; i++) {
				ps.setString(i, insuranceMonth);
			}
			ps.setInt(13, businessId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LabourInsuranceInfo labour = new LabourInsuranceInfo();
				labour.setId(rs.getInt("id"));
				labour.setName(rs.getString("hoten"));
				labour.setInsuranceCode(rs.getString("maBHXH"));
				labour.setPhoneNumber(rs.getString("sdt"));
				labour.setIdNumber(rs.getString("soCMND"));
				labour.setEthnic(rs.getString("dantoc"));
				labour.setInsuranceSalary(rs.getFloat("luongBH"));
				labour.setMedicalInsurance(rs.getFloat("bhyt_nguoi_lao_dong_dong"));
				labour.setSocialInsurance(rs.getFloat("bhxh_nguoi_lao_dong_dong"));
				labour.setUnemployedInsurance(rs.getFloat("bhtn_nguoi_lao_dong_dong"));
				labour.setBusinessMedicalInsurance(rs.getFloat("bhyt_doanh_nghiep_dong"));
				labour.setBusinessSocialInsurance(rs.getFloat("bhxh_doanh_nghiep_dong"));
				labour.setBusinessUnemployedInsurance(rs.getFloat("bhtn_doanh_nghiep_dong"));
				labour.setUnionFee(rs.getFloat("kpcd_nguoi_lao_dong_dong"));
				labour.setBusinessUnionFee(rs.getFloat("kpcd_doanh_nghiep_dong"));
				labours.add(labour);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return labours;
	}
	
	/**
	 * Hàm thêm lao động vào csdl (Bảng Laodong)
	 * @param labour: Người lao động cần thêm
	 * @return
	 */
	
	public boolean addLabour(Labour labour, BusinessUnit businessUnit) {
		ArrayList<Integer> insuranceType = new ArrayList<Integer>();
		insuranceType.add(1);
		insuranceType.add(2);
		insuranceType.add(3);
		if(labour.getIsUnion() == 1) {
			insuranceType.add(4);
		}
		if(labour == null || labour.getName() == null) {
			return false;
		}
		String sql = "INSERT INTO Laodong(hoten, ngaysinh, gioitinh, quoctich, dantoc, maBHXH, sdt, soCMND, mahogiadinh, thamgiaCD, DonviBHid)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, labour.getName());
			ps.setDate(2, (Date) labour.getDateOfBirth());
			ps.setInt(3, labour.getGender());
			ps.setString(4, labour.getNationality());
			ps.setString(5, labour.getEthnic());
			ps.setString(6, labour.getInsuranceCode());
			ps.setString(7, labour.getPhoneNumber());
			ps.setString(8, labour.getIdNumber());
			ps.setString(9, labour.getFamilyCode());
			ps.setInt(10, labour.getIsUnion());
			ps.setInt(11, businessUnit.getId());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				sql = "INSERT INTO nguoidongbhxh(laodongid, loaibaohiemid)"
						+ "VALUES (?,?)";
				ps = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, rs.getInt(1));
				for(Integer i : insuranceType) {
					ps.setInt(2, i);
					int rowAffected = ps.executeUpdate();
					if(rowAffected == 0) {
						conn.rollback();
						return false;
					}
				}
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		return true;
	}
	/**
	 * Tìm người lao động theo mã BHXH
	 * @param insuranceCode: mã BHXH
	 * @return người lao động
	 */
	public Labour getLabourByInsuranceCode(String insuranceCode) {
		Labour labour = new Labour();
		String sql = "SELECT * FROM Laodong WHERE maBHXH = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, insuranceCode);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				labour.setId(rs.getInt("id"));
				labour.setName(rs.getString("hoten"));
				labour.setDateOfBirth(rs.getDate("ngaysinh"));
				labour.setInsuranceCode(rs.getString("maBHXH"));
				labour.setFamilyCode(rs.getString("mahogiadinh"));
				labour.setEmail(rs.getString("email"));
				labour.setGender(rs.getInt("gioitinh"));
				labour.setNationality(rs.getString("quoctich"));
				labour.setEthnic(rs.getString("dantoc"));
				labour.setPhoneNumber(rs.getString("sdt"));
				labour.setIdNumber(rs.getString("soCMND"));
				labour.setIsUnion(rs.getInt("thamgiaCD"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return labour;
	}
	
	public ArrayList<Labour> getLaboursOfBusinessUnit(BusinessUnit businessUnit) {
		ArrayList<Labour> labours = new ArrayList<Labour>();
		String sql = "SELECT ld.*, IFNULL(btg.id, 0) AS btg_id, btg.phongban, btg.chucdanh FROM Laodong ld "
				+ "LEFT JOIN baotangggiam btg ON ld.id = btg.Laodongid "
				+ "AND CURDATE() BETWEEN btg.thogianbatdau AND IFNULL(btg.thoigianketthuc, CURDATE())"
				+ "WHERE ld.DonviBHId = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, businessUnit.getId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Labour labour = new Labour();
				Integer btgId = rs.getInt("btg_id");
				labour.setId(rs.getInt("id"));
				labour.setName(rs.getString("hoten"));
				labour.setDateOfBirth(rs.getDate("ngaysinh"));
				labour.setInsuranceCode(rs.getString("maBHXH"));
				labour.setFamilyCode(rs.getString("mahogiadinh"));
				labour.setGender(rs.getInt("gioitinh"));
				labour.setNationality(rs.getString("quoctich"));
				labour.setEthnic(rs.getString("dantoc"));
				labour.setPhoneNumber(rs.getString("sdt"));
				labour.setIdNumber(rs.getString("soCMND"));
				labour.setIsUnion(rs.getInt("thamgiaCD"));
				labour.setPosition(rs.getString("chucdanh"));
				labour.setDivision(rs.getString("phongban"));
				labour.setBusinessUnit(businessUnit);
				if(btgId == 0) {
					labour.setIsWorking(0);
				} else {
					labour.setIsWorking(1);
				}
				labours.add(labour);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return labours;
	}
}
