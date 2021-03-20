package pages;


import io.qameta.allure.Step;
import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;


public abstract class ParentPage {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected final String baseUrl = configProperties.base_url();
    Logger logger = Logger.getLogger(getClass());

    abstract String getRelativeUrl();


    public ParentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        //  PageFactory.initElements(webDriver, this);
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(webDriver)), this);
        webDriverWait10 = new WebDriverWait(webDriver, 10);
        webDriverWait15 = new WebDriverWait(webDriver, 15);
    }


    protected void waitChatToBeHide() {
        webDriverWait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    protected void waitBackToPostPermalink() {
        webDriverWait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[text() ='« Back to post permalink']")));
    }

    //« Back to post permalink

    protected void enterTextInToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was input in to element" + getElementName(webElement));

        } catch (Exception e) {
            logger.info("");
            printErrorMessageAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = " '" + ((TypifiedElement) webElement).getName() + "' ";
        }
        return elementName;
    }


    private void printErrorMessageAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);

    }
    @Step
    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(getElementName(webElement) + "element was clicked.");

        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }


    @Step
    protected void pressEnterKey(WebElement webElement, int numberOfTimes) {
        try {
            usersPressesKeyEnterTime(numberOfTimes);
            logger.info(getElementName(webElement) + "element was Entered.");

        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }


    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();

        }
    }


    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
            logger.info("Send key TAB.");

        }
    }


    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            logger.info(getElementName(webElement) + "Element displayed:" + state);
            return state;
        } catch (Exception e) {
            logger.info(getElementName(webElement) + "Element displayed:" + false);
            return false;
        }
    }

    protected void checkIsElementVisible(WebElement webElement) {
        Assert.assertTrue("Element is not visible", isElementDisplayed(webElement));
    }

    protected void checkIsElementUnVisible(WebElement webElement) {
        Assert.assertTrue("Element is visible", !isElementDisplayed(webElement));
    }

    protected void selectTextInDropDown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info(text + "was selcted in DropDown." + getElementName(webElement));

        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info(value + "was selcted in DropDown." + getElementName(webElement));

        } catch (Exception e) {
            printErrorMessageAndStopTest(e);
        }
    }

    protected List<String> listOfTextsOfWebElementByXpath(String xpath) {
        ArrayList<String> listOfTextErrors = new ArrayList<>();
        List<WebElement> listOfError = webDriver.findElements(By.xpath(xpath)); // сохранил текст всех эроров в лист
        for (int i = 0; i < listOfError.size(); i++) {
            listOfTextErrors.add(listOfError.get(i).getText());
        }
        return listOfTextErrors;
    }

    protected void checkIsTextEqualsInElement(WebElement element, String text){
        try {
            checkIsElementVisible(element);
            Assert.assertEquals("Text "+ text + "is not Equals.", element.getText(), text);
            logger.info("Text is equals - " + text);
        }catch (Exception e){
            logger.info(e);
        }


    }

    protected String  getBackGroundColorOfElement( WebElement webElement){
        checkIsElementVisible(webElement);
        String backgroundColorOfElement = webElement.getCssValue("background-color");
        logger.info("Color of element -----"+ webElement +"----- is - " + backgroundColorOfElement);
        return backgroundColorOfElement ;
    }

    protected String getBorderColorOfElementByBorderBottonColor(WebElement webElement){
        checkIsElementVisible(webElement);
        String borderColorOfElement = webElement.getCssValue("border-bottom-color");
        logger.info("Border color of element -----"+ webElement +"----- is - " + borderColorOfElement);
        return borderColorOfElement ;
    }


    protected String  getBorderColorOfElementByBorderColor( WebElement webElement){
        checkIsElementVisible(webElement);
        String borderColorOfElement = webElement.getCssValue("border-color");
        logger.info("Border color of element -----"+ webElement +"----- is - " + borderColorOfElement);
        return borderColorOfElement ;
    }

    protected String  getBackGroundColorOfText( WebElement webElement){
        checkIsElementVisible(webElement);
        String colorOfText = webElement.getCssValue("color");
        logger.info("Color of element is - " + colorOfText);
        return colorOfText ;
    }

    protected  void  checkBackGroundColorOfElement(WebElement webElement, String colorOfElement){

        checkIsElementVisible(webElement);
        Assert.assertEquals("Back Ground color of element -----"+ webElement +"----- is not correct", colorOfElement, getBackGroundColorOfElement(webElement)  );
        logger.info("Back Ground color of element is  correct - " + colorOfElement);
    }

    protected  void  checkBorderColorOfElement(WebElement webElement, String colorOfElement){

        checkIsElementVisible(webElement);
        Assert.assertEquals("Border color of element -----"+ webElement +"----- is not correct", colorOfElement, getBorderColorOfElementByBorderBottonColor(webElement)  );
        logger.info("Border color of element is  correct - " + colorOfElement);
    }


}
