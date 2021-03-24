package posts;

import baseTest.BaseTest;
import categories.SmokeTests;
import io.qameta.allure.*;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Epic("Allure examples")
@Feature("Junit 4 support")

public class CreateNewPostTest extends BaseTest {

    String postTitle = "Yura Title of post." + Util.getDateAndTimeFormated();
    String bodyTextPost = "Body text" + Util.getDateAndTimeFormated();
    String valueDopDown = "Частное сообщение";

    @Test
    public void createNewPost() {


        String validLogin = loginPage.createValidLoginBySize(30);
        String validEmail = loginPage.createValidEmail(8);
        String validPassword = loginPage.createValidPasswordBySize(50);

        loginPage.fillRegisterFormByClick(validLogin, validEmail, validPassword)
                .clickOnSignUpForOurAppButton()
                .checkIsButtonSignOutVisible()
                .clickOnCreatePostButton()
                .checkIsRedirectOnCreatePostPage()
                .enterTitleInToTitle(postTitle)
                .enterTextInToInputBody(bodyTextPost)
                .clickValueInDropDownInCreatePost(valueDopDown)
                .clickOnButtonSavePost()
                .checkIsRedirectOnSinglePostPage()
                .checkIsSuccessMessageDisplayed()
                .clickOnProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .checkIsPostWasAdded(postTitle);


    }


    @After
    public void deletePost() {

        homePage.checkIsButtonSignOutVisible()
                .clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .deletePostWhilePresent("Yura Title of post.");

    }


}
