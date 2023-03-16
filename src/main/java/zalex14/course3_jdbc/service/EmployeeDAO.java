package zalex14.course3_jdbc.service;

import zalex14.course3_jdbc.model.Employee;
import java.util.List;

/**
 * Обрабатываем персональные данные
 */
public interface EmployeeDAO {
    // Создание(добавление) сущности Employee в таблицу
    void create(Employee employee);

    // Получение конкретного объекта Employee по id
    Employee readById(int id);

    // Получение списка всех объектов Employee из базы
    List<Employee> readAll();

    // Изменение конкретного объекта Employee в базе по id
    void updateById(int id, Employee employee);

    // Удаление конкретного объекта Employee из базы по id
    void deleteById(Employee employee);
}