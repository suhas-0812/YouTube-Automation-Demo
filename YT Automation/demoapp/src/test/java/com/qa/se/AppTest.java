package com.qa.se;

import java.time.Duration;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest 
{
    // Declare WebDriver as a class variable
    private WebDriver driver;

    // Use @BeforeTest annotation to initialize the driver and navigate to the website
    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Navigate to the YouTube page
        driver.get("https://www.youtube.com");
        // Enter the full screen mode using the driver.manage().window().fullscreen() method
        driver.manage().window().fullscreen();
    }

    // Use @Test annotation to mark the test method
    @Test
    public void sampleTestMethod() throws InterruptedException, IOException {
        // Find the search box element
        driver.findElement(By.name("search_query")).click();
        // Create an Actions object to perform keyboard actions
        Actions actions = new Actions(driver);
        // Type "Selenium WebDriver tutorial" using the sendKeys() method
        actions.sendKeys("Selenium WebDriver tutorial").perform();
        // Wait for 3 seconds
        Thread.sleep(3000);
        // Press the ENTER key using the Keys enum
        actions.sendKeys(Keys.ENTER).perform();
        // Wait for 3 seconds
        Thread.sleep(3000);
        // Find the first video element using XPath
        WebElement firstVideo = driver.findElement(By.xpath("//div[@id='contents']//ytd-video-renderer[1]//a[@id='video-title']"));
        // Execute JavaScript to click the element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", firstVideo);
        // Wait for 3 seconds
        Thread.sleep(3000);
        // Create a WebDriverWait object to wait for the skip ad button with a timeout of 5 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Try to find the skip ad button using XPath
        try {
            WebElement skipAdButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ytp-ad-skip-button ytp-button']")));
            // Execute JavaScript to click the element
            js.executeScript("arguments[0].click();", skipAdButton);
        } catch (Exception e) {
            // If the skip ad button is not found, do nothing
        }
        // Press the SPACE key to pause the video
        actions.sendKeys(Keys.SPACE).perform();
        // Wait for 3 seconds
        Thread.sleep(3000);
        // Press the SPACE key again to play the video
        actions.sendKeys(Keys.SPACE).perform();
        // Wait for 3 seconds
        Thread.sleep(3000);
        
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

