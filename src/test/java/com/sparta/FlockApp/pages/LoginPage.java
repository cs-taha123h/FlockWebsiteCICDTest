package com.sparta.FlockApp.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("http://127.0.0.1:8000/flock/login.html")
public class LoginPage extends PageObject {
    @FindBy(name = "username")
    private WebElementFacade userNameField;

    @FindBy(name = "password")
    private WebElementFacade passwordField;

    @FindBy(id = "login-submit-btn")
    private WebElementFacade loginSubmitButton;

    @FindBy(id = "login-error")
    private WebElementFacade loginError;

    @FindBy(id = "err-login-username")
    private  WebElementFacade usernameError;

    @FindBy(id = "err-login-password")
    private WebElementFacade passwordError;

    @FindBy(xpath = "//*[@id=\"signout-success\"]")
    private WebElementFacade signoutSuccessBanner;

    @FindBy(id = "go-to-signup")
    private WebElementFacade signupButton;
    public void enterUsername(String username){
        userNameField.type(username);
    }

    public void enterPassword(String password){
        passwordField.type(password);
    }

    public void clickLoginButton(){
        loginSubmitButton.click();
    }

    public void clickSignupButton(){
        signupButton.click();
    }

    public void enterAndSubmitFields(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage(){
        return loginError.getText();
    }

    public String getUsernameError(){
        return usernameError.getText();
    }

    public String getPasswordError(){
        return passwordError.getText();
    }

    public void navigateBack(){
        getDriver().navigate().back();
    }

    public void refreshPage(){
        getDriver().navigate().refresh();
    }

    public boolean isSignOutVisible(){
        try {
            signoutSuccessBanner.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
