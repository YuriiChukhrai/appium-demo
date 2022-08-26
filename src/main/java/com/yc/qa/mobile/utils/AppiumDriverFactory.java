package com.yc.qa.mobile.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

@Slf4j
public final class AppiumDriverFactory {
    private AppiumDriverFactory() {
        throw new UnsupportedOperationException("Illegal access to private constructor");
    }

    public static AppiumDriver getAppiumDriver(final String mobileDriver, final String url) throws MalformedURLException {

        if(Objects.isNull(url) || url.isEmpty()){
            throw new RuntimeException(String.format("The Appium URL not specify [%s].", url));
        }

        final DesiredCapabilities caps = new DesiredCapabilities();

        if("IOS".equalsIgnoreCase(mobileDriver)){
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.5");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");

                caps.setCapability(MobileCapabilityType.UDID,  System.getProperty("ios.udid"));
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
                caps.setCapability(MobileCapabilityType.FULL_RESET, "true");
                caps.setCapability(IOSMobileCapabilityType.USE_PREBUILT_WDA, "true");

                caps.setCapability("showXcodeLog", "true");
                caps.setCapability("autoAcceptAlerts", "true");

                return getAppiumDriver(mobileDriver, url, caps);
        }

                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 5");
                caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
                //caps.setCapability(MobileCapabilityType.FULL_RESET, "true");

                return getAppiumDriver(mobileDriver, url, caps);
    }

    public static AppiumDriver getAppiumDriver(final String mobileDriver, final String url, final DesiredCapabilities caps) throws MalformedURLException {

        return "IOS".equalsIgnoreCase(mobileDriver) ? new IOSDriver(new URL(url), caps) : new AndroidDriver(new URL(url), caps);
    }
}
