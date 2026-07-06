package Cucumber;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src\\test\\java\\Cucumber",
        glue = "StepDefinition",
        monochrome = true,
        plugin = {"html:target/cucumver.html"},
        tags = "@ErrorValidations"
        )
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
}
