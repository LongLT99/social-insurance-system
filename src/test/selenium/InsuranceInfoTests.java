package test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import src.dao.BusinessInsuranceInfoDAO;
import src.model.BusinessInsuranceInfo;
import src.model.BusinessUnit;

public class InsuranceInfoTests extends SeleniumTestDriver {

	ChromeDriver driver = getDriver();

	BusinessInsuranceInfoDAO dao = new BusinessInsuranceInfoDAO();

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

	// Test truy cap xem phi bao hiem khi chua dang nhap
	@Test
	public void testNotLogin() {
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test luong tu dang nhap -> trang xem bao hiem
	@Test
	public void testGoToInsuranceInfo() {
		login();
		assertEquals("http://localhost:8080/social-insurance-system/business/businessHome.jsp", driver.getCurrentUrl());
		WebElement hyperlink = driver.findElement(By.xpath("/html/body/nav/div/ul/li[3]/a"));
		driver.get(hyperlink.getAttribute("href"));
		assertEquals("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp",
				driver.getCurrentUrl());
	}

	// Test hien thi ten doanh nghiep
	@Test
	public void testBusinessName() {
		String actualName = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/a")).getText();
		String sql = "SELECT name FROM donviBH dvbh " + "INNER JOIN thanhvien v ON v.id = dvbh.thanhvienid "
				+ "WHERE v.username = 'tnhhA' AND password = '123456'";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String expectedName = "";
			if (rs.next()) {
				expectedName = rs.getString(1);
			}
			assertEquals(expectedName, actualName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Test hien thi ma BHXH
	@Test
	public void testInsuranceCode() {
		String actualName = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/a")).getText();
		String sql = "SELECT maBHXH FROM donviBH dvbh " + "INNER JOIN thanhvien v ON v.id = dvbh.thanhvienid "
				+ "WHERE v.username = 'tnhhA' AND password = '123456'";
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String expectedName = "";
			if (rs.next()) {
				expectedName = rs.getString(1);
			}
			assertEquals(expectedName, actualName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Test de trong thang tinh bao hiem
	@Test
	public void testEmptyInsuranceMonth() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		insuranceMonth.sendKeys("");
		btnSubmit.click();
		WebElement table = driver.findElement(By.tagName("table"));
		assertNull(table);
	}

	// Test nhap thang tinh bao hiem sai dinh dang
	@Test
	public void testInvalidFormatInsuranceMonth() {
		login();
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		insuranceMonth.sendKeys("20215-05");
		btnSubmit.click();
		WebElement table = driver.findElement(By.tagName("table"));
		assertNull(table);
	}

	// Test xem phi BHXH
	@Test
	public void testValidInsuranceInfo() {
		login();
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(1);
		businessUnit.setName("tnhhA");
		BusinessInsuranceInfo insuranceInfo = dao.getInsuranceInfo(businessUnit, "2021-05");
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		insuranceMonth.sendKeys("05");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		btnSubmit.click();
		WebElement table = driver.findElement(By.tagName("table"));
		assertNotNull(table);
		WebElement socialInsuranceTitle = driver.findElement(By.xpath("/html/body/div[1]/div/table/thead/tr/th[1]"));
		assertEquals("Tổng phí BHXH", socialInsuranceTitle.getText());
		WebElement socialInsuranceCell = driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr/th[1]"));
		assertEquals(insuranceInfo.getBusinessSocialInsurance() + insuranceInfo.getEmployeeSocialInsurance(),
				Float.parseFloat(socialInsuranceCell.getText().replaceAll(",", "")), 0.999);
	}

	// Test xem phi BHYT
	@Test
	public void testValidHealthInsuranceInfo() {
		login();
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(1);
		businessUnit.setName("tnhhA");
		BusinessInsuranceInfo insuranceInfo = dao.getInsuranceInfo(businessUnit, "2021-05");
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		insuranceMonth.sendKeys("05");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		btnSubmit.click();
		WebElement table = driver.findElement(By.tagName("table"));
		assertNotNull(table);
		WebElement socialInsuranceTitle = driver.findElement(By.xpath("/html/body/div[1]/div/table/thead/tr/th[2]"));
		assertEquals("Tổng phí BHYT", socialInsuranceTitle.getText());
		WebElement socialInsuranceCell = driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr/th[2]"));
		assertEquals(insuranceInfo.getBusinessMedicalInsurance() + insuranceInfo.getEmployeeMedicalInsurance(),
				Float.parseFloat(socialInsuranceCell.getText().replaceAll(",", "")), 0.999);
	}

	// Test xem phi BHTN
	@Test
	public void testValidUnemploymentInsuranceInfo() {
		login();
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(1);
		businessUnit.setName("tnhhA");
		BusinessInsuranceInfo insuranceInfo = dao.getInsuranceInfo(businessUnit, "2021-05");
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		insuranceMonth.sendKeys("05");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		btnSubmit.click();
		WebElement table = driver.findElement(By.tagName("table"));
		assertNotNull(table);
		WebElement socialInsuranceTitle = driver.findElement(By.xpath("/html/body/div[1]/div/table/thead/tr/th[3]"));
		assertEquals("Tổng phí BHTN", socialInsuranceTitle.getText());
		WebElement socialInsuranceCell = driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr/th[3]"));
		assertEquals(insuranceInfo.getBusinessUnemployedInsurance() + insuranceInfo.getEmployeeUnemployedInsurance(),
				Float.parseFloat(socialInsuranceCell.getText().replaceAll(",", "")), 0.999);
	}

	// Test xem phi KPCD
	@Test
	public void testValidUnionInsuranceInfo() {
		login();
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(1);
		businessUnit.setName("tnhhA");
		BusinessInsuranceInfo insuranceInfo = dao.getInsuranceInfo(businessUnit, "2021-05");
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		insuranceMonth.sendKeys("05");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		btnSubmit.click();
		WebElement table = driver.findElement(By.tagName("table"));
		assertNotNull(table);
		WebElement socialInsuranceTitle = driver.findElement(By.xpath("/html/body/div[1]/div/table/thead/tr/th[4]"));
		assertEquals("Tổng phí công đoàn", socialInsuranceTitle.getText());
		WebElement socialInsuranceCell = driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr/th[4]"));
		assertEquals(insuranceInfo.getBusinessUnionFee() + insuranceInfo.getEmployeeUnionFee(),
				Float.parseFloat(socialInsuranceCell.getText().replaceAll(",", "")), 0.999);
	}

	// Test tong phi BHXH doanh nghiep = tong nguoi lao dong
	@Test
	public void testSyncInsuranceInfoBetweenBusinessAndLabourDetail() {
		login();
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(1);
		businessUnit.setName("tnhhA");
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		insuranceMonth.sendKeys("05");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		btnSubmit.click();
		// Cac o gia tri tuong ung trong bang phi BHXH cua doanh nghiep
		WebElement businessSocialInsuranceCell = driver
				.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr/th[2]"));
		WebElement goToLabourDetail = driver.findElement(By.xpath("/html/body/div[1]/div/a"));

		// Lay gia tri phi BHXH duoi dang so
		Integer businessSocialInsurance = Integer.parseInt(businessSocialInsuranceCell.getText().replaceAll(",", ""));

		// Dieu huong sang trang chi tiet nguoi lao dong
		driver.get(goToLabourDetail.getAttribute("href"));
		List<WebElement> tableRows = driver.findElements(By.xpath("/html/body/div[2]/form/table"));
		Integer totalSocialInsurance = 0;

		for (WebElement tableRow : tableRows) {
			List<WebElement> cells = tableRow.findElements(By.tagName("td"));
			totalSocialInsurance += Integer.parseInt(cells.get(5).getText().replaceAll(",", ""));
			totalSocialInsurance += Integer.parseInt(cells.get(10).getText().replaceAll(",", ""));
		}
		assertEquals(businessSocialInsurance, totalSocialInsurance);
	}

	// Test tong phi BHXH doanh nghiep = tong nguoi lao dong
	@Test
	public void testSyncHealthInsuranceInfoBetweenBusinessAndLabourDetail() {
		login();
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(1);
		businessUnit.setName("tnhhA");
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		insuranceMonth.sendKeys("05");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		btnSubmit.click();
		// Cac o gia tri tuong ung trong bang phi BHXH cua doanh nghiep
		WebElement businessHealthInsuranceCell = driver
				.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr/th[2]"));

		WebElement goToLabourDetail = driver.findElement(By.xpath("/html/body/div[1]/div/a"));

		// Lay gia tri phi BHXH duoi dang so
		Integer businessHealthInsurance = Integer.parseInt(businessHealthInsuranceCell.getText().replaceAll(",", ""));

		// Dieu huong sang trang chi tiet nguoi lao dong
		driver.get(goToLabourDetail.getAttribute("href"));
		List<WebElement> tableRows = driver.findElements(By.xpath("/html/body/div[2]/form/table"));

		Integer totalHealthInsurance = 0;
		for (WebElement tableRow : tableRows) {
			List<WebElement> cells = tableRow.findElements(By.tagName("td"));
			totalHealthInsurance += Integer.parseInt(cells.get(6).getText().replaceAll(",", ""));
			totalHealthInsurance += Integer.parseInt(cells.get(11).getText().replaceAll(",", ""));
		}
		assertEquals(businessHealthInsurance, totalHealthInsurance);
	}

	// Test tong phi BHXH doanh nghiep = tong nguoi lao dong
	@Test
	public void testSyncUnemploymentInsuranceInfoBetweenBusinessAndLabourDetail() {
		login();
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(1);
		businessUnit.setName("tnhhA");
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		insuranceMonth.sendKeys("05");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		btnSubmit.click();
		// Cac o gia tri tuong ung trong bang phi BHXH cua doanh nghiep
		WebElement businessUnemploymentInsuranceCell = driver
				.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr/th[3]"));
		WebElement goToLabourDetail = driver.findElement(By.xpath("/html/body/div[1]/div/a"));

		// Lay gia tri phi BHXH duoi dang so
		Integer businessUnemploymentInsurance = Integer
				.parseInt(businessUnemploymentInsuranceCell.getText().replaceAll(",", ""));

		// Dieu huong sang trang chi tiet nguoi lao dong
		driver.get(goToLabourDetail.getAttribute("href"));
		List<WebElement> tableRows = driver.findElements(By.xpath("/html/body/div[2]/form/table"));

		Integer totalUnemploymentInsurance = 0;
		for (WebElement tableRow : tableRows) {
			List<WebElement> cells = tableRow.findElements(By.tagName("td"));
			totalUnemploymentInsurance += Integer.parseInt(cells.get(7).getText().replaceAll(",", ""));
			totalUnemploymentInsurance += Integer.parseInt(cells.get(12).getText().replaceAll(",", ""));
		}
		assertEquals(businessUnemploymentInsurance, totalUnemploymentInsurance);
	}

	// Test tong phi BHXH doanh nghiep = tong nguoi lao dong
	@Test
	public void testSyncUnionFeeInfoBetweenBusinessAndLabourDetail() {
		login();
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(1);
		businessUnit.setName("tnhhA");
		driver.get("http://localhost:8080/social-insurance-system/business/insuranceInfo.jsp");
		WebElement insuranceMonth = driver.findElement(By.name("insuranceMonth"));
		WebElement btnSubmit = driver.findElement(By.name("btnSubmit"));
		insuranceMonth.sendKeys("05");
		insuranceMonth.sendKeys(Keys.TAB);
		insuranceMonth.sendKeys("2021");
		btnSubmit.click();
		// Cac o gia tri tuong ung trong bang phi BHXH cua doanh nghiep
		WebElement businessUnionFeeCell = driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr/th[4]"));
		WebElement goToLabourDetail = driver.findElement(By.xpath("/html/body/div[1]/div/a"));

		// Lay gia tri phi BHXH duoi dang so
		Integer businessUnionFee = Integer.parseInt(businessUnionFeeCell.getText().replaceAll(",", ""));

		// Dieu huong sang trang chi tiet nguoi lao dong
		driver.get(goToLabourDetail.getAttribute("href"));
		List<WebElement> tableRows = driver.findElements(By.xpath("/html/body/div[2]/form/table"));

		Integer totalUnionFee = 0;
		for (WebElement tableRow : tableRows) {
			List<WebElement> cells = tableRow.findElements(By.tagName("td"));
			totalUnionFee += Integer.parseInt(cells.get(8).getText().replaceAll(",", ""));
			totalUnionFee += Integer.parseInt(cells.get(13).getText().replaceAll(",", ""));
		}
		assertEquals(businessUnionFee, totalUnionFee);
	}

	@After
	public void closeDriver() {
		driver.close();
	}

}
