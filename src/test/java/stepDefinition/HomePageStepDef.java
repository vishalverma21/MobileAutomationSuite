package stepDefinition;

import Timetable.HomePage;
import common.AttachHooks;
import common.ConfigManager;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class HomePageStepDef {

    HomePage homePage = new HomePage(AttachHooks.driver);

    private static Logger LOGGER = LoggerFactory.getLogger(HomePageStepDef.class);

    @And("^Verify footers on app$")
    public void verify_footers_on_app() throws Throwable {
        if (homePage.VerifyFooters()) {
            LOGGER.info("Footer is verified Successfully on home page");
        } else {
            Assert.fail();
        }
    }
}
