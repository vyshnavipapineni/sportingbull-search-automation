Feature: SportingBull Search Functionality
  Background:
    Given I am on the SportingBull homepage



  @partial
  Scenario: Partial keyword match - OR behavior across words
    When I click the search button
    And I search for "Base"
    Then results should contain entries matching either "Baseball" or "Basketball" (or both)



  @unicode
  Scenario Outline: Unicode and special characters
    When I click the search button
    And I search for "<term>"
    Then the search should complete without breaking the UI

    Examples:
      | term     |
      | Fútbol   |
      | Atlético |


  @caseinsensitive
  Scenario Outline: Case insensitivity
    When I click the search button
    When I search for "<query>"
    Then the results should be the same as for "<canonical>"

    Examples:
      | query    | canonical |
      | mlb      | MLB       |
      | MlB      | MLB       |
      | football | Football  |



  @longinput
  Scenario Outline: Very long input trimming/handling
    When I click the search button
    When I search for "<input>"
    Then the system should handle the input gracefully

    Examples:
      | input                                                        |
      | AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA |