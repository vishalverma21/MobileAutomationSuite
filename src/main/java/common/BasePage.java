package common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BasePage {

    protected static WebDriver driver;
    static WebDriverWait wait;
    private static Logger LOGGER = LoggerFactory.getLogger(BasePage.class);


    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    public WebElement waitForWebElementPresent(WebElement element, int timeoutSeconds) {
        wait = new WebDriverWait(driver, timeoutSeconds);
        WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(element));
        if (elem != null) {
            return elem;
        } else {
            return null;
        }
    }


    public static void scrollIntoViewElement(WebDriver driver, WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", element);
    }

    //Common Method created to check if element is present - Vishal
    public boolean isElementPresent(By locator, int timeoutInSeconds) {
        try {
            wait = new WebDriverWait(driver, timeoutInSeconds);
            WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            if (elem != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    //Common Method created to find an element
    public WebElement findElement(By locator, int timeoutSeconds) {
        wait = new WebDriverWait(driver, timeoutSeconds);
        WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        if (elem != null) {
            return elem;
        } else {
            return null;
        }
    }

    //Common Method created to find an element
    public WebElement waitForElementToBePresent(By locator, int timeoutSeconds) {
        wait = new WebDriverWait(driver, timeoutSeconds);
        WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (elem != null) {
            return elem;
        } else {
            return null;
        }
    }

    public String TotalTime(String pageName, double startTime) {
        double endTime = System.currentTimeMillis();
        double total = (endTime - startTime) / 1000;
        LOGGER.info("Total time taken for " + pageName + " is :::: " + total);
        return String.valueOf(total);
    }

    public Integer LessWaitElementFor() {
        int timeInSecond = Integer.parseInt(ConfigManager.getProperty("LessWaitForElementTime"));
        return timeInSecond;
    }

    public Integer WaitElementFor() {
        int timeInSecond = Integer.parseInt(ConfigManager.getProperty("WaitForElementTime"));
        return timeInSecond;
    }

    public Integer LongWaitElementFor() {
        int timeInSecond = Integer.parseInt(ConfigManager.getProperty("LongWaitForElementTime"));
        return timeInSecond;
    }

    public void getscreenshot(AppiumDriver<MobileElement> d) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        Date date = new Date();
        String fileName = sdf.format(date);
        File des = d.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(des, new File(System.getProperty("user.dir") + "/target/" + fileName + ".png"));
        System.out.println("Screenshot is captured");
    }

    public static void clearDataJs(WebElement element, String a) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + a + "';", element);

    }

    public MobileElement mobileScrollToExactElement(String str) {
        MobileElement element = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"" + str + "\"))"));
        return element;
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}