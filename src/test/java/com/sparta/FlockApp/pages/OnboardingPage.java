
package com.sparta.FlockApp.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

import java.util.List;

@DefaultUrl("http://127.0.0.1:8000/flock/onboarding.html")
public class OnboardingPage extends PageObject {

    @FindBy(id = "next-btn")
    private WebElementFacade continueButton;

    @FindBy(id = "skip-btn")
    private WebElementFacade skipButton;

    @FindBy(id = "step-label")
    private WebElementFacade stepLabel;

    @FindBy(id = "step-title")
    private WebElementFacade stepTitle;

    @FindBy(id = "ob-first")
    private WebElementFacade firstNameField;

    @FindBy(id = "ob-last")
    private WebElementFacade lastNameField;

    @FindBy(id = "ob-dob")
    private WebElementFacade dateOfBirthField;

    @FindBy(className = "avatar-opt")
    private List<WebElementFacade> avatarOptions;

    @FindBy(className = "interest-chip")
    private List<WebElementFacade> interestChips;

    @FindBy(className = "ob-flock-card")
    private List<WebElementFacade> flockCards;

    @FindBy(id = "ob-city")
    private WebElementFacade cityDropdown;

    @FindBy(className = "dist-chip")
    private List<WebElementFacade> distanceOptions;

    public void clickContinueButton() {
        continueButton.click();
    }

    public void clickSkipButton() {
        skipButton.click();
    }

    public String getCurrentStep() {
        return stepLabel.getText();
    }

    public String getStepTitle() {
        return stepTitle.getText();
    }

    public void enterFirstName(String firstName) {
        firstNameField.type(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.type(lastName);
    }

    public void enterDateOfBirth(String dateOfBirth) {
        dateOfBirthField.type(dateOfBirth);
    }

    public void clearFirstName() {
        firstNameField.clear();
    }

    public void clearLastName() {
        lastNameField.clear();
    }

    public void clearDateOfBirth() {
        dateOfBirthField.clear();
    }

    public String getFirstName() {
        return firstNameField.getValue();
    }

    public String getLastName() {
        return lastNameField.getValue();
    }

    public void selectAvatar(String avatar) {
        for (WebElementFacade option : avatarOptions) {
            if (option.getText().equals(avatar)) {
                option.click();
                return;
            }
        }
        throw new IllegalArgumentException("Avatar not found: " + avatar);
    }

    public void selectInterest(String interest) {
        for (WebElementFacade chip : interestChips) {
            if (chip.getText().contains(interest)) {
                chip.click();
                return;
            }
        }
        throw new IllegalArgumentException("Interest not found: " + interest);
    }

    public void deselectInterest(String interest) {
        for (WebElementFacade chip : interestChips) {
            if (chip.getText().contains(interest)) {
                if (chip.getAttribute("class").contains("selected")) {
                    chip.click();
                }
                return;
            }
        }
        throw new IllegalArgumentException("Interest not found: " + interest);
    }

    public void selectCity(String city) {
        cityDropdown.selectByVisibleText(city);
    }

    public void selectDistance(String distance) {
        for (WebElementFacade option : distanceOptions) {
            if (option.getText().equals(distance)) {
                option.click();
                return;
            }
        }
        throw new IllegalArgumentException("Distance not found: " + distance);
    }
}
