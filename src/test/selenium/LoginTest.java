package test.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Class test cac chuc nang tren trang dang nhap
 * 
 * @author Khoinh
 *
 */
public class LoginTest extends SeleniumTestDriver {

	ChromeDriver driver = getDriver();

	@Before
	public void init() {
		driver.get("http://localhost:8080/social-insurance-system/login.jsp");
	}

	// Kiem tra title cua trang dang nhap
	@Test
	public void loginTitleTest() {
		String title = driver.getTitle();
		String expectedTitle = "Login";
		assertEquals(expectedTitle, title);
	}

	// Kiem tra dang nhap voi tai khoan khong ton tai
	@Test
	public void loginInvalidTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		loginBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp?err=fail";
		assertEquals(expectedUrl, targetUrl);

	}

	// Kiem tra hanh dong dang nhap co gang thuc hien SQL Injection
	@Test
	public void loginSqlInjectionTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		username.sendKeys("tnhhB");
		password.sendKeys("1 OR 1 = 1");
		loginBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp?err=fail";
		assertEquals(expectedUrl, targetUrl);
	}

	// Kiem tra dang nhap hop le
	@Test
	public void loginValidTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		username.sendKeys("tnhhA");
		password.sendKeys("123456");
		loginBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/business/businessHome.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Kiem tra dang nhap hop le
	@Test
	public void loginValidForAdminTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		username.sendKeys("ema");
		password.sendKeys("123456");
		loginBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/employee/employeeHome.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Kiem tra gioi han ten dang nhap
	@Test
	public void loginExceedUsernameLimitTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		username.sendKeys("123456!@%&!&#^!\\120948104571897219749328576928365789275289357387589217275289375892378"
				+ "lkdfjksljgiploerjowihgioheihjisjijeljgerhgljkehrwetjgckxnvkbnwfj934u42w5#&*(^!@&(*!&*@$#&(!~("
				+ "jrlaewjfwoeigtfhjiowaghoihwieghiwehihjgweiojweiojiogewjiowehjioghuih830yu9&*()#$&@*$*@&*@$^*@$("
				+ "38902yu82q3yt8iofngkwey890&*($^@TR$*IOY *TYUGR@(T$&*(RT &G@*RTY&RT&TỶ*YOIYCUITGUICYXIO*G");
		password.sendKeys("78317284721");
		loginBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp?err=fail";
		assertEquals(expectedUrl, targetUrl);
	}

	// Kiem tra gioi han mat khau
	@Test
	public void loginExceedPasswordLimitTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		username.sendKeys("tnhhA");
		password.sendKeys("123456!@%&!&#^!\\120948104571897219749328576928365789275289357387589217275289375892378"
				+ "lkdfjksljgiploerjowihgioheihjisjijeljgerhgljkehrwetjgckxnvkbnwfj934u42w5#&*(^!@&(*!&*@$#&(!~("
				+ "jrlaewjfwoeigtfhjiowaghoihwieghiwehihjgweiojweiojiogewjiowehjioghuih830yu9&*()#$&@*$*@&*@$^*@$("
				+ "38902yu82q3yt8iofngkwey890&*($^@TR$*IOY *TYUGR@(T$&*(RT &G@*RTY&RT&TỶ*YOIYCUITGUICYXIO*G");
		loginBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp?err=fail";
		assertEquals(expectedUrl, targetUrl);
	}

	// Kiem tra de trong ten dang nhap
	@Test
	public void loginEmptyUsername() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		password.sendKeys("123456!@%&!&#^!\\120948104571897219749328576928365789275289357387589217275289375892378"
				+ "lkdfjksljgiploerjowihgioheihjisjijeljgerhgljkehrwetjgckxnvkbnwfj934u42w5#&*(^!@&(*!&*@$#&(!~("
				+ "jrlaewjfwoeigtfhjiowaghoihwieghiwehihjgweiojweiojiogewjiowehjioghuih830yu9&*()#$&@*$*@&*@$^*@$("
				+ "38902yu82q3yt8iofngkwey890&*($^@TR$*IOY *TYUGR@(T$&*(RT &G@*RTY&RT&TỶ*YOIYCUITGUICYXIO*G");
		loginBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Kiem tra de trong mat khau
	@Test
	public void loginEmptyPassword() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		username.sendKeys("123456!@%&!&#^!\\120948104571897219749328576928365789275289357387589217275289375892378"
				+ "lkdfjksljgiploerjowihgioheihjisjijeljgerhgljkehrwetjgckxnvkbnwfj934u42w5#&*(^!@&(*!&*@$#&(!~("
				+ "jrlaewjfwoeigtfhjiowaghoihwieghiwehihjgweiojweiojiogewjiowehjioghuih830yu9&*()#$&@*$*@&*@$^*@$("
				+ "38902yu82q3yt8iofngkwey890&*($^@TR$*IOY *TYUGR@(T$&*(RT &G@*RTY&RT&TỶ*YOIYCUITGUICYXIO*G");
		loginBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Kiem tra de trong ten dang nhap va mat khau
	@Test
	public void loginEmptyUsernameAndPassword() {
		WebElement loginBtn = driver.findElement(By.name("btnlogin"));
		loginBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	@After
	public void closeDriver() {
		driver.close();
	}
}
