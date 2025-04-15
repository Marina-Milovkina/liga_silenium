
# 🧪 Автотест на Selenium + Java + TestNG

```java
package UI;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import org.testng.annotations.*;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FormTestExample {

    public WebDriver driver;

    // 🔧 Установка и запуск браузера перед тестами
    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup(); // Установка драйвера
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager"); // Загрузка без ожидания ресурсов
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize(); // Разворачиваем окно
    }

    // ❌ Закрытие браузера после всех тестов
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    // ✅ Тест: заполняем форму, отправляем и проверяем результат
    @Test
    public void testFillForm() {
        driver.get("https://demoqa.com/text-box"); // Открываем страницу

        // Поля формы
        WebElement nameInput = driver.findElement(By.xpath("//input[@id='userName']"));
        nameInput.sendKeys("Marina");

        WebElement emailInput = driver.findElement(By.xpath("//input[@id='userEmail']"));
        emailInput.sendKeys("mar@yandex.ru");

        WebElement currentAddress = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        currentAddress.sendKeys("Kursk");

        WebElement permanentAddress = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        permanentAddress.sendKeys("Kursk");

        // Прокручиваем до кнопки и кликаем
        WebElement submitButton = driver.findElement(By.xpath("//button[@id='submit']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", submitButton);
        submitButton.click();

        // Проверка результатов
        WebElement nameOutput = driver.findElement(By.xpath("//p[@id='name']"));
        WebElement emailOutput = driver.findElement(By.xpath("//p[@id='email']"));
        WebElement currAddressOutput = driver.findElement(By.xpath("//p[@id='currentAddress']"));
        WebElement permAddressOutput = driver.findElement(By.xpath("//p[@id='permanentAddress']"));

        Assert.assertTrue(nameOutput.getText().contains("Marina"));
        Assert.assertEquals(emailOutput.getText(), "Email:mar@yandex.ru");
        Assert.assertTrue(currAddressOutput.getText().contains("Kursk"));
        Assert.assertTrue(permAddressOutput.getText().contains("Kursk"));
    }

    // 📄 Доп. тест: заголовок страницы
    @Test
    public void testPageTitle() {
        driver.get("https://demoqa.com/text-box");
        String pageTitle = driver.getTitle();
        System.out.println("Page title: " + pageTitle);
        Assert.assertTrue(pageTitle.contains("DEMOQA"));
    }
}
```

---

## 🧭 XPath шпаргалка

| Что ищем                 | XPath                                    |
|--------------------------|-------------------------------------------|
| По точному id            | `//input[@id='userName']`                |
| По части id              | `//textarea[contains(@id, 'Address')]`   |
| По тексту кнопки         | `//button[text()='Submit']`              |
| Текст результата         | `//p[@id='name']`                         |

---

## 🔑 Часто используемые методы

```java
driver.get("URL");                             // открыть страницу
driver.findElement(By.xpath(...)).sendKeys();  // ввести текст
driver.findElement(By.xpath(...)).click();     // нажать кнопку
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].scrollIntoView();", element); // скролл до элемента
Assert.assertTrue(...);                        // проверка условия
Assert.assertEquals(...);                      // сравнение значений
```
