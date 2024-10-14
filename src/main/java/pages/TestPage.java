package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonMethods.BaseClass;
import utilities.ConfigReader;

public class TestPage {

	public WebDriver driver;
	BaseClass baseClass;
	ConfigReader reader;

	public TestPage(WebDriver idriver) {
		this.driver = idriver;
		PageFactory.initElements(driver, this);
		baseClass = new BaseClass(idriver);
		reader = new ConfigReader();
	}

	private By uniqueText = By.xpath("//a[text() = 'Today’s Paper']");

	private By todaysDate = By.xpath("//span[@data-testid = 'todays-date']");

	// Scenario 1
	private By searchIcon = By.xpath("//button[@class = 'css-tkwi90 e1iflr850']");
	private By searchBox = By.xpath("//input[@placeholder = 'SEARCH']");
	private By goBtn = By.xpath("//button[@type = 'submit']");

	// Scenario 2
	private By gamesHeaderMenu = By.xpath("(//a[text() = 'Games'])[2]");
	private By sudokuGameItem = By.xpath("//h4[text() = 'Sudoku']");
	private By gameText = By.xpath("//em[text() = 'Sudoku']");

	// Scenario 3
	private By tBrandStudio = By.xpath("//a[text() = 'T Brand Studio']");
	private By inspiredBrandText = By.xpath("//span[text() = 'INSPIRED BRAND']");

	// Scenario 4

	private By timeStoreLinkText = By.xpath("//a[text() = 'Times Store']");
	private By subTitleText = By.xpath("//div[text() = 'From the New York Times']");
	private By shopByCategoryHeader = By.xpath("(//div[contains(text() ,'Shop By Category')])[2]");
	private By booksItem = By.xpath("(//a[contains(text() , 'Books')])[2]");
	private By theFootBallBook = By.xpath("//div[contains(text() , 'The Football 100')]");
	private By addToCartBtn = By.xpath("//button[@id = 'AddToCart']");
	private By cartIconBtn = By.xpath("(//a[text() = 'Cart'])[1]");

	// close browser
	public void closeBrowser() {
		driver.close();
	}

	public void handlePopup() {
		baseClass.handleTermsPopup();
	}

	// validate today's date
	public void validateCurrentDate() {
		handlePopup();
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

	// search
	public void search(String searchItem) {
		baseClass.click(searchIcon);
		baseClass.type(searchBox, searchItem);
		baseClass.click(goBtn);
	}

	// Validating search item screen
	public void validateSearchScrn(String searchItem) {
		// Create the expected URL dynamically based on the search term
		String expectedUrl = "https://www.nytimes.com/search?query=" + searchItem;
		// Get the current URL
		String currentUrl = driver.getCurrentUrl();
		// Validate the current URL
		Assert.assertEquals("The current URL does not match the expected URL for search term: " + searchItem,
				expectedUrl, currentUrl);
	}

	public boolean isOnSearchedScreen() {
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

	// Scenario 3 Actions

	// select t brand studio
	public void selectTBrandStudio() {
		baseClass.scrollToBottom();
		WebElement tBrand = driver.findElement(tBrandStudio);
		baseClass.moveToElement(tBrand);
		baseClass.clickElement(tBrand);
	}

	// verify correct screen of t brand
	public boolean verifyTBrandScreen() {
		return baseClass.isElementDisplayed(inspiredBrandText);
	}

	// Scenario 4 Actions

	// click time store link
	public void clickTimeStoreLink() {
		handlePopup();
		baseClass.scrollToBottom();
		baseClass.click(timeStoreLinkText);
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
