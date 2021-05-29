package test.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import src.dao.LabourDAO;

public class InsuranceProcessTests extends SeleniumTestDriver {

	ChromeDriver driver = getDriver();

	LabourDAO dao = new LabourDAO();

	Connection conn = (Connection) dao.conn;

	public boolean retryingFindClick(By quan) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				driver.findElement(quan).click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	@Before
	public void init() {
		driver.get("http://localhost:8080/social-insurance-system/register.jsp");
	}

	

	@After
	public void closeDriver() {
		driver.close();
	}
}
