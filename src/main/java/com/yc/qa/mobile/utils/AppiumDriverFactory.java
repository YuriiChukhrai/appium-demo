package com.yc.qa.mobile.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.java.Log;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@Log
public final class AppiumDriverFactory {
    private AppiumDriverFactory() {
        throw new UnsupportedOperationException("Illegal access to private constructor");
    }

    public static AppiumDriver getAppiumDriver(final String mobileDriver, final String urlS) throws MalformedURLException {

        if(Objects.isNull(mobileDriver) || mobileDriver.isEmpty()){
            throw new RuntimeException(String.format("The Appium Driver not specify [%s].", mobileDriver));
        }

        final URL url = new URL(urlS);
        AppiumDriver appiumDriver = null;
        final DesiredCapabilities caps = new DesiredCapabilities();

        switch(mobileDriver.trim().toUpperCase()) {


            case "IOS": {
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");

                caps.setCapability(MobileCapabilityType.UDID,  System.getProperty("ios.udid"));
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
                caps.setCapability(MobileCapabilityType.FULL_RESET, "true");

                caps.setCapability("showXcodeLog", "true");
                caps.setCapability("autoAcceptAlerts", "true");

                appiumDriver = new IOSDriver(url, caps);

                break;
            }

            case "ANDROID": {
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 5");
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");

                appiumDriver = new AndroidDriver(url, caps);

                break;
            }
        }

        return appiumDriver;
    }
}
