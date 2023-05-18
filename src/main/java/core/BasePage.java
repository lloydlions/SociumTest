package core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import utilities.PropertyHelper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {
    final Logger LOGGER = LogManager.getLogger(BasePage.class);
    protected static WebDriver driver;
    protected WebDriverWait wait = null;
    protected Actions actions;
    private String url;

    protected final Duration setTimeOut() {
        Duration seconds = Duration.ofSeconds(1);
        try{
            seconds = Duration.ofSeconds(Long.parseLong(PropertyHelper.getPropValue("TIMEOUTHANDLER")));
        }catch (Exception e){}
        return seconds;
    }

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebDriver getDriver(){
        return driver;
    }

    public void driverQuit(){
        driver.quit();
    }

    protected void loadUrl(String url){
        try {
            if(url.equals(null)) {
                url = PropertyHelper.getPropValue("URL");
            }

            LOGGER.info("LAUNCH " + url);
            driver.get(url);
            setMaximizeWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void loadUrl(){
        try {
            url = PropertyHelper.getPropValue("URL");

            LOGGER.info("LAUNCH " + url);
            driver.get(url);
            setMaximizeWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getWebpageTitle(){
        return driver.getTitle();
    }

    /*
    ACTIONS VIA WEB ELEMENT CLASS
     */

    protected void inputValue(By identifier,String inputString){
        try {
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            driver.findElement(identifier).sendKeys(inputString);

            LOGGER.info("LOCATE ELEMENT " + identifier + " THEN PERFORM SENDKEYS : " + inputString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void clickElement(By identifier){
        try {
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            driver.findElement(identifier).click();

            LOGGER.info("LOCATE ELEMENT " + identifier + " THEN PERFORM CLICK");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void selectDropDownThenSelectByText(By identifier, String value){
        try {
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            Select dropDown = new Select(driver.findElement(identifier));
            dropDown.selectByVisibleText(value);

            LOGGER.info("LOCATE ELEMENT " + identifier + " THEN SELECT DROPDOWN VALUE : " + value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void selectDropDownThenSelectByValue(By identifier, String value){
        try {
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            Select dropDown = new Select(driver.findElement(identifier));
            dropDown.selectByValue(value);

            LOGGER.info("LOCATE ELEMENT " + identifier + " THEN SELECT DROPDOWN VALUE : " + value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //calendar picker by value ---- ex. yyyy/mm/dd TAb hh:mm AM/PM
    protected void enterDateByValues(By identifier, String date, String time){
        try{
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            WebElement datePicker = driver.findElement(identifier);
            datePicker.sendKeys(date);
            datePicker.sendKeys(Keys.TAB);
            datePicker.sendKeys(time);

            LOGGER.info("LOCATE ELEMENT " + identifier + " THEN SET DATE TIME AS : " + date + " " + time);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

     /*
    ACTIONS VIA ACTIONS CLASS
     */

    //ctrl + A
    protected void keyEvent_SelectAll(){
        try {
            actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL);
            actions.sendKeys("a");
            actions.keyUp(Keys.CONTROL);
            actions.build().perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //ctrl + C
    protected void keyEvent_Copy(){
        try {
            actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL);
            actions.sendKeys("c");
            actions.keyUp(Keys.CONTROL);
            actions.build().perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //ctrl + V
    protected void keyEvent_Paste(){
        try {
            actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL);
            actions.sendKeys("v");
            actions.keyUp(Keys.CONTROL);
            actions.build().perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //press Tab key
    protected void keyEvent_PressTab(){
        try {
            actions = new Actions(driver);
            actions.sendKeys(Keys.TAB);
            actions.build().perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void dragElementThenDropTo(By sourceId, By destinationId){
        try {
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(sourceId));
            WebElement sourceElement = driver.findElement(sourceId);

            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(destinationId));
            WebElement destinationElement = driver.findElement(destinationId);

            actions = new Actions(driver);
            actions.dragAndDrop(sourceElement,destinationElement);

            LOGGER.info("DRAG " + sourceElement + " TO " + destinationElement);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getText(By identifier){
        String text = null;
        try {
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            text = driver.findElement(identifier).getText();
            LOGGER.info("TEXT RETRIEVED : " + text );
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }

    public String getAttribute(By identifier, String attribute){
        String text = null;
        try {
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            text = driver.findElement(identifier).getAttribute(attribute);
            LOGGER.info("ATTRIBUTE VALUE RETRIEVED : " + text );
        }catch (Exception e){
            e.printStackTrace();
        }
        return text;
    }



    public int getChildElementCount(By identifier){
        List<WebElement> childs = null;
        try {
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            childs = driver.findElements(identifier);
            LOGGER.info("LOCATE ELEMENT " + identifier + " THEN COUNT CHILD ELEMENTS : " + childs.size());


        }catch (Exception e){
            e.printStackTrace();
        }
        return childs.size();
    }

    /*
    ACTIONS VIA JAVASCRIPT EXECUTOR
     */

    protected void scrollIntoView(By identifier){
        try{
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            WebElement element = driver.findElement(identifier);
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].scrollIntoView();", element);

            LOGGER.info("CLICK " + element + " BY JAVASCRIPT EXECUTOR");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void jsExecute_Click(By identifier){
        try{
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
            WebElement element = driver.findElement(identifier);
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);

            LOGGER.info("CLICK " + element + " BY JAVASCRIPT EXECUTOR");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //enable field via Class Name
    protected void jsExecute_EnableTextFieldByClassName(String domIdentifier){
        String script = "document.getElementsByClassName(" + domIdentifier + ")[1].removeAttribute('disabled')";
        LOGGER.info("ENABLE FIELD " + domIdentifier + " VIA HTML DOM");

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(script);
    }

    //enable field via Id
    protected void jsExecute_EnableTextFieldById(String domIdentifier){
        String script = "document.getElementsById(" + domIdentifier + ")[1].removeAttribute('disabled')";
        LOGGER.info("ENABLE FIELD " + domIdentifier + " VIA HTML DOM");

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(script);
    }

    //enable field via Name
    protected void jsExecute_EnableTextFieldByName(String domIdentifier){
        String script = "document.getElementsByName(" + domIdentifier + ")[1].removeAttribute('disabled')";
        LOGGER.info("ENABLE FIELD " + domIdentifier + " VIA HTML DOM");

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(script);
    }

    protected void switchHandle(int index){
        ArrayList<String> tabs_windows = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs_windows.get(index));
    }

    protected void dragAndDrop(String item, String target){
        try{
            wait = new WebDriverWait(driver, this.setTimeOut());
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item)));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(target)));
            Actions act=new Actions(driver);
            act.dragAndDrop(
                            driver.findElement(By.xpath(item)),
                            driver.findElement(By.xpath(target)))
                    .build().perform();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterClass(alwaysRun=true)
    public void tearDown(){
        if(!driver.equals(null)){
            LOGGER.info("SHUTTING DOWN WEB DRIVER");
            driver.close();
            driver.quit();
        }
    }

    @BeforeTest(alwaysRun=true)
    public void initializeTestFramework(){
        PropertyHelper.loadLog4jPropFile();
    }

    private void setMaximizeWindow(){
        driver.manage().window().maximize();
    }
}
