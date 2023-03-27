package zalex14.course3_jdbc.service;

import zalex14.course3_jdbc.model.Faculty;
import zalex14.course3_jdbc.model.User;

import java.util.List;

/**
 * Обрабатываем роли пользователей
 */
public interface FacultyDAO {

    // Получение списка всех пользователей с ролью
    List<Faculty> readAll();

    // Получение роли по id
    Faculty readByIdName(int id);

    // Получение роли по id
    Faculty readByIdUser(int id);

    // Добавление роли
    void create(Faculty faculty);
}