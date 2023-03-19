package zalex14.course3_jdbc;

import zalex14.course3_jdbc.model.City;
import zalex14.course3_jdbc.model.Employee;
import zalex14.course3_jdbc.service.CityDAO;
import zalex14.course3_jdbc.service.EmployeeDAO;
import zalex14.course3_jdbc.service.impl.CityDAOImpl;
import zalex14.course3_jdbc.service.impl.EmployeeDAOImpl;

/**
 * Задание 1 JDBC Hibernate работа с несколькими таблицами
 */
public class Course3JdbcApplication {

    public static void main(String[] args) {
        System.out.println("Course3_JDBC_Hibernate работа с несколькими таблицами");

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        CityDAO cityDAO = new CityDAOImpl();
/*
  CRUD для Employee и City
 */
        System.out.println("\nПолучение всех Employee и City. Из одного города может быть несколько сотрудников");
        cityDAO.readAll().forEach(System.out::println);

        System.out.println("\nПолучение всех Employee и City. Один сотрудник только из одного города");
        employeeDAO.readAll().forEach(System.out::println);

        System.out.println("\nПолучение Employee и City по id");
        int id = 2;
        System.out.println("\n " + cityDAO.readById(id));

        System.out.println("\nСоздание(добавление) Employee и City");
        employeeDAO.create(new Employee("Jan", "Mur", "male", 25, new City("Ufa")));

        System.out.println("\nИзменение Employee и City");
        employeeDAO.update(new Employee("Ric", "Rey", "male", 35, new City("Rom")));

        System.out.println("\nУдаление Employee и City");
        employeeDAO.delete(new Employee("Ric", "Rey", "male", 35, new City("Rom")));

    }
}