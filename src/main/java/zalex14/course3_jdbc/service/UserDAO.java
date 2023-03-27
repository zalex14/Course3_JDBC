package zalex14.course3_jdbc.service;

import zalex14.course3_jdbc.model.User;
import java.util.List;

/**
 * Обрабатываем данные пользователей
 */
public interface UserDAO {

    // Получение списка всех пользователей
    List<User> readAllName();

    // Получение пользователя по id
    User readByIdName(int id);

    // Получение пользователя с правами по id
    User readByIdFaculty(int id);

    // Добавление пользователя
    void create(User user);

    // Изменение пользователя
    void update(User user);

    // Удаление пользователя
    void remove(User user);
}