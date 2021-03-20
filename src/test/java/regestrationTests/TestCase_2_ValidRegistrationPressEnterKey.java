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
        String validEmail = loginPage.createValidEmail(8);
        String validPassword = loginPage.createValidPasswordBySize(50);

        loginPage.fillRegisterFormByTab(validLogin, validEmail, validPassword)
                .checkErrorOfMessage(0)
                .checkIsTextIsVisibleAfterMinimizeWindow(0, validLogin, validEmail, validPassword)
                .checkIsTextIsVisibleAfterMinimizeWindow(300, validLogin, validEmail, validPassword)
                .sentKeyEnterOnSignUpForOurAppButton()
                .registrationFormIsNotVisible()
                .messageHelloNewUserIsVisible(validLogin)
                .checkIsSearchButtonVisible()
                .checkIsChatButtonVisible()
                .checkIsAvatarButtonVisible()
                .checkIsUserNameButtonVisible();

    }


}
