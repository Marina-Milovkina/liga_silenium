package UI; // указывает, что класс textBoxTest находится в пакете UI. Это помогает организовать проект

import org.openqa.selenium.By; // импорт классов для работы с браузером (WebDriver,By,WebElement), библиотекой TestNG
import org.openqa.selenium.WebDriver; // (аннотации @Test, @BeforeClass и т.д.), менеджером драйверов и пр.
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.JavascriptExecutor;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class textBoxTest {

    public WebDriver driver;

    @BeforeClass // метод запускается один раз перед всеми тестами
    public void setup() { // настройка браузера
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY,"eager"); // браузер не будет ждать полной загрузки всех ресурсов (картинок, например)
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @AfterClass // выполняется один раз после всех тестов
    public void tearDown() {
        driver.quit();
    } // закрытие браузера после всех тестов

    // основной тест: проверка формы
    @Test
    public void checkTextBox() {
        // WebDriver driver = new ChromeDriver();
        this.driver.get("https://demoqa.com/text-box"); // открытие страницы

        // ввод данных в поля
        // заполнение поля Full Name
        WebElement userNameInput = driver.findElement(By.xpath("//input[@id='userName']"));
        userNameInput.sendKeys("Marina");

        // заполнение поля Email
        WebElement userEmailInput = driver.findElement(By.xpath("//input[@id='userEmail']"));
        userEmailInput.sendKeys("mar@yandex.ru");

        // заполнение поля Current Address
        WebElement currentAddressTextArea = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        currentAddressTextArea.sendKeys("Kursk");

        // заполнение поля Permanent Address
        WebElement permanentAddressTextArea = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        permanentAddressTextArea.sendKeys("Kursk");

        // прокрутка страницы до кнопки submit
        WebElement button = driver.findElement(By.xpath("//button[@id='submit']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", button);
        button.click(); // нажатие на кнопку

        // получаем отображенные данные
        WebElement userNameOutput = driver.findElement(By.xpath("//p[@id='name']"));
        WebElement userEmailOutput = driver.findElement(By.xpath("//p[@id='email']"));
        WebElement currentAddressOutput = driver.findElement(By.xpath("//p[@id='currentAddress']"));
        WebElement permanentAddressOutput = driver.findElement(By.xpath("//p[@id='permanentAddress']"));

        String name = userNameOutput.getText();
        String email = userEmailOutput.getText();
        String currAddress = currentAddressOutput.getText();
        String permAddress = permanentAddressOutput.getText();

        // пауза (необязательно)
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // вывод на консоль
        System.out.println("Name: " + name + " , email: " + email
                    + " , current address: " + currAddress + " , permanent address: " + permAddress);

        // проверка поля Email
        // Assert.assertTrue(name.contains("Marina"));
        Assert.assertEquals(email, "Email:mar@yandex.ru");
        }

    @Test
    public void checkTitleTextBox() {
        String pageTitle = driver.getTitle();
        System.out.println("Title : " + pageTitle);
    }
}


