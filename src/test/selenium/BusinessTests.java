package test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import src.dao.LabourDAO;
import src.model.Labour;

public class BusinessTests extends SeleniumTestDriver {

	ChromeDriver driver = getDriver();

	LabourDAO dao = new LabourDAO();

	Connection conn = (Connection) dao.conn;

	public void login() {
		driver.get("http://localhost:8080/social-insurance-system/login.jsp");
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		username.sendKeys("tnhhA");
		password.sendKeys("123456");
		loginBtn.click();
	}

	@Test
	public void testNotLogin() {
		driver.get("http://localhost:8080/social-insurance-system/business/businessHome.jsp");
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp?err=timeout";
		assertEquals(expectedUrl, targetUrl);
	}

	@Test
	public void testBussinessHomeTitle() {
		login();
		String title = driver.getTitle();
		String expectedTitle = "Hệ thống hỗ trợ tính phí bảo hiểm xã hội";
		assertEquals(expectedTitle, title);
	}

	@Test
	public void testBussinessHomeNavLink() {
		login();
		WebElement stayHome = driver.findElement(By.xpath("/html/body/nav/div/ul/li[1]/a"));
		assertTrue(stayHome.isEnabled());
		assertEquals("http://localhost:8080/social-insurance-system/businessHome.jsp", stayHome.getAttribute("href"));

	}

	@Test
	public void testInfoNavLink() {
		login();
		WebElement goToLabourInfo = driver.findElement(By.xpath("/html/body/nav/div/ul/li[2]/a"));
		assertTrue(goToLabourInfo.isEnabled());
		assertEquals("http://localhost:8080/social-insurance-system/business/labourInfo.jsp",
				goToLabourInfo.getAttribute("href"));
	}

	@Test
	public void testViewLink() {
		login();
		WebElement goToInsuranceInfo = driver.findElement(By.xpath("/html/body/nav/div/ul/li[3]/a"));
		assertTrue(goToInsuranceInfo.isEnabled());
		assertEquals("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp",
				goToInsuranceInfo.getAttribute("href"));
	}

	// Test khong nhap ten
	@Test
	public void addInvalidLabour() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/addLabour.jsp");
		WebElement name = driver.findElement(By.name("name"));
		WebElement idNumber = driver.findElement(By.name("idNumber"));
		WebElement insCode = driver.findElement(By.name("insCode"));
		WebElement dob = driver.findElement(By.name("dob"));
		WebElement gender = driver.findElement(By.name("gender"));
		WebElement nationality = driver.findElement(By.name("nationality"));
		WebElement ethnic = driver.findElement(By.name("ethnic"));
		WebElement isCD = driver.findElement(By.name("isCD"));
		WebElement btnAdd = driver.findElement(By.name("btnAdd"));
		name.sendKeys("");
		idNumber.sendKeys("023901428908");
		insCode.sendKeys("24902319450");
		dob.sendKeys("1997-10-23");
		gender.sendKeys("1");
		nationality.sendKeys("Viet Nam");
		ethnic.sendKeys("Kinh");
		isCD.sendKeys("true");
		btnAdd.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/business/addLabour.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap so CCCD/ Ho chieu / CMT
	@Test
	public void addInvalidIdNumberLabour() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/addLabour.jsp");
		WebElement name = driver.findElement(By.name("name"));
		WebElement idNumber = driver.findElement(By.name("idNumber"));
		WebElement insCode = driver.findElement(By.name("insCode"));
		WebElement dob = driver.findElement(By.name("dob"));
		WebElement gender = driver.findElement(By.name("gender"));
		WebElement nationality = driver.findElement(By.name("nationality"));
		WebElement ethnic = driver.findElement(By.name("ethnic"));
		WebElement isCD = driver.findElement(By.name("isCD"));
		WebElement btnAdd = driver.findElement(By.name("btnAdd"));
		name.sendKeys("Nguyen Hoang Khoi");
		idNumber.sendKeys("");
		insCode.sendKeys("24902319450");
		dob.sendKeys("1997-10-23");
		gender.sendKeys("1");
		nationality.sendKeys("Viet Nam");
		ethnic.sendKeys("Kinh");
		isCD.sendKeys("true");
		btnAdd.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/business/addLabour.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap ma BHXH
	@Test
	public void addInvalidInsuranceCodeLabour() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/addLabour.jsp");
		WebElement name = driver.findElement(By.name("name"));
		WebElement idNumber = driver.findElement(By.name("idNumber"));
		WebElement insCode = driver.findElement(By.name("insCode"));
		WebElement dob = driver.findElement(By.name("dob"));
		WebElement gender = driver.findElement(By.name("gender"));
		WebElement nationality = driver.findElement(By.name("nationality"));
		WebElement ethnic = driver.findElement(By.name("ethnic"));
		WebElement isCD = driver.findElement(By.name("isCD"));
		WebElement btnAdd = driver.findElement(By.name("btnAdd"));
		name.sendKeys("Nguyen Hoang Khoi");
		idNumber.sendKeys("24902319450");
		insCode.sendKeys("");
		dob.sendKeys("1997-10-23");
		gender.sendKeys("1");
		nationality.sendKeys("Viet Nam");
		ethnic.sendKeys("Kinh");
		isCD.sendKeys("true");
		btnAdd.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/business/addLabour.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap ngay sinh
	@Test
	public void addInvalidDobLabour() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/addLabour.jsp");
		WebElement name = driver.findElement(By.name("name"));
		WebElement idNumber = driver.findElement(By.name("idNumber"));
		WebElement insCode = driver.findElement(By.name("insCode"));
		WebElement dob = driver.findElement(By.name("dob"));
		WebElement gender = driver.findElement(By.name("gender"));
		WebElement nationality = driver.findElement(By.name("nationality"));
		WebElement ethnic = driver.findElement(By.name("ethnic"));
		WebElement isCD = driver.findElement(By.name("isCD"));
		WebElement btnAdd = driver.findElement(By.name("btnAdd"));
		name.sendKeys("Nguyen Hoang Khoi");
		idNumber.sendKeys("24902319450");
		insCode.sendKeys("24902319450");
		dob.sendKeys("");
		gender.sendKeys("1");
		nationality.sendKeys("Viet Nam");
		ethnic.sendKeys("Kinh");
		isCD.sendKeys("true");
		btnAdd.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/business/addLabour.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap gioi tinh
	@Test
	public void addInvalidGenderLabour() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/addLabour.jsp");
		WebElement name = driver.findElement(By.name("name"));
		WebElement idNumber = driver.findElement(By.name("idNumber"));
		WebElement insCode = driver.findElement(By.name("insCode"));
		WebElement dob = driver.findElement(By.name("dob"));
		WebElement gender = driver.findElement(By.name("gender"));
		WebElement nationality = driver.findElement(By.name("nationality"));
		WebElement ethnic = driver.findElement(By.name("ethnic"));
		WebElement isCD = driver.findElement(By.name("isCD"));
		WebElement btnAdd = driver.findElement(By.name("btnAdd"));
		name.sendKeys("Nguyen Hoang Khoi");
		idNumber.sendKeys("24902319450");
		insCode.sendKeys("24902319450");
		dob.sendKeys("1997-10-23");
		gender.sendKeys("");
		nationality.sendKeys("Viet Nam");
		ethnic.sendKeys("Kinh");
		isCD.sendKeys("true");
		btnAdd.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/business/addLabour.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap quoc tich
	@Test
	public void addInvalidNationalityLabour() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/addLabour.jsp");
		WebElement name = driver.findElement(By.name("name"));
		WebElement idNumber = driver.findElement(By.name("idNumber"));
		WebElement insCode = driver.findElement(By.name("insCode"));
		WebElement dob = driver.findElement(By.name("dob"));
		WebElement gender = driver.findElement(By.name("gender"));
		WebElement nationality = driver.findElement(By.name("nationality"));
		WebElement ethnic = driver.findElement(By.name("ethnic"));
		WebElement isCD = driver.findElement(By.name("isCD"));
		WebElement btnAdd = driver.findElement(By.name("btnAdd"));
		name.sendKeys("Nguyen Hoang Khoi");
		idNumber.sendKeys("24902319450");
		insCode.sendKeys("24902319450");
		dob.sendKeys("1997-10-23");
		gender.sendKeys("1");
		nationality.sendKeys("");
		ethnic.sendKeys("Kinh");
		isCD.sendKeys("true");
		btnAdd.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/business/addLabour.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap dan toc
	@Test
	public void addInvalidEthnicLabour() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/addLabour.jsp");
		WebElement name = driver.findElement(By.name("name"));
		WebElement idNumber = driver.findElement(By.name("idNumber"));
		WebElement insCode = driver.findElement(By.name("insCode"));
		WebElement dob = driver.findElement(By.name("dob"));
		WebElement gender = driver.findElement(By.name("gender"));
		WebElement nationality = driver.findElement(By.name("nationality"));
		WebElement ethnic = driver.findElement(By.name("ethnic"));
		WebElement isCD = driver.findElement(By.name("isCD"));
		WebElement btnAdd = driver.findElement(By.name("btnAdd"));
		name.sendKeys("Nguyen Hoang Khoi");
		idNumber.sendKeys("24902319450");
		insCode.sendKeys("24902319450");
		dob.sendKeys("1997-10-23");
		gender.sendKeys("1");
		nationality.sendKeys("Viet Nam");
		ethnic.sendKeys("");
		isCD.sendKeys("true");
		btnAdd.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/business/addLabour.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test nhap hop le
	@Test
	public void addValidLabour() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/addLabour.jsp");
		WebElement name = driver.findElement(By.name("name"));
		WebElement idNumber = driver.findElement(By.name("idNumber"));
		WebElement insCode = driver.findElement(By.name("insCode"));
		WebElement dob = driver.findElement(By.name("dob"));
		WebElement gender = driver.findElement(By.name("gender"));
		WebElement nationality = driver.findElement(By.name("nationality"));
		WebElement ethnic = driver.findElement(By.name("ethnic"));
		WebElement isCD = driver.findElement(By.name("isCD"));
		WebElement btnAdd = driver.findElement(By.name("btnAdd"));
		name.sendKeys("Nguyen Hoang Khoi");
		idNumber.sendKeys("023901428908");
		insCode.sendKeys("24902319450");
		dob.sendKeys("1997-10-23");
		gender.sendKeys("1");
		nationality.sendKeys("Viet Nam");
		ethnic.sendKeys("Kinh");
		isCD.sendKeys("true");
		btnAdd.click();
		Labour actualLabour = dao.getLabourByInsuranceCode("24902319450");
		assertEquals("Nguyen Hoang Khoi", actualLabour.getName());
		assertEquals("023901428908", actualLabour.getIdNumber());
		String sql = "DELETE FROM laodong WHERE id = ?";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			System.out.println(actualLabour.getId());
			ps.setInt(1, actualLabour.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void closeDriver() {
		driver.close();
	}
}
