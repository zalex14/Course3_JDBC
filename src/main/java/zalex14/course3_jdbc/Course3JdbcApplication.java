package zalex14.course3_jdbc;

import zalex14.course3_jdbc.model.*;
import zalex14.course3_jdbc.service.EmployeeDAO;
import zalex14.course3_jdbc.service.impl.EmployeeDAOImpl;

import java.util.List;

/**
 * Задание 1 JDBC Hibernate
 */
public class Course3JdbcApplication {

    public static void main(String[] args) {
        System.out.println("Course3_JDBC_Hibernate");

        // Задание 1
        System.out.println("\nЗадание 1");

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        // Получение всех объектов Employee из базы
        System.out.println("\nПолучение всех объектов из базы");
        List<Employee> list = employeeDAO.readAll();
        for (Employee employee : list) {
            System.out.println(employee);
        }

        // Создание(добавление) сущности Employee в таблицу
        System.out.println("\nСоздание(добавление) сущности personNew ");
        int id = 22;
        Employee personNew = new Employee(id, "Jakov", "Yal", "male", 25, 9);
        employeeDAO.create(personNew);

        // Получение объекта Employee по id
        System.out.println("\nПолучение объекта " + id + " " + employeeDAO.readById(id));

        // Изменение объекта personNew в базе
        System.out.println("\nИзменение объекта " + id + " в базе по id");
        Employee personUpdated = new Employee(id, "Jana", "Yal", "female", 25, 4);
        employeeDAO.updateById(id, personUpdated);

        // Удаление объекта Employee из базы
        System.out.println("\nУдаление объекта " + personNew + " из базы");
        employeeDAO.deleteById(personNew);
    }
}