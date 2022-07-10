@expediasearch
Feature: Testing search flow on Expedia

  Scenario: Booking multi-flights for 4 people
    Given browser is open for Expedia
    And user is on Expedia home page
    When Home page is loaded
    Then user click Flighs button
    And user click Multi-city button
    And user select number of traveler
    And user click Add another flight button
    And user edit Flights and search
    And user select Flights
    And user confirm trip details to match the user selections
