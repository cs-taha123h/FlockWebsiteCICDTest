package com.sparta.FlockApp.steps;

import com.sparta.FlockApp.pages.LoginPage;
import com.sparta.FlockApp.pages.SignupPage;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class SignUpStepdefs {

    private LoginPage loginPage;
    private SignupPage signupPage;

    @When("I click the sign up link")
    public void iClickTheSignUpLink() {
        loginPage.clickSignupButton();

    }

    @Then("I should be redirected to the signup page")
    public void iShouldBeRedirectedToTheSignupPage() {
        MatcherAssert.assertThat(
                signupPage.getDriver().getCurrentUrl(),
                Matchers.is("http://127.0.0.1:8000/flock/signup.html")
        );
    }

    @When("I enter {string} as my first name")
    public void iEnterAsMyFirstName(String firstName) {
        signupPage.enterFirstName(firstName);
    }

    @And("I enter {string} as my last name")
    public void iEnterAsMyLastName(String lastName) {
        signupPage.enterLastName(lastName);
    }

    @And("I enter {string} as my username")
    public void iEnterAsMyUsername(String username) {
        signupPage.enterUsername(username);
    }

    @And("I enter {string} as my password")
    public void iEnterAsMyPassword(String password) {
        signupPage.enterPassword(password);
    }

    @And("I confirm my password with {string}")
    public void iConfirmMyPasswordWith(String confirmPassword) {
        signupPage.enterConfirmPassword(confirmPassword);
    }

    @And("I click the Create Account button")
    public void iClickTheCreateAccountButton() {
        signupPage.clickSignupButton();
    }

    @Then("my account should be created successfully")
    public void myAccountShouldBeCreatedSuccessfully() {
        MatcherAssert.assertThat(
                signupPage.getDriver().getCurrentUrl(),
                Matchers.is("http://127.0.0.1:8000/flock/onboarding.html")
        );
    }

    @Then("my account should not be created")
    public void myAccountShouldNotBeCreated() {
        MatcherAssert.assertThat(
                signupPage.getDriver().getCurrentUrl(),
                Matchers.is("http://127.0.0.1:8000/flock/signup.html")
        );
    }

    @And("I should see the first name error {string}")
    public void iShouldSeeTheFirstNameError(String expectedError) {
        MatcherAssert.assertThat(
                signupPage.getFirstNameError(),
                Matchers.is(expectedError)
        );
    }

    @And("I should see the username error {string}")
    public void iShouldSeeTheUsernameError(String expectedError) {
        MatcherAssert.assertThat(
                signupPage.getUsernameError(),
                Matchers.is(expectedError)
        );    }

    @And("I should see the password error {string}")
    public void iShouldSeeThePasswordError(String expectedError) {
        MatcherAssert.assertThat(
                signupPage.getPasswordError(),
                Matchers.is(expectedError)
        );
    }

    @Then("I should see the confirm password error {string}")
    public void iShouldSeeTheConfirmPasswordError(String expectedError) {
        MatcherAssert.assertThat(
                signupPage.getConfirmPasswordError(),
                Matchers.is(expectedError)
        );
    }
}
