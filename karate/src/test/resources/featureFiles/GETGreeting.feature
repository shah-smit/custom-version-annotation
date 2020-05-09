Feature: GET Greeting

  Background:
    * url baseURL

  Scenario Outline: Get Greeting with version <version> gives default response
    Given path '<version>/greeting'
    When method GET
    Then status 200
    Then match response == <result>
    Examples:
    | version | result               |
    | 1.0     | 'Greeting from v1.0' |
    | 2.0     | 'Greeting from v2.0' |

  Scenario Outline: Get Greeting with version <version> and single message give custom response
    Given path '<version>/greeting/<message>'
    When method GET
    Then status 200
    Then match response == <result>
    Examples:
      | version | message      | result               |
      | 1.0     | Hi from      | 'Hi from v1.0'       |
      | 2.0     | Hello from   | 'Hello from v2.0'    |

  Scenario Outline: Get Greeting with version <version> and multiple messages in parameters
    Given path '<version>/greeting/<message>/<secondMessage>'
    When method GET
    Then status 200
    Then match response == <result>
    Examples:
      | version | message      | secondMessage | result               |
      | 1.0     | Hi           | from          |'Hi from v1.0'       |
      | 1.0     | Hello        | from          |'Hello from v1.0'    |
