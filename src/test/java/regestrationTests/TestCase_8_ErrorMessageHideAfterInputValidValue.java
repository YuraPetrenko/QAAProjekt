package regestrationTests;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;

@Epic("Allure examples")
@Feature("Junit 4 support")

public class TestCase_8_ErrorMessageHideAfterInputValidValue extends BaseTest {





   @Test



    public void errorMessageHideAfterInputValidValue() {
       String validLogin = loginPage.createValidLoginBySize(30);
       String validEmail = loginPage.createValidEmail(8);
       String validPassword = loginPage.createValidPasswordBySize(50);
        loginPage.fillRegisterFormByClick("as", "asdsasd", "password")
                .checkErrorOfMessage(3)
                .fillRegisterFormByClick(validLogin,validEmail,validPassword)
                .checkErrorOfMessage(0);


    }


}
