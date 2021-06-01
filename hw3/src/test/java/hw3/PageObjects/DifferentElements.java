package hw3.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DifferentElements {
    @FindBy(xpath = "//*[contains(@type,'checkbox')]")
    List<WebElement> checkboxList;
    @FindBy(xpath = "//*[contains(@type,'radio')]")
    List<WebElement> radioList;
    @FindBy(xpath = "//*[@class='colors']")
    WebElement colorsDropdown;
    @FindBy(xpath = "//*[@class='uui-button']")
    List<WebElement> buttonsList;
    @FindBy(xpath = "//*[@name='log-sidebar']")
    WebElement logSideBar;
    @FindBy(xpath = "//*[@class='panel-body-list logs']/li")
    List<WebElement> logs;
    @FindBy(xpath = "/html/body/div/div[2]/main/div[2]/div/div[4]/select/option[4]")
    WebElement yellow;

    public DifferentElements (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getCheckboxList() {
        return checkboxList;
    }

    public List<WebElement> getRadioList() {
        return radioList;
    }

    public List<WebElement> getButtonsList() {
        return buttonsList;
    }

    public WebElement getLogSideBar() {
        return logSideBar;
    }

    public List<WebElement> getLogs() {
        return logs;
    }

    public WebElement getDropdown() {
        return colorsDropdown;
    }

    public WebElement getYellow() {
        return yellow;
    }
}
