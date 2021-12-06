package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AttachHooks {

    private Scenario scenario;
    public static WebDriver driver;
    DesiredCapabilities capabilities;
    private static Logger LOGGER = LoggerFactory.getLogger(AttachHooks.class);

    //for extent external report only

    ExtentReports extent = new ExtentReports();

    ExtentTest test;

    ExtentHtmlReporter htmlReporter;

    @Before
    public void setUp(Scenario scenario) throws IOException, Exception {
        LOGGER.info("Inside set up method of before hook");
        LOGGER.info(System.getProperty("user.dir"));
        LOGGER.info("setup:::::::");
        ConfigManager.loadConfig();
        this.scenario = scenario;

        String path = System.getProperty("user.dir") + ConfigManager.getProperty("ApkPath");

        /**
         * This method Is responsible for executing test cases on Native Mobile
         * apps.
         */
        if (ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("Mobile")) {
            if (ConfigManager.getProperty("PlatformName").equalsIgnoreCase("Android")) {
                boolean DriverNoResetFlag = true;
                boolean locationServiceEnabled = true;
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("emulator", true);
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.getProperty("DeviceName"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
                        ConfigManager.getProperty("PlatformName"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                        ConfigManager.getProperty("PlatformVersion"));
                capabilities.setCapability("automationName", "UiAutomator2");
                capabilities.setCapability(MobileCapabilityType.APP, path);
                capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60000");
                if (DriverNoResetFlag) {
                    capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
                    capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
                    // capabilities.setCapability("noReset", DriverNoResetFlag);
                }
                if (locationServiceEnabled = true) {
                    capabilities.setCapability("autoAcceptAlerts", true);
                }

                try {
                    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            } else if (ConfigManager.getProperty("PlatformName").equalsIgnoreCase("iOS")) {
                boolean DriverNoResetFlag = true;
                boolean locationServiceEnabled = true;
                capabilities = new DesiredCapabilities();
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
                        ConfigManager.getProperty("PlatformVersion"));
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
                        ConfigManager.getProperty("PlatformName"));
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigManager.getProperty("DeviceName"));
                capabilities.setCapability(MobileCapabilityType.APP, ConfigManager.getProperty("ApkPath"));
                capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60000");
                if (DriverNoResetFlag) {
                    capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
                    capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
                    // capabilities.setCapability("noReset", DriverNoResetFlag);
                }
                if (locationServiceEnabled = true) {
                    capabilities.setCapability("autoAcceptAlerts", true);
                }
                try {
                    // driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

                    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * This method Is responsible for stopping test case and embedding
     * screenshot on failure
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     */
    @After
    public void tearDown() throws Exception {
        if (scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
        } else if (!scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
        }
        driver.quit();
    }

}