package test;

import static org.junit.jupiter.api.Assertions.*;
import src.dao.BusinessUnitDAO;
import src.model.BusinessUnit;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class test_one extends TestCase {

	//test case 1: 
	@Test
	void testGetBusinessUnitbyId(){
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
	
	@Test
	void testGetBusinessUnitbyId2(){
		BusinessUnit b = new BusinessUnit();
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		b= busUnitDAO.getBusinessUnitbyId(2);
		BusinessUnit expected = new BusinessUnit();
		expected.setId(2);
		expected.setName("Công ty trách nhiệm hữu hạn B");
		expected.setTaxCode("234523453452");
		expected.setUnitCode("tnhhB");
		assertEquals(expected.getId(),b.getId());
		assertEquals(expected.getName(),b.getName());
		assertEquals(expected.getTaxCode(),b.getTaxCode());
		assertEquals(expected.getUnitCode(),b.getUnitCode());
	}
	
	@Test
	void testGetBusinessUnitbyId3(){
		BusinessUnit b = new BusinessUnit();
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		b= busUnitDAO.getBusinessUnitbyId(153);
		BusinessUnit expected = new BusinessUnit();
		expected = null;
		assertEquals(expected, b.getName());
	}
	
	@Test
	void testGetBusinessUnitbyId4(){
		BusinessUnit b = new BusinessUnit();
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		b= busUnitDAO.getBusinessUnitbyId(-153);
		BusinessUnit expected = new BusinessUnit();
		expected = null;
		assertEquals(expected, b.getName());
	}

	@Test
	void testSearchBusinessUnit() {
		fail("Not yet implemented");
	}

}
