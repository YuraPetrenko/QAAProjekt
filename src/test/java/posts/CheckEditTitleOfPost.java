package posts;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import libs.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class CheckEditTitleOfPost extends BaseTest {
    String postTitle = "Yura Title of post." + Util.getDateAndTimeFormated();
    String bodyTextPost = "Body text" + Util.getDateAndTimeFormated();
    String valueDopDown = "Частное сообщение";
    String newPostTitle = "++++++++++++++" + Util.getDateAndTimeFormated();

    @Before
    public void createNewPost() {
        String validLogin = loginPage.createValidLoginBySize(30);
        String validEmail = loginPage.createValidEmail(8);
        String validPassword = loginPage.createValidPasswordBySize(50);

        loginPage.fillRegisterFormByClick(validLogin, validEmail, validPassword).clickOnSignUpForOurAppButton()
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


    @Test

    public void checkEditOfTitle() {
        homePage.clickOnMyProfileButton().
                checkIsRedirectOnMyProfilePage().
                clickOnPostWithTitle(postTitle).
                checkIsRedirectOnSinglePostPage().
                clickOnEditPostButton().
                editTitleOfPost(newPostTitle).
                checkIsPostSuccessfullyUpdated().
                clickOnMyProfileButton().
                checkIsRedirectOnMyProfilePage().
                checkIsPostWasAdded(newPostTitle);

    }


    @After
    public void deletePost() {

        homePage.openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .deletePostWhilePresent(newPostTitle);
    }

}