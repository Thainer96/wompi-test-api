package testWompi.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "testWompi.stepDefinitions",
        tags = "@FeatureWompi",
        plugin= {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class RunnerTestWompi {

}
