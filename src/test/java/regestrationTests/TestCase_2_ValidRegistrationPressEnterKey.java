package regestrationTests;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;


@Epic("Allure examples")
@Feature("Junit 4 support")

public class TestCase_2_ValidRegistrationPressEnterKey extends BaseTest {


    @Test

    public void validRegistrationByEnter() {
        String validLogin = loginPage.createValidLoginBySize(30);
        String validEmail = loginPage.createValidEmail();
        String validPassword = loginPage.createValidPasswordBySize(50);

        loginPage.fillRegisterFormAndSubmitByEnter(validLogin, validEmail, validPassword)
                .checkIsTextIsVisibleAfterMinimizeWindow(0,validLogin, validEmail, validPassword)
                .checkIsTextIsVisibleAfterMinimizeWindow(300,validLogin, validEmail, validPassword)
                .checkIsPasswordIsNotVisible(validPassword)
                .checkIsPasswordCopy(validPassword)
                .checkErrorOfMessage(0)
                .clickSingUpForOurAppButton()
                .checkIsRedirectOnHomePage()
                .registrationFormIsNotVisible()
                .messageHelloNewUserIsVisible(validLogin)
                .checkIsSearchButtonVisible()
                .checkIsChatButtonVisible()
                .checkIsAvatarButtonVisible()
                .checkIsUserNameButtonVisible();

    }


}
