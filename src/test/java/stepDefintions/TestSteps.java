package stepDefintions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import pages.TestPage;
import utilities.ConfigReader;
import utilities.ExcelReader;

public class TestSteps {

	WebDriver driver = Hooks.driver;
	ConfigReader configReader = new ConfigReader();
	TestPage pages;

	// Common step
	@Given("User is on the home page")
	public void user_is_on_the_home_page() {
		driver.get(configReader.getProperty("app.url"));
		pages = new TestPage(driver);
		pages.handlePopup();
		pages.validateHomePage();
	}

	// validate date
	@When("User navigates to the home screen")
	public void user_navigates_to_the_home_screen() {
		pages.validateHomePage();
	}

	@Then("User should see today date displayed on home screen")
	public void user_should_see_today_date_displayed_on_home_screen() {
		pages.validateCurrentDate();
	}

	@Then("User closes the browser")
	public void user_closes_the_browser() {
		pages.closeBrowser();
	}

	// search data invalid date
	@When("User clicks the search bar and searches for {string} news")
	public void user_clicks_the_search_bar_and_searches_for_news(String searchCrimes) {
		pages.search(searchCrimes);
	}

	@Then("User should be navigated to the crimes news screen")
	public void user_should_be_navigated_to_the_crimes_news_screen() {
		pages.validateSearchScrn();
	}

	@Then("User selects a specific date range in the Date range dropdown and enters invalid date ranges")
	public void user_selects_a_specific_date_range_in_the_date_range_dropdown_and_enters_invalid_date_ranges() {
		pages.selectDates();
	}

	@Then("User should see an error message indicating an invalid date range")
	public void user_should_see_an_error_message_indicating_an_invalid_date_range() {
		pages.verifyErrorMsg();
		pages.closeBrowser();
	}

	// Header section steps

	@When("User clicks on any header section")
	public void user_clicks_on_any_header_section() {
		pages.selectGameItem();
	}

	@When("User selects an item from the header section")
	public void user_selects_an_item_from_the_header_section() {
		pages.selectGame();
	}

	@Then("User should be navigated to the selected item screen")
	public void user_should_be_navigated_to_the_selected_item_screen() {
		pages.verifyGameText();
	}

	@Then("User quits the browser")
	public void user_quits_the_browser() {
		pages.closeBrowser();
	}

	// Time Store login

	@When("User enters the time store and click signin")
	public void user_enters_the_time_store_and_click_signin() {
		pages.clickTimeStoreLink();
		pages.clickSignIn();
	}

	@When("User enters invalid credentials and verify")
	public void user_enters_invalid_credentials_and_verify() throws IOException {
		pages.invalidLogin();
	}

	// TimeStore steps

	@When("User clicks on the time store link text")
	public void user_clicks_on_the_time_store_link_text() {
		pages.clickTimeStoreLink();
	}

	@When("User should be navigated to the time store screen")
	public void user_should_be_navigated_to_the_time_store_screen() {
		pages.verifySubTitle();
	}

	@When("User Chooses a book from the shop by category header")
	public void user_chooses_a_book_from_the_shop_by_category_header() throws InterruptedException {
		pages.shopByCategory();
	}

	@When("User selects a book from the list")
	public void user_selects_a_book_from_the_list() {
		pages.selectABook();
	}

	@Then("User adds the item to the cart")
	public void user_adds_the_item_to_the_cart() {
		pages.addItemToCart();
	}

	@Then("User Quit the browser")
	public void user_quit_the_browser() {
		pages.closeBrowser();
	}

}
