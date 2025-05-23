package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver; // модификатор доступа - тип переменной (драйвер браузера) - имя переменной

    public BasePage(WebDriver driver) {  // конструктор класса BasePage
        this.driver = driver; // переменной класса присвоить значение, которое пришло в конструкторе
    }

    /**
     * Локатор до названия страницы в средней части страницы
     */
    private static final By NAME_PAGE = By.xpath("//h1[@class='text-center']"); // создание переменной типа By (локатор)

    /**
     * Перейти по url
     * @param url ссылка в виде строки
     */
    public void openUrl(String url) {
        driver.get(url);  // команда WebDriver, которая открывает указанный url в браузере
    }

    /**
     * Найти элемент на странице
     * @param locator путь до элемента, тип - By
     * @return element найденный элемент
     */
    public WebElement findElement(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }

    /**
     * Найти элементы на странице
     * @param locator путь до элемента, тип - String
     * @return список веб-элементов
     */
    public List<WebElement> findElements(String locator) {
        return driver.findElements(By.xpath(locator));
        }

    /**
     * Получить текст из элемента
     * @param locator путь до элементов, тип - By
     */
    public String getText(By locator) {
        return findElement(locator).getText();
        }

    /**
     * Получить имя страницы
     * @return имя текущей страницы
     */
    public String getPageName() {
        return getText(NAME_PAGE);
        }

    /**
     * Проверка доступности элемента
     * @return true если доступен
     */
        public boolean isElementEnabled(By locator) {
        return findElement(locator).isEnabled();
        }

    /**
     * Очистить поле
     * @param locator путь до элемента, тип - By
     */
    public void clear(By locator) {
        findElement(locator).clear();
        }

    /**
     * Внести значение в поле
     * @param locator путь до элемента, тип - By
     * @param text текст
     */
    public void sendKeys(By locator, String text) {
        findElement(locator).sendKeys(text);
        }

    /**
     * Кликнуть на элемент
     * @param locator путь до элемента, тип - By
     */
    public void click(By locator) {
        findElement(locator).click();
        }

    /**
     * Метод, вызывающий AssertionError
     */
    public void failure(){
        throw new AssertionError(); // Иногда в тесте самой по себе ошибки нет, но что-то пошло не так, и ты хочешь сказать об этом явно — вызвать ошибку вручную. Метод failure() как раз для этого.
    } // throw - бросить (вызвать), new - новый объект ошибки и прервать программу

    /**
     * Проверка - элемент отображается
     * @param locator путь до элемента, тип - By
     * @return true если отображается
     */
    public boolean isElementDisplay(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Ожидание - элемент отображается
     * @param locator путь до элемента, тип - By
     * @param second время ожидания в секундах
     */
    public void waitElementsDisplay(By locator, int second){
        new WebDriverWait(driver, Duration.ofSeconds(second)).until(d -> isElementDisplay(locator));
    }

    // скролл до нужного элемента
    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // клик через JS
    protected void clickUsingJavaScript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

}
