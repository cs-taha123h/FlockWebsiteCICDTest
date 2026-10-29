package com.sparta.FlockApp.steps;

import com.sparta.FlockApp.pages.HomePage;
import com.sparta.FlockApp.pages.LoginPage;
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
public class LogoutStepdefs {
    @Managed
    private HomePage logoutPage;

    @When("I click the logout button on the current page")
    public void I_click_the_logout_button_on_the_current_page() {
        logoutPage.open();
        logoutPage.clickSignOutButton();
    }

    @Then("I should be returned to the login page")
    public void I_should_be_returned_to_the_login_page() {
        MatcherAssert.assertThat(logoutPage.getDriver().getCurrentUrl(), Matchers.is("http://127.0.0.1:8000/flock/login.html"));
    }
}