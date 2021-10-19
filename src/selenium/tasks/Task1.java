package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import java.lang.Math;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Task1 {
    WebDriver driver;


    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement field = driver.findElement(By.id("numb"));
        String enteredText = "number";
        field.sendKeys(enteredText);
        driver.findElement(By.className("w3-btn")).click();
        String expectedErrorMessage = "Please enter a number";
        String actualErrorMessage = driver.findElement(By.className("error")).getText();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement field = driver.findElement(By.id("numb"));
        String enteredNumber = "48";
        field.sendKeys(enteredNumber);
        driver.findElement(By.className("w3-btn")).click();
        String expectedErrorMessage = "Number is too small";
        String actualErrorMessage = driver.findElement(By.className("error")).getText();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement field = driver.findElement(By.id("numb"));
        String enteredNumber = "101";
        field.sendKeys(enteredNumber);
        driver.findElement(By.className("w3-btn")).click();
        String expectedErrorMessage = "Number is too big";
        String actualErrorMessage = driver.findElement(By.className("error")).getText();
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement field = driver.findElement(By.id("numb"));
        String enteredNumber = "64";
        field.sendKeys(enteredNumber);
        driver.findElement(By.className("w3-btn")).click();

        double square = Math.sqrt(Double.parseDouble(enteredNumber));
        Alert alert = driver.switchTo().alert();
        String expected = String.format("Square root of %s is %.2f", enteredNumber, square);
        assertEquals(expected, alert.getText());
        alert.accept();
        String actualErrorMessage = driver.findElement(By.className("error")).getText();
        assertTrue(actualErrorMessage.isEmpty());
    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement field = driver.findElement(By.id("numb"));
        String enteredNumber = "77";
        field.sendKeys(enteredNumber);
        driver.findElement(By.className("w3-btn")).click();

        double square = Math.sqrt(Double.parseDouble(enteredNumber));
        Alert alert = driver.switchTo().alert();
        String expected = String.format("Square root of %s is %.2f", enteredNumber, square);
        assertEquals(expected, alert.getText());
        alert.accept();
        String actualErrorMessage = driver.findElement(By.className("error")).getText();
        assertTrue(actualErrorMessage.isEmpty());
    }
}
