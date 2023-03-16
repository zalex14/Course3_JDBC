package zalex14.course3_jdbc.service.impl;

import lombok.AllArgsConstructor;
import zalex14.course3_jdbc.model.City;
import zalex14.course3_jdbc.model.Employee;
import zalex14.course3_jdbc.service.EmployeeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Обработка персональных данных сотрудников
 */
@AllArgsConstructor
public class EmployeeDAOImpl implements EmployeeDAO {
    private final Connection connection;

    /**
     * Создание(добавление) сущности Employee в таблицу
     */
    @Override
    public int create(Employee employee) {
// Формируем запрос к базе с помощью PreparedStatement
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO employee (first_name, last_name, gender, age, city_id)  VALUES ((?), (?), (?), (?), (?));")) {
            // Подставляем значение вместо wildcard
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCityId());
            // отправляем запрос изменения executeUpdate к базе
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;   // изменений нет
    }

    /**
     * Получение конкретного объекта Employee по id
     */
    @Override
    public Employee readById(int id) {

        Employee employee = new Employee();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee LEFT JOIN city ON employee.city_id = city.city_id WHERE employee.id=(?)")) {
            statement.setInt(1, id);

            // отправляем запрос чтения executeQuery к базе
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee.setEmployeeId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCity(new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    /**
     * Получение списка всех объектов Employee из базы
     */
    @Override
    public List<Employee> readAll() {

        // Создаем список, в который будем укладывать объекты
        List<Employee> employeeList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee LEFT JOIN city ON employee.city_id = city.city_id")) {

            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int employeeId = Integer.parseInt(resultSet.getString("id"));
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));
                City city = new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name"));

                // Создаем объекты на основе полученных данных и укладываем их в итоговый список
                employeeList.add(new Employee(employeeId, firstName, lastName, gender, age, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    /**
     * Изменение конкретного объекта Employee в базе по id
     */
    @Override
    public int updateById(int id, Employee employee) {

        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE employee SET first_name = (?), last_name = (?), gender = (?), age = (?), city_id = (?) " +
                        " WHERE id = (?)")) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setLong(5, employee.getCity().getCityId());
            statement.setInt(6, id);

            // отправляем запрос изменения executeUpdate к базе
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;   // изменений нет
    }

    /**
     * Удаление конкретного объекта Employee из базы по id
     */
    @Override
    public int deleteById(int id) {

        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM employee WHERE id = (?)")) {
            statement.setInt(1, id);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;   // изменений нет
    }
}