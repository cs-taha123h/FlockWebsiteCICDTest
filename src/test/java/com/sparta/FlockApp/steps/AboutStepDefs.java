package com.sparta.FlockApp.steps;

import com.sparta.FlockApp.pages.AboutPage;
import com.sparta.FlockApp.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class AboutStepDefs {

    @Managed
    private AboutPage aboutPage;

    @Managed
    private LoginPage loginPage;

    @Given("I am logged into FlockApp")
    public void iAmLoggedIntoFlockApp() {
        aboutPage.open();
        aboutPage.clearLoginSession();

        loginPage.open();
        loginPage.enterAndSubmitFields("nish", "mandal");

        MatcherAssert.assertThat(
                loginPage.getDriver().getCurrentUrl(),
                Matchers.is(
                        "http://127.0.0.1:8000/flock/home.html"
                )
        );
    }

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        aboutPage.openHomePage();
    }

    @Given("I am on the About page")
    public void iAmOnTheAboutPage() {
        aboutPage.open();
    }

    @Given("I am on the About page as a guest")
    public void iAmOnTheAboutPageAsAGuest() {
        aboutPage.open();
        aboutPage.clearLoginSession();
        aboutPage.open();
    }

    @When("I click the What is Flock link")
    public void iClickTheWhatIsFlockLink() {
        aboutPage.clickLoginAboutLink();
    }

    @When("I click About in the sidebar")
    public void iClickAboutInTheSidebar() {
        aboutPage.clickSidebarAboutLink();
    }

    @When("I click the Browse Roosts button")
    public void iClickTheBrowseRoostsButton() {
        aboutPage.clickBrowseRoostsButton();
    }

    @Then("I should be redirected to the About page")
    public void iShouldBeRedirectedToTheAboutPage() {
        MatcherAssert.assertThat(
                aboutPage.isAboutPageDisplayed(),
                Matchers.is(true)
        );
    }

    @Then("I should see information about Flocks and Roosts")
    public void iShouldSeeInformationAboutFlocksAndRoosts() {
        MatcherAssert.assertThat(
                aboutPage.isFlockInformationDisplayed(),
                Matchers.is(true)
        );
    }

    @Then("I should see how Flock works")
    public void iShouldSeeHowFlockWorks() {
        MatcherAssert.assertThat(
                aboutPage.isHowItWorksDisplayed(),
                Matchers.is(true)
        );
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        MatcherAssert.assertThat(
                aboutPage.isLoginPageDisplayed(),
                Matchers.is(true)
        );
    }
}
