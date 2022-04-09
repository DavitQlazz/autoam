@someTag
Feature: Filter cars by Brand

  Background:
    Given the "https://auto.am" page
    And a cancel the notification popup

  Scenario: Filter by brand
    When I select "Audi" as a car brand
    And apply the filter
    Then I should see only the "Audi" brand cars on the appeared list


  Scenario: Filter by max price
    When I set the upper bound of price to "$7000" dollars

