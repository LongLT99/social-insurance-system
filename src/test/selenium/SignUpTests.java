package test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import src.dao.DAO;

/**
 * Test chuc nang dang ky
 * 
 * @author Khoinh
 *
 */
public class SignUpTests extends SeleniumTestDriver {

	ChromeDriver driver = getDriver();

	DAO dao = new DAO();

	Connection conn = (Connection) dao.conn;

	@Before
	public void init() {
		driver.get("http://localhost:8080/social-insurance-system/register.jsp");
	}

	// Kiem tra title cua trang dang ky
	@Test
	public void signupTitleTest() {
		String title = driver.getTitle();
		String expectedTitle = "Đăng ký BHXH";
		assertEquals(expectedTitle, title);
	}

	// Kiem tra dang nhap voi tai khoan khong ton tai
	@Test
	public void signupNotFillAddressTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		;
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		madonvi.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.sendKeys("Cong ty TNHH ABCD");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhc@gmail.com");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap phuong
	@Test
	public void signupNotFillPhuongTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		;
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		madonvi.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.sendKeys("Cong ty TNHH ABCD");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhc@gmail.com");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap quan
	@Test
	public void signupNotFillQuanTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		madonvi.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.sendKeys("Cong ty TNHH ABCD");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhc@gmail.com");
		tinh.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap tinh
	@Test
	public void signupNotFillTinhTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		madonvi.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.sendKeys("Cong ty TNHH ABCD");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhc@gmail.com");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap email
	@Test
	public void signupNotFillEmailTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		madonvi.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		ten.sendKeys("Cong ty TNHH ABCD");
		tencqbh.sendKeys("1");
		sdt.sendKeys("0924823948");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap email
	@Test
	public void signupNotFillsdtTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		madonvi.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		ten.sendKeys("Cong ty TNHH ABCD");
		tencqbh.sendKeys("1");
		email.sendKeys("0924823948");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap ten co quan bao hiem
	@Test
	public void signupNotFillTenCQBHTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		madonvi.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhB@gmail.com");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap masothue
	@Test
	public void signupNotFillMasoThueTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		madonvi.sendKeys("tnhhC");
		ten.sendKeys("038954923");
		tencqbh.sendKeys("1");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhB@gmail.com");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap ten
	@Test
	public void signupNotFillTenTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		madonvi.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.sendKeys("1");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhB@gmail.com");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap madonvi
	@Test
	public void signupNotFillMdvTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		password.sendKeys("1hjdajhdf");
		ten.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.sendKeys("1");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhB@gmail.com");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap password
	@Test
	public void signupNotFillPasswordTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("tnhhB");
		madonvi.sendKeys("1hjdajhdf");
		ten.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.sendKeys("1");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhB@gmail.com");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test khong nhap password
	@Test
	public void signupNotFillUsernameTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		password.sendKeys("tnhhB");
		madonvi.sendKeys("1hjdajhdf");
		ten.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.sendKeys("1");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhB@gmail.com");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);
	}

	// Test nhap password < 6 KY TU
	@Test
	public void signUpInvalidTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("nhkhoi");
		password.sendKeys("tnhhB");
		madonvi.sendKeys("1hjdajhdf");
		ten.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.sendKeys("1");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhB@gmail.com");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);

		try {
			String sql = "SELECT * FROM thanhvien WHERE " + "username = 'nhkhoi' AND password = 'tnhhB'";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			assertFalse(rs.next());
			sql = "DELETE FROM DONVIBH WHERE " + "MADONVI = '1hjdajhdf'";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "DELETE FROM thanhvien WHERE " + "username = 'nhkhoi' AND password = 'tnhhB'";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Test nhap password > 12 KY TU
	@Test
	public void signUpInvalidPasswordTest() {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		WebElement tencqbh = driver.findElement(By.name("tencqbh"));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		WebElement tinh = driver.findElement(By.name("tinh"));
		WebElement quan = driver.findElement(By.name("quan"));
		WebElement phuong = driver.findElement(By.name("phuong"));
		WebElement chitiet = driver.findElement(By.name("chitiet"));
		WebElement signupBtn = driver.findElement(By.name("btnSignup"));
		username.sendKeys("nhkhoi");
		password.sendKeys("tnhhB2134");
		madonvi.sendKeys("1hjdajhdf");
		ten.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.sendKeys("1");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhB@gmail.com");
		tinh.sendKeys("1");
		quan.sendKeys("1");
		phuong.sendKeys("2");
		chitiet.sendKeys("1");
		signupBtn.click();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/register.jsp";
		assertEquals(expectedUrl, targetUrl);

		try {
			String sql = "SELECT * FROM thanhvien WHERE " + "username = 'nhkhoi' AND password = 'tnhhB2134'";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			assertFalse(rs.next());
			sql = "DELETE FROM DONVIBH WHERE " + "MADONVI = '1hjdajhdf'";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "DELETE FROM thanhvien WHERE " + "username = 'nhkhoi' AND password = 'tnhhB2134'";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Test dang ky hop le
	@Test
	public void signUpValidTest() throws InterruptedException {
		WebElement username = driver.findElement(By.name("username"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement madonvi = driver.findElement(By.name("madonvi"));
		WebElement ten = driver.findElement(By.name("ten"));
		WebElement masothue = driver.findElement(By.name("masothue"));
		Select tencqbh = new Select(driver.findElement(By.name("tencqbh")));
		WebElement sdt = driver.findElement(By.name("sdt"));
		WebElement email = driver.findElement(By.name("email"));
		Select tinh = new Select(driver.findElement(By.name("tinh")));
		Select quan = new Select(driver.findElement(By.name("quan")));
		Select phuong = new Select(driver.findElement(By.name("phuong")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		username.sendKeys("nhkhoi");
		password.sendKeys("12345678");
		madonvi.sendKeys("1hjdajhdf");
		ten.sendKeys("tnhhC");
		masothue.sendKeys("038954923");
		tencqbh.selectByVisibleText("Cơ quan BHXH Quận Ba Đình");
		sdt.sendKeys("0924823948");
		email.sendKeys("tnhhB@gmail.com");
		tinh.selectByVisibleText("Thành phố Hà Nội");
		WebElement quand = driver
				.findElementByXPath("/html/body/section/div[1]/div/div/form/div[9]/div/select[2]/option[6]");
		js.executeScript("$('#tinh').trigger('change')");
		quand.click();
		WebElement phuongd = driver
				.findElementByXPath("/html/body/section/div[1]/div/div/form/div[9]/div/select[3]/option[2]");
		phuongd.click();
		WebElement chitiet = driver.findElementByXPath("/html/body/section/div[1]/div/div/form/div[9]/div/input[3]");
		WebElement signupBtn = driver.findElementByXPath("/html/body/section/div[1]/div/div/form/div[10]/input");
		chitiet.sendKeys("abc");
		signupBtn.submit();
		String targetUrl = driver.getCurrentUrl();
		String expectedUrl = "http://localhost:8080/social-insurance-system/login.jsp?idok=1";
		assertEquals(expectedUrl, targetUrl);

		try {
			String sql = "SELECT * FROM thanhvien WHERE " + "username = 'nhkhoi' AND password = '12345678'";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			assertTrue(rs.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			PreparedStatement ps;
			String sql = "DELETE FROM DONVIBH WHERE " + "MADONVI = '1hjdajhdf'";
			try {
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.executeUpdate();
				sql = "DELETE FROM thanhvien WHERE " + "username = 'nhkhoi' AND password = '12345678'";
				ps = (PreparedStatement) conn.prepareStatement(sql);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@After
	public void closeDriver() {
		driver.close();
	}

}
