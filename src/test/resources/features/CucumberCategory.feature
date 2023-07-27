Feature: Category controller output
  Scenario: Putting one category to the db
    When I create one category
    Then There is 1 category in the db

  Scenario: Putting some categories to the db
    When I create 3 categories
    Then There are 4 categories in the db

  Scenario: Check for existence in db
    When I get category by id 2
    Then Controller should return category by id 2