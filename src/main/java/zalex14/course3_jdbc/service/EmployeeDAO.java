package zalex14.course3_jdbc.service;

import zalex14.course3_jdbc.model.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Обрабатываем персональные данные
 */
public interface EmployeeDAO {
    // Создание(добавление) сущности Employee в таблицу
    int create(Employee employee) throws SQLException;

    // Получение конкретного объекта Employee по id
    Employee readById(int id) throws SQLException;

    // Получение списка всех объектов Employee из базы
    List<Employee> readAll();

    // Изменение конкретного объекта Employee в базе по id
    int updateById(int id, Employee employee);

    // Удаление конкретного объекта Employee из базы по id
    int deleteById(int id);
}