package pages.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.base.BasePage;

public class WebFormPage extends BasePage {
    public WebFormPage(WebDriver driver){
        super(driver);
    }

    // локаторы
    private static final String TEXT_INPUT_XPATH = "//input[@type='text']";

    private static final String PASSWORD_INPUT_XPATH = "//input[@type='password']";

    private static final String TEXTAREA_XPATH = "//textarea[@class='form-control']";

    private static final String DROPDOWN_XPATH = "//select[@class='form-select']";

    private static final String DEFAULT_CHECKBOX_XPATH = "//input[@id='my-check-2']";

    private static final String CHECKED_CHECKBOX_XPATH = "//input[@id='my-check-1']";

    private static final String DEFAULT_RADIO_XPATH = "//input[@id='my-radio-2']";

    private static final String DATALIST_XPATH = "//input[@name='my-datalist']";

    private static final String SEATTLE_XPATH = "//input[@name='my-datalist']";

    private static final String SUBMIT_XPATH = "//button[@type='submit']";
    // методы
    public void openWebFormPage(){
        openUrl("https://www.selenium.dev/selenium/web/web-form.html");
    }

    // ввести фио, пароль и название компании
    public void inputForm(String name, String password, String company){
        sendKeys(By.xpath(TEXT_INPUT_XPATH), name);
        sendKeys(By.xpath(PASSWORD_INPUT_XPATH), password);
        sendKeys(By.xpath(TEXTAREA_XPATH), company);
    }

    // Проверка, что введенные значения отобразились
    public String getNameFromTable() {
        return driver.findElement(By.xpath(TEXT_INPUT_XPATH)).getAttribute("value");
    }

    public String getPasswordFromTable() {
        return driver.findElement(By.xpath(PASSWORD_INPUT_XPATH)).getAttribute("value");
    }

    public String getCompanyFromTable() {
        return driver.findElement(By.xpath(TEXTAREA_XPATH)).getAttribute("value");
    }

    // метод открытия списка Dropdown (select)
    public void selectFromDropdown(String visibleText) {
        WebElement dropdown = driver.findElement(By.xpath(DROPDOWN_XPATH)); // или By.xpath(...)
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

//    // В поле «Dropdown (datalist)» выбрать значение «Seattle»
//    WebElement input = driver.findElement(By.xpath(DATALIST_XPATH));
//    input.sendKeys("Tokyo");
//    input.sendKeys(Keys.TAB);
    //Установить оба чек-бокса в положение true
    public boolean getDefaultCheckBoxState() {
    return findElement(By.xpath(DEFAULT_CHECKBOX_XPATH)).isSelected();
}

    public boolean getCheckedCheckBoxState() {
        return findElement(By.xpath(CHECKED_CHECKBOX_XPATH)).isSelected();
    }

    public void setDefaultCheckBox(boolean state) {
    if (!getDefaultCheckBoxState() == state) { // "если текущее состояние чекбокса не соответствует требуемому"
        click(By.xpath(DEFAULT_CHECKBOX_XPATH));
    }
}
    public void clickDefaultRadio(){
        findElement(By.xpath(DEFAULT_RADIO_XPATH)).click();
    }
    public boolean isDefaultRadioDisplayed() {
        return isElementDisplay(By.xpath(DEFAULT_RADIO_XPATH)); // метод String.format позволяет подставить в плейсхолдер константы переменную checkBoxName
    }

    public void clickSubmit(){
        findElement(By.xpath(SUBMIT_XPATH)).click();
    }

    public String getFormTitleText() {
        return findElement(By.xpath("//h1[@class='display-6']]")).getText();
    }

}
