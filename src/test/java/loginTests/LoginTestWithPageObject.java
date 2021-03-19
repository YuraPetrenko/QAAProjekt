package loginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.ExcelDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import pages.ParentPage;

import java.io.IOException;
import java.util.Map;


public class LoginTestWithPageObject extends BaseTest {


    public LoginTestWithPageObject() throws IOException {
    }

    @Test
    public void validLogin() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ParentPage.configProperties.DATA_FILE(), "validLogOn");
        loginPage.fillLoginFormAndSubmit(dataForValidLogin.get("login"), dataForValidLogin.get("pass"));
        checkExpectedResult("Button Sign In is not visible.", homePage.isButtonSignOutVisible());

    }


}
