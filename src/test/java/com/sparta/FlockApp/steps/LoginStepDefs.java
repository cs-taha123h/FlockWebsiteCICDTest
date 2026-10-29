package com.sparta.FlockApp.steps;

import com.sparta.FlockApp.pages.LoginPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class LoginStepDefs {

    @Managed
    private LoginPage loginPage;

    @Given("the user is logged in on the flockApp")
    public void loginUserIn(){
        loginPage.enterAndSubmitFields("nish", "mandal");
    }

    @Given("I am on the FlockApp login page")
    public void iAmOnTheFlockAppLoginPage() {
        loginPage.open();
    }

    @When("I enter a valid email address")
    public void iEnterAValidEmailAddress() {
        loginPage.enterUsername("nish");
    }

    @And("I enter a valid password")
    public void iEnterAValidPassword() {
        loginPage.enterPassword("mandal");
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should be redirected to the home page")
    public void iShouldBeRedirectedToTheHomePage() {
        MatcherAssert.assertThat(loginPage.getDriver().getCurrentUrl(), Matchers.is("http://127.0.0.1:8000/flock/home.html"));
    }

    @When("I enter a valid email but the password is {string}")
    public void iEnterAValidEmailButThePasswordIs(String password) {
        loginPage.enterAndSubmitFields("nish", password);
    }

    @Then("after I have clicked login I should see an error message saying {string}")
    public void iShouldSeeAnErrorMessageSaying(String error) {
        MatcherAssert.assertThat(loginPage.getErrorMessage(), Matchers.is(error));
    }

    @And("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        MatcherAssert.assertThat(loginPage.getDriver().getCurrentUrl(), Matchers.is("http://127.0.0.1:8000/flock/login.html"));
    }

    @When("I enter a empty email")
    public void iEnterAEmptyEmail() {
        loginPage.enterUsername("");
    }

    @And("I enter a empty password")
    public void iEnterAEmptyPassword() {
        loginPage.enterPassword("");
    }

    @Then("I should get an username error saying {string}")
    public void iShouldGetAnUsernameErrorSaying(String usernameError) {
        MatcherAssert.assertThat(loginPage.getUsernameError(), Matchers.is(usernameError));
    }

    @Then("I should get an password error saying {string}")
    public void iShouldGetAnPasswordErrorSaying(String passwordError) {
        MatcherAssert.assertThat(loginPage.getPasswordError(), Matchers.is(passwordError));
    }

    @When("I navigate back")
    public void iNavigateBack() {
        loginPage.navigateBack();
    }


    @And("I should not be told I have been logged out")
    public void iShouldNotBeToldIHaveBeenLoggedOut() {
        MatcherAssert.assertThat(loginPage.isSignOutVisible(), Matchers.is(false));
    }

    @When("I refresh")
    public void iRefresh() {
        loginPage.refreshPage();
    }

    @When("I enter a valid email which is {string}")
    public void iEnterAValidEmailWhichIs(String username) {
        loginPage.enterUsername(username);
    }
}
