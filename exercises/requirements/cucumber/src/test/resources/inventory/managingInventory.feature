Feature: Managing inventory

  Scenario: Adding a product actually adds it to the inventory
    Given there are 4 sweaters in the inventory
    When the User adds 1 sweaters to the inventory
    Then there are 5 sweaters in the inventory


  Scenario: Removing a product actually removes it from the inventory
    Given there are 4 sweaters in the inventory
    When the User removes 1 sweaters from the inventory
    Then there are 3 sweaters in the inventory


  Scenario: The state of the inventory corresponds to the actual state
    Given there are 4 sweaters in the inventory
    Then there are 4 sweaters in the inventory

  Scenario: Returning a product adds it back to the inventory
    Given there are 4 sweaters in the inventory
    When a Client returns 1 sweaters
    Then there are 5 sweaters in the inventory

  Scenario: Removing the last product in stock is not possible
    Given there are 1 sweaters in the inventory
    When the User removes 1 sweaters from the inventory
    Then there are 1 sweaters in the inventory