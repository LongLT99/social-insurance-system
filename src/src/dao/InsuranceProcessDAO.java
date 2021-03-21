package src.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import src.model.BusinessUnit;
import src.model.InsuraceProcess;
import src.model.Labour;
import src.model.LabourChangeType;

public class InsuranceProcessDAO extends DAO {
	
	/**
	 * Get quá trình tham gia BHXH của người lao động ở một doanh nghiệp
	 * @param businessUnit: Đơn vị doanh nghiệp tham gia BHXH
	 * @param labour: Người lao động
	 * @return Các quá trình BHXH của người lao động
	 */
	public ArrayList<InsuraceProcess> getInsuranceProcess(BusinessUnit businessUnit, Labour labour) {
		ArrayList<InsuraceProcess> insuranceProcesses = new ArrayList<InsuraceProcess>();
		String sql = "SELECT btg.*, ltg.name, ltg.id as ltg_id FROM baotanggiam btg "
				+ "INNER JOIN loaitanggiam ltg ON ltg.id = btg.loaitanggiamid "
				+ "WHERE btg.laodongid = ? AND btg.DonVibhId = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, labour.getId());
			ps.setInt(2, businessUnit.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				InsuraceProcess insuranceProcess = new InsuraceProcess();
				LabourChangeType type = new LabourChangeType();
				insuranceProcess.setBusinessUnit(businessUnit);
				insuranceProcess.setLabour(labour);
				insuranceProcess.setStartTime(rs.getDate("thoigianbatdau"));
				insuranceProcess.setEndTime(rs.getDate("thoigianketthuc"));
				insuranceProcess.setDivision(rs.getString("phongban"));
				insuranceProcess.setPosition(rs.getString("chucdanh"));
				type.setName(rs.getString("name"));
				type.setId(rs.getInt("ltg_id"));
				insuranceProcess.setIncreaseType(type);
				insuranceProcesses.add(insuranceProcess);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insuranceProcesses;
	}
	
	/**
	 * Thêm quá trình tham gia BHXH mới
	 * @param insuranceProcess
	 * @return true nếu thêm thành công
	 */
	public boolean addInsuranceProcess(InsuraceProcess insuranceProcess) {
		String sql = "INSERT INTO baotanggiam(mahdld, thoigianbatdau, thoigianketthuc,"
				+ "luongBH, chucdanh, phongban, donvibhid, laodongid, loaitangid) "
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, insuranceProcess.getContractCode());
			ps.setDate(2, (Date) insuranceProcess.getStartTime());
			ps.setDate(3, (Date) insuranceProcess.getEndTime());
			ps.setFloat(4, insuranceProcess.getInsuranceSalary());
			ps.setString(5, insuranceProcess.getPosition());
			ps.setString(6, insuranceProcess.getDivision());
			ps.setInt(7, insuranceProcess.getBusinessUnit().getId());
			ps.setInt(8, insuranceProcess.getLabour().getId());
			ps.setInt(9, insuranceProcess.getIncreaseType().getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
