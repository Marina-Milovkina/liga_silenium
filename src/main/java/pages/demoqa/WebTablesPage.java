package pages.demoqa;

import org.openqa.selenium.*;
import pages.base.BasePage;

public class WebTablesPage extends BasePage {

    public WebTablesPage(WebDriver driver){
        super(driver);
    }

    // Константы (локаторы)

    private static final String URL_WEB_TABLES_PAGE = "https://demoqa.com/webtables";

    private static final String ADD_BUTTON = "//button[@id='addNewRecordButton']";

    private static final String FIRST_NAME_INPUT = "//input[@id='firstName']";

    private static final String LAST_NAME_INPUT ="//input[@id='lastName']";

    private static final String EMAIL_INPUT = "//input[@id='userEmail']";

    private static final String AGE_INPUT = "//input[@id='age']";

    private static final String SALARY_INPUT = "//input[@id='salary']";

    private static final String DEPARTMENT_INPUT = "//input[@id='department']";

    private static final String SUBMIT_BUTTON = "//button[@id='submit']";

    private static final String TABLE_ROW_DATA = "//div[@class='rt-tr-group'][.//div[contains(text(),'%s')]]";

    private static final String TABLE_ROWS = "//select[@aria-label='rows per page']";

    private static final String TABLE_ROWS_VALUE = TABLE_ROWS + "/option[@value='%s']";

    private By getUserRowLocator(String firstName) {
        return By.xpath(String.format(TABLE_ROW_DATA, firstName));
    }

    // Методы

    public void openWebTablesPage(){
        openUrl(URL_WEB_TABLES_PAGE);
    }

    public void clickAddButton(){
        click(By.xpath(ADD_BUTTON));
    }

    // метод fillForm, использующий готовый метод sendKeys из BasePage
    public void fillForm(String firstName, String lastName, String userEmail, String userAge, String userSalary, String userDepartment){

        sendKeys(By.xpath(FIRST_NAME_INPUT), firstName);
        sendKeys(By.xpath(LAST_NAME_INPUT), lastName);
        sendKeys(By.xpath(AGE_INPUT), userAge);
        sendKeys(By.xpath(EMAIL_INPUT), userEmail);
        sendKeys(By.xpath(SALARY_INPUT), userSalary);
        sendKeys(By.xpath(DEPARTMENT_INPUT), userDepartment);
    }

    public void clickSubmit(){
        findElement(By.xpath(SUBMIT_BUTTON)).click();
    }

    // проверка, что введенный текст отображается в таблице
    public String getFirstName(String firstName) {
        return findElement(By.xpath(String.format(TABLE_ROW_DATA, firstName) + "//div[@class='rt-td'][1]")).getText();
    }

    public String getLastName(String lastName) {
        return findElement(By.xpath(String.format(TABLE_ROW_DATA, lastName) + "//div[@class='rt-td'][2]")).getText();
    }

    public String getEmail(String email) {
        return findElement(By.xpath(String.format(TABLE_ROW_DATA, email) + "//div[@class='rt-td'][4]")).getText();
    }

    public String getAge(String age) {
        return findElement(By.xpath(String.format(TABLE_ROW_DATA, age) + "//div[@class='rt-td'][3]")).getText();
    }

    public String getSalary(String salary) {
        return findElement(By.xpath(String.format(TABLE_ROW_DATA, salary) + "//div[@class='rt-td'][5]")).getText();
    }

    public String getDepartment(String department) {
        return findElement(By.xpath(String.format(TABLE_ROW_DATA, department) + "//div[@class='rt-td'][6]")).getText();
    }

    //получение данных строки по firstName
    public String getUserRowText(String firstName) {
        WebElement row = driver.findElement(
                By.xpath("//div[@class='rt-tr-group'][.//div[contains(text(),'" + firstName + "')]]")
        );
        return row.getText();
    }

    // метод редактирование информации о пользователе
    public void editUser(String firstName){
        String editButtonLocator = String.format(TABLE_ROW_DATA, firstName) + "//span[@title='Edit']";
        WebElement editButton = driver.findElement(By.xpath(editButtonLocator));

        scrollIntoView(editButton);
        clickUsingJavaScript(editButton);

        findElement(By.xpath(FIRST_NAME_INPUT)).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        findElement(By.xpath(LAST_NAME_INPUT)).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        findElement(By.xpath(AGE_INPUT)).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        findElement(By.xpath(EMAIL_INPUT)).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        findElement(By.xpath(SALARY_INPUT)).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        findElement(By.xpath(DEPARTMENT_INPUT)).sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
    }

    // метод удаления пользователя
    public void deleteUser(String firstName) {
        String deleteButtonLocator = String.format(TABLE_ROW_DATA, firstName) + "//span[@title='Delete']";
        WebElement deleteButton = driver.findElement(By.xpath(deleteButtonLocator));

        scrollIntoView(deleteButton);
        clickUsingJavaScript(deleteButton);
    }

   // проверка, что пользователь действительно был удален
    public boolean isUserDeleted(String firstName) {
        try {
            return !findElement(getUserRowLocator(firstName)).isDisplayed(); //код, который может вызвать ошибку
        } catch (NoSuchElementException e) { // Если не удастся найти элемент на странице и выбросится ошибка NoSuchElementException, я перехвачу эту ошибку и сохраню её в переменную e
            return true; // обработка ошибки
        }
    }
}
