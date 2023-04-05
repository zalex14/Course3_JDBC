package zalex14.course3_jdbc.dao;

import zalex14.course3_jdbc.entity.Access;
import zalex14.course3_jdbc.entity.Faculty;
import zalex14.course3_jdbc.entity.User;

import java.util.List;

/**
 * Обрабатываем данные пользователей
 */
public interface UserDAO {
    // 1. Список пользователей из БД без ролей
    List<User> readAllUsers();

    // 2. Конкретный пользователь по id
    User readUserById(Long id);

    // Конкретный пользователь с его ролями из БД по id
    User userAndFaculty(Long id);

    // 3. Список пользователей по конкретной роли
    List<Faculty> readAllFaculty();

    List<User> readUsersByFaculty(Access access);

    // 4. Удаление пользователя
    void delete(User user);

    // 5. Добавление нового пользователя с ролями
    Long add(User user);

    // 6. Редактировать существующего пользователя
    Boolean update(User user);
}
