Feature: Dev.by features
  In this feature file we are going to store dev.by feature

  Background:
    Given I open main page

  @login
  Scenario: Login with invalid credentials (imperative)
    When I click on login icon
    And I enter "someLogin" as login
    And I enter "somePassword" password
    And I click on login button
    Then I should see error message

  @login @regression
  Scenario: Login with invalid credentials (declarative)
    When I go to login page
    And I login with "login" as login and "password" as password
    Then I should see error message

  @regression
  Scenario Outline: Login with invalid credentials (dataProvider)
    When I click on login icon
    And I enter "<Login>" as login
    And I enter "<Password>" password
    And I click on login button
    Then I should see error message
    Examples:
      | Login     | Password     |
      | someLogin | somePassword |
      | 123       | 123          |
      | a         | someLogin    |

  @vacancy
  Scenario: I can go to iSoft vacancy page
  When I click on iSoft vacancies
  Then I should be redirected to vacancy page
  And I should see that vacancy menu item is active
  And I should see that vacancy item colour is different

  Scenario: Positive login
    When I go to login page
    And I login with "login" as login and "password" as password
    Then I should see main page should be opened
    And I should see auth icon

  Scenario: I check tag and header on conformity
  When I click on news-tag
  Then I should see that tag and header are identical

  Scenario: I check tags by date
  When I found preview list
  And I find the mark date
  Then I check that date mark content "СЕГОДНЯ"

  Scenario: I check pop up
  When I go to events page
  And I click on eventButton
  Then I should see pop up with "Иду! =)"

#  Scenario: Login with invalid credentials (declarative)
#    When I go to login page
#    And I login with "login" as login and "password" as password
#    Then I should see 5 error message

#  Scenario: Login with invalid credentials (data table)
#    When I go to login page
#    And Я логинюсь со следующими данными
#      | Login     | Password     |
#      | someLogin | somePassword |
#    Then I should see error message