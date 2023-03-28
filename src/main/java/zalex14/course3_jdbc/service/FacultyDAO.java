package zalex14.course3_jdbc.service;

import zalex14.course3_jdbc.model.Faculty;

import java.util.List;

/**
 * Обрабатываем роли пользователей
 */
public interface FacultyDAO {

    // Получение списка всех пользователей с ролью
    List<Faculty> readAll();

    // Получение роли по id
    Faculty read(long id);

    // Добавление роли
    void create(Faculty faculty);
}