package com.sparta.FlockApp.steps;

import com.sparta.FlockApp.pages.LoginPage;
import com.sparta.FlockApp.pages.ProfilePage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static net.serenitybdd.core.Serenity.getDriver;
import static org.hamcrest.Matchers.*;

@ExtendWith(SerenityJUnit5Extension.class)
public class ProfileStepDefs {
    @Managed
    private ProfilePage profilePage;
    private LoginPage loginPage;

    @Then("I open the profile page")
    public void iOpenTheProfilePage() {
        profilePage.open();
    }

    @Then("I should see the profile name {string}")
    public void iShouldSeeTheProfileName(String arg0) {
        MatcherAssert.assertThat(profilePage.getProfileName(), Matchers.equalTo(arg0));
    }

    @And("I should see the profile details {string}")
    public void iShouldSeeTheProfileDetails(String arg0) {
        MatcherAssert.assertThat(profilePage.getProfileInfo(), Matchers.containsString(arg0));
    }

    @And("I should see the {string} button")
    public void iShouldSeeTheButton(String arg0) {
        MatcherAssert.assertThat(profilePage.getEditButtonText(), Matchers.equalTo(arg0));
    }

    @When("I click ✎ Edit profile")
    public void iClickEditProfile() {
        profilePage.clickEditProfileButton();
    }

    @Then("the edit profile form should be displayed")
    public void theEditProfileFormShouldBeDisplayed() {
        MatcherAssert.assertThat(profilePage.isEditOverlayVisible(), Matchers.equalTo(true));
    }

    @Then("the Flocks joined stat should be {int} 🎉")
    public void theFlocksJoinedStatShouldBe(int arg0) {
        MatcherAssert.assertThat(profilePage.getFlocksJoinedTotal(), Matchers.equalTo(arg0));
    }

    @And("the Interests selected stat should be {int}")
    public void theInterestsSelectedStatShouldBe(int arg0) {
        MatcherAssert.assertThat(profilePage.getInterestsSelected(), Matchers.equalTo(arg0));
    }

    @Then("the Interests selected stat should equal the number of selected interests")
    public void theInterestsSelectedStatShouldEqualTheNumberOfSelectedInterests() {
        MatcherAssert.assertThat(profilePage.getInterestsSelected(), Matchers.equalTo(profilePage.getActiveInterests()));
    }

    @When("I change the City preference to {string}")
    public void iChangeTheCityPreferenceTo(String arg0) {
        profilePage.openPreferenceMenu();
        profilePage.selectNewCity(arg0);
    }

    @Then("the City preference should be {string}")
    public void theCityPreferenceShouldBe(String arg0) {
        MatcherAssert.assertThat(profilePage.getCurrentPreferredCity(), Matchers.equalTo(arg0));
    }


    @And("the profile details should include {string}")
    public void theProfileDetailsShouldInclude(String arg0) {
        MatcherAssert.assertThat(profilePage.getProfileInfo(), Matchers.containsString(arg0));
    }


    @Then("the following interests should be selected:")
    public void theFollowingInterestsShouldBeSelected(List<String> expectedInterests) {
        MatcherAssert.assertThat(profilePage.getSelectedInterestNames(),
                containsInAnyOrder(expectedInterests.toArray()));
    }

    @Given("the interest {string} is not selected")
    public void theInterestIsNotSelected(String arg0) {
        MatcherAssert.assertThat(profilePage.getSelectedInterestNames(),
                Matchers.not(containsInAnyOrder(arg0)));
    }

    @When("I tap the interest {string}")
    public void iTapTheInterest(String arg0) {
        profilePage.updateInterest(arg0);
    }

    @Then("the interest {string} should be selected")
    public void theInterestShouldBeSelected(String arg0) {
        MatcherAssert.assertThat(profilePage.getSelectedInterestNames(), hasItem(arg0));
    }

    @And("the Interests selected stat should increase by {int}")
    public void theInterestsSelectedStatShouldIncreaseBy(int arg0) {
        MatcherAssert.assertThat(profilePage.getInterestsSelected(), Matchers.equalTo(8));
    }

    @Then("the interest {string} should not be selected")
    public void theInterestShouldNotBeSelected(String arg0) {
        MatcherAssert.assertThat(profilePage.getSelectedInterestNames(), Matchers.not(hasItem(arg0)));
    }

    @And("the {string} stat should decrease by {int}")
    public void theStatShouldDecreaseBy(String arg0, int arg1) {
        MatcherAssert.assertThat(profilePage.getInterestsSelected(), Matchers.equalTo(6));
    }

    @And("I refresh the page")
    public void iRefreshThePage() {
        profilePage.getDriver().navigate().refresh();
    }

    @Then("{string} should be the active sidebar item")
    public void shouldBeTheActiveSidebarItem(String itemName) {
        MatcherAssert.assertThat(profilePage.getActiveSidebarItem(), Matchers.equalTo(itemName));
    }

    @When("I click {string} in the sidebar")
    public void iClickInTheSidebar(String sidebarHeader) {
        profilePage.clickSideBarItem(sidebarHeader);
    }

    @Then("I should be on the {string} page")
    public void iShouldBeOnThePage(String title) {
        MatcherAssert.assertThat(profilePage.getDriver().getTitle(), Matchers.equalTo("Flock - " + title));
    }

    @Then("I should see a popup message")
    public void iShouldSeeAPopupMessage() {
        MatcherAssert.assertThat(profilePage.isChatsPopupOpen(), Matchers.equalTo(true));
    }

    @When("I click Sign Out in the sidebar footer")
    public void iClickSignOutInTheSidebarFooter() {
        profilePage.clickSignOutButton();
    }

    @Then("I should be signed out")
    public void iShouldBeSignedOut() {
        getDriver().switchTo().alert().accept();
        MatcherAssert.assertThat(loginPage.isSignOutVisible(), Matchers.equalTo(true));
    }

    @And("I should be on the login page")
    public void iShouldBeOnTheLoginPage() {
        MatcherAssert.assertThat(profilePage.getDriver().getCurrentUrl(), Matchers.equalTo("http://127.0.0.1:8000/flock/login.html"));
    }

    @And("I enter {string} and {string}")
    public void iEnterAnd(String first, String last) {
        profilePage.setNewFirstName(first);
        profilePage.setNewLastName(last);
    }

    @And("I click to confirm")
    public void iClickToConfirm() {
        profilePage.confirmEdit();
    }

    @Then("my account page should show {string} and {string}")
    public void myAccountPageShouldShowAnd(String first, String last) {
        MatcherAssert.assertThat(profilePage.getProfileName().replace(" ", ""), Matchers.containsString(first + last));
    }

    @And("I set my date of birth to {string}")
    public void iSetMyDateOfBirthTo(String dob) {
        profilePage.updateDob(LocalDate.parse(dob));
    }
}
