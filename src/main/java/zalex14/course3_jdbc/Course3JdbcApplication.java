package zalex14.course3_jdbc;

import zalex14.course3_jdbc.model.*;
import zalex14.course3_jdbc.service.EmployeeDAO;
import zalex14.course3_jdbc.service.impl.EmployeeDAOImpl;

import java.sql.*;

/**
 * Задание 1 и 2 JDBC
 */
public class Course3JdbcApplication {

    public static void main(String[] args) throws SQLException {
        System.out.println("Course3_JDBC");

    // Задание 1
        System.out.println("\nЗадание 1");
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT first_name, last_name, gender, age, city_name FROM employee " +
                        "INNER JOIN city ON employee.city_id = city.city_id WHERE id = 2;");

        // Делаем запрос к базе и результат кладем в ResultSet
        final ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            // С помощью методов getInt и getString получаем данные из resultSet и  выводим в консоль
            System.out.println(
                    "Имя: " + resultSet.getString("first_name") +
                            " Фамилия: " + resultSet.getString("last_name") +
                            " Пол: " + resultSet.getString("gender") +
                            " Возраст: " + resultSet.getString("age") +
                            " Город: " + resultSet.getString("city_name")
            );
        }

    // Задание 2
        System.out.println("\nЗадание 2");
        Connection connection2 = getConnection();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection2);

        // Создание(добавление) сущности Employee в таблицу
        City cityNew = new City(9, "Riga");
        Employee personNew = new Employee(10, "Jakov", "Yal", "male", 25, cityNew);

        System.out.println("\nСоздание(добавление) сущности personNew в таблицу");
        System.out.println("Изменено строк: " + employeeDAO.create(personNew));

        // Получение объекта Employee по id
        int id = 3;
        System.out.println("\nПолучение объекта " + id + " по id");
        System.out.println(employeeDAO.readById(id));

        // Получение всех объектов Employee из базы
        System.out.println("\nПолучение всех объектов из базы");
        System.out.println(employeeDAO.readAll());

        // Изменение объекта Employee в базе по id
        System.out.println("\nИзменение объекта " + id + " в базе по id");
        System.out.println("Изменено строк: " + employeeDAO.updateById(id, personNew));

        // Удаление объекта Employee из базы по id
        id = 9;
        System.out.println("\nУдаление объекта " + id + " из базы по id");
        System.out.println("Изменено строк: " + employeeDAO.deleteById(id));
    }


    private static Connection getConnection() {
// Определяем подключение к базе
        final String user = "postgres";
        final String password = "pass";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}