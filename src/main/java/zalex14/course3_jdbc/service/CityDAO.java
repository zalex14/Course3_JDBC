package zalex14.course3_jdbc.service;

import zalex14.course3_jdbc.model.City;

import java.util.List;

/**
 * Обрабатываем города сотрудников
 */
public interface CityDAO {
    // Создание(добавление) City в таблицу
    void create(City city);

    // Получение конкретного City по id
    City readById(int id);

    // Получение списка всех City из базы
    List<City> readAll();

    // Изменение конкретного объекта City в базе по id
    void update(City city);

    // Удаление конкретного объекта Employee из базы по id
    void delete(City city);
}