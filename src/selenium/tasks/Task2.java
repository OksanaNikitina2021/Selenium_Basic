package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.AgeSamplePage;
import selenium.pages.FeedbackPage;

import java.io.File;

public class Task2 {
    WebDriver driver;
    static FeedbackPage feedbackPage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        feedbackPage = PageFactory.initElements(driver, FeedbackPage.class);
    }

    @After
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
        feedbackPage.nameInputCheck();
        feedbackPage.ageInputCheck();
        feedbackPage.languageSelectionCheck();
//         "Don't know" is selected in "Genre"
        feedbackPage.defaultGenderCheck();
//         "Choose your option" in "How do you like us?"
        feedbackPage.defaultOptionCheck();
        feedbackPage.commentFieldCheck();
//         check that the button send is blue with white letters
        feedbackPage.sendButtonCheck();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        feedbackPage.sendButtonClick();
//         check fields are empty or null
        feedbackPage.listOfFieldsCheck();
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage.yesButtonCheck();
        feedbackPage.noButtonCheck();
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        feedbackPage.enterName("John Smith");
        feedbackPage.enterAge(25);
        feedbackPage.selectLanguage("English");
        feedbackPage.selectGender("male");
        feedbackPage.selectOption("Good");
        feedbackPage.enterComment("This is a comment.");
        feedbackPage.sendButtonClick();
//         check fields are filled correctly
        feedbackPage.checkSubmition();
//         check button colors
//         (green with white letter and red with white letters)
        feedbackPage.yesButtonCheck();
        feedbackPage.noButtonCheck();
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
        String userName = "John Smith";
//         TODO:
//         enter only name
        feedbackPage.enterName(userName);
//         click "Send"
        feedbackPage.sendButtonClick();
//         click "Yes"
        feedbackPage.yesButtonClick();
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        feedbackPage.yesMessageCheck(userName);
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        feedbackPage.sendButtonClick();
//         click "Yes"
        feedbackPage.yesButtonClick();
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        feedbackPage.yesMessageEmptyCheck();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
        String name = "John Smith";
        int age = 25;
        String language = "English";
        String gender = "male";
        String option = "Why me?";
        String comment = "This is a comment.";
//         TODO:
//         fill the whole form
        feedbackPage.enterName(name);
        feedbackPage.enterAge(age);
        feedbackPage.selectLanguage(language);
        feedbackPage.selectGender(gender);
        feedbackPage.selectOption(option);
        feedbackPage.enterComment(comment);
//         click "Send"
        feedbackPage.sendButtonClick();
//         click "No"
        feedbackPage.noButtonClick();
//         check fields are filled correctly
        feedbackPage.fieldsCorrectnessCheck(name, age, language, gender, option, comment);
    }
}