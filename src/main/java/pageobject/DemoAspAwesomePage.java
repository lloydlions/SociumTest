package pageobject;

import core.BasePage;
import core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.EnumSet;

public class DemoAspAwesomePage extends BasePage {

    protected static WebDriver driver;
    protected By btnSectionCaret = new By.ByXPath("//button[@id='ddlFmw-awed']//i[@class='o-caret']");
    protected By btnFoodCaret = new By.ByXPath("//*[@id='GridFrowfltFood-awed']//i[@class='o-caret']");
    protected By inputPerson = new By.ByXPath("//*[@id='GridFrowfltPerson-awed']");

    public DemoAspAwesomePage() {
        super(DriverFactory.initialize());
    }

    public void launchWebPage(){
        loadUrl();
    }

    public void changeCaretDropdown(String pageSection){
        this.clickElement(btnSectionCaret);
        this.clickElement(By.xpath("//li[text()='" + pageSection +  "']"));
        this.scrollIntoView(By.xpath("//h2[text()='Grid Filter Row']"));
    }

    public void setFilter(String columnName, String filterValue){
        switch (columnName){
            case "Person":
                this.inputValue(inputPerson,filterValue);
                break;
            case "Food":
                this.clickElement(btnFoodCaret);
                String filterFood = "//*[@id='GridFrowfltFood-dropmenu']//div[text()='" + filterValue + "']";
                this.scrollIntoView(By.xpath(filterFood));
                this.clickElement(By.xpath(filterFood));
        }
    }



}
