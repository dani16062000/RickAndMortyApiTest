Feature: Getting characters of Rick and Morty

  @name
  Scenario: Validate that the response getting a character by exact name is 200
    Given Rick and Morty API is available
    When I send a GET request to retrieve the character by name "Rick Sanchez"
    Then the response status code is 200

  @id
  Scenario: Validate that the response getting a character by ID is 200
    Given Rick and Morty API is available
    When I send a GET request to retrieve the character with id "145"
    Then the response status code is 200
    And id of the character equals 145

  @name
  Scenario: Validate that the response getting a character by exact name contains a list of characters
    Given Rick and Morty API is available
    When I send a GET request to retrieve the character by name "Rick Sanchez"
    Then the response is a list of characters which names equals "Rick Sanchez"

  @name
  Scenario: Validate that the response getting a character incomplete name contains a list of characters
    Given Rick and Morty API is available
    When I send a GET request to retrieve the character by name "ri"
    Then the response is a list of characters suggestions
  @name
  Scenario: Validate that the response getting a character by non existing name
    Given Rick and Morty API is available
    When I send a GET request to retrieve the character by name "Nonresult"
    Then the response status code is 404
    And validate that the response body contains the message "There is nothing here"

  @id
  Scenario: Validate that the response getting a character by non existing ID
    Given Rick and Morty API is available
    When I send a GET request to retrieve the character with id "0"
    Then the response status code is 404
    And validate that the response body contains the message "Character not found"

  @name
  Scenario: Validate that the response getting a character contains all the attributes
    Given Rick and Morty API is available
    When I send a GET request to retrieve the character by name "Rick Sanchez"
    Then validate the response body status field is not null
    And validate the response body created field is not null

  @name
  Scenario: Validate that the response getting a character by name and attribute
    Given Rick and Morty API is available
    When I send a GET request to retrieve the character by name "Rick Sanchez" and status "Alive"
    Then the response is a list of characters which names equals "Rick Sanchez"
    And every character status equals "Alive"

  @id
  Scenario: Validate that you can get 3 characters by id
    Given Rick and Morty API is available
    When I send a GET request to retrieve the characters with ids "145" "2" "200"
    Then the API response contains three characters that match with IDs "145" "2" "200"




