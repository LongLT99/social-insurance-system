package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import src.dao.BusinessUnitDAO;
import src.model.BusinessUnit;
import src.model.Member;

public class BusinessUnitDAOTests {
	
	static BusinessUnitDAO dao;

	@BeforeClass
	public static void setUp() throws Exception {
		dao = new BusinessUnitDAO();
	}

	/**
	 * Ham kiem tra thanh vien khong ton tai
	 */
	@Test
	public void getBusinessUnitTestNotExistMember() {
		Member member = new Member();
		member.setId(-1);
		member.setUsername("member1");
		BusinessUnit bussinessUnit = dao.getBusinessUnit(member);
		assertNull(bussinessUnit.getName());
	}
	
	/**
	 * Ham kiem tra thanh vien ton tai
	 */
	@Test
	public void getBusinessUnitTestExistMember() {
		Member member = new Member();
		member.setId(1);
		BusinessUnit bussinessUnit = dao.getBusinessUnit(member);
		assertEquals(member.getId(), bussinessUnit.getId());
	}

}
