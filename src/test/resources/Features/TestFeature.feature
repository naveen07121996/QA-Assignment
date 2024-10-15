Feature: Validating the features in the app

  Background: 
    Given User is on the home page

  @ValidateDate
  Scenario: Validate today date
    When User navigates to the home screen
    Then User should see today date displayed on home screen
    And User closes the browser

  @SearchDate
  Scenario: Search data through invalid date range
    When User clicks the search bar and searches for "Crimes" news
    Then User should be navigated to the crimes news screen
    And User selects a specific date range in the Date range dropdown and enters invalid date ranges
    Then User should see an error message indicating an invalid date range
    And User quits the browser

  @HeaderLink
  Scenario: Verifying the header section items
    When User clicks on any header section
    And User selects an item from the header section
    Then User should be navigated to the selected item screen
    And User quits the browser

  @TimestoreLogin
  Scenario: Login with multiple invalid credentials
    When User enters the time store and click signin
    When User enters invalid credentials and verify
    Then User quits the browser

  @TimeStoreAddtoCart
  Scenario: Add an item to the cart from the Time Store
    When User clicks on the time store link text
    And User should be navigated to the time store screen
    When User Chooses a book from the shop by category header
    And User selects a book from the list
    Then User adds the item to the cart
    And User Quit the browser
