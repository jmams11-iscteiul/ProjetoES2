package tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({ FileCompareTest.class, GenerateHTMLTest.class, MainTest.class})
public class AllTests {

}
