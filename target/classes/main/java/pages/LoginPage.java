package pages;


import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.Form;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

import static org.hamcrest.Matchers.contains;

public class LoginPage extends ParentPage {


    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'username']")
    private TextInput inputUserNameInLoginIn;
    @FindBy(xpath = ".//form[@action = '/login']//input[@name = 'password']")
    private TextInput inputPasswordInLoginIn;

    @FindBy(id = "username-register")
    private TextInput inputUserNameInRegisterIn;
    @FindBy(id = "email-register")
    private TextInput inputEmailInRegisterIn;
    @FindBy(id = "password-register")
    private TextInput inputPassWordInRegisterIn;


    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private Button buttonSignIn;
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text() = 'Username must be at least 3 characters.']")
    private TextBlock popUpErrorUnValidUsername;

    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text() = 'You must provide a valid email address.']")
    private TextBlock popUpErrorUnValidEmail;

    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text() = 'Password must be at least 12 characters.']")
    private TextBlock popUpErrorUnValidPassword;

    @FindBy(xpath = ".//button[@type = 'submit']")
    private Button signUpForOurAppButton;

    @FindBy(xpath = ".//form[@action ='/register']")
    private Form registrationForm;

    private String popUpRegisterError = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    private String popUpLoginError = ".//div[@class='alert alert-danger text-center']";

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public void openLoinPage() {

        try {
            webDriver.get(baseUrl + getRelativeUrl());
            logger.info("Login page was open.");
        } catch (Exception e) {
            logger.info("Can not open loginPage.");
            Assert.fail("Can not open loginPage.");
        }

    }

    @Step
    public void enterLoginSignIn(String login) {
        enterTextInToElement(inputUserNameInLoginIn, login);
    }

    @Step
    public void enterPassWordSignIn(String passWord) {
        enterTextInToElement(inputPasswordInLoginIn, passWord);
    }

    @Step
    public void clickButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public LoginPage fillLoginFormAndSubmit(String login, String password) {
        openLoinPage();
        enterLoginSignIn(login);
        enterPassWordSignIn(password);
        clickButtonSignIn();
        return new LoginPage(webDriver);
    }

    @Step
    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    @Step
    public void enterUserNameRegisterIn(String userName) {
        enterTextInToElement(inputUserNameInRegisterIn, userName);
    }

    @Step
    public void enterEmailRegisterIn(String email) {
        enterTextInToElement(inputEmailInRegisterIn, email);
    }

    @Step
    public void enterPasswordRegisterIn(String password) {
        enterTextInToElement(inputPassWordInRegisterIn, password);
    }

    @Step
    public LoginPage fillRegisterFormAndSubmitByClick(String userName, String email, String password) {
        openLoinPage();
        enterUserNameRegisterIn(userName);
        enterEmailRegisterIn(email);
        enterPasswordRegisterIn(password);
        clickOnElement(signUpForOurAppButton);
        return new LoginPage(webDriver);
    }

    @Step
    public void checkPopUpMessage() {
        Assert.assertTrue("PopUp error userName was not displayed", popUpErrorUnValidUsername.isDisplayed());
    }

    @Step
    public LoginPage checkCountErrorOfMessagesAfterSubmitRegisterIn(int countUnValidValue) {

        Util.waitABit(5);
        List<WebElement> listOfError = webDriver.findElements(By.xpath(popUpRegisterError));
        logger.info(listOfError.size());
        Assert.assertEquals("Count of PopUp errors Message is not correct", listOfError.size(), countUnValidValue);
        return new LoginPage(webDriver);
    }

    @Step
    public LoginPage checkCountErrorOfMessagesAfterSubmitLoginIn(int countUnValidValue) {

        Util.waitABit(5);
        List<WebElement> listOfError = webDriver.findElements(By.xpath(popUpLoginError));

        Assert.assertEquals("Count of PopUp errors Message is not correct", listOfError.size(), countUnValidValue);
        return new LoginPage(webDriver);
    }

    @Step
    public void checkTextOfErrorsInRegisterIn(String textOfErrorMessages) {

        if (textOfErrorMessages.isEmpty()) {
            logger.info("textOfErrorMessages ia empty.");
        } else {
            String[] words = textOfErrorMessages.split(";");

            for (int i = 0; i < words.length; i++) {
                Assert.assertTrue("--------- " + words[i] + " message is not correct in PopUp. ", listOfTextsOfWebElementByXpath(popUpRegisterError).contains(words[i]));
            }
        }

    }

    @Step
    public void checkTextOfErrorsInLoginIn(String textOfErrorMessages) {

        if (textOfErrorMessages.isEmpty()) {
            logger.info("textOfErrorMessages ia empty.");
        } else {
            Assert.assertEquals("", textOfErrorMessages, webDriver.findElement(By.xpath(popUpLoginError)).getText());
        }

    }

    @Step
    public String createValidLoginBySize(int sizeOfLogin) {


        return RandomString.make(sizeOfLogin).toLowerCase();
    }

    @Step
    public String createValidEmail() {
        return Util.getDateAndTimeFormated() + "@ukr.net";
    }

    @Step
    public String createValidPasswordBySize(int sizeOfPassword) {
        return RandomString.make(sizeOfPassword);
    }


    @Step
    public HomePage clickSingUpForOurAppButton() {
        clickOnElement(signUpForOurAppButton);
        return new HomePage(webDriver);
    }

    public boolean checkRegistrationFormVisible() {
        checkIsElementVisible(registrationForm);
        return true;
    }

    @Step
    public LoginPage registrationErrorMessageIsNotVisible() {


        return this;
    }

    @Step
    public LoginPage checkErrorOfMessage(int countOfMessage) {

        Util.waitABit(1);
        List<WebElement> listOfError = webDriver.findElements(By.xpath(popUpRegisterError));
        Assert.assertEquals("Count of PopUp errors Message is not correct", listOfError.size(), countOfMessage);

        return this;
    }

    public LoginPage checkIsPasswordIsNotVisible(String password) {
        Assert.assertEquals("----- " + password + " - Password is visible.", password, inputPassWordInRegisterIn.getText());
        logger.info("Password is not visible");
        return this;
    }

    public LoginPage checkIsPasswordCopy(String validPassword) {
        String copyPassword = inputPassWordInRegisterIn.getText();
        Assert.assertEquals("Password is copy.", copyPassword, validPassword);
        logger.info("Password is not copy.");

        return this;
    }

    public LoginPage fillRegisterFormAndSubmitByEnter(String validLogin, String validEmail, String validPassword) {

        openLoinPage();
        enterUserNameRegisterIn(validLogin);
        enterEmailRegisterIn(validEmail);
        enterPasswordRegisterIn(validPassword);
        pressEnterKey(signUpForOurAppButton);
        return new LoginPage(webDriver);

    }

    public LoginPage checkIsTextIsVisibleAfterMinimizeWindow(int sizeOfWindow, String login, String email, String password) {
        Util.waitABit(2);
        webDriver.manage().window().setPosition(new Point(-2000, sizeOfWindow));
        Util.waitABit(2);
        webDriver.manage().window().fullscreen();
        Util.waitABit(1);
        checkIsTextEqualsInElement(inputUserNameInRegisterIn, login);
        checkIsTextEqualsInElement(inputEmailInRegisterIn, email);
        checkIsTextEqualsInElement(inputPassWordInRegisterIn, password);


        return this;
    }
}

