package com.yc.qa.mobile.utils;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Objects;

@Slf4j
public final class Utils {
    private Utils() {
        throw new UnsupportedOperationException("Illegal access to private constructor");
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] makeScreenShot(final String fileName, final AppiumDriver appiumDriver) {

        if(Objects.nonNull(appiumDriver)){
            return  ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES);
        }
        return null;
    }

    /* This method make Text attachment for Allure report */
    @Attachment(value = "{0}", type = "text/plain")
    public static synchronized String attachText(final String nameOfAttachment, final String bodyOfMessage) {

        log.info(String.format("TID [%d] - Attached to allure file [%s].", Thread.currentThread().getId(),
                nameOfAttachment));

        return bodyOfMessage;
    }
}
