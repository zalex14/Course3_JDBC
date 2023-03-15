package zakex14.course3_jdbc;

import java.sql.*;

/**
 *
 */
public class Course3JdbcApplication {

    public static void main(String[] args) throws SQLException {
        System.out.println("Course3_JDBC");

// Создаем переменные с данными для подключения к базе
        final String user = "postgres";
        final String password = "cv34zS1963";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

// Создаем соединение с базой с помощью Connection и формируем запрос к базе с помощью PreparedStatement
        final Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = connection.prepareStatement(
                "SELECT first_name, last_name, gender, age, city_name FROM employee " +
                        "INNER JOIN city ON employee.city_id = city.city_id WHERE id = 2;");

// Делаем запрос к базе и результат кладем в ResultSet
        final ResultSet resultSet = statement.executeQuery();
        System.out.println("\nЗадание 1");
// Методом next проверяем есть ли следующий элемент в resultSet и переходим к нему, если таковой есть
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
    }

}