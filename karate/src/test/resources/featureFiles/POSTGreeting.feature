Feature: POST Greeting

  Background:
    * url baseURL

  Scenario Outline: When <message> is posted in POST Request with path <version>
    Given path '<version>/greeting'
    And request { message:"<message>"}
    When method POST
    Then status 200
    Then match response == <result>
    Examples:
      | version | message      | result               |
      | 1.0     | Hi from      | 'Hi from v1.0'       |
      | 2.0     | Hello from   | 'Hello from v2.0'    |