package zalex14.course3_jdbc;

import zalex14.course3_jdbc.model.Users;
import zalex14.course3_jdbc.service.GrantDAO;
import zalex14.course3_jdbc.service.UserDAO;
import zalex14.course3_jdbc.service.impl.GrantDAOImpl;
import zalex14.course3_jdbc.service.impl.UserDAOImpl;

/**
 * Задание Hibernate Final
 */
public class Course3JdbcApplication {

    public static void main(String[] args) {
        System.out.println("Hibernate_Final");

        UserDAO userDAO = new UserDAOImpl();
        GrantDAO grantDAO = new GrantDAOImpl();

        System.out.println("\nСписок пользователей из БД без ролей (без логинов и паролей)");
        userDAO.readAll().forEach(System.out::println);

        System.out.println("\nСписок пользователей по конкретной роли");
        grantDAO.readAll().forEach(System.out::println);

        System.out.println("\nКонкретный пользователь с его ролями из БД");
        System.out.println("\n " + userDAO.readById(2).toString(Users.StringFormat.valueOf("F2")));

        System.out.println("\nРедактировать существующего пользователя");
        Users user2 = userDAO.readById(2);
        user2.setName("Edwin King");
        user2.setLogin("king");
        userDAO.update(user2);

        System.out.println("\nУдаление пользователя");
        userDAO.delete(userDAO.readById(21));

        System.out.println("\nДобавление нового пользователя с ролями");
        userDAO.create(new Users(0, "Jan Bronte", "bronte", "z00v5", grantDAO.readById(4)));
    }
}