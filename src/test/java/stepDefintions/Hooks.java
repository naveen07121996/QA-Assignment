package stepDefintions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
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

	@Before
	public void setUp() {
		String browser = config.getProperty("browser").trim();

		// Initializing browser based on config
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
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

		// Set timeouts based on properties file
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait").trim())));
		driver.manage().timeouts()
				.pageLoadTimeout(Duration.ofSeconds(Long.parseLong(config.getProperty("page.load.timeout").trim())));
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
