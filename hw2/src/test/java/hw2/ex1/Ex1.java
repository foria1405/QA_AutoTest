package hw2.ex1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;

import java.util.List;

public class Ex1 {
    private WebDriver webDriver;

    @BeforeClass
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.navigate().to("https://jdi-testing.github.io/jdi-light/index.html");
        webDriver.manage().window().maximize();

    }

    @AfterClass
    public void closeBrowser() {

        /* 17. Close Browser */

        webDriver.quit();
    }

    @Test
    public void siteURLTest() {

        /* 1. Open test site by URL */

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://jdi-testing.github.io/jdi-light/index.html");
    }

    @Test
    public void browserTitleTest() {

        /* 2. Assert Browser title  */

        Assert.assertEquals(webDriver.getTitle(), "Home Page");
    }

    @Test
    public void loginTest() {

        /* 3. Perform login */

        webDriver.findElement(By.xpath("//*[@id='user-icon']")).click();
        webDriver.findElement(By.xpath("//*[@id='name']")).sendKeys("Roman");
        webDriver.findElement(By.xpath("//*[@id='password']")).sendKeys("Jdi1234");
        webDriver.findElement(By.xpath("//*[@id='login-button']")).click();


        /* 4) Assert User name in the left-top side of screen that user is loggined */

        String currentUsername = webDriver.findElement(By.xpath("//*[@id='user-name']")).getAttribute("innerHTML");
        Assert.assertEquals(currentUsername, "Roman Iovlev");

        /* 5)  Assert Browser title */

        Assert.assertEquals(webDriver.getTitle(), "Home Page");
    }

    @Test
    public void testHeaderSection() {

        /* 6) Assert that there are 4 items on the header section are displayed
         and they have proper texts*/

        List<WebElement> headerObject = webDriver.findElements(By.xpath("//*[starts-with(@class,'uui-header')]/nav/ul[1]/li/a"));

        Assert.assertEquals(headerObject.size(), 4);

        for(WebElement we : headerObject) {

            Assert.assertTrue(we.isDisplayed());
        }

        String[] expectedTestHeaderObject = {"HOME","CONTACT FORM","SERVICE","METALS & COLORS"};

        for(int i = 0; i < headerObject.size(); i++) {
            String actualText = headerObject.get(i).getText();
            Assert.assertEquals(actualText, expectedTestHeaderObject[i]);
        }
    }

    @Test
    public void testIndexPageDisplayed() {

        /* 7) Assert that there are 4 images on the Index Page and they are displayed */

        List<WebElement> indexPageImages = webDriver.findElements(By.xpath("//*[@class='benefit-icon']"));

        Assert.assertEquals(indexPageImages.size(), 4);

        for(WebElement we : indexPageImages) {
            Assert.assertTrue(we.isDisplayed());
        }

        /* 8) Assert that there are 4 texts on the Index Page under icons and they have proper text */

        List<WebElement> textUnderIcons = webDriver.findElements(By.xpath("//*[@class='benefit-txt']"));

        Assert.assertEquals(textUnderIcons.size(), 4);

        String[] indexPageCaptionsExpectedTexts = {
                "To include good practices\nand ideas from successful\nEPAM project",
                "To be flexible and\ncustomizable",
                "To be multiplatform",
                "Already have good base\n(about 20 internal and\nsome external projects),\nwish to get more…"};

        for(int i = 0; i < textUnderIcons.size(); i++) {

            Assert.assertTrue(textUnderIcons.get(i).isDisplayed());
            Assert.assertEquals(textUnderIcons.get(i).getText(), indexPageCaptionsExpectedTexts[i]);

        }
    }

    @Test
    public void testTextOfMainHeaders() {

        /* 9) Assert a text of the main headers */

        String mainTitle = webDriver.findElement(By.xpath("//*[@name='main-title']")).getText();
        String mainText = webDriver.findElement(By.xpath("//*[@name='jdi-text']")).getText();
        String expectedMainTitle = "EPAM FRAMEWORK WISHES…";
        String expectedMainText =
                "LOREM IPSUM DOLOR SIT AMET, CONSECTETUR ADIPISICING ELIT, " +
                        "SED DO EIUSMOD TEMPOR INCIDIDUNT UT LABORE ET DOLORE MAGNA ALIQUA. " +
                        "UT ENIM AD MINIM VENIAM, QUIS NOSTRUD EXERCITATION ULLAMCO LABORIS NISI " +
                        "UT ALIQUIP EX EA COMMODO CONSEQUAT DUIS AUTE IRURE DOLOR IN REPREHENDERIT " +
                        "IN VOLUPTATE VELIT ESSE CILLUM DOLORE EU FUGIAT NULLA PARIATUR.";
        Assert.assertEquals(mainTitle, expectedMainTitle);
        Assert.assertEquals(mainText, expectedMainText);
    }

    @Test
    public void testIframeInCenter() {

        /* 10) Assert that there is the iframe in the center of page */

        WebElement centerIFrame = webDriver.findElement(By.xpath("//*[@id='second_frame']"));
        Assert.assertTrue(centerIFrame.isDisplayed());

        /* 11) Switch to the iframe and check that there is Epam logo in the left top conner of iframe*/

        webDriver.switchTo().frame(centerIFrame);
        WebElement logo = webDriver.findElement(By.xpath("//*[@id='epam-logo']"));
        Assert.assertTrue(logo.isDisplayed());

        /* 12) Switch to original window back*/

        webDriver.switchTo().parentFrame();
    }

    @Test
    public void textTextOfSubHeader() {
        WebElement subHeader = webDriver.findElement(By.xpath("//*[@class='text-center']/a"));

        /* 13) Assert a text of the sub header */

        String textSubHeader = subHeader.getText();
        Assert.assertEquals(textSubHeader, "JDI GITHUB");

        /* 14) Assert that JDI GITHUB is a link and has a proper URL */

        String subHeaderLink = subHeader.getAttribute("href");
        Assert.assertEquals(subHeaderLink, "https://github.com/epam/JDI");

    }

    @Test
    public void testLeftSection() {

        /* 15) Assert that there is Left Section*/

        Assert.assertTrue(webDriver.findElement(By.xpath("//*[@name='navigation-sidebar']")).isDisplayed());
    }

    @Test
    public void testFooter() {

        /* 16) Assert that there is Footer*/
        
        Assert.assertTrue(webDriver.findElement(By.xpath("//*[@class='footer-bg']")).isDisplayed());
    }

}

