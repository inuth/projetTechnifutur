package be.spring.bootProject.selenium;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test1Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  @Before
  public void setUp() {
	  // IL FAUT INSTALLER LE DRIVER 
	  // ET DONNER AU SYSTEM LE CHEMIN VERS LE .EXE
	System.setProperty("webdriver.gecko.driver", "C:\\Users\\GOger\\Desktop\\Cours\\selenium\\geckodriver.exe");
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void test1() {
    driver.get("http://localhost:8084/product");
    driver.manage().window().setSize(new Dimension(826, 704));
    driver.findElement(By.linkText("LOGIN")).click();
    driver.findElement(By.id("username")).sendKeys("admin123");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("admin123");
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.linkText("go to products")).click();
    driver.findElement(By.linkText("LOGOUT")).click();
    //driver.close();
  }
}
