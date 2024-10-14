package stepDefintions;

import java.io.IOException;
import java.util.List;
import org.junit.Assert;
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
		pages.validateHomePage();
	}

	// validate date
	@When("User navigates to the home screen")
	public void user_navigates_to_the_home_screen() {
		pages.validateHomePage();
	}

	@Then("User should see today's date displayed on home screen")
	public void user_should_see_today_s_date_displayed_on_home_screen() {
		pages.validateCurrentDate();
	}

	// Search data steps
	@When("User clicks the search bar and enters multiple data from the Excel file")
	public void user_clicks_the_search_bar_and_enters_multiple_data_from_the_excel_file() throws IOException {
		List<String[]> data = ExcelReader.readExcelData("Sheet1");
		// Loop through each row of data
		for (String[] row : data) {
			pages.handlePopup();
			// Assuming you want to use the first column for searching
			String searchItem = row[0];
			pages.search(searchItem);
			pages.validateSearchScrn(searchItem);
		}
	}

	@Then("User should land on the specific search results page for each data")
	public void user_should_land_on_the_specific_search_results_page_for_each_data() {
		Assert.assertTrue("Not on the searched screen.", pages.isOnSearchedScreen());
	}

	@Then("User closes the browser")
	public void user_closes_the_browser() {
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

	// Footer link steps

	@When("User clicks on a footer link")
	public void user_clicks_on_a_footer_link() {
		pages.selectTBrandStudio();

	}

	@When("User should be navigated to the correct screen")
	public void user_should_be_navigated_to_the_correct_screen() {
		pages.verifyTBrandScreen();
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
