package test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BusinessTests extends SeleniumTestDriver {
	
	ChromeDriver driver = getDriver();
	
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
		String targetUrl = driver.getCurrentUrl();
		String title = driver.getTitle();
		String expectedTitle = "Hệ thống hỗ trợ tính phí bảo hiểm xã hội";
		assertEquals(expectedTitle, title);
	}
	
	@Test
	public void testBussinessHomeNavLink() {
		login();
		assertTrue(driver.findElement(By.xpath("/html/body/nav/div/ul/li[1]/a")).isEnabled());
	}
	
	@Test
	public void testInfoLinkNavLink() {
		login();
		assertTrue(driver.findElement(By.xpath("/html/body/nav/div/ul/li[2]/a")).isEnabled());
	}
	
	@After
	public void closeDriver() {
		driver.close();
	}
}
