package UI;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.base.BasePage;
import pages.demoqa.CheckBoxPage;
import pages.demoqa.WebFormPage;

public class WebFormTest extends BaseTest {

    private WebFormPage webFormPage;

    @BeforeClass
    public void beforeClass(){
        System.out.println("Setting up before class in WebFormTest.");
        webFormPage = new WebFormPage(driver); // помещаем в переменную новый объект и передаем ему драйвер
    }

    @Test(description = "Перейти на страницу")
    public void step_01(){
        webFormPage.openWebFormPage();
       // Assert.assertEquals(webFormPage.getPageName(),"Web form");
    }

    @Test(description = "Ввести фио, пароль и название компании")
    public void step_02(){
        webFormPage.inputForm("Миловкина Марина Валентиновна", "Password", "Liga");
        Assert.assertEquals("Миловкина Марина Валентиновна", webFormPage.getNameFromTable());
        Assert.assertEquals("Password", webFormPage.getPasswordFromTable());
        Assert.assertEquals("Liga", webFormPage.getCompanyFromTable());
    }

    @Test(description = "В поле «Dropdown select» выбрать значение «two»")
    public void step_03(){
        webFormPage.selectFromDropdown("Two");
    }

    @Test(description = "Установить оба чекбокса в состояние TRUE")
    public void step_04(){
        webFormPage.setDefaultCheckBox(true);
        Assert.assertTrue(webFormPage.getDefaultCheckBoxState());
        Assert.assertTrue(webFormPage.getCheckedCheckBoxState());
    }

    @Test(description = "выбрать radioButton")
    public void step_05(){
        webFormPage.clickDefaultRadio();
        Assert.assertTrue(webFormPage.isDefaultRadioDisplayed(), "Radio button должен отображаться на странице");
    }

    @Test(description = "Нажать на Submit")
    public void step_06(){
        webFormPage.clickSubmit();
       //Assert.assertEquals(webFormPage.getFormTitleText(), "Form submitted Received!");
    }



}
