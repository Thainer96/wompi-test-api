package testWompi.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    Scenario scenario ;

    @BeforeAll
    public void beforeScenario(Scenario scenario){
        this.scenario = scenario;
        logger.info("Executing the following test -> " + scenario.getName());
    }

    @Before("@HappyPathInssuancePolicyWithMultiplesCoverages")
    public void test1() {
        logger.info("soy el escenario de emision");
    }

    @After
    public void afterScenariosWeb() throws IOException {
        if (scenario.isFailed()){
            System.out.println("TEST FAILED, DO SNAPSHOT");

        }

    }

}
