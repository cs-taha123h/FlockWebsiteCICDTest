package com.sparta.FlockApp.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.By;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@DefaultUrl("http://127.0.0.1:8000/flock/profile.html")
public class ProfilePage extends PageObject {

    @FindBy(className = "profile-name")
    private WebElementFacade profileName;

    @FindBy(className = "profile-sub")
    private WebElementFacade profileInfo;

    @FindBy(className = "profile-edit-btn")
    private WebElementFacade editProfileButton;

    @FindBy(xpath = "//*[@id=\"edit-profile-overlay\"]/div")
    private WebElementFacade editProfileOverlay;

    @FindBy(xpath = "//*[@id='profile-body']/div[1]/div[2]/div/span[2]")
    private List<WebElementFacade> profileValues;

    @FindBy(className = "inline-select")
    private WebElementFacade preferenceMenu;

    @FindBy(className = "inline-chips-wrap")
    private WebElementFacade chipsWrap;

    @FindBy(css = ".inline-chip")
    private List<WebElementFacade> allInterests;

    @FindBy(css = ".inline-chip.active")
    private List<WebElementFacade> selectedInterests;

    @FindBy(css = ".sb-nav > *")
    private List<WebElementFacade> sideNavBarItems;

    @FindBy(id= "signout-btn")
    private WebElementFacade signOutButton;

    @FindBy(id= "sidebar-chats-close-btn")
    private WebElementFacade chatsCloseButton;

    @FindBy(id= "ep-first")
    private WebElementFacade firstNameField;

    @FindBy(id= "ep-last")
    private WebElementFacade lastNameField;

    @FindBy(xpath = "//*[@id=\'edit-profile-overlay\']/div/button")
    private WebElementFacade confirmEditButton;

    @FindBy(id = "ep-dob")
    private WebElementFacade dobField;


    public String getProfileName() {
        return profileName.getText();
    }
    public String getProfileInfo() {
        return profileInfo.getText();
    }

    public int getFlocksJoinedTotal() {
        var out = profileValues.getFirst().getText().replace(" \uD83C\uDF89", "");
        return Integer.parseInt(out);
    }

    public int getInterestsSelected() {
        return Integer.parseInt(profileValues.get(1).getText());
    }

    public void openPreferenceMenu() {
        preferenceMenu.click();
    }

    public void updatePreference() {
        preferenceMenu.thenFind("//*[@id='profile-body']/div[2]/div[2]/div/div/select/option[4]").click();
    }

    public String getEditButtonText() {
        return editProfileButton.getText();
    }

    public boolean isEditOverlayVisible() {
        try {
            editProfileOverlay.waitUntilVisible();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickEditProfileButton() {
        editProfileButton.click();
    }

    public void clickSignOutButton() {
        signOutButton.click();
    }

    public int getActiveInterests() {
        List <WebElementFacade> interestBadges = chipsWrap.thenFindAll(By.cssSelector(".inline-chip.active"));
        var amount = 0;
        for (WebElementFacade interestBadge : interestBadges) {
            amount += 1;
        }
        return amount;
    }

    public String getCurrentPreferredCity() {
        return preferenceMenu.getSelectedValue();
    }

    public List<String> getAvailableCities() {
        return preferenceMenu.getSelectOptions();
    }

    public void selectNewCity(String city) {
        preferenceMenu.selectByVisibleText(city);
    }

    public List<String> getSelectedInterestNames() {
        List<String> names = new ArrayList<>();
        for (WebElementFacade chip : selectedInterests) {
            String name = chip.getText().replaceAll("[^\\p{L}\\p{N}& ]", "").trim();
            names.add(name);
        }
        return names;
    }

    public void updateInterest(String interest) {
        for (WebElementFacade interestBadge : allInterests) {
            if (interestBadge.getText().contains(interest)) {
                interestBadge.click();
                return;
            }
        }
    }

    public String getActiveSidebarItem() {
        for (WebElementFacade navItem : sideNavBarItems) {
            if (navItem.getAttribute("class").contains("active")) {
                return navItem.getText();
            }
        }
        return "";
    }

    public void clickSideBarItem(String item) {
        for (WebElementFacade navItem : sideNavBarItems) {
            System.out.println(navItem.getText());
            if (navItem.getText().contains(item)) {
                navItem.click();
                return;
            }
        }
    }

    public boolean isChatsPopupOpen() {
        return chatsCloseButton.isVisible();
    }

    public void setNewFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    public void setNewLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void confirmEdit() {
        confirmEditButton.click();
    }

    public void updateDob(LocalDate date) {
        dobField.clear();
        dobField.sendKeys(date.format(DateTimeFormatter.ofPattern("ddMMyyyy")));
    }
    public String getDob() {
        return dobField.getValue();
    }
}
