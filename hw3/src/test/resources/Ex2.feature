Feature: Ex2

  Background:
    When User open test site by URL
    Then Web page is opened
    When User log in as "Roman" - "Jdi1234"
    Then User logged in as "ROMAN IOVLEV"

  Scenario: Test browser title
    Then Browser title is "Home Page"

  Scenario: Test Header Service category contents
    When User click on Service subcategory in the Header
    Then There are 9 elements with proper texts in the Header

  Scenario: Test Left Section Service category contents
    When User click on Service subcategory in the Left Section
    Then There are 9 elements with proper texts in the Left Section

  Scenario: Test elements on the Different Elements Page
    When User click on Different Elements page in Service subcategory
    Then "Different Elements" page is opened

    Then There are 4 Check Boxes
    And There are 4 Radio Buttons
    And There is a dropdown
    And There are 2 buttons
    And There is the Right Section
    And There is the Left Section

    When User select Water and Wind Check Boxes
    Then Check Boxes are clicked
    And  There is an individual log row for each Check Box and value is corresponded to the status of Check Box

    When User select Selen Radio Button
    Then There is a log row and value is corresponded to the status of Radio Button

    When User select Yellow in dropdown
    Then There is a log row and value is corresponded to the selected value

    When User unselect Check Boxes
    Then Check Boxes are unselected
    And  There is an individual log row for each Check Box and value is corresponded to the unselected status of Check Box