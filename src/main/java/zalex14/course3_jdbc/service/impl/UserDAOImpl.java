package zalex14.course3_jdbc.service.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import zalex14.HibernateSessionFactoryUtil;
import zalex14.course3_jdbc.model.Users;
import zalex14.course3_jdbc.service.UserDAO;

import java.util.List;

/**
 * Обработка персональных данных пользователей
 */
@AllArgsConstructor
public class UserDAOImpl implements UserDAO {

    /**
     * Создание(добавление) сущности Employee в таблицу
     */
    @Override
    public void create(Users users) {
// Формируем запрос к базе с помощью PreparedStatement
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.save(users);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Получение конкретного объекта по id
     */
    @Override
    public Users readById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Users.class, id);
        }
    }

    /**
     * Получение списка всех объектов Employee из базы
     */
    @Override
    public List<Users> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("From Users", Users.class).list();
        }
    }

    /**
     * Изменение конкретного объекта Employee в базе по id
     */
    @Override
    public void update(Users users) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.update(users);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Удаление конкретного объекта Employee из базы по id
     */
    @Override
    public void delete(Users users) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            try {
                Transaction transaction = session.beginTransaction();
                session.remove(users);
                transaction.commit();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}