package base;

import browser.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public WebDriver driver;

    @BeforeClass
    public void setupClass() {
        System.out.println("Setting up before class in BaseTest.");
        driver = Browser.createDriver();
    }

//    @AfterClass
//    public void tearDownClass(){
//        System.out.println("Tearing down after class.");
//        if (driver != null){ // проверка:  если объект драйвера создан — т.е. не null
//            driver.quit();
//        }
//    }
}
