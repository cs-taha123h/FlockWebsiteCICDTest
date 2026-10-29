package com.sparta.FlockApp.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;

@DefaultUrl("http://127.0.0.1:8000/flock/about.html")
public class AboutPage extends PageObject {

    private static final String BASE_URL =
            "http://127.0.0.1:8000/flock/";

    @FindBy(id = "login-about-link")
    private WebElementFacade loginAboutLink;

    @FindBy(id = "sidebar-nav-about")
    private WebElementFacade sidebarAboutLink;

    @FindBy(tagName = "h1")
    private WebElementFacade pageHeading;

    @FindBy(xpath = "//h2[contains(., \"What's a Flock?\")]")
    private WebElementFacade flocksHeading;

    @FindBy(xpath = "//h2[contains(., \"What's a Roost?\")]")
    private WebElementFacade roostsHeading;

    @FindBy(xpath = "//h2[normalize-space(.)='How it works']")
    private WebElementFacade howItWorksHeading;

    @FindBy(xpath = "//h2[contains(., 'What kind of Roosts?')]")
    private WebElementFacade kindsOfRoostsHeading;

    @FindBy(xpath = "//h2[contains(., 'Who is Flock for?')]")
    private WebElementFacade whoIsFlockForHeading;

    @FindBy(id = "about-browse-btn")
    private WebElementFacade browseRoostsButton;

    public void clickLoginAboutLink() {
        loginAboutLink.click();
    }

    public void clickSidebarAboutLink() {
        sidebarAboutLink.click();
    }

    public void openHomePage() {
        getDriver().get(BASE_URL + "home.html");
    }

    public void clearLoginSession() {
        ((JavascriptExecutor) getDriver()).executeScript(
                "window.localStorage.removeItem('flock_user');"
        );
    }

    public boolean isAboutPageDisplayed() {
        return getDriver().getCurrentUrl()
                .equals(BASE_URL + "about.html")
                && pageHeading.isVisible()
                && pageHeading.getText().contains("What is Flock?");
    }

    public boolean isFlockInformationDisplayed() {
        return flocksHeading.isVisible()
                && roostsHeading.isVisible()
                && kindsOfRoostsHeading.isVisible()
                && whoIsFlockForHeading.isVisible();
    }

    public boolean isHowItWorksDisplayed() {
        return howItWorksHeading.isVisible();
    }

    public void clickBrowseRoostsButton() {
        browseRoostsButton.click();
    }

    public boolean isLoginPageDisplayed() {
        return getDriver().getCurrentUrl()
                .equals(BASE_URL + "login.html");
    }
}
