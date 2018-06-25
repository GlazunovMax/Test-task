package by.testTask.testApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 Задание:
Система обработки заявок. 

Необходимо разработать систему обработки заявок. Система должна состоять из двух модулей: клиент, админ. 

Модуль клиент 
Требования:
- регистрировать заявки 
- проверять статус заявки

Модуль админ
Требования:
- получать список всех заявок
- возможность выполнять\отказывать конкретную заявку
- статистика выполненных\отказанных заявок

Пример заявки:
{
“request”: “Помыть слона”
“bid”: 100.0
“due_date”: “2018-09-30”
}

Требования к приложению:
- Java 8
- Spring Boot
- Mysql\MongoDb
- Jooq\Spring Data
 - Tomcat\Jetty

 */
@SpringBootApplication
public class ApplicationStart {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);
	}

}
