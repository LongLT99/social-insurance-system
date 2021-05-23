package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.servlet.jsp.tagext.TryCatchFinally;

import src.dao.DAO;
import src.dao.MemberDAO;
import src.model.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Connection;

class TestMemberDAO {
	MemberDAO member = new MemberDAO();

	@Test
	void testLuuthanhvien() {
		MemberDAO md = new MemberDAO();
		Member sMember = new Member("hdhuong","123456","hdhuong@gmail.com","9812381");
		try {
			DAO.conn.setAutoCommit(false);
			md.luuthanhvien(sMember);
			Assert.assertNotNull(sMember);
//			Assert.assertTrue(1 < sMember.getId()); // test id dung
//			Assert.assertEquals(0, md.getAllMember().size()); // test tat ca cac hang trong bang
			
			// test hang moi duoc insert
			Member member = md.getMemberByID(sMember.getId());
			Assert.assertEquals(sMember.getUsername(), member.getUsername());
			Assert.assertEquals(sMember.getPassword(),member.getPassword());
			Assert.assertEquals(sMember.getEmail(),member.getEmail());
			Assert.assertEquals(sMember.getPhoneNumber(),member.getPhoneNumber());
			Assert.assertEquals(sMember.getRole(),member.getRole());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DAO.conn.rollback();
				DAO.conn.setAutoCommit(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return;
		
		
	}
	@Test
	void testGetAllMember() {
		MemberDAO md = new MemberDAO();
		ArrayList<Member> listMem = md.getAllMember();
		Assert.assertNotNull(listMem);
		Assert.assertEquals(0, listMem.size());
		return;
	}

	@Test
	void testGetIdThanhVien() {
		// test get id thanh vien chuan
		String username = "tnhhA";
		String password = "123456";
		int idMember = member.getIdThanhVien(username, password);
		Assert.assertNotNull(idMember);
		Assert.assertEquals(1, idMember);
		
	}
	@Test 
	void testGetIDThanhvien_ngoaile1(){
		// test get id thanh vien voi username ton tai , password khong ton tai
		String username = "tnhhA";
		String password = "hdhuong";
		int idMember = member.getIdThanhVien(username, password);
		Assert.assertNull(idMember);
		
	}
	
	@Test 
	void testGetIDThanhvien_ngoaile2(){
		// test get id thanh vien voi username khong ton tai , password ton tai
		String username = "hdhuong";
		String password = "123456";
		int idMember = member.getIdThanhVien(username, password);
		Assert.assertNull(idMember);
		
	}
	@Test 
	void testGetIDThanhvien_ngoaile3(){
		// test get id thanh vien voi username va password khong ton tai
		String username = "hdhuong";
		String password = "hdhuong";
		int idMember = member.getIdThanhVien(username, password);
		Assert.assertNull(idMember);
		
	}
	

	

}
