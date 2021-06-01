Feature: Ex1

  Background:
    When User open test site by URL
    Then Web page is opened
    When User log in as "Roman" - "Jdi1234"
    Then User logged in as "ROMAN IOVLEV"


  Scenario: Test browser title
    Then Browser title is "Home Page"

  Scenario: Test contents header section
    Then Header section contains expected items

  Scenario: Test index page images
    Then There are 4 benefit images
    And Images are displayed
    Then There are 4 texts under images
    And They have proper text

  Scenario: Test index page of main headers text
    Then Text is displayed and equals to expected result

  Scenario: Test center iframe
    Then IFrame is displayed
    When User switch to the iframe
    Then EPAM logo is displayed
    And User switch to original window back

  Scenario: Test sub header text
    Then Sub header text is "JDI GITHUB"
    And Sub header text is a link "https://github.com/epam/JDI"

  Scenario: Test Left Section
    Then There is a Left Section

  Scenario: Test Footer
    Then There is a Footer