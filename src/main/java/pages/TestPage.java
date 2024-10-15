package pages;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import commonMethods.BaseClass;
import utilities.ConfigReader;
import utilities.ExcelReader;

public class TestPage {

	public WebDriver driver;
	BaseClass baseClass;
	ConfigReader reader;

	String errorDateMsg = "Have search feedback? Let us know what you think.";

	public TestPage(WebDriver idriver) {
		this.driver = idriver;
		baseClass = new BaseClass(idriver);
		reader = new ConfigReader();
	}

	private By uniqueText = By.xpath("//a[text() = 'Today’s Paper']");

	// Scenario 1
	private By todaysDate = By.xpath("//span[@data-testid = 'todays-date']");

	// Scenario 2
	private By searchIcon = By.xpath("//button[@class = 'css-tkwi90 e1iflr850']");
	private By searchBox = By.xpath("//input[@placeholder = 'SEARCH']");
	private By goBtn = By.xpath("//button[@type = 'submit']");
	private By dateRangeBtn = By.xpath("//button[@class = 'css-p5555t']");
	private By specificDatesSection = By.xpath("//button[text() = 'Specific Dates']");
	private By startDateField = By.xpath("//input[@id = 'startDate']");
	private By endDateField = By.xpath("//input[@id = 'endDate']");
	private By errorMsg = By.xpath("//a[text() = 'Have search feedback? Let us know what you think.']");

	// Scenario 3
	private By gamesHeaderMenu = By.xpath("(//a[text() = 'Games'])[2]");
	private By sudokuGameItem = By.xpath("//h4[text() = 'Sudoku']");
	private By gameText = By.xpath("//em[text() = 'Sudoku']");

	// Scenario 4
	private By signInText = By.xpath("//div[contains(text() , 'Sign in')]");
	private By emailField = By.id("CustomerEmail");
	private By passwordField = By.id("CustomerPassword");
	private By signInBtn = By.xpath("//input[@value = 'Sign In']");

	// Scenario 5
	private By timeStoreLinkText = By.xpath("//a[text() = 'Times Store']");
	private By subTitleText = By.xpath("//div[text() = 'From the New York Times']");
	private By shopByCategoryHeader = By.xpath("(//div[contains(text() ,'Shop By Category')])[2]");
	private By booksItem = By.xpath("(//a[contains(text() , 'Books')])[2]");
	private By theFootBallBook = By.xpath("//div[contains(text() , 'The Football 100')]");
	private By addToCartBtn = By.xpath("//button[@id = 'AddToCart']");
	private By cartIconBtn = By.xpath("(//a[text() = 'Cart'])[1]");

	// close browser
	public void closeBrowser() {
		if (driver != null) {
			try {
				driver.close(); // Close the browser window
			} catch (NoSuchSessionException e) {
				System.out.println("The browser session is already closed.");
			}
		}
	}

	public void handlePopup() {
		baseClass.handleTermsPopup();
	}

	// validate today's date
	public void validateCurrentDate() {
		baseClass.handleTermsPopup();
		// Get the current system date
		LocalDate today = LocalDate.now();
		// Adjust formatter to match the format displayed on the home screen
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy", Locale.ENGLISH);
		String expectedDate = today.format(formatter);

		WebElement dateElement = driver.findElement(todaysDate);
		String displayedDate = dateElement.getText();
		// Validate that the displayed date matches today's date
		Assert.assertEquals("The date displayed on the home screen does not match today's date.", expectedDate,
				displayedDate);
	}

	// Scenario 1 Actions

	// validating home page
	public boolean validateHomePage() {
		return baseClass.isElementDisplayed(uniqueText);

	}

	public boolean verifySearchScrnPage() {
		return baseClass.isElementDisplayed(uniqueText);
	}

	// Validating search item screen
	public void validateSearchScrn() {
		baseClass.handleTermsPopup();
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.nytimes.com/search?query=Crimes";
		Assert.assertEquals(expectedUrl, currentUrl);
	}

	// select invalid date range
	public void selectDates() {
		baseClass.handleTermsPopup();
		String startDate = "10/14/2024";
		String endDate = "10/15/2024";

		driver.findElement(dateRangeBtn).click();
		WebElement specificDate = driver.findElement(specificDatesSection);
		baseClass.moveToElement(specificDate);
		baseClass.clickElement(specificDate);

		WebElement startDates = driver.findElement(startDateField);
		startDates.clear();
		startDates.sendKeys(startDate);

		WebElement endDates = driver.findElement(endDateField);
		startDates.clear();
		endDates.sendKeys(endDate);
		endDates.sendKeys(Keys.ENTER);
	}

	// Validating error message
	public void verifyErrorMsg() {
		String expectedMsg = errorDateMsg;
		WebElement actualMsg = driver.findElement(errorMsg);
		Assert.assertEquals(actualMsg.getText(), expectedMsg);
	}

	// search
	public void search(String searchItem) {
		baseClass.handleTermsPopup();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
		search.click();
		// driver.findElement(searchIcon).click();
		driver.findElement(searchBox).sendKeys(searchItem);
		driver.findElement(goBtn).click();
	}

	// Validating search item screen
	public void validateSearchScrn(String searchItem) {
		baseClass.handleTermsPopup();
		// Create the expected URL dynamically based on the search term
		String expectedUrl = "https://www.nytimes.com/search?query=" + searchItem.trim();
		// Get the current URL
		String currentUrl = driver.getCurrentUrl();
		System.out.println("Search term from Excel: " + searchItem);

		// Validate the current URL
		Assert.assertEquals("The current URL does not match the expected URL for search term: " + searchItem.trim(),
				expectedUrl, currentUrl);
	}

	public boolean isOnSearchedScreen() {
		baseClass.handleTermsPopup();
		String expectedUrlPattern = "https://www.nytimes.com/search?query=";
		String currentUrl = driver.getCurrentUrl();
		// Check if the current URL contains the expected pattern
		return currentUrl.startsWith(expectedUrlPattern);
	}

	// Scenario 2 Actions

	// select US header menu
	public void selectUSItem(By uSHeaderMenu, By currentYearElectionsItem) {
		baseClass.moveToElement(driver.findElement(uSHeaderMenu));
		baseClass.clickElement(driver.findElement(currentYearElectionsItem));
	}

	// select game header
	public void selectGameItem() {
		baseClass.handleTermsPopup();
		driver.findElement(gamesHeaderMenu).click();
	}

	// select game
	public void selectGame() {
		driver.findElement(sudokuGameItem).click();
	}

	// verify the screen title
	public boolean verifyGameText() {
		return baseClass.isElementDisplayed(gameText);
	}

	// Scenario 4 Actions
	public void clickSignIn() {
		baseClass.handlePopupByCloseIcon();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement signIn = wait.until(ExpectedConditions.elementToBeClickable(signInText));
		signIn.click();
		driver.findElement(signInText).click();
	}

	// login
	public void invalidLogin() throws IOException {
		// Read data from Excel
		List<String[]> data = ExcelReader.readExcelData("Sheet1");

		for (String[] row : data) {
			String username = row[0]; // Assuming first column is the username
			String password = row[1]; // Assuming second column is the password

			// Input credentials
			WebElement email = driver.findElement(emailField);
			email.clear();
			email.sendKeys(username);
			WebElement pass = driver.findElement(passwordField);
			pass.clear();
			pass.sendKeys(password);
			clickLogin();
		}
	}

	// click login btn
	public void clickLogin() {
		driver.findElement(signInBtn).click();
	}
	// Scenario 5 Actions

	// click time store link
	public void clickTimeStoreLink() {
		handlePopup();
		baseClass.scrollToBottom();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement timeStoreLink = wait.until(ExpectedConditions.elementToBeClickable(timeStoreLinkText));
		timeStoreLink.click();
	}

	// verify through sub title text
	public boolean verifySubTitle() {
		return baseClass.isElementDisplayed(subTitleText);
	}

	// select shop by category
	public void shopByCategory() throws InterruptedException {
		baseClass.handlePopupByCloseIcon();
		try {
			WebElement shopcategory = driver.findElement(shopByCategoryHeader);
			// Wait until the element is clickable and move to it
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(shopcategory));
			baseClass.moveToElement(shopcategory);
			Thread.sleep(3000);
			// select book
			WebElement bookSection = driver.findElement(booksItem);
			baseClass.moveToElement(bookSection);
			baseClass.clickElement(bookSection);
		} catch (NoSuchElementException e) {
			System.out.println("Shop by Category header or Books section not found: " + e.getMessage());
		} catch (MoveTargetOutOfBoundsException e) {
			System.out.println("Move target out of bounds: " + e.getMessage());
		}
	}

	// select football book
	public void selectABook() {
		try {
			driver.findElement(theFootBallBook).click();
		} catch (NoSuchElementException e) {
			System.out.println("Book is not found: " + e.getMessage());
		}

	}

	// add item to cart
	public void addItemToCart() {
		baseClass.click(addToCartBtn);
		baseClass.click(cartIconBtn);
	}

}
