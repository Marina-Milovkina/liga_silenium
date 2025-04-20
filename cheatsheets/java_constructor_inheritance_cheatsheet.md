# 🧱 Java: Конструкторы и Наследование

## 📌 Конструктор класса

**Конструктор** — специальный метод, вызываемый при создании объекта класса.

### Признаки:
- Имя конструктора совпадает с именем класса.
- Не имеет возвращаемого типа (даже void).
- Может принимать параметры для инициализации объекта.
- Может быть перегружен (несколько конструкторов с разными параметрами).

### Пример:

```java
public class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }
}
```

```java
Person person = new Person("Alice");
```

Если не создать явный конструктор, компилятор добавит **пустой конструктор по умолчанию**.

---

## 🔁 Наследование

**Наследование** позволяет создавать новый класс (дочерний), который **наследует** поля и методы другого класса (родительского).

### Ключевое слово: `extends`

```java
public class Animal {
    public void sound() {
        System.out.println("Some sound");
    }
}

public class Dog extends Animal {
    // Наследует метод sound()
}
```

```java
Dog dog = new Dog();
dog.sound(); // Some sound
```

---

## 🧩 Наследование и Конструктор

При наследовании, если родительский класс имеет конструктор с параметрами, дочерний класс должен вызывать его через `super(...)`:

```java
public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
}

public class CheckBoxPage extends BasePage {
    public CheckBoxPage(WebDriver driver) {
        super(driver); // Вызов конструктора родителя
    }
}
```

---

## 🔑 Что наследуется:

✅ Публичные и защищённые (`protected`) переменные и методы.  
❌ Приватные (`private`) поля и методы — НЕ наследуются напрямую.