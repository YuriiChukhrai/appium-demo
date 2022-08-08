package com.yc.qa.mobile.test;

import com.yc.qa.mobile.utils.AppiumDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

@Log
public class BrowserTest {

    /**
     * For CLI: System.getProperty("mobile.driver")
     *
     * */
    @DataProvider(name = "dp", parallel=false)
    public Iterator<AppiumDriver> appiumDriverDataProvider() throws MalformedURLException {
        final String url = String.format("%s:%s/wd/hub", System.getProperty("appium.uri"), System.getProperty("appium.port"));

        return new ArrayList<AppiumDriver>(Arrays.asList(AppiumDriverFactory.getAppiumDriver("ios", url), AppiumDriverFactory.getAppiumDriver("android", url))).iterator();
    }

    @Test(dataProvider = "dp")
    public void titleValidationTest(AppiumDriver ad) {
        final WebDriverWait wait = new WebDriverWait(ad, Duration.ofSeconds(60L));

        ad.get("https://fiber.google.com");
        wait.until(ExpectedConditions.titleContains("Google Fiber"));
        log.info("1#Step");
        makeScreenShot("#1 Landing page", ad);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder='Enter your address']")))
                .sendKeys("1600 Amphitheatre Pkwy");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pac-container .pac-item:nth-child(1) .pac-matched:nth-child(2)")))
                .click();
        log.info("2#Step");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder='Unit #']:nth-child(1)")))
                .sendKeys("111");
        log.info("3#Step");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder='ZIP']:nth-child(1)")))
                .sendKeys("94043");
        log.info("4#Step");
        makeScreenShot("#2 All fields filled", ad);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".address-checker__submit.mobile.address-checker__submit--inline.glue-button.glue-button--high-emphasis"))).click();
        log.info("5#Step");
        makeScreenShot("#3 Check Address?", ad);



        if(Objects.nonNull(ad)){
            ad.quit();
        }
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] makeScreenShot(final String attachmentName, final AppiumDriver ad) {
        final byte[] rawImage = ((TakesScreenshot) ad).getScreenshotAs(OutputType.BYTES);
        return rawImage;
    }
}
