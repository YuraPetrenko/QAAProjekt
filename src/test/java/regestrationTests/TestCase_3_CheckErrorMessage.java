package regestrationTests;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Epic("Allure examples")
@Feature("Junit 4 support")
public class TestCase_3_CheckErrorMessage extends BaseTest {


    @Test
    @Parameters({
            "qw,bald2004@ukr.net,qwertyuiopq,3,Username must be at least 3 characters.;That email is already being used.;Password must be at least 12 characters.",
            "qwertyuiopqwertyuiopqwertyuiopq,bald2004ukr.net,123456789012345678901234567890123456789012345678901,3,Username cannot exceed 30 characters.;You must provide a valid email address.;Password cannot exceed 50 characters.",
            "bald2004,,,3,That username is already taken.;You must provide a valid email address.;Password must be at least 12 characters.",
            "@@@@,,,3,Username can only contain letters and numbers.;You must provide a valid email address.;Password must be at least 12 characters.",
            "abc d,,,3,Username can only contain letters and numbers.;You must provide a valid email address.;Password must be at least 12 characters.",
            "abcd,,asdadsadadadadasdasd,1,You must provide a valid email address.",
            ",baldasd@zczcczc.com,asdadsadadadadasdasd,1,Username must be at least 3 characters.",
            "dgdg,baldasd@zczcczc.com,,1,Password must be at least 12 characters."
//
//            "qwerty,bald2004@ukr.nrtet,123456qwerty, 0, ",
//            ",abc@,123456qwerty, 2,Username must be at least 3333 characters.;You must provide a valid email address.",
//            ",abc@,56qwerty, 3,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
//            "qwerty,bald2004@ukr.nrtet,12,1,Password must be at least 12 characters."
    })
    @TestCaseName("checkErrorMessageInRegisterForm: userName = {0}, email = {1}, password = {2}, countUnValidValue = {3}, textOfErrorMessages = {4}")

    public void checkErrorMessageInRegisterForm(String userName, String email, String password, int countUnValidValue, String textOfErrorMessages) {
        loginPage.fillRegisterFormByClick(userName, email, password)
                .clickOnSignUpForOurAppButtonForUnValidRegistration()
                .checkCountErrorOfMessagesAfterSubmitRegisterIn(countUnValidValue)
                .checkTextOfErrorsInRegisterIn(textOfErrorMessages);


    }


}
