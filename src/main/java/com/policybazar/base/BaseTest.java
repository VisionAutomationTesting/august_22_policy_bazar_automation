package com.policybazar.base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.policybazar.utils.PropertiesUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver = null;
	public static Properties props = PropertiesUtils.readPropertyFile(
			System.getProperty("user.dir") + "\\src\\main\\java\\com\\policybazar\\config\\application.properties");
	
	public static Actions act=null;
	public static JavascriptExecutor js=null;
	public static void init() {
		String browser = props.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--remote-allow-origins=*");
			
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("ff") || browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(props.getProperty("timeout"))));
		driver.get(props.getProperty("url"));
		act=new Actions(driver);
		js=(JavascriptExecutor)driver;
	}

	public static void closeAll() {
		driver.quit();
	}

	// Selenium wrapper method

	// scroll till element
	// wait untile element is present
	// wait and click

	public static void scrollTillElement(WebElement Element) {
		js.executeScript("arguments[0].scrollIntoView();", Element);
	}
	
	//overloading
	public static void waitUntileElementVisibleBy(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public static void waitUntileElementVisibleBy(By ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}
	
	public static void waitUntileElementVisibleBy(String xpath) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public static void waitUntileElementVisibleAndClickBy(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
		ele.click();
	}
	
	public static List<String> getListBsedOnElements(List<WebElement> elements){
		List<String> data=new ArrayList<>();
		
		for(WebElement ele:elements) {
			data.add(ele.getText().trim());
		}
		
		return data;
	}
	
	
	public static void clickByJs(WebElement ele) {
		js.executeScript("arguments[0].click();", ele);
	}
	
	
	// Role on Automation tester
	// New script creaction of alreday tested manual stories/scenarios
	// Existing script ubdation / if any peer has created any tc scripts, that need review by us
	// Scriptexecution and code mangement > on git / vcs

	
}
