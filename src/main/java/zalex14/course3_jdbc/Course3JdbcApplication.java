package zalex14.course3_jdbc;

import zalex14.course3_jdbc.model.*;
import zalex14.course3_jdbc.service.FacultyDAO;
import zalex14.course3_jdbc.service.UserDAO;
import zalex14.course3_jdbc.service.impl.FacultyDAOImpl;
import zalex14.course3_jdbc.service.impl.UserDAOImpl;

import java.util.Set;

/**
 * Задание Hibernate Final
 */
public class Course3JdbcApplication {

    public static void main(String[] args) {
        System.out.println("Hibernate_Final");

        UserDAO userDAO = new UserDAOImpl();
        FacultyDAO facultyDAO = new FacultyDAOImpl();

        //  Заполняем и заносим значения
        Faculty facultyAdmin = new Faculty("ADMIN");
        Faculty facultyRoot = new Faculty("ROOT");
        User userSenior = new User("Tom Kruzik", "kruzik", "de4ght",
                Set.of(facultyRoot, facultyAdmin));
        facultyDAO.create(facultyAdmin);
        userDAO.create(userSenior);

        System.out.println("\n1. Список пользователей из БД без ролей");
        userDAO.readAllName().forEach(System.out::println);

        System.out.println("\n2. Конкретный пользователь с его ролями из БД");
        System.out.println("\n " + userDAO.read(12L).toString(User.StringFormat.valueOf("ALL")));

        System.out.println("\n3. Список пользователей по конкретной роли");
        System.out.println("\n " + facultyDAO.readAll());

        System.out.println("\n4. Удаление пользователя");
        userDAO.delete(userSenior);

        System.out.println("\n5. Добавление нового пользователя с ролями");
        userDAO.create(userSenior);

        System.out.println("\n6. Редактировать существующего пользователя");
        User user2 = userDAO.read(12);
        user2.setName("Edwin King");
        user2.setLogin("king");
        userDAO.update(user2);
    }
}