package tests;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Login {

    WebDriver driver = new FirefoxDriver();
    @BeforeMethod

    public void openBrowser()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        driver.navigate().to("https://www.demoblaze.com/");


    }
    @Test(priority = 1)
    public void loginAndPurchaseWithoutFillingData() {
        // Login
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("omarRiad@gmail.com");
        driver.findElement(By.id("loginpassword")).sendKeys("123456789");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        String signedIn = driver.findElement(By.id("nameofuser")).getText();
        Assert.assertTrue(signedIn.contains("omarRiad@gmail.com"));

        // Add item to cart
        driver.findElement(By.xpath("//*[@id=\"itemc\"]")).click();
        driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div[4]/div/div/h4/a")).click();
        driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();

        // Handle alert if present
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert present, continue with the test
        }

        // Proceed to checkout
        driver.findElement(By.id("cartur")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/button")).click();

        // Click on purchase button without filling data
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
        try {
            Alert alert = driver.switchTo().alert();
            String errorAlert =alert.getText();
            Assert.assertTrue(errorAlert.contains("Please fill out Name and Creditcard"));
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert present, continue with the test
        }
        // Click on purchase button with filling data
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("omarRiad");
        driver.findElement(By.xpath("//*[@id=\"country\"]")).sendKeys("Egy");
        driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("cairo");
        driver.findElement(By.xpath("//*[@id=\"card\"]")).sendKeys("11111111111");
        driver.findElement(By.xpath("//*[@id=\"month\"]")).sendKeys("6");
        driver.findElement(By.xpath("//*[@id=\"year\"]")).sendKeys("2022");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
        WebDriverWait waitttt = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        String kk = driver.findElement(By.xpath("/html/body/div[10]/h2")).getText();
        Assert.assertTrue(kk.contains("Thank you for your purchase!"));
        driver.findElement(By.xpath("/html/body/div[10]/div[7]/div/button")).click();
    }
    /*
   @Test (priority = 1)
   public void Login()
    {
        //login

        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("omarRiad@gmail.com");
        driver.findElement(By.id("loginpassword")).sendKeys("123456789");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        String signedIn = driver.findElement(By.id("nameofuser")).getText();
        Assert.assertTrue(signedIn.contains("omarRiad@gmail.com"));

        //add to cart
     driver.findElement(By.xpath("//*[@id=\"itemc\"]")).click();
        driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div[4]/div/div/h4/a")).click();
        driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a")).click();
        try {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    } catch (NoAlertPresentException e) {
        // No alert present, continue with your test
    }
        driver.findElement(By.id("cartur")).click();
        driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/button")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down the webpage
        js.executeScript("window.scrollBy(0, 50 );"); // Change '500' to the desired scroll distance

        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();

        WebDriverWait waittt = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='logInModal']//div[@class='modal-body']")));

        // Assert that the error message is displayed
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");

        // Optionally, you can also assert the text of the error message
        String expectedErrorMessage = "Please fill out Name and Creditcard.";
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage, "Error message text is incorrect");
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert present, continue with your test
        }
        driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/button")).click();
}
*/

/*
    @Test
    public void Scenario_of_TaskII()
    {
        driver.findElement(By.id("login2")).click();
        driver.findElement(By.id("loginusername")).sendKeys("omarRiad@gmail.com");
        driver.findElement(By.id("loginpassword")).sendKeys("123456789");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

//        String signedIn = driver.findElement(By.id("nameofuser")).getText();
//        Assert.assertTrue(signedIn.contains("omarRiad@gmail.com"));
       // driver.findElement(By.xpath("//*[@id=\"logout2\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"itemc\"]")).click();
        driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div[4]/div/div/h4/a")).click();
        driver.findElement(By.className("btn btn-success btn-lg")).click();
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert present, continue with your test
        }
        driver.findElement(By.id("cartur")).click();
        WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        waitt.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
        driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/button")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();

        WebDriverWait waittt = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='logInModal']//div[@class='modal-body']")));

        // Assert that the error message is displayed
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");

        // Optionally, you can also assert the text of the error message
        String expectedErrorMessage = "Please fill out Name and Creditcard.";
        Assert.assertEquals(errorMessage.getText(), expectedErrorMessage, "Error message text is incorrect");
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert present, continue with your test
        }
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("omarRiad");
        driver.findElement(By.xpath("//*[@id=\"country\"]")).sendKeys("Egy");
        driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("cairo");
        driver.findElement(By.xpath("//*[@id=\"card\"]")).sendKeys("11111111111");
        driver.findElement(By.xpath("//*[@id=\"month\"]")).sendKeys("6");
        driver.findElement(By.xpath("//*[@id=\"year\"]")).sendKeys("2022");
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
        WebDriverWait waitttt = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds timeout
        String kk = driver.findElement(By.xpath("/html/body/div[10]/h2")).getText();
        Assert.assertTrue(kk.contains("Thank you for your purchase!"));
        driver.findElement(By.xpath("/html/body/div[10]/div[7]/div/button")).click();

    }



//    @Test
//    public void inValidLoginn()
//    {
//        driver.findElement(By.id("login2")).click();
//        driver.findElement(By.id("loginusername")).sendKeys("omar");
//        driver.findElement(By.id("loginpassword")).sendKeys("123456");
//        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
//         String signedIn = driver.findElement(By.id("logInModalLabel")).getText();
//        Assert.assertTrue(signedIn.contains("Log in"));
//      }

//    @Test
//    public void emptyLoginn()
//    {
//        driver.findElement(By.id("login2")).click();
//        driver.findElement(By.id("loginusername")).sendKeys("  ");
//        driver.findElement(By.id("loginpassword")).sendKeys("  ");
//        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();
//        String signedIn = driver.findElement(By.id("logInModalLabel")).getText();
//        Assert.assertTrue(signedIn.contains("Log in"));
//    }

*/
    @AfterMethod
    public void quitDriver() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();

    }
}
