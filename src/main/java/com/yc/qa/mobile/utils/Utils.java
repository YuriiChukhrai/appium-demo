package com.yc.qa.mobile.utils;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Objects;

@Log
public final class Utils {
    private Utils() {
        throw new UnsupportedOperationException("Illegal access to private constructor");
    }

    @Attachment(value = "{0}", type = "image/png")
    @Step("Make makeScreenshot [{0}]")
    public static byte[] makeScreenShot(final String fileName, final AppiumDriver appiumDriver) {

        if(Objects.nonNull(appiumDriver)){
            return  ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES);
        }
        return null;
    }

    /* This method make Text attachment for Allure report */
    @Attachment(value = "{0}", type = "text/plain")
    @Step("Add attachment [{0}] to report")
    public static synchronized String attachText(final String nameOfAttachment, final String bodyOfMessage) {

        log.info(String.format("TID [%d] - Attached to allure file [%s].", Thread.currentThread().getId(),
                nameOfAttachment));

        return bodyOfMessage;
    }
}
