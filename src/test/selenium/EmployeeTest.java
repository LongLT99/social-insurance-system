package test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmployeeTest extends SeleniumTestDriver {
	
	ChromeDriver driver = getDriver();
	
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
	
	@After
	public void closeDriver() {
		driver.close();
	}
}
