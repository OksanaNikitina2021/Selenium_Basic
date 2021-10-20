package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;

public class FeedbackPage extends GenericSamplePage {
    @FindBy(how = How.ID, using = "fb_name")
    private WebElement nameInput;
    @FindBy(how = How.ID, using = "fb_age")
    private WebElement ageInput;
    @FindBy(how = How.CLASS_NAME, using = "w3-check")
    private List<WebElement> listOfLanguages;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fb_form\"]/form/div[4]/input[3]")
    private WebElement defaultGender;
    @FindBy(how = How.XPATH, using = "//*[@id=\"like_us\"]/option[1]")
    private WebElement defaultOption;
    @FindBy(how = How.XPATH, using = "//*[@id=\"fb_form\"]/form/div[6]/textarea")
    private WebElement commentField;
    @FindBy(how = How.CLASS_NAME, using = "w3-btn-block")
    private WebElement sendButton;
    @FindBy(how = How.XPATH, using = "//div[@class=\"description\"]//span")
    private List<WebElement> listOfFields;
    @FindBy(how = How.CLASS_NAME, using = "w3-green")
    private WebElement yesButton;
    @FindBy(how = How.CLASS_NAME, using = "w3-red")
    private WebElement noButton;
    @FindBy(how = How.CLASS_NAME, using = "w3-radio")
    private List<WebElement> listOfGenders;
    @FindBy(className = "w3-select")
    private WebElement select;
    @FindBy(how = How.ID, using = "message")
    private WebElement message;


    public void nameInputCheck() {
        assertTrue(nameInput.getAttribute("value").isEmpty());
    }

    public void ageInputCheck() {
        assertTrue(ageInput.getAttribute("value").isEmpty());
    }

    public void languageSelectionCheck() {
        for (WebElement checkBox : listOfLanguages) {
            assertFalse(checkBox.isSelected());
        }
    }

    public void defaultGenderCheck() {
        assertTrue(defaultGender.isSelected());
    }

    public void defaultOptionCheck() {
        String defaultText = "Choose your option";
        assertEquals(defaultText, defaultOption.getText());
    }

    public void commentFieldCheck() {
        assertTrue(commentField.getText().isEmpty());
    }

    public void sendButtonCheck() {
        assertEquals("rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    public void sendButtonClick() {
        sendButton.click();
    }

    public void listOfFieldsCheck() {
        for (WebElement field : listOfFields) {
            if (field.getText().isEmpty()) {
                assertTrue(field.getText().isEmpty());
            } else {
                assertEquals("null", field.getText());
            }
        }
    }

    public void yesButtonCheck() {
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));
    }

    public void noButtonCheck() {
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    public void enterName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterAge(int age) {
        enterAge(String.valueOf(age));
    }

    private void enterAge(String age) {
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    public void selectLanguage(String language) {
        for (WebElement lang : listOfLanguages) {
            if (lang.getAttribute("value").equals(language)) {
                lang.click();
                assertTrue(lang.isSelected());
            }
        }
    }

    public void selectGender(String gender) {
        for (WebElement gen : listOfGenders) {
            if (gen.getAttribute("value").equals(gender)) {
                gen.click();
                assertTrue(gen.isSelected());
            }
        }
    }

    public void selectOption(String text) {
        Select dropdown = new Select(select);
        dropdown.selectByVisibleText(text);
    }

    public void enterComment(String comment) {
        commentField.clear();
        commentField.sendKeys(comment);
    }

    public void checkSubmition() {
        for (WebElement field : listOfFields) {
            assertFalse(field.getText().isEmpty());
        }
    }

    public void yesButtonClick() {
        yesButton.click();
    }

    public void yesMessageCheck(String userName) {
        String expectedMessage = String.format("Thank you, %s, for your feedback!", userName);
        assertEquals(expectedMessage, message.getText());
    }

    public void yesMessageEmptyCheck() {
        String expectedText = "Thank you for your feedback!";
        assertEquals(expectedText, message.getText());
    }

    public void noButtonClick() {
        noButton.click();
    }

    public WebElement getSelectedLanguage() {
        for (WebElement lang : listOfLanguages) {
            if (lang.isSelected()) {
                return lang;
            }
        }
        return null;
    }

    public WebElement getSelectedGender() {
        for (WebElement gen : listOfGenders) {
            if (gen.isSelected()) {
                return gen;
            }
        }
        return null;
    }

    public WebElement getSelectedOption() {
        Select dropdown = new Select(select);
        return dropdown.getFirstSelectedOption();
    }

    public void fieldsCorrectnessCheck(String name, int age, String language, String gender, String option, String comment) {
        assertEquals(name, nameInput.getAttribute("value"));
        assertEquals(String.valueOf(age), ageInput.getAttribute("value"));

        String selectedLanguage = getSelectedLanguage().getAttribute("value");
        assertEquals(language, selectedLanguage);

        String selectedGender = getSelectedGender().getAttribute("value");
        assertEquals(gender, selectedGender);

        String selectedOption = getSelectedOption().getAttribute("value");
        assertEquals(option, selectedOption);

        assertEquals(comment, commentField.getAttribute("value"));
    }

}