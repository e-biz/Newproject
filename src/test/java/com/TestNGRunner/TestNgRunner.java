package com.TestNGRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="C:\\eRequisition_Latest_Script\\VerifyingHistoryPageStatus\\src\\test\\resources\\FeatureFile\\StatusVerification.feature",
glue= {"com.AppHooks","StepDefinition"},
monochrome = true,
plugin = {"pretty",  "json:target/cucumber/report.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
tags = "@VerifyingAllStatusOfHistoryPageOfStandalone"
)
public class TestNgRunner extends AbstractTestNGCucumberTests  {
}
