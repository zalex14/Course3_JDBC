package zalex14.course3_jdbc.service;

import zalex14.course3_jdbc.model.Users;

import java.util.List;

/**
 * Обрабатываем данные пользователей
 */
public interface UserDAO {
    // Создание(добавление) сущности Employee в таблицу
    void create(Users users);

    // Получение конкретного объекта Employee по id
    Users readById(int id);

    // Получение списка всех объектов Employee из базы
    List<Users> readAll();

    // Изменение конкретного объекта Employee в базе по id
    void update(Users users);

    // Удаление конкретного объекта Employee из базы по id
    void delete(Users users);
}