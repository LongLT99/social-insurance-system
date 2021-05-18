package test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;

import src.dao.DAO;
import src.dao.InsuranceProcessDAO;
import src.model.BusinessUnit;
import src.model.InsuranceProcess;
import src.model.InsuranceProcessType;
import src.model.Labour;

public class InsuranceProcessTests extends DAO {
	
	static InsuranceProcessDAO dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new InsuranceProcessDAO();
	}

	@Test
	public void testInvalidGetInsuranceProcesses() {
		ArrayList<InsuranceProcess> processes = 
				dao.getInsuranceProcesses(new BusinessUnit(), new Labour());
		assertEquals(0, processes.size());
	}
	
	@Test
	public void testValidGetInsuranceProcesses() {
		BusinessUnit busUnit = new BusinessUnit();
		busUnit.setId(1);
		busUnit.setUsername("tnhhA");
		Labour labour = new Labour();
		labour.setId(1);
		labour.setName("Nguyễn Hoàng Khôi");
		ArrayList<InsuranceProcess> processes = 
				dao.getInsuranceProcesses(busUnit, labour);
		String sql = "SELECT * FROM baotangggiam WHERE laodongid = ?";
		PreparedStatement ps;
		int expected = 0;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, labour.getId());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				rs.last();
				expected = rs.getRow();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(expected, processes.size());
	}
	
	@Test
	public void testInvalidAddInsuranceProcess() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	boolean result = dao.addInsuranceProcess(new InsuranceProcess());
	            });
	}
	
	@Test
	public void testInvalidAddInsuranceProcess1() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	boolean result = dao.addInsuranceProcess(null);
	            });
	}
	
	@Test
	public void testValidInsuranceProcess() {
		InsuranceProcess process = new InsuranceProcess();
		Labour labour = new Labour();
		labour.setId(1);
		InsuranceProcessType type = new InsuranceProcessType();
		type.setId(1);
		process.setContractCode("test");
		process.setDivision("test");
		process.setPosition("test");
		process.setLabour(null);
		process.setInsuranceSalary(200000.0F);
		process.setLabour(labour);
		process.setProcessType(type);
		boolean result = dao.addInsuranceProcess(process);
		assertTrue(result);
		String sql = "SELECT * FROM baotangggiam WHERE chucdanh = ? and phongban = ? and contract_code = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, "test");
			ps.setString(2, "test");
			ps.setString(3, "test");
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(result);
		sql = "DELETE FROM baotangggiam WHERE chucdanh = ? and phongban = ? and contract_code = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, "test");
			ps.setString(2, "test");
			ps.setString(3, "test");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvalidUpdateInsuranceProcess() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	boolean result = dao.updateProcess(new InsuranceProcess());
	            });
	}
	
	@Test
	public void testInvalidUpdateInsuranceProcess1() {
		assertThrows(NullPointerException.class,
	            ()->{
	            	boolean result = dao.updateProcess(null);
	            });
	}

	@Test
	public void testValidUpdateInsuranceProcess() {
		InsuranceProcess process = new InsuranceProcess();
		Labour labour = new Labour();
		labour.setId(1);
		InsuranceProcessType type = new InsuranceProcessType();
		type.setId(1);
		process.setId(28);
		process.setContractCode("test");
		process.setDivision("test");
		process.setPosition("test");
		process.setInsuranceSalary(200000.0F);
		process.setLabour(labour);
		process.setProcessType(type);
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean result = dao.updateProcess(process);
		assertTrue(result);
		String sql = "SELECT * FROM baotangggiam WHERE id=28";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			InsuranceProcess insuranceProcess = new InsuranceProcess();
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				insuranceProcess.setId(rs.getInt("id"));
				insuranceProcess.setContractCode(rs.getString("mahdld"));
				insuranceProcess.setDivision(rs.getString("phongban"));
				insuranceProcess.setPosition(rs.getString("chucdanh"));
				insuranceProcess.setInsuranceSalary(rs.getFloat("luongBH"));
				insuranceProcess.setLabour(labour);
				insuranceProcess.setProcessType(type);
			}
			assertEquals(process.getContractCode(), insuranceProcess.getContractCode());
			assertEquals(process.getDivision(), insuranceProcess.getDivision());
			assertEquals(process.getPosition(), insuranceProcess.getPosition());
			assertEquals(process.getInsuranceSalary(), insuranceProcess.getInsuranceSalary());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
