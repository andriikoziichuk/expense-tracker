package ua.andrew1903.expensetracker.cucumber.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "ua/andrew1903/expensetracker/cucumber/test",
//        plugin = {"pretty", "html:target/cucumber/bagbasics"},
        extraGlue = "ua.andrew1903.expensetracker.cucumber.commons")
public class CategoryCucumberIntegrationTest {
}
