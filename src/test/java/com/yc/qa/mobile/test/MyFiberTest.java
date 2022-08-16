package com.yc.qa.mobile.test;

import com.google.gson.Gson;
import com.yc.qa.mobile.utils.AppiumDriverFactory;
import com.yc.qa.mobile.utils.BaseListener;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.*;
import lombok.extern.java.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Objects;

import com.yc.qa.mobile.utils.Utils;

/**
 * APK file: https://apkpure.com/google-fiber/com.google.android.apps.fiber.myfiber
 *
 * */
//TODO Listener
    /**
     *
     * Listener should to attache, for the failed TC:
     * a) Source page/screen;
     * b) Screenshot
     * c) Appium server logs
     * */

@Log
@Listeners(BaseListener.class)
public class MyFiberTest {

    private URL appiumServerUrl;
    private AppiumDriver ad;
    private WebDriverWait wait;
    private AppiumDriverLocalService service;

    @BeforeSuite
    public void beforeSuite(){
        final DesiredCapabilities serverCap = new DesiredCapabilities();
        final AppiumServiceBuilder builder = new AppiumServiceBuilder();

        // Set Capabilities
        serverCap.setCapability("noReset", "false");

        // Build the Appium service
        builder.withIPAddress("127.0.0.1"); //String.format("%s:%s/wd/hub", System.getProperty("appium.uri"), System.getProperty("appium.port"));
        builder.usingAnyFreePort();
        //builder.usingPort(4723);
        builder.withCapabilities(serverCap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"info");

        /**
         *
         * @url: https://github.com/appium/java-client/blob/master/docs/v7-to-v8-migration-guide.md#appiumdriverlocalservice
         *
         * If you still would like to use v8 of the Java client with Appium v1.2x,
         * where the server URL contains the /wd/hub suffix by default, then consider providing [--base-path]
         * setting explicitly while building AppiumServiceBuilder instance
         * */
        builder.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/");


        // Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);

        appiumServerUrl = service.getUrl();

        service.start();
        service.enableDefaultSlf4jLoggingOfOutputData();
    }

    @AfterSuite
    public void afterSuite(){
        if(Objects.nonNull(service)){
            Utils.attachText("Appium server logs", service.getStdOut());
            service.stop();
        }
    }

    @BeforeClass
    public void beforeClass() throws FileNotFoundException {

        //TODO move to the AppiumDriverFactory
//        final DesiredCapabilities caps = new DesiredCapabilities();
//
//        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
//        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");
//
//        caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
//        caps.setCapability(MobileCapabilityType.NO_RESET, "false");
//
//        caps.setCapability(MobileCapabilityType.FULL_RESET, "true");
//        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
//
//        final UiAutomator2Options options = new UiAutomator2Options();
//        options.merge(caps);
//        options.setApp(Paths.get("./src/main/resources/artifacts/GoogleFiber_v2.1.3.apk").toAbsolutePath().toString());
//        options.setAppPackage("com.google.android.apps.fiber.myfiber");
//        options.fullReset();
//        options.setNewCommandTimeout(Duration.ofSeconds(10L));

        // Download [DesiredCapabilities] from JSON file [pixel-3_android_11.json]
        final UiAutomator2Options options = new Gson().fromJson(new FileReader(Paths.get("./src/main/resources/capabilities/pixel-3_android_11.json").toAbsolutePath().toString()), UiAutomator2Options.class);

        ad = new AndroidDriver(appiumServerUrl, options); //AppiumDriverFactory.getAppiumDriver("android", url, caps);
        wait = new WebDriverWait(ad, Duration.ofSeconds(15L));
    }

    @AfterClass
    public void afterClass() {
        if(Objects.nonNull(ad)){
            ad.quit();
        }
    }

    @Features({ @Feature("Appium"), @Feature("Android") })
    @Issues({ @Issue("GA-001"), @Issue("OLA-002") })
    @Stories({ @Story("Stories: CIR-098"), @Story("Stories: CIR-099") })
    @Epics({ @Epic("Epic03"), @Epic("Epic04") })
    @TmsLinks({ @TmsLink("1234"), @TmsLink("4321") })
    @Links({ @Link(url="https://github.com/YuriiChukhrai/appium-demo", name="GitHub"), @Link(url="https://www.linkedin.com/in/yurii-c-b55aa6174/", name="LinkedIn") })
    @Lead("Yurii Chukhrai")
    @Flaky
    @Severity(SeverityLevel.NORMAL)
    @Description("Title assertion")
    @Owner("Yurii Chukhrai")
    @Test
    public void openMyFiberTest(){
        String welcomeText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.ImageView[contains(@content-desc,'Google Fiber')]"))).getAttribute("content-desc");

        log.info(String.format("1#Step: welcome text [%s]", welcomeText));
        Utils.makeScreenShot("#1 Landing page", ad);
    }
}
