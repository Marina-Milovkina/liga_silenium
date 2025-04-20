🗂 Структура проекта
Page-объект:
WebTablesPage.java

Константы (локаторы):

ADD_BUTTON — кнопка "Add"

FIRST_NAME_INPUT, LAST_NAME_INPUT, EMAIL_INPUT и т.д. — поля формы

SUBMIT_BUTTON — кнопка "Submit"

TABLE_ROWS — строки таблицы

EDIT_BUTTON, DELETE_BUTTON — кнопки управления

Методы:

openPage() — открыть страницу Web Tables

clickAddButton() — нажать "Add"

fillForm(...) — заполнить форму

submitForm() — отправить форму

getTableData() — получить данные из таблицы

findRowByFirstName(String name) — найти строку по имени

editUser(String name, ...) — редактировать пользователя

deleteUser(String name) — удалить пользователя

Тест:
WebTablesTest.java

Тесты:

testAddNewUser() — проверить добавление пользователя

testEditUser() — проверить редактирование пользователя

testDeleteUser() — проверить удаление пользователя

💡 Примерные шаги для testAddNewUser()
Открыть страницу.

Нажать "Add".

Заполнить поля: имя, фамилию, email и т.д.

Нажать "Submit".

Убедиться, что пользователь появился в таблице.
