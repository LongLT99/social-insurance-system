package test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import src.dao.DAO;
import src.dao.InsuranceProcessDAO;
import src.model.BusinessUnit;
import src.model.InsuranceProcess;
import src.model.Labour;

public class InsuranceProcessTests extends SeleniumTestDriver {

	ChromeDriver driver = getDriver();

	InsuranceProcessDAO dao = new InsuranceProcessDAO();
	
	Connection conn = (Connection) DAO.conn;

	Labour labour;

	@Before
	public void init() {
		labour = new Labour();
		String sql = "SELECT * FROM laodong WHERE ID = 1";
		try {
			PreparedStatement ps = (PreparedStatement) DAO.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				labour.setId(rs.getInt("id"));
				labour.setName(rs.getString("hoten"));
				labour.setDateOfBirth(rs.getDate("ngaysinh"));
				labour.setInsuranceCode(rs.getString("maBHXH"));
				labour.setFamilyCode(rs.getString("mahogiadinh"));
				labour.setGender(rs.getInt("gioitinh"));
				labour.setNationality(rs.getString("quoctich"));
				labour.setEthnic(rs.getString("dantoc"));
				labour.setPhoneNumber(rs.getString("sdt"));
				labour.setIdNumber(rs.getString("soCMND"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testNoLogin() {
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/social-insurance-system/login.jsp", url);
	}

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
	public void testViewProcessLabourName() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		String actualName = driver.findElement(By.xpath("/html/body/div/div/div/table/thead/tr[1]/td")).getText();
		String expectedName = "Tên người lao động: " + labour.getName();
		assertEquals(expectedName, actualName);
	}

	@Test
	public void testViewProcessInsuranceCode() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		String actualName = driver.findElement(By.xpath("/html/body/div[1]/div/div/table/thead/tr[2]/td")).getText();
		String expectedName = "Mã BHXH: " + labour.getInsuranceCode();
		assertEquals(expectedName, actualName);
	}

	@Test
	public void testEmptyContractCode() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		driver.get("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1");
		WebElement contractCode = driver.findElement(By.name("contractCode"));
		WebElement startDate = driver.findElement(By.name("startDate"));
		WebElement endDate = driver.findElement(By.name("endDate"));
		WebElement division = driver.findElement(By.name("division"));
		WebElement position = driver.findElement(By.name("position"));
		WebElement salary = driver.findElement(By.name("salary"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		Select processType = new Select(driver.findElement(By.name("processType")));
		contractCode.sendKeys("");
		startDate.sendKeys("10/22/1999");
		endDate.sendKeys("10/22/2000");
		division.sendKeys("Phong CNTT");
		position.sendKeys("Nhan vien");
		salary.sendKeys("10000000");
		processType.selectByIndex(1);
		btnSubmit.click();
		String actualUrl = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1",
				actualUrl);
	}

	@Test
	public void testEmptyStartDate() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		driver.get("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1");
		WebElement contractCode = driver.findElement(By.name("contractCode"));
		WebElement startDate = driver.findElement(By.name("startDate"));
		WebElement endDate = driver.findElement(By.name("endDate"));
		WebElement division = driver.findElement(By.name("division"));
		WebElement position = driver.findElement(By.name("position"));
		WebElement salary = driver.findElement(By.name("salary"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		Select processType = new Select(driver.findElement(By.name("processType")));
		contractCode.sendKeys("HD002");
		startDate.sendKeys("");
		endDate.sendKeys("10/22/2000");
		division.sendKeys("Phong CNTT");
		position.sendKeys("Nhan vien");
		salary.sendKeys("10000000");
		processType.selectByIndex(1);
		btnSubmit.click();
		String actualUrl = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1",
				actualUrl);
	}

	@Test
	public void testEmptyEndDate() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		driver.get("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1");
		WebElement contractCode = driver.findElement(By.name("contractCode"));
		WebElement startDate = driver.findElement(By.name("startDate"));
		WebElement endDate = driver.findElement(By.name("endDate"));
		WebElement division = driver.findElement(By.name("division"));
		WebElement position = driver.findElement(By.name("position"));
		WebElement salary = driver.findElement(By.name("salary"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		Select processType = new Select(driver.findElement(By.name("processType")));
		contractCode.sendKeys("HD002");
		startDate.sendKeys("10/22/2000");
		endDate.sendKeys("");
		division.sendKeys("Phong CNTT");
		position.sendKeys("Nhan vien");
		salary.sendKeys("10000000");
		processType.selectByIndex(1);
		btnSubmit.click();
		String actualUrl = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1",
				actualUrl);
	}

	@Test
	public void testEmptyDivision() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		driver.get("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1");
		WebElement contractCode = driver.findElement(By.name("contractCode"));
		WebElement startDate = driver.findElement(By.name("startDate"));
		WebElement endDate = driver.findElement(By.name("endDate"));
		WebElement division = driver.findElement(By.name("division"));
		WebElement position = driver.findElement(By.name("position"));
		WebElement salary = driver.findElement(By.name("salary"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		Select processType = new Select(driver.findElement(By.name("processType")));
		contractCode.sendKeys("HD002");
		startDate.sendKeys("10/22/2025");
		endDate.sendKeys("10/22/2026");
		division.sendKeys("");
		position.sendKeys("Nhan vien");
		salary.sendKeys("10000000");
		processType.selectByIndex(1);
		btnSubmit.click();
		String actualUrl = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1",
				actualUrl);
	}

	@Test
	public void testEmptyPosition() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		driver.get("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1");
		WebElement contractCode = driver.findElement(By.name("contractCode"));
		WebElement startDate = driver.findElement(By.name("startDate"));
		WebElement endDate = driver.findElement(By.name("endDate"));
		WebElement division = driver.findElement(By.name("division"));
		WebElement position = driver.findElement(By.name("position"));
		WebElement salary = driver.findElement(By.name("salary"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		Select processType = new Select(driver.findElement(By.name("processType")));
		contractCode.sendKeys("HD002");
		startDate.sendKeys("10/22/1999");
		endDate.sendKeys("10/22/2000");
		division.sendKeys("Phong CNTT");
		position.sendKeys("");
		salary.sendKeys("10000000");
		processType.selectByIndex(1);
		btnSubmit.click();
		String actualUrl = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1",
				actualUrl);
	}

	@Test
	public void testEmptySalary() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		driver.get("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1");
		WebElement contractCode = driver.findElement(By.name("contractCode"));
		WebElement startDate = driver.findElement(By.name("startDate"));
		WebElement endDate = driver.findElement(By.name("endDate"));
		WebElement division = driver.findElement(By.name("division"));
		WebElement position = driver.findElement(By.name("position"));
		WebElement salary = driver.findElement(By.name("salary"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		Select processType = new Select(driver.findElement(By.name("processType")));
		contractCode.sendKeys("HD002");
		startDate.sendKeys("10/22/1999");
		endDate.sendKeys("10/22/2000");
		division.sendKeys("Phong CNTT");
		position.sendKeys("Nhan vien");
		salary.sendKeys("");
		processType.selectByIndex(1);
		btnSubmit.click();
		String actualUrl = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1",
				actualUrl);
	}

	@Test
	public void testEmptyProcessType() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		driver.get("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1");
		WebElement contractCode = driver.findElement(By.name("contractCode"));
		WebElement startDate = driver.findElement(By.name("startDate"));
		WebElement endDate = driver.findElement(By.name("endDate"));
		WebElement division = driver.findElement(By.name("division"));
		WebElement position = driver.findElement(By.name("position"));
		WebElement salary = driver.findElement(By.name("salary"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		Select processType = new Select(driver.findElement(By.name("processType")));
		contractCode.sendKeys("HD002");
		startDate.sendKeys("10/22/1999");
		endDate.sendKeys("10/22/2000");
		division.sendKeys("Phong CNTT");
		position.sendKeys("Nhan vien");
		salary.sendKeys("100000");
		btnSubmit.click();
		String actualUrl = driver.getCurrentUrl();
		assertEquals("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1",
				actualUrl);
	}

	@Test
	public void testStartDateLargerThanEndDate() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		driver.get("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1");
		WebElement contractCode = driver.findElement(By.name("contractCode"));
		WebElement startDate = driver.findElement(By.name("startDate"));
		WebElement endDate = driver.findElement(By.name("endDate"));
		WebElement division = driver.findElement(By.name("division"));
		WebElement position = driver.findElement(By.name("position"));
		WebElement salary = driver.findElement(By.name("salary"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		Select processType = new Select(driver.findElement(By.name("processType")));
		boolean result = true;
		try {
			DAO.conn.setAutoCommit(false);
			contractCode.sendKeys("HD002");
			startDate.sendKeys("10/22/2024");
			endDate.sendKeys("10/22/2023");
			division.sendKeys("Phong CNTT");
			position.sendKeys("Nhan vien");
			salary.sendKeys("100000");
			processType.selectByIndex(1);
			btnSubmit.click();
			BusinessUnit busUnit = new BusinessUnit();
			busUnit.setId(1);
			ArrayList<InsuranceProcess> processes = dao.getInsuranceProcesses(busUnit, labour);
			for (InsuranceProcess process : processes) {
				if (process.getContractCode() == "HD002" && process.getDivision() == "Phong CNTT") {
					result = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DAO.conn.rollback();
				DAO.conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String actualUrl = driver.getCurrentUrl();
		assertTrue(result);
		assertEquals("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1",
				actualUrl);
	}

	@Test
	public void testStartDateLargerThanLatestProcessStartDate() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/labourInfo.jsp");
		driver.get("http://localhost:8080/social-insurance-system/business/labourInsuranceProcess.jsp?idld=1");
		driver.get("http://localhost:8080/social-insurance-system/business/addInsuranceProcess.jsp?idld=1");
		WebElement contractCode = driver.findElement(By.name("contractCode"));
		WebElement startDate = driver.findElement(By.name("startDate"));
		WebElement endDate = driver.findElement(By.name("endDate"));
		WebElement division = driver.findElement(By.name("division"));
		WebElement position = driver.findElement(By.name("position"));
		WebElement salary = driver.findElement(By.name("salary"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		Select processType = new Select(driver.findElement(By.name("processType")));
		InsuranceProcess latestProcess = dao.getLatestProcess(labour);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(latestProcess.getStartTime());
		calendar.add(Calendar.DATE, -2);
		String startTime = dateFormat.format(calendar.getTime());
		System.out.println(startTime);
		boolean result = true;
		try {
			DAO.conn.setAutoCommit(false);
			contractCode.sendKeys("HD002");
			startDate.sendKeys(startTime);
			endDate.sendKeys("10/22/2023");
			division.sendKeys("Phong CNTT");
			position.sendKeys("Nhan vien");
			salary.sendKeys("100000");
			processType.selectByIndex(1);
			btnSubmit.click();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DAO.conn.rollback();
				DAO.conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String alertDialog = driver.switchTo().alert().getText();
		driver.switchTo().alert().dismiss();
		assertEquals("Không thể thêm quá trình mới do thời gian bắt đầu nhỏ hơn thời gian kết thúc của quá trình trước", alertDialog);
	}

	@After
	public void closeDriver() {
		driver.close();
	}
}
