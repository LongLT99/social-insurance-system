package test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import src.dao.BusinessInsuranceInfoDAO;
import src.dao.BusinessUnitDAO;
import src.dao.DAO;
import src.dao.RealTimePaymentDAO;
import src.model.BusinessInsuranceInfo;
import src.model.BusinessUnit;

public class EmployeeTest extends SeleniumTestDriver {
	
	ChromeDriver driver = getDriver();
	
	Connection conn = (Connection) DAO.conn;
	
	public void login() {
		driver.get("http://localhost:8080/social-insurance-system/login.jsp");
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		username.sendKeys("ema");
		password.sendKeys("123456");
		loginBtn.click();
	}
	
	@Test
	public void testNoLogin() {
		driver.get("http://localhost:8080/social-insurance-system/employeeHome.jsp");
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp?err=timeout";
		assertEquals(expectedUrl, targetUrl);
	}
	
	@Test
	public void testHomeNavLink() {
		login();
		WebElement stayHome = driver.findElement(By.xpath("/html/body/div/nav/ul/li[2]/a"));
		assertTrue(stayHome.isEnabled());
	}
	
	@Test
	public void testConfirmLink() {
		login();
		WebElement confirm = driver.findElement(By.xpath("/html/body/div/nav/ul/li[3]/a"));
		assertTrue(confirm.isEnabled());
	}

	@Test
	public void testEmployeeHomeTitle() {
		login();
		String title = driver.getTitle();
		String expectedTitle = "Employee Home page";
		assertEquals(expectedTitle, title);
	}
	
	@Test
	public void testSearchBussinessTitle() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/employee/searchBusiness.jsp");
		String title = driver.getTitle();
		String expectedTitle = "Search Business page";
		assertEquals(expectedTitle, title);
		
	}
	
	@Test
	public void testSearchBussiness1() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/employee/searchBusiness.jsp");
		WebElement bussiness = driver.findElement(By.name("busName"));
		WebElement searchBtn = driver.findElement(By.name("btnsearch"));
		bussiness.sendKeys("Công ty TNHH A");
		searchBtn.click();
		WebElement baseTable = driver.findElement(By.tagName("table"));
		  
		WebElement tableRow = baseTable.findElement(By.xpath("/html/body/div[2]/table/tbody/tr"));
		WebElement cellIneed = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/th[1]"));
		WebElement cellIneed2 = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/th[2]"));
		WebElement cellIneed3 = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/th[3]"));
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		ArrayList <BusinessUnit> b = new ArrayList<>();
		b = busUnitDAO.searchBusinessUnit("Công ty TNHH A");
		//test case nhap dung key
		assertEquals(b.get(0).getName(), cellIneed.getText());
		assertEquals(b.get(0).getUnitCode(), cellIneed2.getText());
		assertEquals(b.get(0).getTaxCode(), cellIneed3.getText());
	}
	
	@Test
	public void testSearchBussiness2() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/employee/searchBusiness.jsp");
		WebElement bussiness = driver.findElement(By.name("busName"));
		WebElement searchBtn = driver.findElement(By.name("btnsearch"));
		bussiness.sendKeys("wdefgertwert");
		searchBtn.click();
		WebElement baseTable = driver.findElement(By.tagName("table"));
		  
		 //To find third row of table
		WebElement tableRow = baseTable.findElement(By.xpath("/html/body/div[2]/table/tbody/tr"));
		WebElement cellIneed = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/th"));
		
		String notify ="Không có kết quả để hiển thị xin hãy nhập đúng từ khóa!";
		//test case khong nhap dung key
		assertEquals(notify, cellIneed.getText());
	}
	
	@Test
	public void testgetInfoBussiness1() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/employee/getInsuranceInfo.jsp?idbu=1");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement getBtn = driver.findElement(By.name("btngetinfo"));
		//test case nhap dung thong tin
		insuranceMonth.sendKeys("04");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		getBtn.click();
		BusinessUnitDAO busUnitDAO = new BusinessUnitDAO();
		BusinessUnit businessUnit = busUnitDAO.getBusinessUnitbyId(1);
		BusinessInsuranceInfoDAO dao = new BusinessInsuranceInfoDAO();
		BusinessInsuranceInfo insuranceInfo = dao.getInsuranceInfo(businessUnit, "2021-04");
		
		WebElement baseTable = driver.findElement(By.tagName("table"));
		WebElement tableRow = baseTable.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]"));
		WebElement cellIneed = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/th[2]"));
		WebElement cellIneed2 = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[3]/th[2]"));
		WebElement cellIneed3 = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[4]/th[2]"));
		WebElement cellIneed4 = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[5]/th[2]"));
		
		float bhxh = insuranceInfo.getEmployeeSocialInsurance() + insuranceInfo.getBusinessSocialInsurance();
		float bhyt = insuranceInfo.getEmployeeMedicalInsurance() + insuranceInfo.getBusinessMedicalInsurance();
		float bhtn = insuranceInfo.getEmployeeUnemployedInsurance() + insuranceInfo.getBusinessUnemployedInsurance();
		float pcd = insuranceInfo.getEmployeeUnionFee() + insuranceInfo.getBusinessUnionFee();
		DecimalFormat df = new DecimalFormat("0");
		String bhxhWeb = cellIneed.getText().replaceAll(",", "");
		String bhytWeb = cellIneed2.getText().replaceAll(",", "");
		String bhtnWeb = cellIneed3.getText().replaceAll(",", "");
		String pcdWeb = cellIneed4.getText().replaceAll(",", "");

		assertEquals(bhxhWeb, df.format(bhxh));
		assertEquals(bhytWeb, df.format(bhyt));
		assertEquals(bhtnWeb, df.format(bhtn));
		assertEquals(pcdWeb, df.format(pcd));
		
	}
	
	@Test
	public void testgetInfoBussiness2() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/employee/getInsuranceInfo.jsp?idbu=1");
		WebElement getBtn = driver.findElement(By.name("btngetinfo"));
		//test case khong nhap thong tin
		getBtn.click();
		
		WebElement baseTable = driver.findElement(By.tagName("table"));
		WebElement tableRow = baseTable.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]"));
		WebElement cellIneed = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/th[2]"));

		String notify = "Không có kết quả để hiển thị xin hãy nhập đúng từ khóa!";
		String bhxhWeb = cellIneed.getText().replaceAll(",", "");

		assertEquals(bhxhWeb, notify);
		
	}
	
	@Test
	public void testConfirmPayment() {
		boolean rtPayCheck = false;
		RealTimePaymentDAO rtpDAO = new RealTimePaymentDAO();
		login();
		driver.get("http://localhost:8080/social-insurance-system/employee/getInsuranceInfo.jsp?idbu=1");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement getBtn = driver.findElement(By.name("btngetinfo"));
		//test case nhap dung thong tin
		insuranceMonth.sendKeys("04");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		getBtn.click();
		WebElement baseTable = driver.findElement(By.tagName("table"));
		WebElement tableRow = baseTable.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]"));
		WebElement cellIneed = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/th[4]"));
		driver.get("http://localhost:8080/social-insurance-system/employee/confirmPayment.jsp?idit=1&insuranceMonth=2021-04");
		WebElement time = driver.findElement(By.name("realTime"));
		WebElement bankName = driver.findElement(By.name("BankName"));
		WebElement code = driver.findElement(By.name("inputCode"));
		WebElement des = driver.findElement(By.name("Description"));
		WebElement pay = driver.findElement(By.name("inputPayment"));
		WebElement btnconfirm = driver.findElement(By.name("btncomfirm"));
		try {
			DAO.conn.setAutoCommit(false);
			time.sendKeys("04/05/2021");
			bankName.sendKeys("TP Bank");
			code.sendKeys("2301299");
			des.sendKeys("Thanh toan bao hiem y te");
			pay.sendKeys("chuyen khoan");
			btnconfirm.click();
			// test hang moi duoc insert
			rtPayCheck = rtpDAO.CheckPayment(1, "2021-04", 1);
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
	       
	       assertTrue(rtPayCheck);
	}
	
	@Test
	public void testgetInfoBussiness2() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/employee/getInsuranceInfo.jsp?idbu=1");
		WebElement getBtn = driver.findElement(By.name("btngetinfo"));
		//test case khong nhap thong tin
		getBtn.click();
		
		WebElement baseTable = driver.findElement(By.tagName("table"));
		WebElement tableRow = baseTable.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]"));
		WebElement cellIneed = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/th[2]"));

		String notify = "Không có kết quả để hiển thị xin hãy nhập đúng từ khóa!";
		String bhxhWeb = cellIneed.getText().replaceAll(",", "");

		assertEquals(bhxhWeb, notify);
		
	}
	
	@Test
	public void testConfirmPayment() {
		boolean rtPayCheck = false;
		RealTimePaymentDAO rtpDAO = new RealTimePaymentDAO();
		login();
		driver.get("http://localhost:8080/social-insurance-system/employee/getInsuranceInfo.jsp?idbu=1");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement getBtn = driver.findElement(By.name("btngetinfo"));
		//test case nhap dung thong tin
		insuranceMonth.sendKeys("04");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		getBtn.click();
		WebElement baseTable = driver.findElement(By.tagName("table"));
		WebElement tableRow = baseTable.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[1]"));
		WebElement cellIneed = tableRow.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/th[4]"));
		driver.get("http://localhost:8080/social-insurance-system/employee/confirmPayment.jsp?idit=1&insuranceMonth=2021-04");
		WebElement time = driver.findElement(By.name("realTime"));
		WebElement bankName = driver.findElement(By.name("BankName"));
		WebElement code = driver.findElement(By.name("inputCode"));
		WebElement des = driver.findElement(By.name("Description"));
		WebElement pay = driver.findElement(By.name("inputPayment"));
		WebElement btnconfirm = driver.findElement(By.name("btncomfirm"));
		try {
			DAO.conn.setAutoCommit(false);
			time.sendKeys("04/05/2021");
			bankName.sendKeys("TP Bank");
			code.sendKeys("2301299");
			des.sendKeys("Thanh toan bao hiem y te");
			pay.sendKeys("chuyen khoan");
			btnconfirm.click();
			// test hang moi duoc insert
			rtPayCheck = rtpDAO.CheckPayment(1, "2021-04", 1);
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
	       
	       assertTrue(rtPayCheck);
	}
	
	@After
	public void closeDriver() {
		driver.close();
	}
}
