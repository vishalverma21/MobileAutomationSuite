package Timetable;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }

    private static Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    public boolean VerifyFooters() {
        try {
            // Put Conditions
            return true;

        } catch (Exception e) {
            LOGGER.error("Footer texts and icons are not verified successfully " + e.getMessage());
        }
        LOGGER.error("Footer texts and icons are not verified successfully");
        return false;
    }

}
