Feature: Account operations

  Scenario: deposit 100 and balance check
    Given a new account
    When a deposit of 100 is performed
    Then the balance should be 100

  Scenario: multiple deposit and balance check
    Given a new account
    When a deposit of 100 is performed
    And a deposit of 200 is performed
    Then the balance should be 300

  Scenario: deposit 100, deposit 200, withdraw 120 and balance check
    Given a new account
    When a deposit of 100 is performed
    And a deposit of 200 is performed
    And a withdraw of 120 is performed
    Then the balance should be 180

  Scenario: deposit 100, deposit 200, withdraw 120 and statement history check
    Given a new account
    When a deposit of 100 is performed
    And a deposit of 200 is performed
    And a withdraw of 120 is performed
    Then the statement history should be
      | operation | amount | balance |
      | Deposit   | 100    | 100     |
      | Deposit   | 200    | 300     |
      | Withdraw  | -120   | 180     |

  Scenario: multiple operations and check history
    Given a new account
    When the following operations are done
      | operation | amount |
      | Deposit   | 100    |
      | Deposit   | 200    |
      | Withdraw  | 120    |
    Then the statement history should be
      | operation | amount | balance |
      | Deposit   | 100    | 100     |
      | Deposit   | 200    | 300     |
      | Withdraw  | -120   | 180     |