package regestrationTests;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;


@Epic("Allure examples")
@Feature("Junit 4 support")

public class TestCase_1_ValidRegistrationByClick extends BaseTest {


    @Test

    public void validRegistrationByClick() {
        String validLogin = loginPage.createValidLoginBySize(3);
        String validEmail = loginPage.createValidEmail(8);
        String validPassword = loginPage.createValidPasswordBySize(12);

        loginPage.fillRegisterFormByClick(validLogin, validEmail, validPassword)
                .checkErrorOfMessage(0)
                .checkIsPasswordIsNotVisible(validPassword)
                .checkIsPasswordCopy(validPassword)
                .clickOnSignUpForOurAppButton()
                .checkIsRedirectOnHomePage()
                .registrationFormIsNotVisible()
                .messageHelloNewUserIsVisible(validLogin)
                .checkIsSearchButtonVisible()
                .checkIsChatButtonVisible()
                .checkIsAvatarButtonVisible()
                .checkIsUserNameButtonVisible()
                .checkBackGroundCreatePosButton("rgba(40, 167, 69, 1)")
                .checkBackGroundSingOutButton("rgba(108, 117, 125, 1)");

    }


}
