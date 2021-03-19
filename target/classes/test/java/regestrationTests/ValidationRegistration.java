package regestrationTests;

import baseTest.BaseTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ValidationRegistration extends BaseTest {


    @Test
    public void validationRegistration() {
        loginPage.openLoinPage();
        loginPage.enterUserNameRegisterIn("12");
        loginPage.enterEmailRegisterIn("test.ua");
        loginPage.enterPasswordRegisterIn("123456789123");
        SoftAssertions softAssertions = new SoftAssertions();


    }


}

