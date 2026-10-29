package com.sparta.FlockApp.steps;

import com.sparta.FlockApp.pages.HomePage;
import com.sparta.FlockApp.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationStepDefs {

    private HomePage homePage;
    private LoginPage loginPage;

    @Given("the user is logged in on the flockApp for use of location and clickable")
    public void theUserIsLoggedInOnTheFlockApp() {
        loginPage.open();
        loginPage.enterAndSubmitFields("nish", "mandal");

        WebDriverWait wait = new WebDriverWait(loginPage.getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("http://127.0.0.1:8000/flock/home.html"));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("location-btn")));
    }

    @Given("browser geolocation permission is granted")
    public void browserGeolocationPermissionIsGranted() {
        homePage.open();
        homePage.setGeolocationPermission(true);
    }

    @When("I select Use my location")
    public void iSelectUseMyLocation() {
        homePage.useMyLocation();
    }

    @Then("the application should attempt to use my location")
    public void theApplicationShouldAttemptToUseMyLocation() {
        assertTrue(
                homePage.locationActionWasAttempted(),
                "Expected the Use my location button to be clicked"
        );
    }

    @Then("the displayed results should reflect location if supported")
    public void theDisplayedResultsShouldReflectLocationIfSupported() {
        assertTrue(
                homePage.locationHandlingIsVisible(),
                "Expected location-related content or a location action"
        );
    }

    @Given("browser geolocation permission is denied")
    public void browserGeolocationPermissionIsDenied() {
        homePage.setGeolocationPermission(false);
    }

    @Then("the application should handle the denial gracefully")
    public void theApplicationShouldHandleTheDenialGracefully() {
        assertTrue(
                homePage.locationHandlingIsVisible(),
                "Expected the page to show a location or permission response"
        );
    }
}
