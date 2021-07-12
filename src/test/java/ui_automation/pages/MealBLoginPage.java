package ui_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui_automation.utilities.Driver;

public class MealBLoginPage {

    WebDriver driver;
    public MealBLoginPage(){
        driver= Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="usernameOrEmailAddress")
    public WebElement userNameInput;

    @FindBy(name="Password")
    public WebElement passwordInput;

    @FindBy(id="LoginButton")
    public WebElement loginBtn;

    @FindBy(id="HeaderCurrentUserName")
    public WebElement userAccountName;


}
