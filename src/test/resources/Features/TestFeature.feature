Feature: Validating the features in the app

  Background: 
    Given User is on the home page

  @ValidateDate
  Scenario: Validate today's date
    When User navigates to the home screen
    Then User should see today's date displayed on home screen
    And User closes the browser

  @SearchData
  Scenario: Search data through Excel file
    When User clicks the search bar and enters multiple data from the Excel file
    And User should land on the specific search results page for each data
    Then User closes the browser

  @HeaderLink
  Scenario: Verifying the header section items
    When User clicks on any header section
    And User selects an item from the header section
    Then User should be navigated to the selected item screen
    And User quits the browser

  @FooterLink
  Scenario: Verify the footer links functionality
    When User clicks on a footer link
    And User should be navigated to the correct screen
    Then User quits the browser

  @TimeStoreAddtoCart
  Scenario: Add an item to the cart from the Time Store
    When User clicks on the time store link text
    And User should be navigated to the time store screen
    When User Chooses a book from the shop by category header
    And User selects a book from the list
    Then User adds the item to the cart
    And User Quit the browser
