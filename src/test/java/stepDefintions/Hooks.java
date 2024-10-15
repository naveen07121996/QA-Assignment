package stepDefintions;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import commonMethods.BaseClass;

import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.time.Duration;

public class Hooks {

	public static WebDriver driver;
	// Load the config file
	ConfigReader config = new ConfigReader();

	// BaseClass baseClass;

	@Before
	public void setUp() {
		String browser = config.getProperty("browser").trim();

		// Initializing browser based on config
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported Browser: " + browser);
		}

		driver.manage().window().maximize();

		// Navigate to the app URL
		driver.get(config.getProperty("app.url"));
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
