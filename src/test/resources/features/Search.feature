Feature: SportingBull Search Functionality
  Background:
    Given I am on the SportingBull homepage

#  Scenario: Search with valid sport name
#    When I click the search button
#    And I enter "Football" in the search box
#    Then I should see search results count matches actual items


#  Scenario: Synonym handling (MLB / Baseball)
#    When I search for "Baseball"
#    And I search for "MLB"
#    Then both result sets should contain baseball-related events

  @noresults
  Scenario: No results message for unknown token
    When I click the search button
    When I search for "xyz123nonexistent"
    Then I should see a message "There are no results that match your search. Try again."