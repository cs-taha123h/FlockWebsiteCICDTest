
package com.sparta.FlockApp.pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@DefaultUrl("http://127.0.0.1:8000/flock/flocks.html")
public class FlocksPage extends PageObject {

    @FindBy(id = "search-input")
    private WebElementFacade searchField;

    @FindBy(id = "search-clear")
    private WebElementFacade clearSearchButton;

    @FindBy(id = "city-filter")
    private WebElementFacade cityFilter;

    @FindBy(id = "sort-filter")
    private WebElementFacade sortFilter;

    @FindBy(id = "location-btn")
    private WebElementFacade locationButton;

    @FindBy(className = "cat-chip")
    private List<WebElementFacade> categories;

    @FindBy(id = "events-container")
    private WebElementFacade eventsContainer;

    @FindBy(id = "nav-my-flocks")
    private WebElementFacade myFlocksLink;

    @FindBy(id = "nav-profile")
    private WebElementFacade profileLink;

    public void searchForFlock(String searchText) {
        searchField.waitUntilVisible();
        searchField.type(searchText);
    }

    public String getSearchText() {
        return searchField.getValue();
    }

    public void clearSearch() {
        clearSearchButton.waitUntilClickable().click();
    }

    public boolean isSearchEmpty() {
        return getSearchText().isEmpty();
    }

    public boolean isCategoryDisplayed(String category) {
        return categories.stream()
                .anyMatch(element ->
                        category.equals(element.getAttribute("data-cat"))
                                && element.isDisplayed()
                );
    }

    public void selectCategory(String category) {
        for (WebElementFacade element : categories) {
            if (category.equals(element.getAttribute("data-cat"))) {
                element.waitUntilClickable().click();
                return;
            }
        }

        throw new IllegalArgumentException(
                "Flock category not found: " + category
        );
    }

    public boolean isCategoryActive(String category) {
        return categories.stream()
                .anyMatch(element ->
                        category.equals(element.getAttribute("data-cat"))
                                && element.getAttribute("class").contains("active")
                );
    }

    public void selectSortOption(String option) {
        new Select(sortFilter).selectByVisibleText(option);
    }

    public String getSelectedSortOption() {
        return new Select(sortFilter)
                .getFirstSelectedOption()
                .getText()
                .trim();
    }

    public String selectAvailableCity() {
        Select select = new Select(cityFilter);

        for (WebElement option : select.getOptions()) {
            String value = option.getAttribute("value");

            if (option.isEnabled()
                    && value != null
                    && !value.isBlank()
                    && !option.isSelected()) {

                select.selectByValue(value);
                return value;
            }
        }

        throw new IllegalStateException(
                "No alternative city is available"
        );
    }

    public String getSelectedCity() {
        return new Select(cityFilter)
                .getFirstSelectedOption()
                .getAttribute("value");
    }

    public int getDisplayedFlockCardCount() {
        return eventsContainer.findElements(
                By.cssSelector(".event-card")
        ).size();
    }


    public boolean areFlockCardsDisplayed() {
        return !eventsContainer.findElements(
                By.cssSelector(".event-card")
        ).isEmpty();
    }

    public void clickFirstFlockCard() {
        eventsContainer.findElements(
                By.cssSelector(".event-card")
        ).get(0).click();
    }

    public boolean isOnFlockDetailsPage() {
        return getDriver().getCurrentUrl().contains("/event.html?id=");
    }

}
