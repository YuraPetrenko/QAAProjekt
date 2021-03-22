package regestrationTests;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;


@Epic("Allure examples")
@Feature("Junit 4 support")

public class TestCase_5_ValidRegistrationByTabAndCheckBorderColor extends BaseTest {


    @Test

    public void validRegistrationByTab() {
        String validLogin = loginPage.createValidLoginBySize(30);
        String validEmail = loginPage.createValidEmail(8);
        String validPassword = loginPage.createValidPasswordBySize(50);
        String borderColorOfActiveInput = "rgba(128, 189, 255, 1)";
        String borderColorOfSignUpFprUppActive = "rgb(40, 167, 69)";
        String colorOfSignUpForOurAppButton = "rgba(40, 167, 69, 1)";
        loginPage.fillAndCheckBorderColorRegisterForm(validLogin, validEmail, validPassword, borderColorOfActiveInput, borderColorOfSignUpFprUppActive)
                .checkColorOfSignUpForOurAppButton(colorOfSignUpForOurAppButton)
                .clickOnSignUpForOurAppButton()
                .checkIsRedirectOnHomePage()
                .isButtonSignOutVisible();


    }


}
