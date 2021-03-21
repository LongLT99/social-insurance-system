package src.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import src.model.Labour;

public class LabourDAO extends DAO {

	/**
	 * Hàm trả lại danh sách lao động của doanh nghiệp trong tháng và phí BHXH mà
	 * doanh nghiệp và người lao động phải đóng
	 * 
	 * @param businessId:     id của doanh nghiệp
	 * @param insuranceMonth: tháng tính BHXH, có định dạng yyyy-MM
	 * @return Danh sách lao động cùng với các loại phí BHXH phải nộp trong tháng
	 */
	public ArrayList<Labour> getInsuranceInfo(Integer businessId, String insuranceMonth) {
		ArrayList<Labour> labours = new ArrayList<Labour>();
		String sql = "SELECT ld.*, bt.luongBH as luongBH,\r\n"
				+ "	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH * (ptbh.tyledndong) END) AS bhxh_doanh_nghiep_dong,\r\n"
				+ " 	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH * (ptbh.tylenlddong) END) AS bhxh_nguoi_lao_dong_dong,\r\n"
				+ " 	SUM(CASE WHEN lbh.id = 2 AND bt_yt.donvibhid = dvbh.id THEN bt.luongBH * (ptbh.tyledndong) END) AS bhyt_doanh_nghiep_dong,\r\n"
				+ " 	SUM(CASE WHEN lbh.id = 2 AND bt_yt.donvibhid = dvbh.id THEN bt_yt.luongBH * (ptbh.tylenlddong) END) AS bhyt_nguoi_lao_dong_dong,\r\n"
				+ " 	SUM(CASE WHEN lbh.id = 3 THEN bt_yt.luongBH * (ptbh.tyledndong) END) AS bhtn_doanh_nghiep_dong,\r\n"
				+ " 	SUM(CASE WHEN lbh.id = 3 THEN bt.luongBH * (ptbh.tylenlddong) END) AS bhtn_nguoi_lao_dong_dong\r\n"
				+ "FROM laodong ld\r\n"
				+ "INNER JOIN baotangggiam bt ON bt.laodongid = ld.id AND bt.thogianbatdau IN (SELECT MIN(bt.thogianbatdau) FROM baotangggiam bt WHERE IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n"
				+ "INNER JOIN loaitanggiam ltg ON ltg.id = bt.Loaitangid\r\n"
				+ "INNER JOIN baotangggiam bt_yt ON bt_yt.laodongid = ld.id AND bt_yt.luongBH IN (SELECT MAX(bt_yt.luongBH) FROM baotangggiam bt_yt WHERE IFNULL(bt_yt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n"
				+ "INNER JOIN donvibh dvbh ON dvbh.id = bt.donvibhid\r\n"
				+ "INNER JOIN nguoidongbhxh ndbh ON ndbh.Laodongid = ld.id\r\n"
				+ "INNER JOIN loaibaohiem lbh ON lbh.id = ndbh.Loaibaohiemid\r\n"
				+ "INNER JOIN phantrambh ptbh ON ptbh.Loaibaohiemid = lbh.id\r\n"
				+ "INNER JOIN coquanbh cqbh ON cqbh.id = dvbh.CoquanBHid\r\n"
				+ "INNER JOIN quan q ON q.id = cqbh.Quanid\r\n" + "INNER JOIN khuvuc kv ON kv.id = q.Khuvucid\r\n"
				+ "INNER JOIN luongminmax ltd ON ltd.LoaibaohiemId = lbh.id AND ltd.Khuvucid = kv.id\r\n"
				+ "WHERE ltg.loai = 1\r\n"
				+ "AND DATE(CONCAT(?, '-01')) BETWEEN bt_yt.thogianbatdau AND IFNULL(bt_yt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n"
				+ "AND DATE(CONCAT(?, '-01')) BETWEEN bt.thogianbatdau AND IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n"
				+ "AND DATEDIFF(IFNULL(bt.thoigianketthuc, LAST_DAY(DATE(CONCAT(?, '-01')))), DATE(CONCAT(?, '-01'))) >= IFNULL(ltg.songaytoidanghi, 0)\r\n"
				+ "AND DATE(CONCAT(?, '-01')) BETWEEN ltd.thoigianbatdau AND IFNULL(ltd.thoigianketthuc, DATE(CONCAT(?, '-01')))\r\n"
				+ "AND bt.luongBH <= ltd.luongtoida\r\n" + "AND bt.luongBH >= ltd.luongmin\r\n" + "AND dvbh.id = ?\r\n"
				+ "GROUP BY ld.id";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			for (int i = 1; i <= 12; i++) {
				ps.setString(i, insuranceMonth);
			}
			ps.setInt(13, businessId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Labour labour = new Labour();
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
	public boolean addLabour(Labour labour) {
		String sql = "INSERT INTO Laodong(hoten, ngaysinh, gioitinh, quoctich, dantoc, maBHXH, sdt, soCMND, mahogiadinh, thamgiaCD)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
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
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Thêm mapping người lao động và các loại BHXH vào bảng nguoidongbhxh
	 * @param labour: người lao động cần thêm
	 * @return true nếu thêm thành công
	 */
	public boolean addInsuranceParticipation(Labour labour) {
		ArrayList<Integer> insuranceType = new ArrayList<Integer>();
		insuranceType.add(1);
		insuranceType.add(2);
		insuranceType.add(3);
		if(labour.getIsUnion() == 1) {
			insuranceType.add(4);
		}
		String sql = "INSERT INTO nguoidongbhxh(laodongid, loaibaohiemid)"
				+ "VALUES (?,?)";
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, labour.getId());
			for(Integer i : insuranceType) {
				ps.setInt(2, i);
				int rowAffected = ps.executeUpdate();
				if(rowAffected == 0) {
					conn.rollback();
					return false;
				}
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
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
				labour.setName(rs.getString("hoten"));
				labour.setDateOfBirth(rs.getDate("ngaysinh"));
				labour.setInsuranceCode(rs.getString("insuranceCode"));
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
}
