
# 🧩 Page Object Model (POM) в тестировании

## 🔸 Что такое Page Object?
**Page Object Model (POM)** — это _паттерн проектирования_, который помогает сделать автотесты чище, понятнее и удобнее для поддержки.  
Весь код, связанный с конкретной страницей, выносится в отдельный Java-класс.  

---

## 🔸 Что такое паттерн проектирования?

**Паттерн проектирования** (англ. *design pattern*) — это **готовое решение** для часто встречающейся задачи в программировании.  
Например: как удобно работать со страницами в автотестах? Ответ — использовать Page Object.

---

## 📂 Структура проекта в POM

```
src
└── main
    └── java
        ├── config           ← настройки (например, URL)
        ├── browser          ← запуск браузера
        ├── base             ← базовые классы (BasePage, BaseTest)
        ├── pages            ← Page Object-классы для страниц
        └── tests            ← классы с автотестами
```

---

## 📁 config / `Config.java`

```java
public class Config {
    public static final String BASE_URL = "https://demoqa.com";
}
```

🧠 **Разбор:**
- `static` — переменная принадлежит классу, а не объекту.
- `final` — нельзя переопределить (это константа).
- Удобно менять URL в одном месте, а не по всему коду.

---

## 📁 browser / `Browser.java`

```java
public class Browser {
    public static WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");
        return new ChromeDriver(options);
    }
}
```

🧠 **Зачем:** Создаёт и настраивает драйвер, чтобы его можно было легко переиспользовать.

---

## 📁 base / `BasePage.java`

```java
public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }
}
```

🧠 **Объяснение:**
- `protected` — переменная видна внутри класса и его наследников.
- Здесь находятся общие методы для всех страниц.

---

## 📁 base / `BaseTest.java`

```java
public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = Browser.getDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
```

🧠 **Зачем:** Настройка браузера до каждого теста и его закрытие после.

---

## 📁 pages / `TextBoxPage.java`

```java
public class TextBoxPage extends BasePage {

    private By userName = By.id("userName");
    private By userEmail = By.id("userEmail");
    private By submitButton = By.id("submit");

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    public void enterUserName(String name) {
        type(userName, name);
    }

    public void enterUserEmail(String email) {
        type(userEmail, email);
    }

    public void clickSubmit() {
        click(submitButton);
    }
}
```

🧠 **Суть:**
- Здесь описываются элементы и действия страницы.
- Все действия используют методы из `BasePage`.

---

## 📁 tests / `TextBoxTest.java`

```java
public class TextBoxTest extends BaseTest {

    @Test
    public void testFillForm() {
        TextBoxPage page = new TextBoxPage(driver);
        page.open(Config.BASE_URL + "/text-box");
        page.enterUserName("Марина");
        page.enterUserEmail("mar@yandex.ru");
        page.clickSubmit();

        // Здесь можно добавить проверки
    }
}
```

🧠 **Зачем:** Это сам тест. В нём мы просто вызываем нужные методы из Page Object.

---

## 🧠 Часто встречающиеся модификаторы переменных:

| Модификатор | Что делает                                           |
|-------------|------------------------------------------------------|
| `private`   | Переменная доступна только внутри класса            |
| `protected` | Переменная доступна в этом классе и его наследниках |
| `public`    | Переменная доступна везде                           |
| `static`    | Принадлежит классу, а не объекту                    |
| `final`     | Нельзя изменить (константа)                         |


# Шпаргалка по локаторам в Selenium

В Selenium WebDriver для поиска элементов на веб-странице используется класс `By`
---

## 1. **By.id**
Поиск элемента по его уникальному атрибуту `id`.

By userNameInput = By.id("userName");
driver.findElement(userNameInput).sendKeys("Марина");

## 2. By.className
Поиск элемента по его атрибуту class.
By loginButton = By.className("login-btn");
driver.findElement(loginButton).click();

## 3. By.name
Поиск элемента по атрибуту name.
By searchBox = By.name("search");
driver.findElement(searchBox).sendKeys("Java");

## 4. By.xpath
Поиск элемента с использованием XPath (язык запросов для поиска элементов).
By userNameInput = By.xpath("//input[@id='userName']");
driver.findElement(userNameInput).sendKeys("Марина");
XPath поддерживает различные способы поиска, включая:

Поиск по атрибутам:
By userNameInput = By.xpath("//input[@name='userName']");

Поиск по тексту элемента:
By button = By.xpath("//button[text()='Submit']");

Поиск по частичному совпадению текста:
By button = By.xpath("//button[contains(text(),'Submit')]");

## 5. By.cssSelector
Поиск элемента с использованием CSS-селекторов.
By submitButton = By.cssSelector("button#submit");
driver.findElement(submitButton).click();

## 6. By.linkText
Поиск элемента по полному тексту ссылки.
By signUpLink = By.linkText("Sign Up");
driver.findElement(signUpLink).click();

## 7. By.partialLinkText
Поиск элемента по части текста ссылки.
By signUpLink = By.partialLinkText("Sign");
driver.findElement(signUpLink).click();

## 8. contains()
Метод XPath для поиска по части значения атрибута или текста.
By userNameInput = By.xpath("//input[contains(@id,'user')]");
driver.findElement(userNameInput).sendKeys("Марина");

## Общие примеры:
Поиск элемента по классу:
By loginButton = By.className("login");
Поиск по частичному тексту:
By submitButton = By.xpath("//button[contains(text(),'Submit')]");
Эти локаторы часто используются в автоматизированных тестах для поиска элементов и взаимодействия с ними. Выбирай подходящий локатор в зависимости от структуры HTML страницы и требований теста.







