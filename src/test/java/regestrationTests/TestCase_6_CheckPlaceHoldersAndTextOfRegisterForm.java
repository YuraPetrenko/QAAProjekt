package regestrationTests;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;


@Epic("Allure examples")
@Feature("Junit 4 support")

public class TestCase_6_CheckPlaceHoldersAndTextOfRegisterForm extends BaseTest {


    @Test

    public void checkPlaceHoldersAndTextPlaceHolder() {
        String placeHolderUserName = "Pick a username";
        String placeHolderEmail = "you@example.com";
        String placeHolderPassword = "Create a password";
        String textOverUserName = "Username";
        String textOverEmail = "Email";
        String textOverPassword = "Password";

        loginPage.checkPlaceHolder(placeHolderUserName, placeHolderEmail, placeHolderPassword)
                .checkTextOverInputFieldInRegisterForm(textOverUserName, textOverEmail, textOverPassword)
                ;


    }


}
