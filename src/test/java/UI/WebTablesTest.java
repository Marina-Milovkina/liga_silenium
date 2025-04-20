package UI;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.demoqa.WebTablesPage;

import static org.testng.Assert.*;

public class WebTablesTest extends BaseTest {

    private WebTablesPage webTablesPage;

    @BeforeClass
    public void BeforeClass(){
        System.out.println("Setting up before class in WebTablesTest");
        webTablesPage = new WebTablesPage(driver);
    }

    @Test(description = "Перейти на страницу")
    public void step_01(){
        webTablesPage.openWebTablesPage();
        assertEquals(webTablesPage.getPageName(), "Web Tables");
    }

    @Test(description = "Добавить нового пользователя")
    public void step_02(){
        webTablesPage.clickAddButton();
        webTablesPage.fillForm("Karina", "Ivanova", "Kar@mail.ru", "56", "3500", "HR");
        webTablesPage.clickSubmit();
        Assert.assertEquals(webTablesPage.getFirstName("Karina"), "Karina");
    }

    @Test(description = "Редактировать добавленного пользователя")
    public void step_03(){
        webTablesPage.editUser("Karina");
        webTablesPage.fillForm("Oleg", "Turbin", "oleg@mail.ru", "50", "5000", "lawyer");
        webTablesPage.clickSubmit();
        Assert.assertEquals(webTablesPage.getFirstName("Oleg"), "Oleg");
    }

    @Test(description = "Удалить пользователя")
    public void step_04(){
        webTablesPage.deleteUser("Oleg");
        Assert.assertTrue(webTablesPage.isUserDeleted("Oleg"), "Пользователь не был удален");
    }
}


