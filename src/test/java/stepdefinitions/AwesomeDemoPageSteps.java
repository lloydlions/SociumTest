package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageobject.DemoAspAwesomePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AwesomeDemoPageSteps {

    DemoAspAwesomePage demoAspAwesomePage;

    @Given("that the user access the demo awesome webpage")
    public void that_the_user_access_the_demo_awesome_webpage() {
        demoAspAwesomePage = new DemoAspAwesomePage();
        demoAspAwesomePage.launchWebPage();
        Assert.assertEquals(demoAspAwesomePage.getWebpageTitle(),"ASP.net Core Awesome Controls overview- ASP.net Core MVC Awesome demos");
    }

    @Given("the user navigate to Grid with Filter Row section")
    public void the_user_navigate_to_grid_with_filter_row_section() {
        demoAspAwesomePage.changeCaretDropdown("aweui (Angular, React, Vue)");
    }

    @When("the user set the following filters")
    public void the_user_set_the_following_filters(List<Map<String, String>> dataTable) {
        HashMap<String,String> filters= new HashMap<>();
        for (Map<String, String> dataRow : dataTable) {
            filters.put(dataRow.get("columnName"),dataRow.get("value"));
        }
        filters.forEach((columnName,value)->{
            demoAspAwesomePage.setFilter(columnName,value);
        });
    }

    @Then("the system will return all the values")
    public void the_system_will_return_all_the_values(List<Map<String, String>> dataTable) {
        /* Count data retrieve, should return only 1 */
        Assert.assertEquals(demoAspAwesomePage.getChildElementCount(new By.ByXPath("(//table[@class='awe-table'])[2]//tr")),1);

        for (Map<String, String> dataRow : dataTable) {
            /* Validate : ID */
            Assert.assertEquals(demoAspAwesomePage.getText(new By.ByXPath("(//table[@class='awe-table'])[2]//td[1]")),dataRow.get("id"));
            /* Validate : Person */
            Assert.assertEquals(demoAspAwesomePage.getText(new By.ByXPath("(//table[@class='awe-table'])[2]//td[2]")),dataRow.get("person"));
            /* Validate : Food */
            Assert.assertEquals(demoAspAwesomePage.getText(new By.ByXPath("(//table[@class='awe-table'])[2]//td[3]")),dataRow.get("food"));
            Assert.assertNotNull(demoAspAwesomePage.getAttribute(new By.ByXPath("(//table[@class='awe-table'])[2]//td[3]//img"), "src"));
            /* Validate : Country */
            Assert.assertEquals(demoAspAwesomePage.getText(new By.ByXPath("(//table[@class='awe-table'])[2]//td[4]")),dataRow.get("country"));
            /* Validate : Date */
            Assert.assertEquals(demoAspAwesomePage.getText(new By.ByXPath("(//table[@class='awe-table'])[2]//td[5]")),dataRow.get("date"));
            /* Validate : Meals */
            Assert.assertEquals(demoAspAwesomePage.getText(new By.ByXPath("(//table[@class='awe-table'])[2]//td[6]")),dataRow.get("meals"));
            /* Validate : Chef */
            Assert.assertEquals(demoAspAwesomePage.getText(new By.ByXPath("(//table[@class='awe-table'])[2]//td[7]")),dataRow.get("chef"));
        }


        demoAspAwesomePage.driverQuit();
    }
}
