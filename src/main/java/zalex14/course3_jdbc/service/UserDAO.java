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
    User read(long id);

    // Добавление пользователя
    void create(User user);

    // Изменение пользователя
    void update(User user);

    // Удаление пользователя
    void delete(User user);
}