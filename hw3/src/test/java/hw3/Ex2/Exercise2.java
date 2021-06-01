package hw3.Ex2;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/Ex2.feature"},
        glue = {"hw3.Ex2glue"}
)

public class Exercise2 extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider()
    public Object[][] scenarios() {
        return super.scenarios();
    }
}