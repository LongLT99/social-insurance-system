package src.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import com.mysql.jdbc.PreparedStatement;

import src.model.BusinessUnit;
import src.model.InsuranceProcess;
import src.model.InsuranceProcessType;
import src.model.Labour;

public class InsuranceProcessDAO extends DAO {

	/**
	 * Get quá trình tham gia BHXH của người lao động ở một doanh nghiệp
	 * 
	 * @param businessUnit: Đơn vị doanh nghiệp tham gia BHXH
	 * @param labour:       Người lao động
	 * @return Các quá trình BHXH của người lao động
	 */
	public ArrayList<InsuranceProcess> getInsuranceProcesses(BusinessUnit businessUnit, Labour labour) {
		ArrayList<InsuranceProcess> insuranceProcesses = new ArrayList<InsuranceProcess>();
		String sql = "SELECT btg.*, ltg.tenloaitang as name, ltg.id as ltg_id FROM baotangggiam btg "
				+ "INNER JOIN laodong ld ON ld.id = btg.laodongid "
				+ "INNER JOIN loaitanggiam ltg ON ltg.id = btg.loaitangid "
				+ "WHERE btg.laodongid = ? AND ld.DonVibhId = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, labour.getId());
			ps.setInt(2, businessUnit.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				InsuranceProcess insuranceProcess = new InsuranceProcess();
				InsuranceProcessType type = new InsuranceProcessType();
				insuranceProcess.setId(rs.getInt("id"));
				insuranceProcess.setLabour(labour);
				insuranceProcess.setContractCode(rs.getString("mahdld"));
				insuranceProcess.setInsuranceSalary(rs.getFloat("luongBH"));
				insuranceProcess.setStartTime(rs.getDate("thogianbatdau"));
				insuranceProcess.setEndTime(rs.getDate("thoigianketthuc"));
				insuranceProcess.setDivision(rs.getString("phongban"));
				insuranceProcess.setPosition(rs.getString("chucdanh"));
				type.setName(rs.getString("name"));
				type.setId(rs.getInt("ltg_id"));
				insuranceProcess.setProcessType(type);
				insuranceProcesses.add(insuranceProcess);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		insuranceProcesses.sort(new Comparator<InsuranceProcess>() {

			@Override
			public int compare(InsuranceProcess o1, InsuranceProcess o2) {
				// TODO Auto-generated method stub
				return o1.getStartTime().compareTo(o2.getStartTime());
			}
			
		});
		
		return insuranceProcesses;
	}

	/**
	 * Thêm quá trình tham gia BHXH mới
	 * 
	 * @param insuranceProcess
	 * @return true nếu thêm thành công
	 */
	public boolean addInsuranceProcess(InsuranceProcess insuranceProcess) {
		String sql = "INSERT INTO baotangggiam(mahdld, thogianbatdau, thoigianketthuc,"
				+ "luongBH, chucdanh, phongban, laodongid, loaitangid) " 
				+ "VALUES(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, insuranceProcess.getContractCode());
			ps.setDate(2, (Date) insuranceProcess.getStartTime());
			ps.setDate(3, (Date) insuranceProcess.getEndTime());
			ps.setFloat(4, insuranceProcess.getInsuranceSalary());
			ps.setString(5, insuranceProcess.getPosition());
			ps.setString(6, insuranceProcess.getDivision());
			ps.setInt(7, insuranceProcess.getLabour().getId());
			ps.setInt(8, insuranceProcess.getProcessType().getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Lay qua trinh bao hiem xa hoi gan nhat
	 * @param labour
	 * @return
	 */
	public InsuranceProcess getLatestProcess(Labour labour) {
		InsuranceProcess insuranceProcess = new InsuranceProcess();
		String sql = "SELECT btg.*, ltg.tenloaitang as name, ltg.id as ltg_id FROM baotangggiam btg "
				+ "INNER JOIN loaitanggiam ltg ON ltg.id = btg.loaitangid "
				+ "WHERE btg.laodongid = ? AND btg.Thogianbatdau IN "
				+ "(SELECT MAX(thogianbatdau) FROM baotangggiam WHERE laodongid = ?)";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, labour.getId());
			ps.setInt(2, labour.getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				InsuranceProcessType type = new InsuranceProcessType();
				insuranceProcess.setId(rs.getInt("id"));
				insuranceProcess.setLabour(labour);
				insuranceProcess.setContractCode(rs.getString("mahdld"));
				insuranceProcess.setStartTime(rs.getDate("thogianbatdau"));
				insuranceProcess.setEndTime(rs.getDate("thoigianketthuc"));
				insuranceProcess.setDivision(rs.getString("phongban"));
				insuranceProcess.setPosition(rs.getString("chucdanh"));
				insuranceProcess.setInsuranceSalary(rs.getFloat("luongBH"));
				type.setName(rs.getString("name"));
				type.setId(rs.getInt("ltg_id"));
				insuranceProcess.setProcessType(type);
				System.out.println(insuranceProcess.getEndTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insuranceProcess;
	}
	
	/**
	 * Cap nhat qua trinh bao hiem xa hoi
	 * @param insuranceProcess
	 * @return
	 */
	public boolean updateProcess(InsuranceProcess insuranceProcess) {
		String sql = "UPDATE baotangggiam SET mahdld = ?, "
				+ "thogianbatdau = ?, thoigianketthuc = ?, luongBH = ?, "
				+ "chucdanh = ?, phongban = ?, loaitangid = ? WHERE id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, insuranceProcess.getContractCode());
			ps.setDate(2, (Date) insuranceProcess.getStartTime());
			ps.setDate(3, (Date) insuranceProcess.getEndTime());
			ps.setFloat(4, insuranceProcess.getInsuranceSalary());
			ps.setString(5, insuranceProcess.getPosition());
			ps.setString(6, insuranceProcess.getDivision());
			ps.setInt(7, insuranceProcess.getProcessType().getId());
			ps.setInt(8, insuranceProcess.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
