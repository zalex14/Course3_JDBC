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

        //  Заполняем
        Faculty rootFaculty = new Faculty("ROOT");
        Faculty adminFaculty = new Faculty("ADMIN");
        Faculty userFaculty = new Faculty("USER");
        Faculty guestFaculty = new Faculty("GUEST");

        facultyDAO.create(rootFaculty);
        facultyDAO.create(adminFaculty);
        facultyDAO.create(userFaculty);
        facultyDAO.create(guestFaculty);

        User userSenior = new User(null, "Tom Kruze", "kruze", "de4ght",
                Set.of(rootFaculty, adminFaculty));
        User userJunior = new User(null, "Will Smith", "smith", "ly2ght",
                Set.of(guestFaculty, userFaculty));


        System.out.println("\n1. Список пользователей из БД без ролей");
        userDAO.readAllName().forEach(System.out::println);

        System.out.println("\n2. Конкретный пользователь с его ролями из БД");
        System.out.println("\n " + userDAO.readByIdFaculty(803).toString(User.StringFormat.valueOf("ALL")));

        System.out.println("\n3. Список пользователей по конкретной роли");
        facultyDAO.readByIdName(userFaculty.getGrantId()).getUsers().forEach(System.out::println);

        System.out.println("\n4. Удаление пользователя");
        userDAO.remove(userSenior);

        System.out.println("\n5. Добавление нового пользователя с ролями");
        userDAO.create(userSenior);

        System.out.println("\n6. Редактировать существующего пользователя");
        User user2 = userDAO.readByIdName(802);
        user2.setName("Edwin King");
        user2.setLogin("king");
        userDAO.update(user2);
    }
}