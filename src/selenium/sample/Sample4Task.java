package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.*;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";
    String homePage = "https://kristinek.github.io/site/";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        Thread.sleep(5000);
        driver.close();
    }

    @Test
    public void enterNumber() throws Exception {
//         TODO:
//        enter a number under "Number"
        WebElement number = driver.findElement(By.id("number"));
        number.clear();
        number.sendKeys("3");
//        check that button is not clickable "Clear Result"
        WebElement resultButton = driver.findElement(By.id("clear_result_button_number"));
        assertFalse(resultButton.isEnabled());
//        check that text is not displayed
        WebElement resultText = driver.findElement(By.id("result_number"));
        assertFalse(resultText.isDisplayed());
//        click on "Result" button
        WebElement resultButtonClick = driver.findElement(By.id("result_button_number"));
        resultButtonClick.click();
//        check that text is displayed
        assertTrue(resultText.isDisplayed());
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
        String actualText = resultText.getText();
        String displayedNumber = number.getAttribute("value");
        String expectedText = "You entered number: \"" + displayedNumber + "\"";
        assertEquals(expectedText, actualText);
//        check that the button "Clear Result" is clickable now
        assertTrue(resultButton.isEnabled());
//        click on "Clear Result"
        resultButton.click();
//        check that the text is still (""), but it is not displayed
        assertFalse(resultText.isDisplayed());
        assertEquals(expectedText, actualText);
    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
        assertEquals(base_url, driver.getCurrentUrl());
//        click on "This is a link to Homepage"
        driver.findElement(By.xpath("//*[@id='header']/ul/li[1]/a")).click();
//        check that current url is not base_url
        assertFalse(base_url == driver.getCurrentUrl());
//        verify that current url is homepage
        assertEquals(homePage, driver.getCurrentUrl());
    }
}
