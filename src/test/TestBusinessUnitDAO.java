package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import src.dao.BusinessUnitDAO;
import src.model.BusinessUnit;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class TestBusinessUnitDAO extends TestCase {

	//test case 1: chọn đơn vị tham gia bảo hiểm bằng id, id có trong db 1
	@Test
	void testGetBusinessUnitbyId1(){
		BusinessUnit b = new BusinessUnit();
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		b= busUnitDAO.getBusinessUnitbyId(1);
		BusinessUnit expected = new BusinessUnit();
		expected.setId(1);
		expected.setName("Công ty TNHH A");
		expected.setTaxCode("093498253");
		expected.setUnitCode("tnhhA");
		assertEquals(expected.getId(),b.getId());
		assertEquals(expected.getName(),b.getName());
		assertEquals(expected.getTaxCode(),b.getTaxCode());
		assertEquals(expected.getUnitCode(),b.getUnitCode());
	}
	
	
	//test case 2: chọn đơn vị tham gia bảo hiểm bằng id, id không có trong db id 1
	@Test
	void testGetBusinessUnitbyId3(){
		BusinessUnit b = new BusinessUnit();
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		b= busUnitDAO.getBusinessUnitbyId(153);
		BusinessUnit expected = new BusinessUnit();
		expected = null;
		assertEquals(expected, b.getName());
	}
	
	//test case 3: chọn đơn vị tham gia bảo hiểm bằng id, id không có trong db id 2 id âm
	@Test
	void testGetBusinessUnitbyId4(){
		BusinessUnit b = new BusinessUnit();
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		b= busUnitDAO.getBusinessUnitbyId(-153);
		BusinessUnit expected = new BusinessUnit();
		expected = null;
		assertEquals(expected, b.getName());
	}

	//test case 4: tìm đơn vị tham gia bao hiểm, key đúng ngắn 1
	@Test
	void testSearchBusinessUnit1() {
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		ArrayList <BusinessUnit> b = new ArrayList<>();
		ArrayList <BusinessUnit> expected = new ArrayList<>();
		b = busUnitDAO.searchBusinessUnit("Công ty TNHH A");
		BusinessUnit ct1 = new BusinessUnit();
		ct1.setId(1);
		ct1.setUnitCode("tnhhA");
		ct1.setName("Công ty TNHH A");
		ct1.setTaxCode("093498253");
		expected.add(ct1);
		assertEquals("wrong size", 1, b.size());
		assertEquals(expected.get(0).getName(), b.get(0).getName());
		assertEquals(expected.get(0).getTaxCode(), b.get(0).getTaxCode());
	}
	
	//test case 5: tìm đơn vị tham gia bao hiểm, key sai 1
	@Test
	void testSearchBusinessUnit2() {
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		ArrayList <BusinessUnit> b = new ArrayList<>();
		ArrayList <BusinessUnit> expected = new ArrayList<>();
		b = busUnitDAO.searchBusinessUnit("fdasfds");
		
		assertEquals(expected, b);
	}

}
