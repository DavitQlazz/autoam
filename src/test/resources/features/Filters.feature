@someTag
Feature: Brand and max price tests

  Background: Navigate to home page
    Given the "https://auto.am" page
    And a cancel the notification popup

  Scenario: Filter by max price
    When I set the upper bound of price to 7000 dollar
    And apply the filter
    Then price of all the items in the search results should be less than 6900 dollar

  Scenario Outline: Filter by brand
    When I select '<brand>' as a car brand
    And apply the filter
    Then I should see only the '<brand>' brand cars on the search result
    And the count of items should appear on the blue button
    When I navigate to the search result last page
    Then I should see that the number of elements corresponds to the value of the blue button
    Examples:
      | brand  |
      | Audi   |
      | BMW    |
      | Toyota |

