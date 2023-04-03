package zalex14.course3_jdbc;

import zalex14.course3_jdbc.dao.UserDAO;
import zalex14.course3_jdbc.dao.impl.UserDAOImpl;
import zalex14.course3_jdbc.entity.Access;
import zalex14.course3_jdbc.entity.Faculty;
import zalex14.course3_jdbc.entity.User;

import java.util.Set;

/**
 * Задание Hibernate Final
 */
public class Course3JdbcApplication {

    public static void main(String[] args) {
        System.out.println("Hibernate_Final");

        UserDAO userDAO = new UserDAOImpl();

        System.out.println("\n1. Список пользователей без ролей");
        userDAO.readAllUsers().forEach(System.out::println);

        System.out.println("\n2. Конкретный пользователь с его ролями");
        System.out.println(userDAO.userAndFaculty(1L));

        System.out.println("\n3. Список пользователей по ролям");
        userDAO.readAllFaculty().forEach(System.out::println);  // все

        System.out.println("\n Список пользователей по конкретной роли");
        userDAO.readUsersByFaculty(Access.ADMIN).forEach(System.out::println);

        System.out.println("\n4. Удаление пользователя" + userDAO.delete(userDAO.readUserById(3L)));

        System.out.println("\n5. Добавление нового пользователя с ролями");
        Set<Faculty> accessRootUser = Set.of(new Faculty(Access.ADMIN), new Faculty(Access.GUEST));
        User userSenior = new User("Tim Lord", "lord", "ase4gt", accessRootUser);
        System.out.println(" Добавлен пользователь: " + userDAO.userAndFaculty(userDAO.add(userSenior)));

        System.out.println("\n6. Редактировать существующего пользователя");
        User updatedUser = userDAO.readUserById(1L);
        updatedUser.setName("Edwin King");
        updatedUser.setLogin("king");
        System.out.println(" Изменения внесены: " + userDAO.update(updatedUser));
    }
}