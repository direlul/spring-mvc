# Simple Twitter

Учебный проект, с элементами социальной сети, простая копия твиттера.
[Краткое описание проекта](https://github.com/direlul/spring-mvc/blob/main/readme/Description.md)


## Технологии
- JDK 11
    - Spring
      - Boot
      - Security
      - JPA
      - Validation
      - Web
- FlyWay
- JUnit
- Maven
- PostgreSQL
- HTML/CSS
    - Thymeleaf
    - Bootstrap 5

## Установка

Склонировать репозиторий:
```sh
git clone https://github.com/direlul/spring-mvc.git
```
В application-production.properties установить путь upload.path и настроить бд.
В postgres создать бд twitter:
```sh
CREATE DATABASE twitter;
```
Собрать проект:

```sh
mvn clean install
```

Запустить jar архив в target:
```sh
java -jar spring-mvc-1.0-SNAPSHOT.jar
```
## Docker

Склонировать репозиторий:
```sh
git clone https://github.com/direlul/spring-mvc.git
```
Собрать проект:

```sh
mvn clean install
```
Собрать и запустить контейнеры:
```sh
docker-compose up
```
