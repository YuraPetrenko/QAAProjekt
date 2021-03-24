package pages;

import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.util.List;

import static org.hamcrest.Matchers.containsString;

public class MyProfilePage extends ParentPage {

    final String postTitleLocator = ".//*[contains(text(),'%s')]";

    @FindBy(xpath = postTitleLocator)
    private TextBlock validTitleOfPost;

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private Button myProfileButton;
    @FindBy(xpath = ".//*[contains(text(), 'successfully deleted')]")
    private TextBlock successPostDeleteElement;

    @Override
    String getRelativeUrl() {
        return "/profile";
    }

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectOnMyProfilePage() {

        waitChatToBeHide();
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), containsString(baseUrl + getRelativeUrl()));
        return this;
    }

    @Step
    public MyProfilePage checkIsPostWithTheValidTitleIsVisible(String postTitle) {
        List<WebElement> postsList = webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
        Assert.assertEquals("Numbers of posts with title " + postTitle + " is " + postsList.size(), 1, postsList.size());
        logger.info(postsList + " Post was added. ");
        return this;

    }


    @Step
    public SinglePostPage clickOnPostWithTheValidTitle() {

        clickOnElement(validTitleOfPost);
        return new SinglePostPage(webDriver);
    }

    @Step
    public MyProfilePage checkIsPostWithTheValidTitleIsNotVisible(String postTitle) {
        try {
            Assert.assertTrue("Element is visible", !isElementDisplayed(validTitleOfPost));
            logger.info("Post with title \"" + TestData.VALID_TITLE + "\" is not visible in the  list of posts.");
            return this;
        } catch (Exception e) {
            logger.info("Post with title \" " + TestData.VALID_TITLE + "\" is visible in the list of posts.");
            Assert.fail("Post with title \" " + TestData.VALID_TITLE + "\" is visible in the list of posts.");
        }

        return this;
    }

    public MyProfilePage clickOnMyProfileButton() {

        clickOnElement(myProfileButton);
        return new MyProfilePage(webDriver);
    }

    @Step
    public MyProfilePage deletePostWhilePresent(String post_title) {

        List<WebElement> listOfPost = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
        int count = listOfPost.size();
        for (WebElement element: listOfPost ) {
            clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, post_title))));
            new SinglePostPage(webDriver)
                    .checkIsRedirectOnSinglePostPage()
                    .clickOnDeletePostButton()
                    .checkIsRedirectOnMyProfilePage()
                    .checkSuccessDeletePost();
            logger.info("Post with titlt "+ post_title + " was deleted ");
        }

        return this;
    }

    @Step
    private MyProfilePage checkSuccessDeletePost() {
        checkIsElementVisible(successPostDeleteElement);
        return this;
    }

    @Step
    public MyProfilePage checkIsPostWasAdded(String post_title) {


        List<WebElement> postsList = webDriver.findElements(By.xpath(String.format(postTitleLocator, post_title)));
        Assert.assertEquals("Numbers of posts with title " + post_title + " is " + postsList.size(), 1, postsList.size());
        logger.info(post_title + " Post was added. ");
        return this;
    }

    @Step
    public SinglePostPage clickOnPostWithTitle(String post_title) {
        clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, post_title))));
        return new SinglePostPage(webDriver);
    }
}
