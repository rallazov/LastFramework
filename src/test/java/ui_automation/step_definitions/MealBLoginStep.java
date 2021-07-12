package ui_automation.step_definitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import ui_automation.pages.MealBLoginPage;
import ui_automation.utilities.ConfigurationReader;
import ui_automation.utilities.Driver;

public class MealBLoginStep {

    MealBLoginPage mealBLoginPage = new MealBLoginPage();


    @Given("Im on MealB login page")
    public void i_m_on_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("ui-config.properties", "mealB.url")+"Account/Login");
    }

    @Given("I enter valid MealB username")
    public void i_enter_valid_username() {
        mealBLoginPage.userNameInput.sendKeys(ConfigurationReader.getProperty("ui-config.properties", "mealB.userName"));
    }

    @Given("I enter valid MealB password")
    public void i_enter_valid_password() {
        mealBLoginPage.passwordInput.sendKeys(ConfigurationReader.getProperty("ui-config.properties", "mealB.password"));
    }

    @When("I click MealB log in button")
    public void i_click_log_in_button() {
        mealBLoginPage.loginBtn.click();
    }

    @Then("I land in the MealB homepage")
    public void i_land_in_the_homepage() {
        Assert.assertEquals(mealBLoginPage.userAccountName.getText(), ConfigurationReader.getProperty("ui-config.properties", "mealB.fullUserName"));
    }

}
