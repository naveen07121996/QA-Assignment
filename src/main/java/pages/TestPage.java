package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

	// Scenario 1
	private By searchIcon = By.xpath("//button[@class = 'css-tkwi90 e1iflr850']");
	private By searchBox = By.xpath("//input[@placeholder = 'SEARCH']");
	private By goBtn = By.xpath("//button[@type = 'submit']");
	private By dateRangeBtn = By.xpath("//button[@class = 'css-p5555t']");
	private By specificDatesSection = By.xpath("//button[text() = 'Specific Dates']");
	private By startDateField = By.xpath("//input[@id = 'startDate']");
	private By endDateField = By.xpath("//input[@id = 'endDate']");
	private By errorMsg = By.xpath("//a[text() = 'Have search feedback? Let us know what you think.']");

	// Scenario 2
	private By uSHeaderMenu = By.xpath("(//a[@data-navid = 'U.S.'])[2]");
	private By currentYearElectionsItem = By.xpath("(//a[text() = '2024 Elections'])[2]");
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

	// Scenario 1 Actions

	// validating home page
	public boolean validateHomePage() {
		try {
			// Check if the element is displayed
			WebElement homePageText = driver.findElement(uniqueText);
			return homePageText.isDisplayed();
		} catch (Exception e) {
			// Return false if element is not found or not displayed
			return false;
		}

	}

	// search
	public void search(String searchItem) {
		baseClass.handleTermsPopup();
		driver.findElement(searchIcon).click();
		driver.findElement(searchBox).sendKeys(searchItem);
		driver.findElement(goBtn).click();
	}

	// Validating search item screen
	public void validateSearchScrn() {
		String currentUrl = driver.getCurrentUrl();
		String expectedUrl = reader.getProperty("crimesscreenurl");
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
		String expectedMsg = reader.getProperty("error.invalidDateRange");
		WebElement actualMsg = driver.findElement(errorMsg);
		Assert.assertEquals(actualMsg.getText(), expectedMsg);
	}

	// Scenario 2 Actions

	// select US header menu
	public void selectUSItem() {
		WebElement uSHeader = driver.findElement(uSHeaderMenu);
		baseClass.moveToElement(uSHeader);

		WebElement elections = driver.findElement(currentYearElectionsItem);
		baseClass.moveToElement(elections);
		baseClass.clickElement(elections);
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
		try {
			// Check if the element is displayed
			WebElement gamePageText = driver.findElement(gameText);
			return gamePageText.isDisplayed();
		} catch (Exception e) {
			// Return false if element is not found or not displayed
			return false;
		}
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
		try {
			// Check if the element is displayed
			WebElement tBrandText = driver.findElement(inspiredBrandText);
			return tBrandText.isDisplayed();
		} catch (Exception e) {
			// Return false if element is not found or not displayed
			return false;
		}
	}

	// Scenario 4 Actions

	// click time store link
	public void clickTimeStoreLink() {
		baseClass.handleTermsPopup();
		baseClass.scrollToBottom();
		WebElement timeStore = driver.findElement(timeStoreLinkText);
		baseClass.moveToElement(timeStore);
		baseClass.clickElement(timeStore);
		// baseClass.handlePopupByCloseIcon();
	}

	// verify through sub title text
	public boolean verifySubTitle() {
		// baseClass.handlePopupByCloseIcon();
		try {
			// Check if the element is displayed
			WebElement sTitleText = driver.findElement(subTitleText);
			return sTitleText.isDisplayed();
		} catch (Exception e) {
			// Return false if element is not found or not displayed
			return false;
		}
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
		driver.findElement(addToCartBtn).click();
		driver.findElement(cartIconBtn).click();
	}

}
