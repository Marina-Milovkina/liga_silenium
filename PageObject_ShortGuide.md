
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
