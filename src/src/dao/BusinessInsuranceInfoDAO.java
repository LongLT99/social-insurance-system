package src.dao;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

import src.model.BusinessInsuranceInfo;
import src.model.BusinessUnit;

public class BusinessInsuranceInfoDAO extends DAO {
	
	public BusinessInsuranceInfo getInsuranceInfo(BusinessUnit businessUnit, String insuranceMonth) {
		BusinessInsuranceInfo insuranceInfo = new BusinessInsuranceInfo();
		System.out.println(insuranceMonth);
		String sql = "SELECT\r\n" + 
				"	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH * (ptbh.tyledndong) END) AS bhxh_doanh_nghiep_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 1 THEN bt.luongBH * (ptbh.tylenlddong) END) AS bhxh_nguoi_lao_dong_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 2 AND bt_yt.donvibhid = dvbh.id THEN bt.luongBH * (ptbh.tyledndong) END) AS bhyt_doanh_nghiep_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 2 AND bt_yt.donvibhid = dvbh.id THEN bt_yt.luongBH * (ptbh.tylenlddong) END) AS bhyt_nguoi_lao_dong_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 3 THEN bt_yt.luongBH * (ptbh.tyledndong) END) AS bhtn_doanh_nghiep_dong,\r\n" + 
				" 	SUM(CASE WHEN lbh.id = 3 THEN bt.luongBH * (ptbh.tylenlddong) END) AS bhtn_nguoi_lao_dong_dong\r\n" + 
				"FROM laodong ld\r\n" + 
				"INNER JOIN baotangggiam bt ON bt.laodongid = ld.id AND bt.thogianbatdau IN (SELECT MIN(bt.thogianbatdau) FROM baotangggiam bt WHERE IFNULL(bt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n" + 
				"INNER JOIN loaitanggiam ltg ON ltg.id = bt.Loaitangid\r\n" + 
				"INNER JOIN baotangggiam bt_yt ON bt_yt.laodongid = ld.id AND bt_yt.luongBH IN (SELECT MAX(bt_yt.luongBH) FROM baotangggiam bt_yt WHERE IFNULL(bt_yt.thoigianketthuc, DATE(CONCAT(?, '-01'))) >= DATE(CONCAT(?, '-01')))\r\n" + 
				"INNER JOIN donvibh dvbh ON dvbh.id = bt.donvibhid\r\n" + 
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
		try {
			insuranceInfo.setId(businessUnit.getId());
			insuranceInfo.setName(businessUnit.getName());
			insuranceInfo.setEmail(businessUnit.getEmail());
			insuranceInfo.setTaxCode(businessUnit.getTaxCode());
			
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			for(int i = 1; i <= 12; i++) {
				ps.setString(i, insuranceMonth);
			}
			ps.setInt(13, businessUnit.getId());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				insuranceInfo.setBusinessUnemployedInsurance(rs.getFloat("bhtn_doanh_nghiep_dong"));
				insuranceInfo.setBusinessMedicalInsurance(rs.getFloat("bhyt_doanh_nghiep_dong"));
				insuranceInfo.setBusinessSocialInsurance(rs.getFloat("bhxh_doanh_nghiep_dong"));
				insuranceInfo.setEmployeeMedicalInsurance(rs.getFloat("bhyt_nguoi_lao_dong_dong"));
				insuranceInfo.setEmployeeSocialInsurance(rs.getFloat("bhxh_nguoi_lao_dong_dong"));
				insuranceInfo.setEmployeeUnemployedInsurance(rs.getFloat("bhtn_nguoi_lao_dong_dong"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return insuranceInfo;
	}
}
