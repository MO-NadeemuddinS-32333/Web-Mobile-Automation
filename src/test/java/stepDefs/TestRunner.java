package stepDefs;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = { "stepDefs" },   // ✅ must match your step definition package
    monochrome = true,
    plugin = { "pretty", "html:target/HtmlReports.html" }
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
