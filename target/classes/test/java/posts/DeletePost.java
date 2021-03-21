package posts;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.Test;
@Epic("Allure examples")
@Feature("Junit 4 support")
public class DeletePost extends BaseTest {


   @Test
    public void deletePost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .checkIsPostWithTheValidTitleIsVisible()
                .clickOnPostWithTheValidTitle()
                .checkIsRedirectOnSinglePostPage()
               .clickOnDeletePostButton();

    }
    @Test
    public void deleteSecondPost() {
        loginPage.loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnMyProfileButton()
                .checkIsRedirectOnMyProfilePage()
                .checkIsPostWithTheValidTitleIsVisible()
                .clickOnPostWithTheValidTitle()
                .checkIsRedirectOnSinglePostPage()
                .clickOnDeletePostButton();

    }

}
