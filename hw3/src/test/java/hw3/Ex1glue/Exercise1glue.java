package hw3.Ex1glue;

import hw3.PageObjects.CommonElements;
import hw3.PageObjects.IndexPageElements;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Exercise1glue {

    private WebDriver webDriver;
    private IndexPageElements indexPage;
    private CommonElements commonElements;
    private Properties properties;

    @Before
    public void settingUp() throws IOException {

        Path resourceDirectory = Paths.get("src", "test", "resources", "test.properties");
        properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream(resourceDirectory.toFile()), StandardCharsets.UTF_8));
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

    }

    @Then("Web page is opened")
    public void testSiteURL() {
        Assert.assertEquals(webDriver.getCurrentUrl(), properties.getProperty("URL"));
    }

    @When("User log in as {string} - {string}")
    public void logIn(String userName, String password) {
        indexPage.login(userName, password);
    }


    @Then("User logged in as {string}")
    public void testUserName(String expectedUserName) {
        Assert.assertEquals(indexPage.getUsername(), expectedUserName);
    }

    @Then("Browser title is {string}")
    public void testBrowserTitle(String expectedTitle) {
        Assert.assertEquals(webDriver.getTitle(), expectedTitle);
    }

    @Then("Header section contains expected items")
    public void testContentsHeaderSection() {
        ArrayList<String> menuElements = commonElements.getHeaderElementsTitles();
        List<String> serviceMenuExpected = Arrays.asList(properties.getProperty("serviceMenuExpected").split(","));
        Assert.assertTrue(menuElements.containsAll(serviceMenuExpected));
        Assert.assertTrue(serviceMenuExpected.containsAll(menuElements));
    }

    @Then("There are {int} benefit images")
    public void testIndexPageImages(int amount) {
        Assert.assertEquals(indexPage.getImagesList().size(), amount);
    }

    @And("Images are displayed")
    public void imagesDisplayedTest() {
        for(WebElement we : indexPage.getImagesList())
        {
            Assert.assertTrue(we.isDisplayed());
        }
    }

    @Then("There are {int} texts under images")
    public void testUnderImages(int amount) {
        Assert.assertEquals(indexPage.getTextList().size(), amount);
    }

    @And("They have proper text")
    public void testIndexPageTextContents() {

        List<String> indexPageExpectedTexts = Arrays.asList(properties.getProperty("underTexts").split(";"));
        List<WebElement> indexPageActualTexts = indexPage.getTextList();
        for(int i = 0; i < indexPageActualTexts.size(); i++)
        {
            Assert.assertEquals(indexPageActualTexts.get(i).getText().replaceAll("…", ""), indexPageExpectedTexts.get(i));
        }

    }

    @Then("Text is displayed and equals to expected result")
    public void testIndexPageMainHeadersTexts() {
        Assert.assertEquals(indexPage.getText1().replaceAll("…", ""), properties.getProperty("mainText1"));
        Assert.assertEquals(indexPage.getText2(), properties.getProperty("mainText2"));
    }

    @Then("IFrame is displayed")
    public void testIframeIsDisplayed() {
        List<WebElement> imagesList = webDriver.findElements(By.tagName("iframe"));
        Assert.assertTrue(imagesList.get(0).isDisplayed());
    }

    @When("User switch to the iframe")
    public void switchToTheIframe() {
        webDriver.switchTo().frame(properties.getProperty("frameSite"));
    }

    @Then("EPAM logo is displayed")
    public void testEpamLogo() {
        Assert.assertFalse(indexPage.getLogo().isEmpty());
    }

    @And("User switch to original window back")
    public void switchToOriginalWindowBack() {
        webDriver.switchTo().parentFrame();
        webDriver.switchTo().defaultContent();
    }

    @Then("Sub header text is {string}")
    public void testSubHeaderText(String expectedText) {
        Assert.assertEquals(indexPage.getTextCenter().getText(), expectedText);
    }

    @And("Sub header text is a link {string}")
    public void testSubHeaderLink(String URL) {
        Assert.assertEquals(indexPage.getTextCenter().getAttribute("href"), URL);
    }

    @Then("There is a Left Section")
    public void testLeftSection() {
        Assert.assertTrue(commonElements.getLeftSection().isDisplayed());
    }

    @Then("There is a Footer")
    public void testFooter() {
        Assert.assertTrue(indexPage.getFooter().isDisplayed());
    }
}
