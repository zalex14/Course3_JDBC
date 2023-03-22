package zalex14.course3_jdbc.service;

import zalex14.course3_jdbc.model.Grants;

import java.util.List;

/**
 * Обрабатываем права пользователей
 */
@SuppressWarnings("ALL")
public interface GrantDAO {
    // Создание(добавление) City в таблицу
    void create(Grants grants);

    // Получение конкретного City по id
    Grants readById(int id);

    // Получение списка всех City из базы
    List<Grants> readAll();

    // Изменение конкретного объекта City в базе по id
    void update(Grants grants);

    // Удаление конкретного объекта Employee из базы по id
    void delete(Grants grants);
}