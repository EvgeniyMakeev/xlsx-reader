# XLSX Reader Application

Сервис для чтения чисел из Excel-файлов (.xlsx) и поиска N-го минимального числа.

## Описание

Сервис загружает Excel-файл, извлекает числа из первого столбца и позволяет получить N-е минимальное число среди них. Также реализована обработка ошибок и автогенерация документации OpenAPI.

## Технологии

- Java 21
- Spring Boot 3.4.4
- Apache POI
- springdoc-openapi (Swagger/OpenAPI)

## Сборка и запуск

### Клонирование репозитория

```bash
git clone https://github.com/EvgeniyMakeev/xlsx-reader.git
cd xlsx-reader
```

### Сборка проекта

```bash
mvn clean package
```

### Запуск приложения

```bash
mvn spring-boot:run
```

Или запуск собранного jar-файла:

```bash
java -jar target/xlsx-reader-1.0.jar
```

## Конфигурация

Конфигурационные параметры задаются в файле:

```
src/main/resources/application.yml
```

## Запуск тестов

```bash
mvn test
```

## Документация API

После запуска приложения документация доступна по адресам:

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Пример Excel-файла

Исходный Excel-файл должен содержать числа в первом столбце:

| A   |
|-----|
| 10  |
| 2   |
| 5   |
| 8   |

## Обработка ошибок

| Сценарий | HTTP статус | Сообщение |
|----------|-------------|-------|
| Пустой файл или некорректные данные | 400 Bad Request | Файл не содержит чисел |
| N меньше 1 или больше количества чисел | 400 Bad Request | N не может быть меньше 1 или больше количества чисел в файле |

## Автор

Евгений Макеев  
evgeniyvmakeev@gmail.com
