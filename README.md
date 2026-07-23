Added RandomUtils

 Особенности проекта
Генерация случайных данных — каждый запуск тестов использует уникальные данные

Page Object Model — структурированная архитектура для поддержки тестов

Позитивные и негативные тесты — покрытие различных сценариев


 Пример тестовых данных
Данные генерируются автоматически с помощью Faker:

java
firstName = faker.name().firstName();           
lastName = faker.name().lastName();             
email = faker.internet().emailAddress();        
mobileNumber = faker.number().digits(10);       
address = faker.address().fullAddress();       
