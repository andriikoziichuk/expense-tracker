Feature: Testing a REST API with Karate
  Scenario: Testing that GET response contains specific field
    Given url 'http://localhost:8080/api/categories/11'
    When method GET
    Then status 200
    And match $ contains {"id":11}

  Scenario: Creating new category
    Given url 'http://localhost:8080/api/categories'
    And request {"name": "pool", "type": "ACTIVITIES"}
    When method post
    Then status 200

    Given def id = response.id;
    And path '/' + id
    When method get
    Then status 200
    And match $.id == id


