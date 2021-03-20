package regestrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class TestCase_4_CheckErrorMessageIfEmptyFieldByTab extends BaseTest {


    @Test
    @Parameters({
            ",,,3,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters."
    })
    @TestCaseName("checkErrorMessageInRegisterForm: userName = {0}, email = {1}, password = {2}, countUnValidValue = {3}, textOfErrorMessages = {4}")

    public void checkErrorMessageInRegisterForm(String userName, String email, String password, int countUnValidValue, String textOfErrorMessages) {
        loginPage.fillRegisterFormByTab(userName, email, password)
                .sentKeyEnterOnSignUpForOurAppButtonForUnValidRegistration(1)
                .checkCountErrorOfMessagesAfterSubmitRegisterIn(countUnValidValue)
                .checkTextOfErrorsInRegisterIn(textOfErrorMessages);


    }


}
