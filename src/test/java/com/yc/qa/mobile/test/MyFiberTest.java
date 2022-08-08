package com.yc.qa.mobile.test;

import com.yc.qa.mobile.utils.AppiumDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Objects;

/**
 * APK file: https://apkpure.com/google-fiber/com.google.android.apps.fiber.myfiber
 *
 * */
public class MyFiberTest {

    //private final static String url = String.format("%s:%s/wd/hub", System.getProperty("appium.uri"), System.getProperty("appium.port"));
    private AppiumDriver ad;
    private WebDriverWait wait;


    @BeforeClass
    public void beforeClass() throws MalformedURLException {

        //File app = new File("build/ApiDemos-debug.apk");
        final DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");

        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        //caps.setCapability(MobileCapabilityType.NO_RESET, "false");
        //caps.setCapability(MobileCapabilityType.FULL_RESET, "true");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");

        final UiAutomator2Options options = new UiAutomator2Options();
        options.merge(caps);
        options.setApp(Paths.get("./src/main/resources/artifacts/GoogleFiber_v2.1.3.apk").toAbsolutePath().toString());
        options.setAppPackage("com.google.android.apps.fiber.myfiber");
        options.fullReset();
        //options.setAppActivity(".ApiDemos");

        ad = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options); //AppiumDriverFactory.getAppiumDriver("android", url, caps);
        wait = new WebDriverWait(ad, Duration.ofSeconds(60L));
    }

    @AfterClass
    public void afterClass() {
        if(Objects.nonNull(ad)){
            ad.quit();
        }
    }

    @Test
    public void openMyFiberTest(){

    }
}
