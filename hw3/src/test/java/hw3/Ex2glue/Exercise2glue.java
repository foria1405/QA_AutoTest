package hw3.Ex2glue;

import hw3.PageObjects.CommonElements;
import hw3.PageObjects.DifferentElements;
import hw3.PageObjects.IndexPageElements;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Exercise2glue {
    private WebDriver webDriver;
    private IndexPageElements indexPage;
    private CommonElements commonElements;
    private DifferentElements elementsPage;
    private Properties properties;
    private SoftAssert softAssert;

    @Before
    public void settingUp() throws IOException {

        Path resourceDirectory = Paths.get("src", "test", "resources", "test.properties");
        properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(resourceDirectory.toFile()), StandardCharsets.UTF_8));
        softAssert = new SoftAssert();
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to(properties.getProperty("URL"));
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @After
    public void exit() {
        webDriver.quit();
    }

    @When("User open test site by URL")
    public void openWebPage() {

        webDriver.get(properties.getProperty("URL"));
        indexPage = new IndexPageElements(webDriver);
        commonElements = new CommonElements(webDriver);
        elementsPage = new DifferentElements(webDriver);

    }

    @Then("Web page is opened")
    public void siteURLTest() {
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty("URL"));
    }

    @When("User log in as {string} - {string}")
    public void login(String userName, String password) {
        indexPage.login(userName, password);
    }


    @Then("User logged in as {string}")
    public void testUserLogin(String expectedUserName) {
        Assert.assertEquals(indexPage.getUsername(), expectedUserName);
    }

    @Then("Browser title is {string}")
    public void testBrowserTitle(String expectedTitle) {
        Assert.assertEquals(webDriver.getTitle(), expectedTitle);
    }

    @When("User click on Service subcategory in the Header")
    public void clickOnServiceSubcategory() {
        commonElements.getDropdown().click();
    }

    @Then("There are {int} elements with proper texts in the Header")
    public void testServiceSubcategoryElements(int amount) {
        List<String> serviceElements = commonElements.getHeaderDropdownElements();
        Assert.assertEquals(serviceElements.size(), amount);
        List<String> expectedTitles = Arrays.asList(properties.getProperty("fullServiceMenu").split(","));
        for (int i = 0; i < serviceElements.size(); i++) {
            softAssert.assertEquals(serviceElements.get(i), expectedTitles.get(i));
        }
        softAssert.assertAll();
    }

    @When("User click on Service subcategory in the Left Section")
    public void clickOnServiceSubcategoryInTheLeftSection() {
        commonElements.getLeftSectionDropdown().click();
    }

    @Then("There are {int} elements with proper texts in the Left Section")
    public void elementsWithProperTextsInLeftSection(int amount) {
        List<String> serviceElements = commonElements.getLeftSectionDropdownElements();
        Assert.assertEquals(serviceElements.size(), amount);
        List<String> expectedTitles = Arrays.asList(properties.getProperty("fullServiceMenuLowerCase").split(","));
        for (int i = 0; i < serviceElements.size(); i++) {
            softAssert.assertEquals(serviceElements.get(i), expectedTitles.get(i));
        }
        softAssert.assertAll();
    }

    @When("User click on Different Elements page in Service subcategory")
    public void clickOnDifferentElements() {
        WebElement service = commonElements.getDropdown();
        service.click();
        WebElement toggle = commonElements.getDifferentElementsPage();
        toggle.click();
    }

    @Then("{string} page is opened")
    public void testDifferentElements(String title) {
        Assert.assertEquals(webDriver.getTitle(), title);
    }

    @Then("There are {int} Check Boxes")
    public void testCheckBoxesNumber(int amount) {
        Assert.assertEquals(elementsPage.getCheckboxList().size(), amount);
        for(WebElement we : elementsPage.getCheckboxList())
        {
            softAssert.assertTrue(we.isDisplayed(),"");
        }
        softAssert.assertAll();
    }

    @And("There are {int} Radio Buttons")
    public void testRadioButtonsNumber(int amount) {
        Assert.assertEquals(elementsPage.getRadioList().size(), amount);
        for(WebElement we : elementsPage.getRadioList())
        {
            softAssert.assertTrue(we.isDisplayed(), "");
        }
        softAssert.assertAll();
    }

    @And("There is a dropdown")
    public void testDropdown() {
        Assert.assertTrue(elementsPage.getDropdown().isDisplayed());
    }

    @And("There are {int} buttons")
    public void testButtonsNumber(int amount) {
        Assert.assertEquals(elementsPage.getButtonsList().size(), amount);
        for(WebElement we : elementsPage.getButtonsList())
        {
            softAssert.assertTrue(we.isDisplayed(),"");
        }
        softAssert.assertAll();
    }

    @And("There is the Right Section")
    public void testRightSection() {
        Assert.assertTrue(elementsPage.getLogSideBar().isDisplayed());
    }

    @And("There is the Left Section")
    public void testLeftSection() {
        Assert.assertTrue(commonElements.getLeftSection().isDisplayed());
    }

    @When("User select Water and Wind Check Boxes")
    public void selectWaterAndWindCheckBoxes() {
        List<WebElement> checkboxes = elementsPage.getCheckboxList();
        WebElement water = checkboxes.get(0);
        WebElement wind = checkboxes.get(2);
        water.click();
        wind.click();
    }

    @Then("Check Boxes are clicked")
    public void testCheckBoxesSelection() {
        List<WebElement> checkboxList = elementsPage.getCheckboxList();
        softAssert.assertTrue(checkboxList.get(0).isSelected(), "");
        softAssert.assertTrue(checkboxList.get(2).isSelected(), "");
        softAssert.assertAll();
    }

    @And("There is an individual log row for each Check Box and value is corresponded to the status of Check Box")
    public void testCheckBoxesLog() {
        List<WebElement> logs = elementsPage.getLogs();
        softAssert.assertTrue(logs.get(0).isDisplayed(), "");
        softAssert.assertTrue(logs.get(1).isDisplayed(), "");
        String waterLog = logs.get(1).getText();
        String windLog = logs.get(0).getText();
        softAssert.assertTrue(waterLog.contains("Water") && (waterLog.contains("true")), "");
        softAssert.assertTrue(windLog.contains("Wind") && (windLog.contains("true")), "");
        softAssert.assertAll();
    }

    @When("User select Selen Radio Button")
    public void selenRadioButtonSelection() {
        List<WebElement> radioList = elementsPage.getRadioList();
        WebElement selen = radioList.get(3);
        selen.click();
    }

    @Then("There is a log row and value is corresponded to the status of Radio Button")
    public void testRadioButtonLog() {
        String radiobuttonLog = elementsPage.getLogs().get(0).getText();
        Assert.assertTrue(radiobuttonLog.contains("metal") && radiobuttonLog.contains("Selen"));
    }

    @When("User select Yellow in dropdown")
    public void dropdownOptionSelection() {
        elementsPage.getYellow().click();
    }

    @Then("There is a log row and value is corresponded to the selected value")
    public void testDropdownLog() {
        String dropdownLog = elementsPage.getLogs().get(0).getText();
        Assert.assertTrue(dropdownLog.contains("Colors") && dropdownLog.contains("Yellow"));
    }

    @When("User unselect Check Boxes")
    public void checkboxesUnselection() {
        List<WebElement> checkboxes = elementsPage.getCheckboxList();
        WebElement water = checkboxes.get(0);
        WebElement wind = checkboxes.get(2);
        water.click();
        wind.click();
    }

    @Then("Check Boxes are unselected")
    public void testUnselectionCheckBoxes() {
        List<WebElement> checkboxList = elementsPage.getCheckboxList();
        softAssert.assertFalse(checkboxList.get(0).isSelected());
        softAssert.assertFalse(checkboxList.get(2).isSelected());
        softAssert.assertAll();
    }


    @And("There is an individual log row for each Check Box and value is corresponded to the unselected status of Check Box")
    public void testUnselectedStatusCheckBoxes() {
        List<WebElement> logRow = elementsPage.getLogs();
        softAssert.assertTrue(logRow.get(0).isDisplayed(), "");
        softAssert.assertTrue(logRow.get(1).isDisplayed(), "");
        String waterLog = logRow.get(1).getText();
        String windLog = logRow.get(0).getText();
        softAssert.assertTrue(waterLog.contains("Water") && (waterLog.contains("false")), "");
        softAssert.assertTrue(windLog.contains("Wind") && (windLog.contains("false")), "");
        softAssert.assertAll();
    }
}