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
    public LoginPage fillRegisterFormByClick(String userName, String email, String password) {
        openLoinPage();
        enterUserNameRegisterIn(userName);
        enterEmailRegisterIn(email);
        enterPasswordRegisterIn(password);

        return this;
    }


    @Step
    public LoginPage fillRegisterFormByTab(String userName, String email, String password) {
        openLoinPage();
        usersPressesKeyTabTime(5);
        enterUserNameRegisterIn(userName);
        usersPressesKeyTabTime(1);
        enterEmailRegisterIn(email);
        usersPressesKeyTabTime(1);
        enterPasswordRegisterIn(password);
        usersPressesKeyTabTime(1);

        return this;
    }

    @Step
    public LoginPage fillAndCheckBorderColorRegisterForm(String userName, String email, String password,String borderColorOfActiveInput, String borderColorOfSignUpFprUppActive){
        openLoinPage();
        usersPressesKeyTabTime(5);

        Util.waitABit(1);
        Assert.assertEquals("Border color is not correct.", borderColorOfActiveInput, getBorderColorOfElementByBorderBottonColor(inputUserNameInRegisterIn) );
        enterUserNameRegisterIn(userName);

        usersPressesKeyTabTime(1);
        Util.waitABit(1);

        Assert.assertEquals("Border color is not correct.", borderColorOfActiveInput, getBorderColorOfElementByBorderBottonColor(inputEmailInRegisterIn) );
        enterEmailRegisterIn(email);
        usersPressesKeyTabTime(1);
        
        Util.waitABit(1);
        Assert.assertEquals("Border color is not correct.", borderColorOfActiveInput, getBorderColorOfElementByBorderBottonColor(inputPassWordInRegisterIn) );
        enterPasswordRegisterIn(password);

        usersPressesKeyTabTime(1);
        Util.waitABit(1);
        Assert.assertEquals("Border color is not correct.", borderColorOfSignUpFprUppActive, getBorderColorOfElementByBorderColor(signUpForOurAppButton) );

        return this;
    }

    @Step
    public HomePage clickOnSignUpForOurAppButton() {
        clickOnElement(signUpForOurAppButton);
        return new HomePage(webDriver);
    }


    @Step
    public LoginPage clickOnSignUpForOurAppButtonForUnValidRegistration() {
        clickOnElement(signUpForOurAppButton);
        return this;
    }

    @Step
    public HomePage sentKeyEnterOnSignUpForOurAppButton(int count) {
        usersPressesKeyEnterTime(count);
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage sentKeyEnterOnSignUpForOurAppButtonForUnValidRegistration(int count) {
        usersPressesKeyEnterTime(count);
        return this;
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
    public LoginPage checkTextOfErrorsInRegisterIn(String textOfErrorMessages) {
        Util.waitABit(1);
        if (textOfErrorMessages.isEmpty()) {
            logger.info("textOfErrorMessages ia empty.");
        } else {
            String[] words = textOfErrorMessages.split(";");

            for (int i = 0; i < words.length; i++) {
                Assert.assertTrue("--------- " + words[i] + " message is not correct in PopUp. ", listOfTextsOfWebElementByXpath(popUpRegisterError).contains(words[i]));
            }
        }
        return this;
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
        String validLogin = RandomString.make(sizeOfLogin).toLowerCase();
        logger.info("Create random login - " + validLogin);
        return validLogin;
    }

    @Step
    public String createValidEmail(int sizeOfEmail) {
        String validEmail = RandomString.make(sizeOfEmail).toLowerCase() + "@ukr.net";
        logger.info("Create random email - " + validEmail);

        return validEmail;
    }

    @Step
    public String createValidPasswordBySize(int sizeOfPassword) {
        String validPassword = RandomString.make(sizeOfPassword).toLowerCase();
        logger.info("Create random password - " + validPassword);
        return validPassword;
    }


    @Step
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
        logger.info("Count of PopUp errors Message is correct -  " + listOfError);
        return this;
    }

    @Step
    public LoginPage checkIsPasswordIsNotVisible(String password) {
        Assert.assertEquals("----- " + password + " - Password is visible.", password, inputPassWordInRegisterIn.getText());
        logger.info("Password is not visible");
        return this;
    }

    @Step
    public LoginPage checkIsPasswordCopy(String validPassword) {
        String copyPassword = inputPassWordInRegisterIn.getText();
        Assert.assertEquals("Password is copy.", copyPassword, validPassword);
        logger.info("Password is not copy.");

        return this;
    }


    @Step
    public LoginPage checkIsTextIsVisibleAfterMinimizeWindow(int sizeOfWindow, String login, String email, String password) {

        webDriver.manage().window().setPosition(new Point(-2000, sizeOfWindow));
        logger.info("Windows size was change and now it is - 2000," + sizeOfWindow);
        webDriver.manage().window().maximize();
        logger.info("Windows size was is maximize.");
        checkIsTextEqualsInElement(inputUserNameInRegisterIn, login);
        checkIsTextEqualsInElement(inputEmailInRegisterIn, email);
        checkIsTextEqualsInElement(inputPassWordInRegisterIn, password);


        return this;
    }


}

