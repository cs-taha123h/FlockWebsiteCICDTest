package com.sparta.FlockApp.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("http://127.0.0.1:8000/flock/signup.html")
public class SignupPage extends PageObject {

    @FindBy(name = "firstname")
    private WebElementFacade firstNameField;

    @FindBy(name = "lastname")
    private WebElementFacade lastNameField;

    @FindBy(name = "username")
    private WebElementFacade userNameField;

    @FindBy(name = "password")
    private WebElementFacade passwordField;

    @FindBy(name = "confirm")
    private WebElementFacade confirmPasswordField;

    @FindBy(id = "signup-submit-btn")
    private WebElementFacade signupSubmitButton;

    @FindBy(id = "go-to-login")
    private WebElementFacade loginButton;

    @FindBy(id = "err-signup-firstname")
    private WebElementFacade firstNameError;

    @FindBy(id = "err-signup-username")
    private WebElementFacade usernameError;

    @FindBy(id = "err-signup-password")
    private WebElementFacade passwordError;

    @FindBy(id = "err-signup-confirm")
    private WebElementFacade confirmPasswordError;

    public void enterFirstName(String firstName){
        firstNameField.type(firstName);
    }

    public void enterLastName(String lastName){
        lastNameField.type(lastName);
    }

    public void enterUsername(String username){
        userNameField.type(username);
    }

    public void enterPassword(String password){
        passwordField.type(password);
    }

    public void enterConfirmPassword(String password){
        confirmPasswordField.type(password);
    }

    public void clickSignupButton(){
        signupSubmitButton.click();
    }

    public void enterAndSubmitFields(
            String firstName,
            String lastName,
            String username,
            String password,
            String confirmPassword){

        enterFirstName(firstName);
        enterLastName(lastName);
        enterUsername(username);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickSignupButton();
    }

    public String getFirstNameError(){
        return firstNameError.getText();
    }

    public String getUsernameError(){
        return usernameError.getText();
    }

    public String getPasswordError(){
        return passwordError.getText();
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError.getText();
    }
}
