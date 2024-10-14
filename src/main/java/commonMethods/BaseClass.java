package commonMethods;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {

	public WebDriver driver;
	public Actions actions;

	By popUpContinueBtn = By.xpath("//button[@class='css-1fzhd9j']");

	By closeIconBtn = By.xpath("//div[@class = 'btn-close close-pop-up']");

	public BaseClass(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
	}

	// Common utility methods to reuse
	public void click(By locator) {
		driver.findElement(locator).click();
	}
	
	public void type(By locator, String text) {
		WebElement element = driver.findElement(locator);
		element.clear();
		element.sendKeys(text);
	}
	
	public boolean isElementDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// Actions

	// move to element
	public void moveToElement(WebElement element) {
		actions.moveToElement(element).build().perform();
	}

	// click element
	public void clickElement(WebElement element) {
		actions.click(element).build().perform();
	}

	// handle continue popup
	public void handleTermsPopup() {
		try {
			// Wait for the popup to be visible
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // 10 seconds wait
			WebElement continueButton = wait.until(ExpectedConditions.visibilityOfElementLocated(popUpContinueBtn));

			// If the pop-up is visible, click the "Continue" button
			if (continueButton.isDisplayed()) {
				continueButton.click();
				System.out.println("Pop-up dismissed.");
			}
		} catch (NoSuchElementException e) {
			// If the pop-up is not found, continue with the test
			System.out.println("Pop-up not found, continuing.");
		} catch (Exception e) {
			System.out.println("An error occurred while handling the pop-up: " + e.getMessage());
		}
	}

	// handle popup
	public void handlePopupByCloseIcon() {
		try {
			// Wait for the popup close icon to be visible
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement closeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(closeIconBtn));

			// If the popup is present, click on the close icon
			if (closeIcon.isDisplayed()) {
				closeIcon.click();
				System.out.println("Popup closed.");
			}
		} catch (Exception e) {
			System.out.println("Popup not found or already dismissed: " + e.getMessage());
		}
	}

	// scroll to bottom of the page
	public void scrollToBottom() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

}
